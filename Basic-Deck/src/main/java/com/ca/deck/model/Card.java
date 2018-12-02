package com.ca.deck.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3266647240414721694L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int cardID;

	private Suit suit;
	private Rank rank;
	@JsonIgnore
	@ManyToOne
	private Deck deck;
	@JsonIgnore
	@ManyToOne
	private Player player;

	public Card() {
		
	}
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Card(Suit suit, Rank rank, Deck deck) {
		this(suit,rank);
		this.deck = deck;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String toString() {
		return suit.getName() + rank.getValue();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
}