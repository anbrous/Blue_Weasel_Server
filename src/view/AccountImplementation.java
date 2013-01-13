package view;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import model.Game;
import model.MonEntity;
import model.Player;

public class AccountImplementation implements AccountInterface {

	public AccountImplementation(EntityManagerFactory entityManagerFactory){
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	EntityManager entityManager;

	@PersistenceContext
	EntityManager em;
	
	public void createEntity() {
		MonEntity entity = new MonEntity();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(entity);
		tx.commit();
	}
		
	public void createPlayer(String nickname, String  email, String password) {
		Player player = new Player();
		player.setEmail(email);
		player.setName(nickname);
		player.setPassword(password);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(player);
		tx.commit();
	}
	public void createGame() {
		Game game = new Game();
		game.setGameName("Boris fight");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(game);
		tx.commit();
	}

	public String connection( String action, String nickname, String email, String password) {
		if( action.equals("signin")) {
			return "signedin";
		}
		else if ( action.equals("signup")) {
			if ( nickname != null && email != null && password != null && nickname != "" && email != "" && password != "") {
				createPlayer( nickname, email, password );
				return "registered";
			}
			else {
				return "Error, missing bad information";
			}
		}
		else {
			return "Unknown error";
		}
	
	}
	
}
