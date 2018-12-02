package com.ca.deck.model;

/**
 * The card ranks. 2-10, Jack, Queen, and King and Ace.
 */
public enum Rank {
	KING(13),
	QUEEN(12),
	JACK(11),
	TEN(10),
	NINE(9),
	EIGHT(8),
	SEVEN(7),
	SIX(6),	
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ACE(1);	

	/**
	 * Representation of the rank using its value.
	 */
	private final int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
