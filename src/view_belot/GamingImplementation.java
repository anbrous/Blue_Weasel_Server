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
	
	public void saveGame(Game game) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(game);
		tx.commit();
	}

	public void startGame(long gameid) {
		
		//setting the game status to processing
			Game game = new Game();
			game = entityManager.find(Game.class, gameid);
			game.setGameStatus("started");
			game.setPlayingStatus("beginning");
			System.out.println("Game started!!!!!!!!!!!!!!");
		//setting current master to player 1 and currendealed to player1
			game.setCurrentMaster(game.getPlayer1());
			game.setCurrentCardReceiver(game.getPlayer1());
		//starting the dealing1 methode
			dealing1(gameid);
	}
	public void dealing1(long gameid) {
		 
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game();
		game = entityManager.find(Game.class, gameid);
		//setting the playing status to dealing1
		if(!game.getPlayingStatus().contains("dealing")) {
			// if we deal the first time turn
			game.setPlayingStatus("dealing1");
			game.setCurrentCardReceiver(game.nextRoundPlayer(game.getCurrentCardReceiver()));
			System.out.println("First dealing process");
			game.setGame_info("Please, scan 1st card for "+ game.getCurrentCardReceiver());
			
			this.saveGame(game);
			
			return;
		}
		else if (game.getPlayingStatus().equals("dealing1")) {
			if(check3fistcards(game.playerx_getHand(game.getCurrentCardReceiver()))){
				System.out.println("ok");
			}
		}
		//ask for dealing cards to currentdealed
		//ask for dealing cards to nextplayer
		
		//ask for dealing cards to nextplayer
		
		//ask for dealing cards to nextplayer
		
	}
	 public static boolean check3fistcards(String [] hand) {
		 return true;
	 }

}