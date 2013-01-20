package view_admin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import model.Card;
import model.Game;
import model.GameStatus;
import model.MonEntity;
import model.Member;
import model.Card.Color;
import model.Card.Value;

public class AdminImplementation implements AdminRfidInterface, AdminInterface, AdminInitialisationManuelleInterface{

	public static String idArecuperer = "nocard";
	
	public static boolean pooling = false; 
	
	public AdminImplementation(EntityManagerFactory entityManagerFactory){
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
		
	public void createMember() {
		Member member = new Member();
		member.setEmail("beau_le_bobo");
		member.setName("Bobo");
		member.setPassword("esigetel2013");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(member);
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
				
	public void cardsInitialisation(String player){
		pooling = true;
		int i = 0;
		ArrayList<String> cards = new ArrayList<>();
		
		
		for(Value value : Value.values()){
			
			for(Color color : Color.values()){
				
				i++;
				System.out.println("Please scan the "+ value + " of "+ color);
				idArecuperer = "nocard";// implémenter une méthode pour recuperer l'ID RFID
				boolean Waiting = true;
							
				if(!pooling)
				{
					System.out.println("On quitte la méthode cardsInitialisation");
					return;
				}
					
				
				while(Waiting && pooling){	
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					idArecuperer = getCurrentCard(123);
					//System.out.println(" pas de current card..\n checking...");
					if(!idArecuperer.equals("nocard")){
						if(!cards.contains(idArecuperer)){
							cards.add(idArecuperer);
							Waiting = false;
							createIdCard(123);
						}
					}				
				}
				//Scanner scan = new Scanner(System.in);
				//idArecuperer = scan.next();
				//idArecuperer = ""+i;
				Card card = new Card(idArecuperer, player, value, color);
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
	

	public void MakeTable(){
		
		for(Value value : Value.values()){ // pour parcourir les enumerations
			
			for(Color color : Color.values()){
				
				Card card = new Card();
				card.setIdRFID(null);
				card.setPlayer("auto");
				card.setColor(color);
				card.setValue(value);
				
				EntityTransaction tx = entityManager.getTransaction();
				tx.begin();
				entityManager.merge(card);
				tx.commit();
				
			}
		}
				
	}
	
	public void ManualCardConfiguration(Value value, Color color, String idRFID){ 
		
		Card card = new Card();
		card.setIdRFID(idRFID);
		card.setPlayer("auto");
		card.setColor(color);
		card.setValue(value);
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(card);
		tx.commit();
		
	}

	public String[][] showCards(String player) {

		String[][] response = new String [14][4];
		int c,v;
		for (v = 0; v <= 13 ; v++){
			for (c = 0; c <= 3 ; c++){
				response[v][c] = "null";
			}
			
		}
		List<Card> cardslist2 = em.createQuery("SELECT c FROM Card c").getResultList();
		for ( Card card : cardslist2) {
			if ( card.getPlayer() != null){
				if ( card.getPlayer().equals(player)){
					response[card.ValueToInt()][card.ColorToInt()] = card.getIdRFID();
				}
			}
		}
		return response;
	}

}
