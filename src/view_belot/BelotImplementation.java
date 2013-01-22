package view_belot;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import model.Card;
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
	
	public void playerPlayAcard(Member member, Card card){
		
		
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

		
		game.setGameName("testGame");
		game.setTeam1_score(260);
		game.setTeam2_score(570);
		game.setCurrentMaster(player2.getName());
		game.setCurrentTeamTrump("team1");
	/*	game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.setPlayer3(player3);
		game.setPlayer4(player4); */
		game.setGameStatus("simulation");
		
		System.out.println("Starting persistence");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(game);
		tx.commit();
		
	}
}
