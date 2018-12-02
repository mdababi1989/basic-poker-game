package com.ca.deck;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ca.deck.dao.GameRepository;
import com.ca.deck.model.Deck;
import com.ca.deck.model.Game;
import com.ca.deck.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GameRepository gameRepository;

	@Test
	public void findAllTest() throws Exception {
		/*
		 * Test find all games
		 */		
		List<Game> games = new ArrayList<>();
		Game game1= new Game();
		Game game2= new Game();
		Game game3= new Game();
		games.add(game1); games.add(game2); games.add(game3);

		when(gameRepository.findAll()).thenReturn(games);
		this.mockMvc.perform(get("/games")).andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(games.size())));
	
	}
	
	@Test
	public void createGameTest() throws Exception {
		/*
		 * Test find all games
		 */		
		Game game1= new Game();
	
		List<Deck> decks = new ArrayList<>();		
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		decks.add(deck1); decks.add(deck2);

		List<Player> players = new ArrayList<>();
		Player player1 = new Player();
		player1.setName("George");
		Player player2 = new Player();
		player2.setName("Maxwell");
		players.add(player1);	players.add(player2);

		game1.setDecks(decks);
		game1.setPlayers(players);
		
		when(gameRepository.save(game1)).thenReturn(game1);
		this.mockMvc.perform(post("/games/addgame")).andExpect(status().isOk());
		
	}

	
}