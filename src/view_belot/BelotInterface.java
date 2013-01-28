package view_belot;

import java.util.ArrayList;

import model.Card;
import model.Game;
import model.Member;

public interface BelotInterface {

	public void createEntity();
	
	public void simulation();

	public ArrayList<Game> gameList(String status, String login);
	
	public Game gameById(long id );
	
	public long createGame( String gamename, int winningscore, String player1, String position1 );
		//return id of created game
	public long createGame( String gamename, int winningscore, String player1, String position1, String player2, String position2 );
	//return id of created game

	public long createGame( String gamename, int winningscore, String player1, String position1, String player2, String position2, String player3, String position3 );
		//return id of created game


}
