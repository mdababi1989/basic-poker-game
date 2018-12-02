package com.ca.deck.model;

/**
 * The card suits.
 */
public enum Suit {	
	
	HEARTS("h"),
	SPADES("s"),
	CLUBS("c"),
	DIAMONDS("d");

	/**
	 * Representation of the suit using one letter symbol.
	 */
	private final String name;

	private Suit(String name) {
		this.name = name;	
	}

	public String getName() {
		return name;
	}

}
