package com.xor.rest.rest_api_bb.controller;


import com.xor.rest.rest_api_bb.payload.response.ApiResponse;
import com.xor.rest.rest_api_bb.payload.user.UserDTO;
import com.xor.rest.rest_api_bb.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<UserDTO>> createUser(
            @RequestBody UserDTO user
    ) {
       UserDTO newUser =  this.userService.createUser(user);
       ApiResponse<UserDTO> api = new ApiResponse<>("Success",
                HttpStatus.CREATED.value(),
                "user", newUser
       );

       return new ResponseEntity<>(api, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(
            @PathVariable Long id
    ) {
        UserDTO newUser = this.userService.getUserById(id);
        ApiResponse<UserDTO> api = new ApiResponse<>("Success", HttpStatus.OK.value(), "user", newUser);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }
}
