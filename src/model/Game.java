package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Game {

	private long id;
	private String gameName;
	private String gameStatus;
	private int winningScore;
	private String currentTrump;
	private String currentTeamTrump;
	private String currentMaster;
	private Player player1;
	private Player player2;
	//private Player player3;
	//private Player player4;
	private int team1_score;
	private int team2_score;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getWinningScore() {
		return winningScore;
	}

	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}

	public String getCurrentTrump() {
		return currentTrump;
	}

	public void setCurrentTrump(String currentTrump) {
		this.currentTrump = currentTrump;
	}	

	public int getTeam1_score() {
		return team1_score;
	}

	public void setTeam1_score(int team1_score) {
		this.team1_score = team1_score;
	}

	public int getTeam2_score() {
		return team2_score;
	}

	public void setTeam2_score(int team2_score) {
		this.team2_score = team2_score;
	}

	public String getCurrentTeamTrump() {
		return currentTeamTrump;
	}

	public void setCurrentTeamTrump(String currentTeamTrump) {
		this.currentTeamTrump = currentTeamTrump;
	}

	public String getCurrentMaster() {
		return currentMaster;
	}

	public void setCurrentMaster(String currentMaster) {
		this.currentMaster = currentMaster;
	}
	/*
	@OneToOne(cascade=CascadeType.PERSIST, mappedBy = "game")
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	
	@OneToOne
	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	@OneToOne(mappedBy = "game")
	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

	@OneToOne(mappedBy = "game")
	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer4(Player player4) {
		this.player4 = player4;
	}
*/
}
