package view_admin;

import java.util.ArrayList;

import model.Card;

public interface AdminInterface {

	public void createEntity();
	
	public void createPlayer();
	
	public void createGame();
	
	public void cardsInitialisation(String player);
	
	public String[][] showCards(String player);
	
}
