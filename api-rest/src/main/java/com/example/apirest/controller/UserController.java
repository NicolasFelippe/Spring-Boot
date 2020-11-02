package com.example.apirest.controller;

import com.example.apirest.model.UserModel;
import com.example.apirest.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "User")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Retorna usuario pelo ID")
    @GetMapping(path = "/api/user/{id}")
    public ResponseEntity getById(@PathVariable Integer id){

    return repository.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Salva o usuario")
    @PostMapping(path = "/api/user/save")
    public UserModel save (@RequestBody UserModel user){
        return repository.save(user);
    }

}
