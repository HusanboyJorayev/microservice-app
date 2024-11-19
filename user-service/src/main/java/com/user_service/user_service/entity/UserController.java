package com.user_service.user_service.entity;

import com.user_service.user_service.client.ResponseTemplateClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto cardDto) {
        return this.userService.create(cardDto);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDto> get(@RequestParam("id") Integer id) {
        return this.userService.get(id);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto cardDto,
                                          @RequestParam("id") Integer id) {
        return this.userService.update(cardDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> delete(@RequestParam("id") Integer id) {
        return this.userService.delete(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getUserWithCards")
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallback")
    public ResponseEntity<ResponseTemplateClient> getUserWithCards(@RequestParam("id") Integer id) {
        return this.userService.getUserWithCards(id);
    }

    public ResponseEntity<ResponseTemplateClient> fallback(Integer id, Exception e) {
        UserDto body = this.get(id).getBody();
        return new ResponseEntity<>(new ResponseTemplateClient(body,"CARD-SERVICE IS NOT WORKING"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
