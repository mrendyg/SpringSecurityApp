package com.andyg.SpringSecurityApp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hello World - GET";
    }

    @GetMapping("/post")
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE') or hasAuthority('UPDATE') or hasAuthority('DELETE')")
    public String helloPost(){
        return "Hello World - POST";
    }

    @PutMapping("/put")

    public String helloPut(){
        return "Hello World - PUT";
    }

    public String helloDelete(){
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    public String helloPatch(){
        return "Hello World - PATCH";
    }

}
