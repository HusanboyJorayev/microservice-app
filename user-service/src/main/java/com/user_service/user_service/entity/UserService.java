package com.user_service.user_service.entity;

import com.user_service.user_service.client.ResponseTemplateClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    ResponseEntity<UserDto> create(UserDto userDto);

    ResponseEntity<UserDto> get(Integer id);

    ResponseEntity<UserDto> update(UserDto userDto, Integer id);

    ResponseEntity<UserDto> delete(Integer id);

    ResponseEntity<List<UserDto>> getAll();

    ResponseEntity<ResponseTemplateClient> getUserWithCards(Integer id);

}
