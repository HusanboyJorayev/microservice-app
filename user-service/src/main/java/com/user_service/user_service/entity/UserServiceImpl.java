package com.user_service.user_service.entity;

import com.user_service.user_service.client.CardClient;
import com.user_service.user_service.client.CardDto;
import com.user_service.user_service.client.ResponseTemplateClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CardClient cardClient;


    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<UserDto> create(UserDto userDto) {
        User entity = this.userMapper.toEntity(userDto);
        this.userRepository.save(entity);
        return ResponseEntity.ok(this.userMapper.toDto(entity));
    }

    @Override
    public ResponseEntity<UserDto> get(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
        return ResponseEntity.status(HttpStatus.OK).body(this.userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<UserDto> update(UserDto userDto, Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
        this.userMapper.update(user, userDto);
        User updateuser = this.userRepository.save(user);
        return ResponseEntity.ok(this.userMapper.toDto(updateuser));
    }

    @Override
    public ResponseEntity<UserDto> delete(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
        this.userRepository.delete(user);
        return ResponseEntity.ok(this.userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> all = this.userRepository.findAll();
        if (!all.isEmpty()) {
            List<UserDto> list = this.userMapper.getAll(all);
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(null);
    }


    // TODO WORK WITH REST_TEMPLATE
    /*@Override
    public ResponseEntity<ResponseTemplateClient> getUserWithCards(Integer id) {
        User user = this.userRepository.findById(id)
                .orElse(new User());
        ResponseTemplateClient client = new ResponseTemplateClient();
        client.setUserDto(this.userMapper.toDto(user));

        String url = "http://CARD-SERVICE/api/card/getAllCardsByUserId?userId=";
        List<CardDto> body = restTemplate.exchange(url + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {
                }).getBody();
        client.setCardDtoList(body);
        return ResponseEntity.ok(client);
    }*/

    // TODO WORK WITH FEIGN_CLIENT
    public ResponseEntity<ResponseTemplateClient> getUserWithCards(Integer id) {
        User user = this.userRepository.findById(id)
                .orElse(new User());
        ResponseTemplateClient client = new ResponseTemplateClient();
        client.setUserDto(this.userMapper.toDto(user));

        List<CardDto> body = cardClient.getAllCardsByUserId(id).getBody();

        client.setCardDtoList(body);
        client.setMessage("OK");
        return ResponseEntity.ok(client);
    }
}
