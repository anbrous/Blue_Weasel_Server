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
public class BelotImplementation implements BelotInterface {

	public BelotImplementation(EntityManagerFactory entityManagerFactory){
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
	
	public ArrayList<Game> gameList(String status){
		
		ArrayList<Game> listOfGames = new ArrayList<>();
		
		if(status.equals("all"))
		{
			listOfGames = (ArrayList<Game>) entityManager.createQuery("SELECT g FROM Game g WHERE g.gameStatus=:gameStatus OR g.gameStatus=:gameStatus2").setParameter("gameStatus", "awaiting").setParameter("gameStatus2", "started").getResultList();	
		}
		else if(status.equals("history"))
			listOfGames = (ArrayList<Game>) entityManager.createQuery("SELECT g FROM Game g WHERE g.gameStatus=:gameStatus").setParameter("gameStatus", "finished").getResultList();
		
		return listOfGames;
	}

	public Game gameById(long id ) {
		
		Game game = new Game();
		
		game= entityManager.find(Game.class, id);
		
		return game;
		
	}
	public void simulation(){
		System.out.println("Starting simulation of creating a game");
		Game game = new Game();
		Player player1 = new Player();
		player1.setName("Boris");
		player1.setType(Type.Virtual);
		Player player2 = new Player();
		player2.setName("Timal");
		player2.setType(Type.Virtual);
		Player player3 = new Player();
		player3.setName("Lamine");
		player3.setType(Type.Virtual);
		Player player4 = new Player();
		player4.setName("Lyvia");
		player4.setType(Type.Virtual);
		
		game.setGameName("Bastien Fight");
		game.setTeam1_score(260);
		game.setTeam2_score(570);
		game.setCurrentMaster(player2.getName());
		game.setCurrentTeamTrump("team1");
		game.setGameStatus("started");
		game.setWinningScore(1000);
		game.setPlayer1(player1.getName());
		game.setPlayer2(player2.getName());
		game.setPlayer3(player3.getName());
		game.setPlayer4(player4.getName()); 
		game.simulation();
		/*
		Card card1 = new Card();
		card1.setColor(Color.Diamond);
		card1.setPlayer(player);
		
		Card card2 = new Card();
		card2.setColor(Color.Heart);
		card2.setPlayer(player);
		
		List<Card> cards = new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
		
		player.setHandcards(cards);
		
		card1.setPlayer(player);
		card2.setPlayer(player);
		*/
		System.out.println("Starting persistence");
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(game);
		tx.commit();
	}
}
