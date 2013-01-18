package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import model.Card;
import model.Game;
import model.MonEntity;
import model.Player;

public class AccountImplementation implements AccountInterface {

	public static final Pattern EmailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
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

	public boolean checkEmailExistance(String email){
		
		boolean found = false;
		List<Player> players = em.createQuery("SELECT p FROM Player p ").getResultList();
		for ( Player player : players) {
			if ( player.getEmail() != null){
				if ( player.getEmail().equals(email)){
					found = true;
				}
			}
		}
		
		return found;
	}
	
	
	public boolean checkUserNameExistance(String username){
		
		boolean found = false;
		List<Player> players = em.createQuery("SELECT p FROM Player p ").getResultList();
		for ( Player player : players) {
			if ( player.getName() != null){
				if ( player.getName().equals(username)){
					found = true;
				}
			}
		}
		
		return found;	
	}
	
	public String connection( String action, String username, String email, String password, String password2) {
		if( action.equals("signin")) {
			return "signedin";
		}
		else if ( action.equals("signup")) {
			
			Matcher matcher = EmailPattern.matcher(email);
			Boolean emailCorrect = matcher.matches();

			if(!emailCorrect)
				return "Error, email mistake";
			
			if(checkUserNameExistance(username))
				return "Error, username already used";
			
			if(checkEmailExistance(email))
				return "Error, email already used";
			
			if (password != "" && !password.equals(password2))
				return "Error, password mistake";
			
			if ( username != null && email != null && password != null && username != "" && email != "" && password != "") {
				createPlayer( username, email, password );
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
