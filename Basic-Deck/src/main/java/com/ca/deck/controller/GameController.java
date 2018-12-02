package com.ca.deck.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.deck.dao.CardRepository;
import com.ca.deck.dao.DeckRepository;
import com.ca.deck.dao.GameRepository;
import com.ca.deck.dao.PlayerRepository;
import com.ca.deck.model.Card;
import com.ca.deck.model.Deck;
import com.ca.deck.model.Game;
import com.ca.deck.model.Player;
import com.ca.deck.model.RemainingCards;
import com.ca.deck.model.TotalValuePlayer;
import com.ca.deck.model.UndealtCards;

@RestController
public class GameController {
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private DeckRepository deckRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private CardRepository cardRepository;

	// get list of all games
	@GetMapping("/games")
	List<Game> listGames() {
		return gameRepository.findAll();
	}

	// add a new game
	@PostMapping("/games/addgame")
	Game newGame() {
		Game game = new Game(new ArrayList<Deck>(), new ArrayList<Player>());
		return gameRepository.save(game);
	}
		
	//delete a game
	@PostMapping("/games/{gameid}/delete")
	public ResponseEntity<Object> deleteGame(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			gameRepository.delete(game);
			return new ResponseEntity<>("The game has been deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
	}

	// add a deck to a game
	@PostMapping("/games/adddeck/{gameid}")
	ResponseEntity<Object> newDeck(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			Deck deck = new Deck();
			deck.shuffle();
			deck.setGame(game);
			deckRepository.save(deck);
			cardRepository.saveAll(deck.getDeckOfCards());
			game.getDecks().add(deck);
			gameRepository.save(game);
			return new ResponseEntity<>("The Deck has been added successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("There in no Game with id "+gameid , HttpStatus.NOT_FOUND);
	}

	// add a player to a game
	@PostMapping("/games/addplayer/{gameid}")
	public ResponseEntity<Object> newPlayer(@PathVariable Integer gameid, @RequestBody String name) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			Player player = new Player();
			player.setGame(game);
			player.setName(name);
			playerRepository.save(player);
			game.getPlayers().add(player);
			gameRepository.save(game);
			return new ResponseEntity<>("The player has been added successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("There in no Game with id "+gameid , HttpStatus.NOT_FOUND);
	}

	// remove a player from a game
	@DeleteMapping("/games/{gameid}/removeplayer/{playerid}")
	public ResponseEntity<Object> removePlayer(@PathVariable Integer gameid, @PathVariable Integer playerid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			Optional<Player> optPlayer = playerRepository.findById(playerid);
			if (!optPlayer.isPresent())
				return new ResponseEntity<>("The Player with id "+playerid+" doesn't exist", HttpStatus.NOT_FOUND);
			Player player = optPlayer.get();
			game.getPlayers().remove(player);
			playerRepository.delete(player);
			gameRepository.save(game);
			return new ResponseEntity<>("Player has been deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("There in no Game with id "+gameid , HttpStatus.NOT_FOUND);
	}

	// Deal card to each player
	@PostMapping("/games/{gameid}/deal")
	public ResponseEntity<Object> dealCards(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			List<Player> players = game.getPlayers();
			List<Deck> decks = game.getDecks();

			if (players.isEmpty())
				return new ResponseEntity<>("No Player found, add a player to the game.", HttpStatus.NOT_FOUND);
			if (decks.isEmpty())
				return new ResponseEntity<>("The deck of cards is empty, add a deck to the game.",
						HttpStatus.NOT_FOUND);
			if (decks.size() == 1 && decks.get(0).getDeckOfCards().size() < players.size())
				return new ResponseEntity<>("There isn't enought cards in the deck, add a deck to the game.",
						HttpStatus.NOT_FOUND);

			Deck deck = decks.get(0);
			for (Player player : players) {
				if (deck.getDeckOfCards().isEmpty()) {// if the current deck is empty, move to the next deck
					decks.remove(0);
					deck = decks.get(0);
				}
				Card card = deck.deal();
				card.setPlayer(player);
				card.setDeck(null);
				cardRepository.save(card);
				player.getCards().add(card);
				playerRepository.save(player);
			}
			gameRepository.save(game);
			return new ResponseEntity<>("The card deal has been done successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("There in no Game with id "+gameid , HttpStatus.NOT_FOUND);
	}

	// return count of undealt cards
	@PostMapping("/games/{gameid}/undealt")
	public UndealtCards undealtCards(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			UndealtCards undealtCards = new UndealtCards(game);
			return undealtCards;
		}
		return null;
	}

	// return count of each card remaining in the game
	@PostMapping("/games/{gameid}/remaining")
	public RemainingCards remainingCards(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			RemainingCards remainingCards = new RemainingCards(game);
			return remainingCards;
		}
		return null;
	}

	// Get the list of players in a game along with the total added value of all the
	// cards each player holds
	@PostMapping("/games/{gameid}/players")
	public List<TotalValuePlayer> listPlayers(@PathVariable Integer gameid) {
		Optional<Game> optGame = gameRepository.findById(gameid);
		if (optGame.isPresent()) {
			Game game = optGame.get();
			List<Player> players = game.getPlayers();
			List<TotalValuePlayer> totalValuePlayers = new ArrayList<>();
			for (Player player : players)
				totalValuePlayers.add(new TotalValuePlayer(player));
			Collections.sort(totalValuePlayers, Collections.reverseOrder());
			return totalValuePlayers;
		}
		return null;
	}

}