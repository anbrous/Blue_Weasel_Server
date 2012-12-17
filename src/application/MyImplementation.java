package application;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import metier.Card;
import metier.Card.Color;
import metier.Card.Value;
import metier.Game;
import metier.GameStatus;
import metier.MonEntity;
import metier.Player;

public class MyImplementation implements RfidInterface, MonInterface{

	public static String idArecuperer = "nocard";
	
	public MyImplementation(EntityManagerFactory entityManagerFactory){
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
				
	public void cardsInitialisation(){
		
		int i = 0;
		for(Value value : Value.values()){
			
			for(Color color : Color.values()){
				
				i++;
				System.out.println("Please scan the "+ value + " of "+ color);
				idArecuperer = "nocard";// implémenter une méthode pour recuperer l'ID RFID
				boolean Waiting = true;
				
				while(Waiting){	
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					idArecuperer = getCurrentCard(123);
					System.out.println(" pas de current card..\n checking...");
					if(!idArecuperer.equals("nocard")){
						Waiting = false;
						createIdCard(123);
					}				
				}
				//Scanner scan = new Scanner(System.in);
				//idArecuperer = scan.next();
				//idArecuperer = ""+i;
				Card card = new Card(idArecuperer, value, color);
				EntityTransaction tx = entityManager.getTransaction();
				tx.begin();
				entityManager.merge(card);
				tx.commit();
			}
		}
		System.out.println("nbrs de cartes: "+i);

	}

	public void setCurrentCard( String cardId) {
		// TODO Auto-generated method stub
		createIdCard(cardId,123);
		//System.out.println("loooool "+a);
	}

	public void createIdCard(long id){
		
		GameStatus gameStatus = new GameStatus();
		gameStatus.setId(id);
		gameStatus.initializeIdArecuperer();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(gameStatus);
		tx.commit();
	}
	
	public void createIdCard(String idCard, long id){
		
		GameStatus gameStatus = new GameStatus();
		gameStatus.setId(id);
		gameStatus.setIdArecuperer(idCard);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(gameStatus);
		tx.commit();
	}

	public String getCurrentCard(long id) {
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		GameStatus gameStatus = entityManager.find(GameStatus.class, id);
		tx.commit();
		return gameStatus.getIdArecuperer();
	}
	
	
}
