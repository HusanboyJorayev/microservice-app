package com.user_service.user_service.client;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer id;
    private Double price;
    private String number;
    private String type;
    private String code;
    private String owvName;
    private Integer userId;
}
