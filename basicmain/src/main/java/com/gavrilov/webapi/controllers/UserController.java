package com.gavrilov.webapi.controllers;

import com.gavrilov.core.dto.UserDTO;
import com.gavrilov.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<LinkedList<UserDTO>> getUserList() {
        LinkedList<UserDTO> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }
}
