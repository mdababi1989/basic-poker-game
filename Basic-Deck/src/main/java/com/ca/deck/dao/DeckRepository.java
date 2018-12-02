package com.ca.deck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ca.deck.model.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer> {

}