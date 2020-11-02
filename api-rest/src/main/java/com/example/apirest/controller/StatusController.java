package com.example.apirest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Status")
public class StatusController {

    @ApiOperation(value = "Mostra status da API")
    @GetMapping(path = "/api/status")
    public String check () {
        return "Online";
    }

}
