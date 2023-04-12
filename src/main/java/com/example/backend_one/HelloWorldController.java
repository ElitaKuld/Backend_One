package com.example.backend_one;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping("/es")
    public String holaMundo() {
        return "Hola Mundo!";
    }
}