package com.user_service.card_service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public ResponseEntity<CardDto> create(CardDto cardDto) {
        Card entity = this.cardMapper.toEntity(cardDto);
        this.cardRepository.save(entity);
        return ResponseEntity.ok(this.cardMapper.toDto(entity));
    }

    @Override
    public ResponseEntity<CardDto> get(Integer id) {
        Card card = this.cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card is not found"));
        return ResponseEntity.status(HttpStatus.OK).body(this.cardMapper.toDto(card));
    }

    @Override
    public ResponseEntity<CardDto> update(CardDto cardDto, Integer id) {
        Card card = this.cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card is not found"));
        this.cardMapper.update(card, cardDto);
        Card updatecard = this.cardRepository.save(card);
        return ResponseEntity.ok(this.cardMapper.toDto(updatecard));
    }

    @Override
    public ResponseEntity<CardDto> delete(Integer id) {
        Card card = this.cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card is not found"));
        this.cardRepository.delete(card);
        return ResponseEntity.ok(this.cardMapper.toDto(card));
    }

    @Override
    public ResponseEntity<List<CardDto>> getAll() {
        List<Card> all = this.cardRepository.findAll();
        if (!all.isEmpty()) {
            List<CardDto> list = this.cardMapper.getAll(all);
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<CardDto>> getAllCardByUserId(Integer userId) {
        List<Card> cardList = this.cardRepository.findByUserId(userId);
        List<CardDto> list = this.cardMapper.getAll(cardList);
        return ResponseEntity.ok(list);
    }
}
