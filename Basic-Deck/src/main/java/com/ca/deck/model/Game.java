package com.ca.deck.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2118034334244116813L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gameID;
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Deck> decks;
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Player> players;
	
	public Game() {
		
	}
	
	public Game(List<Deck> decks, List<Player> players) {
		super();
		this.decks = decks;
		this.players = players;
	}

	public int getGameID() {
		return gameID;
	}
	
	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
