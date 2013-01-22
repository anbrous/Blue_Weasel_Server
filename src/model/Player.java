package model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Player {

	private long id;
	private String name;
	public enum Type{Real, Virtual};
	private Type type;
	//private Collection<Card> handcards;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	//@OneToOne
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
	/*
	@ManyToMany
	public Collection<Card> getHandcards() {
		return handcards;
	}

	public void setHandcards(Collection<Card> handcards) {
		this.handcards = handcards;
	} */
}