package com.wordpress.binarycoders.helloworldsb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String sayHello() {
        return "Greetings from Spring Boot!";
    }
}
