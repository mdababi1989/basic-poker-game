package com.ca.deck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ca.deck.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}