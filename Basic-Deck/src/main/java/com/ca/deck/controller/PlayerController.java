package com.ca.deck.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ca.deck.dao.PlayerRepository;
import com.ca.deck.model.Card;
import com.ca.deck.model.Player;

@RestController
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepository;

	// Show list of all players
	@GetMapping("/players")
	List<Player> listPlayers() {
		return playerRepository.findAll();
	}

	
	// Show a player
	@GetMapping("/players/{playerid}")
	public Player getPlayer(@PathVariable Integer playerid) {
		Optional<Player> optPlayer = playerRepository.findById(playerid);
		if (optPlayer.isPresent()) {
			return optPlayer.get();
		}
		return null;
	}

	// Show list of cards of a player
	@GetMapping("/players/{playerid}/cards")
	public List<Card> getPlayerCards(@PathVariable Integer playerid) {
		Optional<Player> optPlayer = playerRepository.findById(playerid);
		if (optPlayer.isPresent()) {
			return optPlayer.get().getCards();
		}
		return null;
	}
	
	
	
}