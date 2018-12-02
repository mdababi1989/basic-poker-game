package com.ca.deck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ca.deck.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}