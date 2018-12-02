package com.ca.deck.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -622503380283861270L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int playerID;
	private String name;
	
	@OneToMany(mappedBy="player", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Card> cards;
	@JsonIgnore
	@ManyToOne
	private Game game;
	
	
	
	public Player() {
		
	}


	public Player(int playerID, String name) {
		super();
		this.playerID = playerID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	public int getPlayerID() {
		return playerID;
	}	
}
