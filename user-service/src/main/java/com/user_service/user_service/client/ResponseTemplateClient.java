package com.user_service.user_service.client;

import com.user_service.user_service.entity.UserDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseTemplateClient {
    private UserDto userDto;
    private List<CardDto> cardDtoList;
    private String message;

    public ResponseTemplateClient(UserDto userDto, String message) {
        this.userDto = userDto;
        this.message = message;
    }

    public ResponseTemplateClient(String message) {
        this.message = message;
    }
}
