package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.FileUploaded;
import com.orange.hiring_automation.repository.FileUploadedRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "File Upload API", value = "Uploading file to the system", produces = "application/json")
@RestController
public class FileUploadedController {
    private ServletContext servletContext;
    private FileUploadedRepository fileUploadedRepository;
    //    @Value( "${file.upload-dir}" )
    private static String UPLOADED_FOLDER = "C://temp//";

    FileUploadedController(FileUploadedRepository fileUploadedRepository, ServletContext servletContext) {
        this.fileUploadedRepository = fileUploadedRepository;
        this.servletContext = servletContext;
    }

    @ApiOperation(value = "Upload File")
    @PostMapping(value = "/upload")
    FileUploaded upload(@ApiParam(value = "file") @RequestParam MultipartFile file) throws IOException {
        saveFile(UPLOADED_FOLDER, file);
        FileUploaded fileUploaded = new FileUploaded();
        fileUploaded.setName(file.getOriginalFilename());
        fileUploaded.setLocation(UPLOADED_FOLDER);
        return fileUploadedRepository.save(fileUploaded);
    }

    @ApiOperation(value = "Download File")
    @GetMapping(value = "/download/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getFile(@ApiParam(value = "File Id", required = true, example = "3") @PathVariable Long id) throws IOException {
        FileUploaded fileUploaded = fileUploadedRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("FileUploaded", id));
        MediaType mediaType = FileUploadedController.getMediaTypeForFileName(this.servletContext, fileUploaded.getName());
        Path path = Paths.get(fileUploaded.getLocation() + "" + fileUploaded.getName());
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(resource);
    }

    private void saveFile(String uploadedFolder, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    private File downloadFile(String uploadedFolder, String fileName) throws IOException {
        File file = ResourceUtils.getFile("file:" + uploadedFolder + fileName);
        return file;
    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}

