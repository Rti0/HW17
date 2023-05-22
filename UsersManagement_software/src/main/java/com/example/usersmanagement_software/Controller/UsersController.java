package com.example.usersmanagement_software.Controller;

import com.example.usersmanagement_software.ApiResponse.ApiResponse;
import com.example.usersmanagement_software.Model.Users;
import com.example.usersmanagement_software.Service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List <Users> coffeeList = usersService.getAllUsers();
        return ResponseEntity.status(200).body(coffeeList);
    }

    @PostMapping("add")
    public ResponseEntity addAllUsers(@Valid @RequestBody Users users, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        usersService.addUsers(users);
        return ResponseEntity.status(200).body("User Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUsers(@Valid @RequestBody Users users,@PathVariable Integer id,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated= usersService.updateUsers(id, users);
        if(isUpdated){
            return ResponseEntity.status(200).body("Users updated");
        }
        return ResponseEntity.status(200).body("Users not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUsers(@PathVariable Integer id){
        boolean isDeleted= usersService.deleteUsers(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Users deleted");
        }
        return ResponseEntity.status(400).body("id not found");
    }
}

