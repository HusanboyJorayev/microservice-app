package com.user_service.user_service;

import com.user_service.user_service.entity.User;
import com.user_service.user_service.entity.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runUSer(UserRepository userRepository) {
        return args -> {
            for (int i = 1; i <= 100; i++) {
                User user = User.builder()
                        .age(i + 2)
                        .firstname("firstname_" + i)
                        .lastname("lastname_" + i)
                        .build();
                userRepository.save(user);
            }
        };
    }
}
