package com.example.apirest.controller;

import com.example.apirest.model.UserModel;
import com.example.apirest.repository.UserRepository;
import com.example.apirest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticateController(UserRepository repository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/authenticate")
    public String generateToken(@RequestBody UserModel authUser) throws Exception {

        try {
            UserModel user = repository.findByUserLoginAndPassword(authUser.getLogin(),authUser.getPassword());
            if(user != null){


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
            );

                return jwtUtil.generateToken(user.getLogin());
            }
           return "Usuário ou senha incorretos";
        } catch (Exception ex) {
            return ex.getMessage() ;
//            throw new Exception("Invalid username/password");
        }


    }
}
