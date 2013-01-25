package view_belot;

import java.util.ArrayList;

import model.Card;
import model.Game;
import model.Member;

public interface BelotInterface {

	public void createEntity();
	
	public void simulation();

	public ArrayList<Game> gameList(String status);
	
	public Game gameById(long id );
}
