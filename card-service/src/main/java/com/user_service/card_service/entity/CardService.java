package com.user_service.card_service.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService {
    ResponseEntity<CardDto> create(CardDto cardDto);

    ResponseEntity<CardDto> get(Integer id);

    ResponseEntity<CardDto> update(CardDto cardDto, Integer id);

    ResponseEntity<CardDto> delete(Integer id);

    ResponseEntity<List<CardDto>> getAll();

    ResponseEntity<List<CardDto>> getAllCardByUserId(Integer userId);
}
