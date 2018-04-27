package ru.security.schat.pass.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {
    @RequestMapping(value = "/")
    public String root() {
        return "Welcome!";
    }

    @RequestMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
