package com.orange.hiring_automation.api;

import com.orange.hiring_automation.model.User;
import com.orange.hiring_automation.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "User Controller", description = "Operations pertaining to Users")
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    @ApiOperation(value = "List All Users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
