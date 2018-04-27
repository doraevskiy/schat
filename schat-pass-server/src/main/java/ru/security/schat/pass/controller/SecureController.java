package ru.security.schat.pass.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.security.schat.pass.dto.Message;

@RestController
public class SecureController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecureController.class);

    @RequestMapping(value = "/")
    public String root() {
        return "Welcome!";
    }

    @RequestMapping(value = "/hello/{name}")
    public Message hello(@PathVariable String name) {
        LOGGER.info("hello method call start: " + name);
        return Message.MessageBuilder.aMessage()
                .withCode(0)
                .withDescription("Hello, " + name + "!")
                .build();
    }
}
