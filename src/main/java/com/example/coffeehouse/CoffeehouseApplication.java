package com.example.coffeehouse;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.services.ClientAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeehouseApplication.class, args);
    }

}
