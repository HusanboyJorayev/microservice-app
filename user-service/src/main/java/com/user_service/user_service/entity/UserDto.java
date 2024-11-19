package com.user_service.user_service.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
}
