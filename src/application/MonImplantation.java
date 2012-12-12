package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import metier.Card;
import metier.Game;
import metier.MonEntity;
import metier.Player;

public class MonImplantation implements MonInterface{

	public MonImplantation(EntityManagerFactory entityManagerFactory){
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	EntityManager entityManager;

	public void createEntity() {
		MonEntity entity = new MonEntity();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(entity);
		tx.commit();
	}
		
	public void createPlayer() {
		Player player = new Player();
		player.setEmail("beau_le_bobo");
		player.setName("Bobo");
		player.setPassword("esigetel2013");
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
				
	public void createCard() {
		Card card = new Card();
		card.setValue("Dame de coeur");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(card);
		tx.commit();
	}
	
	
	
}
