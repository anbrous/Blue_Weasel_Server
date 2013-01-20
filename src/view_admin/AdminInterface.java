package view_admin;



public interface AdminInterface {

	public void createEntity();
	
	public void createMember();
	
	public void createGame();
	
	public void cardsInitialisation(String player);
	
	public String[][] showCards(String player);
	
}
