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

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserById(
            @PathVariable Long id
    ){
        this.userService.deleteUser(id);
        ApiResponse<String> api = new ApiResponse<>("user has been deleted succesfully", HttpStatus.OK.value(),"user", "The user was deleted");
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>>  updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO
    ) {
        UserDTO toReturn = this.userService.updateUser(id, userDTO);
        ApiResponse<UserDTO> api = new ApiResponse<>("Success", HttpStatus.OK.value(), "user", toReturn);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }
}
