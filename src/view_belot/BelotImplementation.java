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

	
	public void createEntity() {
		MonEntity entity = new MonEntity();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(entity);
		tx.commit();
	}
	public void saveGame(Game game) {
		
		EntityTransaction tx = this.entityManager.getTransaction();
		tx.begin();
		this.entityManager.persist(game);
		//em.flush();
		tx.commit();
	}
	public ArrayList<Game> gameList(String status, String login){
		
		ArrayList<Game> listOfGames = new ArrayList<>();
		System.out.println(status);
		if(status.equals("all"))
		{
			listOfGames = (ArrayList<Game>) entityManager.createQuery("SELECT g FROM Game g WHERE g.gameStatus=:gameStatus OR g.gameStatus=:gameStatus2").setParameter("gameStatus", "awaiting").setParameter("gameStatus2", "started").getResultList();	
		}
		else if(status.equals("history"))
		{
			listOfGames = (ArrayList<Game>) entityManager.createQuery("SELECT g FROM Game g WHERE g.gameStatus=:gameStatus AND (g.player1=:player1 OR g.player2=:player2 OR g.player3=:player3 OR g.player4=:player4)").setParameter("gameStatus", "finished").setParameter("player1", login).setParameter("player2", login).setParameter("player3", login).setParameter("player4", login).getResultList();
		}
		else if(status.equals("awaiting"))
		{
			listOfGames = (ArrayList<Game>) entityManager.createQuery("SELECT g FROM Game g WHERE g.gameStatus=:gameStatus").setParameter("gameStatus", "awaiting").getResultList();
		}
		else
			System.out.println("error getting list");
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

	public boolean gamenamecheck(String name) {
		if (entityManager.createQuery("SELECT g FROM Game g WHERE g.gameName=:gamename").setParameter("gamename", name).getResultList().size() >= 1)
			return true;
		else		
			return false;
	}
	public long createGame(String gamename, int winningscore, String player1,
			String position1) {
		if(!gamenamecheck(gamename)){
			Game game = new Game();
			game.setGameName(gamename);
			game.setGameStatus("awaiting");
			game.setWinningScore(winningscore);
			game.setGame_info("Please wait for other players");
			
			switch(position1){
			
			case "1" : game.setPlayer1(player1); break;
			case "2" :game.setPlayer2(player1); break;
			case "3" :game.setPlayer3(player1); break;
			case "4" :game.setPlayer4(player1); break;
			}
			
			game.setTeam1_score(0);
			game.setTeam2_score(0);
			

			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(game);
			tx.commit();
			System.out.println(game.getId());
			return game.getId();
			
		}	
		else {
			System.out.println("Name deja existant");
			return -1;
		}
	}


	public long createGame(String gamename, int winningscore, String player1,
			String position1, String player2, String position2) {
		if(!gamenamecheck(gamename)){
			Game game = new Game();
			game.setGameName(gamename);
			game.setGameStatus("awaiting");
			game.setWinningScore(winningscore);
			game.setGame_info("Please wait for other players");
			
			switch(position1){
			
			case "1" : game.setPlayer1(player1); break;
			case "2" :game.setPlayer2(player1); break;
			case "3" :game.setPlayer3(player1); break;
			case "4" :game.setPlayer4(player1); break;
			}
			switch(position2){
			
			case "1" : game.setPlayer1(player2); break;
			case "2" :game.setPlayer2(player2); break;
			case "3" :game.setPlayer3(player2); break;
			case "4" :game.setPlayer4(player2); break;
			}
			
			game.setTeam1_score(0);
			game.setTeam2_score(0);
			

			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(game);
			tx.commit();
			System.out.println(game.getId());
			return game.getId();
			
		}	
		else {
			System.out.println("Name deja existant");
			return -1;
		}
	}

	public long createGame(String gamename, int winningscore, String player1,
			String position1, String player2, String position2, String player3,
			String position3) {
		if(!gamenamecheck(gamename)){
			Game game = new Game();
			game.setGameName(gamename);
			game.setGameStatus("awaiting");
			game.setWinningScore(winningscore);
			game.setGame_info("Please wait for other players");
			
			switch(position1){
			
			case "1" : game.setPlayer1(player1); break;
			case "2" :game.setPlayer2(player1); break;
			case "3" :game.setPlayer3(player1); break;
			case "4" :game.setPlayer4(player1); break;
			}
			switch(position2){
			
			case "1" : game.setPlayer1(player2); break;
			case "2" :game.setPlayer2(player2); break;
			case "3" :game.setPlayer3(player2); break;
			case "4" :game.setPlayer4(player2); break;
			}
			switch(position3){
			
			case "1" : game.setPlayer1(player3); break;
			case "2" :game.setPlayer2(player3); break;
			case "3" :game.setPlayer3(player3); break;
			case "4" :game.setPlayer4(player3); break;
			}
			
			game.setTeam1_score(0);
			game.setTeam2_score(0);
			

			saveGame(game);
			System.out.println(game.getId());
			return game.getId();
			
		}	
		else {
			System.out.println("Name deja existant");
			return -1;
		}
	}	
	
	/*
	 * GAME-PROCESS
	 * @see view_belot.BelotInterface#joinGame(long, java.lang.String, java.lang.String)
	 */
	public long joinGame(long gameid, String player,
			String position) {

		Game game = gameById(gameid);
		if(!player.equals(game.getPlayer1()) && !player.equals(game.getPlayer2()) && !player.equals(game.getPlayer3()) && !player.equals(game.getPlayer4())){
			
			switch(position){
			
			case "top" : game.setPlayer1(player); break;
			case "left" :game.setPlayer2(player); break;
			case "bottom" :game.setPlayer3(player); break;
			case "right" :game.setPlayer4(player); break;
			}			
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(game);
			tx.commit();
			
			if( game.getPlayer1() != null && game.getPlayer2() != null && game.getPlayer3() != null && game.getPlayer4() != null )
				return -999;
			return game.getId();
			
		}	
		else {
			System.out.println("Player already in game");
			return -2;
		}
	}

	public void dealing1(long gameid) {
		 
		Game game = new Game();
		game = entityManager.find(Game.class, gameid);
		//setting the playing status to dealing1
		if(!game.getPlayingStatus().contains("dealing")) {
			// if we deal the first time turn
			game.setPlayingStatus("dealing1");
			game.setCurrentCardReceiver(game.nextRoundPlayer(game.getCurrentCardReceiver()));
			System.out.println("First dealing process");
			game.setGame_info("Please, scan 1st card for "+ game.getCurrentCardReceiver());
			saveGame(game);
			System.out.println();
			
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
			saveGame(game);

		//starting the dealing1 methode
			dealing1(gameid);
	}
	 public static boolean check3fistcards(String [] hand) {
		 return true;
	 }

}
