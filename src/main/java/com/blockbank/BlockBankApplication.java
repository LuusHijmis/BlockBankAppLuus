package com.blockbank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockBankApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlockBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
