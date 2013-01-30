package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

	private long id;
	private String gameName;
	private String gameStatus;
	private int winningScore;
	private String currentTrump;
	private String currentTeamTrump;
	private String currentMaster;
	private String currentCardReceiver;
	private String playingStatus;
	private String player1;
	private String player2;
	private String player3;
	private String player4;
	private int team1_score;
	private int team2_score;
	private String current_card_1;
	private String current_card_2;
	private String current_card_3;
	private String current_card_4;
	private String player1_card1;
	private String player1_card2;
	private String player1_card3;
	private String player1_card4;
	private String player1_card5;
	private String player1_card6;
	private String player1_card7;
	private String player1_card8;
	private String player2_card1;
	private String player2_card2;
	private String player2_card3;
	private String player2_card4;
	private String player2_card5;
	private String player2_card6;
	private String player2_card7;
	private String player2_card8;
	private String player3_card1;
	private String player3_card2;
	private String player3_card3;
	private String player3_card4;
	private String player3_card5;
	private String player3_card6;
	private String player3_card7;
	private String player3_card8;
	private String player4_card1;
	private String player4_card2;
	private String player4_card3;
	private String player4_card4;
	private String player4_card5;
	private String player4_card6;
	private String player4_card7;
	private String player4_card8;
	private String game_info;
	
	

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
	
	public String getCurrentCardReceiver() {
		return currentCardReceiver;
	}

	public void setCurrentCardReceiver(String currentDealer) {
		this.currentCardReceiver = currentDealer;
	}

	public String getPlayingStatus() {
		return playingStatus;
	}

	public void setPlayingStatus(String playingStatus) {
		this.playingStatus = playingStatus;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	
	
	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getPlayer3() {
		return player3;
	}

	public void setPlayer3(String player3) {
		this.player3 = player3;
	}

	public String getPlayer4() {
		return player4;
	}

	public void setPlayer4(String player4) {
		this.player4 = player4;
	}

	public String getPlayer1_card1() {
		return player1_card1;
	}

	public void setPlayer1_card1(String player1_card1) {
		this.player1_card1 = player1_card1;
	}

	public String getPlayer1_card2() {
		return player1_card2;
	}

	public void setPlayer1_card2(String player1_card2) {
		this.player1_card2 = player1_card2;
	}

	public String getPlayer1_card3() {
		return player1_card3;
	}

	public void setPlayer1_card3(String player1_card3) {
		this.player1_card3 = player1_card3;
	}

	public String getPlayer1_card4() {
		return player1_card4;
	}

	public void setPlayer1_card4(String player1_card4) {
		this.player1_card4 = player1_card4;
	}

	public String getPlayer1_card5() {
		return player1_card5;
	}

	public void setPlayer1_card5(String player1_card5) {
		this.player1_card5 = player1_card5;
	}

	public String getPlayer1_card6() {
		return player1_card6;
	}

	public void setPlayer1_card6(String player1_card6) {
		this.player1_card6 = player1_card6;
	}

	public String getPlayer1_card7() {
		return player1_card7;
	}

	public void setPlayer1_card7(String player1_card7) {
		this.player1_card7 = player1_card7;
	}

	public String getPlayer1_card8() {
		return player1_card8;
	}

	public void setPlayer1_card8(String player1_card8) {
		this.player1_card8 = player1_card8;
	}

	public String getPlayer2_card1() {
		return player2_card1;
	}

	public void setPlayer2_card1(String player2_card1) {
		this.player2_card1 = player2_card1;
	}

	public String getPlayer2_card2() {
		return player2_card2;
	}

	public void setPlayer2_card2(String player2_card2) {
		this.player2_card2 = player2_card2;
	}

	public String getPlayer2_card3() {
		return player2_card3;
	}

	public void setPlayer2_card3(String player2_card3) {
		this.player2_card3 = player2_card3;
	}

	public String getPlayer2_card4() {
		return player2_card4;
	}

	public void setPlayer2_card4(String player2_card4) {
		this.player2_card4 = player2_card4;
	}

	public String getPlayer2_card5() {
		return player2_card5;
	}

	public void setPlayer2_card5(String player2_card5) {
		this.player2_card5 = player2_card5;
	}

	public String getPlayer2_card6() {
		return player2_card6;
	}

	public void setPlayer2_card6(String player2_card6) {
		this.player2_card6 = player2_card6;
	}

	public String getPlayer2_card7() {
		return player2_card7;
	}

	public void setPlayer2_card7(String player2_card7) {
		this.player2_card7 = player2_card7;
	}

	public String getPlayer2_card8() {
		return player2_card8;
	}

	public void setPlayer2_card8(String player2_card8) {
		this.player2_card8 = player2_card8;
	}

	public String getPlayer3_card1() {
		return player3_card1;
	}

	public void setPlayer3_card1(String player3_card1) {
		this.player3_card1 = player3_card1;
	}

	public String getPlayer3_card2() {
		return player3_card2;
	}

	public void setPlayer3_card2(String player3_card2) {
		this.player3_card2 = player3_card2;
	}

	public String getPlayer3_card3() {
		return player3_card3;
	}

	public void setPlayer3_card3(String player3_card3) {
		this.player3_card3 = player3_card3;
	}

	public String getPlayer3_card4() {
		return player3_card4;
	}

	public void setPlayer3_card4(String player3_card4) {
		this.player3_card4 = player3_card4;
	}

	public String getPlayer3_card5() {
		return player3_card5;
	}

	public void setPlayer3_card5(String player3_card5) {
		this.player3_card5 = player3_card5;
	}

	public String getPlayer3_card6() {
		return player3_card6;
	}

	public void setPlayer3_card6(String player3_card6) {
		this.player3_card6 = player3_card6;
	}

	public String getPlayer3_card7() {
		return player3_card7;
	}

	public void setPlayer3_card7(String player3_card7) {
		this.player3_card7 = player3_card7;
	}

	public String getPlayer3_card8() {
		return player3_card8;
	}

	public void setPlayer3_card8(String player3_card8) {
		this.player3_card8 = player3_card8;
	}

	public String getPlayer4_card1() {
		return player4_card1;
	}

	public void setPlayer4_card1(String player4_card1) {
		this.player4_card1 = player4_card1;
	}

	public String getPlayer4_card2() {
		return player4_card2;
	}

	public void setPlayer4_card2(String player4_card2) {
		this.player4_card2 = player4_card2;
	}

	public String getPlayer4_card3() {
		return player4_card3;
	}

	public void setPlayer4_card3(String player4_card3) {
		this.player4_card3 = player4_card3;
	}

	public String getPlayer4_card4() {
		return player4_card4;
	}

	public void setPlayer4_card4(String player4_card4) {
		this.player4_card4 = player4_card4;
	}

	public String getPlayer4_card5() {
		return player4_card5;
	}

	public void setPlayer4_card5(String player4_card5) {
		this.player4_card5 = player4_card5;
	}

	public String getPlayer4_card6() {
		return player4_card6;
	}

	public void setPlayer4_card6(String player4_card6) {
		this.player4_card6 = player4_card6;
	}

	public String getPlayer4_card7() {
		return player4_card7;
	}

	public void setPlayer4_card7(String player4_card7) {
		this.player4_card7 = player4_card7;
	}

	public String getPlayer4_card8() {
		return player4_card8;
	}

	public void setPlayer4_card8(String player4_card8) {
		this.player4_card8 = player4_card8;
	}

	public String getCurrent_card_1() {
		return current_card_1;
	}

	public void setCurrent_card_1(String current_card_1) {
		this.current_card_1 = current_card_1;
	}

	public String getCurrent_card_2() {
		return current_card_2;
	}

	public void setCurrent_card_2(String current_card_2) {
		this.current_card_2 = current_card_2;
	}

	public String getCurrent_card_3() {
		return current_card_3;
	}

	public void setCurrent_card_3(String current_card_3) {
		this.current_card_3 = current_card_3;
	}

	public String getCurrent_card_4() {
		return current_card_4;
	}

	public void setCurrent_card_4(String current_card_4) {
		this.current_card_4 = current_card_4;
	}
	
	public String getGame_info() {
		return game_info;
	}

	public void setGame_info(String game_info) {
		this.game_info = game_info;
	}
 	
	public String [] playerx_getHand(String player) {
		
		if (this.player1.equals(player))
			return player1_getHand();		
		if (this.player2.equals(player))
			return player2_getHand();		
		if (this.player3.equals(player))
			return player3_getHand();		
		if (this.player4.equals(player))
			return player4_getHand();
		else
			return null;
	}
	
	public String [] player1_getHand(){
		String[] hand = {player1_card1,player1_card2,player1_card3,player1_card4,player1_card5,player1_card6,player1_card7,player1_card8};
		return hand;
	}
	public String [] player2_getHand(){
		String[] hand = {player2_card1,player2_card2,player2_card3,player2_card4,player2_card5,player2_card6,player2_card7,player2_card8};
		return hand;
	}
	public String [] player3_getHand(){
		String[] hand = {player3_card1,player3_card2,player3_card3,player3_card4,player3_card5,player3_card6,player3_card7,player3_card8};
		return hand;
	}
	public String [] player4_getHand(){
		String[] hand = {player4_card1,player4_card2,player4_card3,player4_card4,player4_card5,player4_card6,player4_card7,player4_card8};
		return hand;
	}
	
	public String [] hidelist( String [] hand){
		
		String [] result;
		result = hand;	
		int i;
		
		for(i = 0; i< result.length; i++){
			if(result[i] == null)
			{
				result[i] = "none";
			}
			if(!result[i].equals("none")){
				result[i]="b1fv";
			}
		}
		return result;
	}
	
	public void simulation() {
		// we are going to make random cards on the game
		// we have 32 cards and 4 cards on table possibilities
		//<we are simulating only the beginning when all cards are on the board
		ArrayList<String> allcards = new ArrayList<String>();
		allcards.add("none");allcards.add("none");allcards.add("none");allcards.add("none");
		int v,c;
		for (v=0;v<8;v++) {
			for (c=0;c<4;c++){
				allcards.add(""+v+"-"+c);
			}
		}
		Collections.shuffle(allcards);
		
		String[] randomcards = allcards.toArray(new String[36]);
		
		//now dealing those random cards;
		this.current_card_1 = randomcards[0];
		this.current_card_2 = randomcards[1];
		this.current_card_3 = randomcards[2];
		this.current_card_4 = randomcards[3];
		//---
		this.player1_card1 = randomcards[4];
		this.player1_card2 = randomcards[5];
		this.player1_card3 = randomcards[6];
		this.player1_card4 = randomcards[7];
		this.player1_card5 = randomcards[8];
		this.player1_card6 = randomcards[9];
		this.player1_card7 = randomcards[10];
		this.player1_card8 = randomcards[11];
		//---
		this.player2_card1 = randomcards[12];
		this.player2_card2 = randomcards[13];
		this.player2_card3 = randomcards[14];
		this.player2_card4 = randomcards[15];
		this.player2_card5 = randomcards[16];
		this.player2_card6 = randomcards[17];
		this.player2_card7 = randomcards[18];
		this.player2_card8 = randomcards[19];
		//---
		this.player3_card1 = randomcards[20];
		this.player3_card2 = randomcards[21];
		this.player3_card3 = randomcards[22];
		this.player3_card4 = randomcards[23];
		this.player3_card5 = randomcards[24];
		this.player3_card6 = randomcards[25];
		this.player3_card7 = randomcards[26];
		this.player3_card8 = randomcards[27];
		//---
		this.player4_card1 = randomcards[28];
		this.player4_card2 = randomcards[29];
		this.player4_card3 = randomcards[30];
		this.player4_card4 = randomcards[31];
		this.player4_card5 = randomcards[32];
		this.player4_card6 = randomcards[33];
		this.player4_card7 = randomcards[34];
		this.player4_card8 = randomcards[35];
		
	}
	
	public String nextRoundPlayer( String currentplayer  ){
		
		if(this.player1.equals(currentplayer))
			return this.player2;
		else if (this.player2.equals(currentplayer))
			return this.player3;
		else if (this.player3.equals(currentplayer))
			return this.player4;
		else if (this.player4.equals(currentplayer))
			return this.player1;
		else
			return "error";
	}
	
	public String[] tabletConvert(String [] hand) {
		String[] result = hand;
		int i;
		for (i=0;i<hand.length;i++){
			
			switch(hand[i]) {
			
			case "0-0": ; result[i] = "ass"; break;
			case "0-1": ; result[i] = "ash"; break;
			case "0-2": ; result[i] = "asc"; break;
			case "0-3": ; result[i] = "asd"; break;
			case "1-0": ; result[i] = "kis"; break;
			case "1-1": ; result[i] = "kih"; break;
			case "1-2": ; result[i] = "kic"; break;
			case "1-3": ; result[i] = "kid"; break;
			case "2-0": ; result[i] = "qus"; break;
			case "2-1": ; result[i] = "quh"; break;
			case "2-2": ; result[i] = "quc"; break;
			case "2-3": ; result[i] = "qud"; break;
			case "3-0": ; result[i] = "jas"; break;
			case "3-1": ; result[i] = "jah"; break;
			case "3-2": ; result[i] = "jac"; break;
			case "3-3": ; result[i] = "jad"; break;
			case "4-0": ; result[i] = "tes"; break;
			case "4-1": ; result[i] = "teh"; break;
			case "4-2": ; result[i] = "tec"; break;
			case "4-3": ; result[i] = "ted"; break;
			case "5-0": ; result[i] = "nis"; break;
			case "5-1": ; result[i] = "nih"; break;
			case "5-2": ; result[i] = "nic"; break;
			case "5-3": ; result[i] = "nid"; break;
			case "6-0": ; result[i] = "eis"; break;
			case "6-1": ; result[i] = "eih"; break;
			case "6-2": ; result[i] = "eic"; break;
			case "6-3": ; result[i] = "eid"; break;
			case "7-0": ; result[i] = "ses"; break;
			case "7-1": ; result[i] = "seh"; break;
			case "7-2": ; result[i] = "sec"; break;
			case "7-3": ; result[i] = "sed"; break;
			
			}
			
		}
		
		return result;
	}
}
