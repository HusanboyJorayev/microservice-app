package com.user_service.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@FeignClient(value = "CARD-SERVICE", url = "http://localhost:8081/api/card/")
public interface CardClient {
    @GetMapping("/getAllCardsByUserId")
    ResponseEntity<List<CardDto>> getAllCardsByUserId(@RequestParam("userId") Integer userId);
}
