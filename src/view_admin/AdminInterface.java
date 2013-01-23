package view_admin;

import model.CardValueTempo;



public interface AdminInterface {

	public void createEntity();
	
	public void createMember();
	
	public void createGame();
	
	public void cardsInitialisation(String player);// lance la demande de saisie auto des cartes
	
	public void saveCardValueTempo777(String cardValue);
	
	public String getCardValueTempo777();
	
	public String[][] showCards(String player);
	
}
