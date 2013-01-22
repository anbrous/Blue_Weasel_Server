package model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Player {

	private long id;
	private String name;
	public enum Type{Real, Virtual};
	private Type type;
	//private Game game;
	
	private Collection<Card> handcards;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "player")
	public Collection<Card> getHandcards() {
		return handcards;
	}

	public void setHandcards(Collection<Card> handcards) {
		this.handcards = handcards;
	} 
	/*
	@OneToOne(cascade=CascadeType.PERSIST)
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	*/

	
}