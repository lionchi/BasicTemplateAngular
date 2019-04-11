package com.gavrilov.webapi.controllers;

import com.gavrilov.core.domain.User;
import com.gavrilov.core.dto.UserDTO;
import com.gavrilov.core.service.UserService;
import com.gavrilov.webapi.security.AuthToken;
import com.gavrilov.webapi.security.JwtTokenUtil;
import com.gavrilov.webapi.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/management")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/generate-token")
    public ResponseEntity generateToken(@RequestBody LoginUser loginUser) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getLogin(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.findOne(loginUser.getLogin());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity registerUser(@RequestBody @Valid UserDTO userDTO) {
        userService.validationNewUser(userDTO);
        userService.saveUser(userDTO);
        return ResponseEntity.ok().build();
    }
}
