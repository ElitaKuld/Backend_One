package com.example.backend_one;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldViewController {
    @RequestMapping("/hello")
    public String helloWorld() {
        return "helloworld.html";
    }

    //OBS! Fungerar inte med en slash!!!
    @RequestMapping("/helloes")
    public String holaMundo() {
        return "holamundo.html";
    }

}
