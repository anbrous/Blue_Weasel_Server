package view;

public interface AccountInterface {

	public void createEntity();
	
	public void createPlayer(String nickname, String  email, String password);
	
	public void createGame();
	
	public String connection( String action, String name, String email, String password, String password2);
	
	
}
