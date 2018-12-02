package com.ca.deck.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RemainingCards implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8692353369618353175L;

	List<String> remainingHearts = new ArrayList<>();
	List<String> remainingSpades = new ArrayList<>();
	List<String> remainingClubs = new ArrayList<>();
	List<String> remainingDiamonds = new ArrayList<>();
	
	@JsonIgnore int[] countHearts = new int[14];
	@JsonIgnore int[] countSpades = new int[14];
	@JsonIgnore int[] countClubs = new int[14];
	@JsonIgnore int[] countDiamonds = new int[14];

	public RemainingCards(Game game) {
		countCards(game);
		int index = 13;
		for(Rank rank : Rank.values()) {
			remainingHearts.add(countHearts[index]+" "+rank);
			remainingSpades.add(countSpades[index]+" "+rank);
			remainingClubs.add(countClubs[index]+" "+rank);
			remainingDiamonds.add(countDiamonds[index]+" "+rank);	
			index--;
		}		
	}

	public void countCards(Game game) {
		List<Deck> decks = game.getDecks();
		for (Deck deck : decks) {
			for (Card card : deck.getDeckOfCards()) {
				if (card.getSuit() == Suit.HEARTS)
					countHearts[card.getRank().getValue()]++;
				if (card.getSuit() == Suit.SPADES)
					countSpades[card.getRank().getValue()]++;
				if (card.getSuit() == Suit.CLUBS)
					countClubs[card.getRank().getValue()]++;
				if (card.getSuit() == Suit.DIAMONDS)
					countDiamonds[card.getRank().getValue()]++;
			}
		}
	}

	public List<String> getRemainingHearts() {
		return remainingHearts;
	}

	public void setRemainingHearts(List<String> remainingHearts) {
		this.remainingHearts = remainingHearts;
	}

	public List<String> getRemainingSpades() {
		return remainingSpades;
	}

	public void setRemainingSpades(List<String> remainingSpades) {
		this.remainingSpades = remainingSpades;
	}

	public List<String> getRemainingClubs() {
		return remainingClubs;
	}

	public void setRemainingClubs(List<String> remainingClubs) {
		this.remainingClubs = remainingClubs;
	}

	public List<String> getRemainingDiamonds() {
		return remainingDiamonds;
	}

	public void setRemainingDiamonds(List<String> remainingDiamonds) {
		this.remainingDiamonds = remainingDiamonds;
	}
	
}
