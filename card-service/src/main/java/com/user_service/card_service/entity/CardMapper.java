package com.user_service.card_service.entity;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toDto(Card card);

    @Mapping(ignore = true, target = "id")
    Card toEntity(CardDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Card card, CardDto dto);

    List<CardDto> getAll(List<Card> cards);
}
