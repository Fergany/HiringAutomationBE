package com.orange.hiring_automation.api;

import com.orange.hiring_automation.model.FileUploaded;
import com.orange.hiring_automation.repository.FileUploadedRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "File Upload API", value = "Uploading file to the system" , produces = "application/json")
@RestController
public class FileUploadedController {
    private FileUploadedRepository fileUploadedRepository;
    private static String UPLOADED_FOLDER = "C://temp//";

    FileUploadedController(FileUploadedRepository fileUploadedRepository){
        this.fileUploadedRepository = fileUploadedRepository;
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

    private void saveFile(String uploadedFolder, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
        Files.write(path, bytes);
    }
}
