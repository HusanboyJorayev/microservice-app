package com.user_service.card_service.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardDto {
    private Integer id;
    private Double price;
    private String number;
    private String type;
    private String code;
    private String owvName;
    private Integer userId;
}
