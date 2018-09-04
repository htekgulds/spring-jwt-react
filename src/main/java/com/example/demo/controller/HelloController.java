package com.example.demo.controller;

import com.example.demo.payload.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Hello hello() {
        return new Hello("Hasan");
    }
}
