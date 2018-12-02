package com.ca.deck.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A deck represent Fifty-two playing cards in four suits: hearts, spades,
 * clubs, and diamonds.
 */
@Entity
public class Deck implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1413739816154579799L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deckID;
	
	private final int NOFCARDS = 51; // number of cards 52. used to generate random numbers between 0 and 51
	@OneToMany(mappedBy="deck", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Card> deckOfCards; // will Contains all 52 cards
	private boolean shuffled = false; // true if shuffled deck and false if not
	@JsonIgnore
	@ManyToOne
	private Game game;
	
	/**
	 * Initialize a deck using a pre existing list of cards
	 */
	public Deck() {
		deckOfCards = new LinkedList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deckOfCards.add(new Card(suit, rank, this));
			}
		}
	}

	/**
	 * shuffle the deck n times
	 */
	public void shuffle() {
		int randomNumber1, randomNumber2; // two random numbers between 0 and 51
											// used to pick 2 random cards in the deck
											// and swap them
		for (int i = 0; i < 1000; i++) {
			randomNumber1 = (int) (NOFCARDS * Math.random()) ;
			randomNumber2 = (int) (NOFCARDS * Math.random()) ;
			Card tmp = deckOfCards.get(randomNumber1);
			deckOfCards.set(randomNumber1, deckOfCards.get(randomNumber2));
			deckOfCards.set(randomNumber2, tmp);
		}
		this.shuffled = true;
	}

	/**
	 * Returns the next card from the deck.
	 */
	public Card deal() {
		if (deckOfCards.size() > 0)
			return deckOfCards.remove(0);
		else
			return (null);
	}

	public List<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public boolean isShuffled() {
		return shuffled;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getDeckID() {
		return deckID;
	}
	
}
