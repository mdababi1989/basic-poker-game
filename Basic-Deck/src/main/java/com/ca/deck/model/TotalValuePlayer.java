package com.ca.deck.model;

import java.io.Serializable;
import java.util.List;

public class TotalValuePlayer implements Serializable, Comparable<TotalValuePlayer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3808089372770749687L;

	private int id; // player id
	private String name;// player name
	private int totalValue;// Total added value of all the cards each player holds
	
	public TotalValuePlayer(Player player) {
		this.id=player.getPlayerID();
		this.name=player.getName();
		List<Card> cards = player.getCards();
		for(Card card:cards) {
			totalValue+=card.getRank().getValue();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public int compareTo(TotalValuePlayer totalValueOtherPlayer) {
		if(this.totalValue>totalValueOtherPlayer.getTotalValue())
			return 1;
		else if(this.totalValue<totalValueOtherPlayer.getTotalValue())
			return -1;		
		return 0;
	}

}
