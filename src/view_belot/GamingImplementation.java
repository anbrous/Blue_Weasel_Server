package view_belot;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import model.Card;
import model.Card.Color;
import model.Game;
import model.Member;
import model.MonEntity;
import model.Player;
import model.Player.Type;
public class GamingImplementation implements GamingInterface {

	public GamingImplementation(EntityManagerFactory entityManagerFactory){
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

	public void startGame(long gameid) {
		
		//setting the game status to processing
		
		//setting the playing status to dealing1
		
		//setting current master to player 1 and currendealed to player1
		
		//starting the dealing1 methode
		
	}
	public void dealing1(long gameid) {
		
		//ask for dealing cards to currentdealed
		
		//ask for dealing cards to nextplayer
		
		//ask for dealing cards to nextplayer
		
		//ask for dealing cards to nextplayer
		
	}

}
