package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demo")

public class ApplicationEntry {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationEntry.class, args);
    }
}
