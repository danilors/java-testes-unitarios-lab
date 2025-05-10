package br.com.api.bdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "br.com.api.bdd")

public class StepApplication {
    public static void main(String[] args) {
        SpringApplication.run(StepApplication.class, args);
    }
}
