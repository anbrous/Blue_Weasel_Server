package view_belot;

import model.Card;
import model.Member;

public interface BelotInterface {

	public void createEntity();
	
	public void simulation();
	
	public void playerPlayAcard(Member member, Card card);
}
