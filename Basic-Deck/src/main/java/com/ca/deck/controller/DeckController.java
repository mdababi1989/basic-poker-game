package com.ca.deck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.deck.dao.DeckRepository;
import com.ca.deck.model.Deck;

@RestController
public class DeckController {
	@Autowired
	private DeckRepository repository;

	// get list of all decks
	@GetMapping("/decks")
	public List<Deck> listDecks() {
		return repository.findAll();
	}
}