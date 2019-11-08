package com.tz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoStarter {

    public static void main(String[] args) {
        SpringApplication.run(DemoStarter.class, args);
    }

}
