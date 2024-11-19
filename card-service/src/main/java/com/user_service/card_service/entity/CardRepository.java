package com.user_service.card_service.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query(value = "select * from card_mic where user_id=?1", nativeQuery = true)
    List<Card> findByUserId(Integer userId);
}
