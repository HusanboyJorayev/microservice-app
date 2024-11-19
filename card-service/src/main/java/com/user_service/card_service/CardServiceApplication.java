package com.user_service.card_service;

import com.user_service.card_service.entity.Card;
import com.user_service.card_service.entity.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runCard(CardRepository cardRepository) {
        return args -> {
            for (int i = 1; i <= 100; i++) {
                Card card = Card.builder()
                        .code("card" + i)
                        .type("HUMO")
                        .price((double) (i * 9 / 2))
                        .number("123456789876541" + i / 18)
                        .code("123" + i / 24)
                        .owvName("John" + i)
                        .userId(1+i / 5)
                        .build();
                cardRepository.save(card);
            }
        };
    }
}
