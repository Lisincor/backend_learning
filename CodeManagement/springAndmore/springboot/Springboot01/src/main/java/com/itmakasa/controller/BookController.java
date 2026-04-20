package com.itmakasa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${lesson}")
    private String lessson;

    @GetMapping("/{id}")
    public String test(@PathVariable Integer id){

        System.out.println(lessson);
        return "aicode" + id;
    }

}
