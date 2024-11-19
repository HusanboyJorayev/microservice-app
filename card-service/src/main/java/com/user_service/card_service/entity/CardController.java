package com.user_service.card_service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card/")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<CardDto> create(@RequestBody CardDto cardDto) {
        return this.cardService.create(cardDto);
    }

    @GetMapping("/get")
    public ResponseEntity<CardDto> get(@RequestParam("id") Integer id) {
        return this.cardService.get(id);
    }

    @PutMapping("/update")
    public ResponseEntity<CardDto> update(@RequestBody CardDto cardDto,
                                          @RequestParam("id") Integer id) {
        return this.cardService.update(cardDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CardDto> delete(@RequestParam("id") Integer id) {
        return this.cardService.delete(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CardDto>> getAll() {
        return this.cardService.getAll();
    }

    @GetMapping("/getAllCardsByUserId")
    public ResponseEntity<List<CardDto>> getAllCardsByUserId(@RequestParam("userId") Integer userId) {
        return this.cardService.getAllCardByUserId(userId);
    }
}
