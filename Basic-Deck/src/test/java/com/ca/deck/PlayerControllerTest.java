package com.ca.deck;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ca.deck.dao.PlayerRepository;
import com.ca.deck.model.Card;
import com.ca.deck.model.Player;
import com.ca.deck.model.Rank;
import com.ca.deck.model.Suit;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerRepository playerRepository;

	@Test
	public void findAllTest() throws Exception {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Suit.CLUBS, Rank.ACE));
		cards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		List<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Suit.CLUBS, Rank.EIGHT));
		cards2.add(new Card(Suit.DIAMONDS, Rank.FOUR));

		List<Player> players = new ArrayList<>();
		Player player1 = new Player();
		player1.setName("George");
		player1.setCards(cards);
		Player player2 = new Player();
		player2.setName("Maxwell");
		player2.setCards(cards2);
		players.add(player1);
		players.add(player2);

		when(playerRepository.findAll()).thenReturn(players);
		this.mockMvc.perform(get("/players")).andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(players.size())));
	}

	@Test
	public void getPlayerTest() throws Exception {
		Player player = new Player();
		player.setName("George");
		Integer id = player.getPlayerID();
		Optional<Player> optPlayer = Optional.of(player);

		when(playerRepository.findById(id)).thenReturn(optPlayer);

		this.mockMvc.perform(get("/players/" + id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("George")));
	}

}