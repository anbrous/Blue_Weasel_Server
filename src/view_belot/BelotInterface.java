package view_belot;

import model.Card;
import model.Member;

public interface BelotInterface {

	public void createEntity();
	
	public void playerPlayAcard(Member member, Card card);
}
