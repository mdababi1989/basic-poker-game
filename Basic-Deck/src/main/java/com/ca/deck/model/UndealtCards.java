package com.ca.deck.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UndealtCards implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7172684315442520457L;
	@JsonProperty("Heart cards undealt")
	private int undealtHearts;
	@JsonProperty("Spade cards undealt")
	private int undealtSpades;
	@JsonProperty("Clubs cards undealt")
	private int undealtClubs;
	@JsonProperty("Diamonds cards undealt")
	private int undealtDiamonds;

	public UndealtCards(Game game) {
		List<Deck> decks = game.getDecks();
		for (Deck deck : decks) {
			for (Card card : deck.getDeckOfCards()) {
				if (card.getSuit() == Suit.HEARTS)
					undealtHearts++;
				if (card.getSuit() == Suit.SPADES)
					undealtSpades++;
				if (card.getSuit() == Suit.CLUBS)
					undealtClubs++;
				if (card.getSuit() == Suit.DIAMONDS)
					undealtDiamonds++;

			}
		}

	}

	public int getUndealtHearts() {
		return undealtHearts;
	}

	public void setUndealtHearts(int undealtHearts) {
		this.undealtHearts = undealtHearts;
	}

	public int getUndealtSpades() {
		return undealtSpades;
	}

	public void setUndealtSpades(int undealtSpades) {
		this.undealtSpades = undealtSpades;
	}

	public int getUndealtClubs() {
		return undealtClubs;
	}

	public void setUndealtClubs(int undealtClubs) {
		this.undealtClubs = undealtClubs;
	}

	public int getUndealtDiamonds() {
		return undealtDiamonds;
	}

	public void setUndealtDiamonds(int undealtDiamonds) {
		this.undealtDiamonds = undealtDiamonds;
	}
}
