package com.payworks.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.payworks.superhero")
public class SuperheroApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperheroApplication.class, args);
    }
}
