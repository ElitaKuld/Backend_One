package com.example.backend_one;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //för att Spring skulle se denna klass som en klass som svarar på urlar
public class HelloWorldParameterController {

    @RequestMapping("/helloparameter")
    public String helloWorld(@RequestParam String fName) {

        return "Hello " + fName;
    }

    @RequestMapping("/helloparameterMany")
    public String helloWorld2(@RequestParam String fName,
                              @RequestParam String lName) {

        return "Hello " + fName + " " + lName + "!";
    }

    @RequestMapping("/helloparameterDefault")
    public String helloWorld3(@RequestParam(defaultValue = "Eli") String fName,
                              @RequestParam(defaultValue = "Kuld") String lName) {

        return "Hello " + fName + " " + lName + "!";
    }

    @RequestMapping("/helloparameterOptional")
    public String helloWorldOptional(@RequestParam(required = false) String fName,
                                     @RequestParam(required = false) String lName) {

        if (fName == null) {
            fName = "";
        }
        if (lName == null) {
            lName = "";
        }
        return "Hello " + fName + " " + lName + "!";
    }

    //http://localhost:8080/helloparameterList?list=1,2,3,4,5
    //http://localhost:8080/helloparameterList?list=1&list=2&list=Hej
    @RequestMapping("/helloparameterList")
    public String helloWorldList(@RequestParam List<String> list) {

        return "Hello " + list;
    }

    //http://localhost:8080/helloparameterPath/3
    @RequestMapping("/helloparameterPath/{id}")
    public String helloWorldPath(@PathVariable String id) {

        return "Hello " + id;
    }

    //http://localhost:8080/helloparameterPath/asdfghjkl/hej/
    @RequestMapping("/helloparameterPath/{id}/hej/")
    public String helloWorldPath2(@PathVariable String id) {

        return "Hello " + id;
    }

    @RequestMapping("/helloHTML")
    public String helloHTML(@RequestParam String fName) {

        return "<HTML><HEAD><TITLE>Hello</TITLE></HEAD><BODY><h1>Hello " + fName + "</h1></BODY></HTML>";
    }

}
