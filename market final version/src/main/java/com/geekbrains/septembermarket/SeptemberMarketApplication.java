package com.geekbrains.septembermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.geekbrains.septembermarket"})
public class SeptemberMarketApplication {
    // Идеи:
    // Капча
    // Развертывание приложения
    // Починка куков
    // REST + Security
    // Principal -> UserDetails
    // OR in filters

    public static void main(String[] args) {
        SpringApplication.run(SeptemberMarketApplication.class, args);
    }
}
