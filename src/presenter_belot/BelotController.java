package presenter_belot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Card;
import model.Game;
import model.CardIdTempo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileWriter;
import java.io.PrintWriter;


import view_belot.BelotInterface;

@Controller
public class BelotController {
	
	BelotInterface belotInterface;

	EntityManager entityManager;
	
	public BelotInterface getBelotInterface() {
		return belotInterface;
	}



	public void setBelotInterface(BelotInterface belotInterface) {
		this.belotInterface = belotInterface;
	}




	@RequestMapping("creategameform/")
	public ModelAndView createGameForm(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("createGameForm"); //jsp page
		return mav;
		
	}

	@RequestMapping(value = "creategame/", method = RequestMethod.POST)
	public ModelAndView createGame(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> register</a>");
			return mav;
		}
		int nbofrp = Integer.parseInt(req.getParameter("nborp"));
		int winningscore = Integer.parseInt(req.getParameter("winningscore"));
		long game_result = 0;
		if( nbofrp == 1) {
			game_result = belotInterface.createGame(req.getParameter("gamename"),winningscore,req.getParameter("player1"),req.getParameter("position1"));
		}
		else if (nbofrp == 2) {
			game_result = belotInterface.createGame(req.getParameter("gamename"),winningscore,req.getParameter("player1"),req.getParameter("position1"),req.getParameter("player2"),req.getParameter("position2"));
		}
		else if (nbofrp == 3){
			game_result = belotInterface.createGame(req.getParameter("gamename"),winningscore,req.getParameter("player1"),req.getParameter("position1"),req.getParameter("player2"),req.getParameter("position2"),req.getParameter("player3"),req.getParameter("position3"));			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("emptyPage"); //jsp page ( game_result == 0) {
		
		if (game_result == -1) {
			mav.addObject("message", "Sorry, this name already exists, find another name please!!");
		}
		else if (game_result == 0) {
			mav.addObject("message", "Unknown error :(");
		}
		else if ( game_result >= 1){

			mav.addObject("message", "New Game successfully created");
			session.setAttribute("gameid", game_result);
		}
		

		return mav;
		
	}
	
	

	@RequestMapping("game_table/")
	public ModelAndView game_table(HttpSession session) {

		/**
		 * 
		 *  This part represents just show the game on the central screen on the real game table
		 * 
		 **/
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/'> register</a>");
			return mav;
		}
		if ( session.getAttribute("gameid") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be part of a Game<br> <a href='/Blue_Weasel_Server/belot/creategameform/'>Create a game</a>");
			return mav;
		}
		long id = (long) session.getAttribute("gameid");
		Game game = new Game();
	   	game = belotInterface.gameById(id);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("player_top", game.getPlayer1());
		mav.addObject("player_left", game.getPlayer2());
		mav.addObject("player_bottom", game.getPlayer3());
		mav.addObject("player_right", game.getPlayer4());
		mav.addObject("played_card_top",game.getCurrent_card_1());
		mav.addObject("played_card_left",game.getCurrent_card_2());
		mav.addObject("played_card_bottom",game.getCurrent_card_3());
		mav.addObject("played_card_right",game.getCurrent_card_4());
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());
		mav.addObject("game_info",game.getGame_info());
		
		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping("show_table/")
	public ModelAndView show_table(HttpSession session) {

		/**
		 * 
		 *  This part represents just show the game but not accordingly to the player
		 * 
		 **/
 		//Simulation
		//belotInterface.simulation();
		// all codes must be created in interafces and implementation, and it will be called from the controller
		long id = 1;
		Game game = new Game();
	   	game = belotInterface.gameById(id);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("player_top", game.getPlayer1());
		mav.addObject("player_left", game.getPlayer2());
		mav.addObject("player_bottom", game.getPlayer3());
		mav.addObject("player_right", game.getPlayer4());
		mav.addObject("played_card_top",game.getCurrent_card_1());
		mav.addObject("played_card_left",game.getCurrent_card_2());
		mav.addObject("played_card_bottom",game.getCurrent_card_3());
		mav.addObject("played_card_right",game.getCurrent_card_4());
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());

		mav.addObject("player_top_cards",game.player1_getHand());
		mav.addObject("player_left_cards",game.player2_getHand());
		mav.addObject("player_bottom_cards",game.player3_getHand());
		mav.addObject("player_right_cards",game.player4_getHand());
		mav.addObject("game_info",game.getGame_info());
		
		
		mav.setViewName("showTable");
		return mav;
		
	}

	@RequestMapping("show_admintable/")
	public ModelAndView show_admintable(HttpSession session) {

		/**
		 * 
		 *  This part represents the table with a view of every cards
		 * 
		 **/
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "connection.html");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		else if (session.getAttribute("gameid") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/'> Main page</a>");
			return mav;
		}
		
		String player = (String) session.getAttribute("login"); 
		//Simulation
		// all codes must be created in interfaces and implementation, and it will be called from the controller
		long id = (long) session.getAttribute("gameid");
		Game game = new Game();
		game = belotInterface.gameById(id);
		
		ModelAndView mav = new ModelAndView();
		// the following conditions shows the cards accordingly to the fact that the current player always has his cards in the bottom position
		if( player.equals(game.getPlayer1())) {

			mav.addObject("player_bottom", game.getPlayer1());
			mav.addObject("player_right", game.getPlayer2());
			mav.addObject("player_top", game.getPlayer3());
			mav.addObject("player_left", game.getPlayer4());
			
			mav.addObject("played_card_bottom",game.getCurrent_card_1());
			mav.addObject("played_card_right",game.getCurrent_card_2());
			mav.addObject("played_card_top",game.getCurrent_card_3());
			mav.addObject("played_card_left",game.getCurrent_card_4());
			

			mav.addObject("player_bottom_cards",game.player1_getHand());
			mav.addObject("player_right_cards",game.player2_getHand());
			mav.addObject("player_top_cards",game.player3_getHand());
			mav.addObject("player_left_cards",game.player4_getHand());
			
		}
		
		else if( player.equals(game.getPlayer2())) {

			mav.addObject("player_left", game.getPlayer1());
			mav.addObject("player_bottom", game.getPlayer2());
			mav.addObject("player_right", game.getPlayer3());
			mav.addObject("player_top", game.getPlayer4());
			
			mav.addObject("played_card_left",game.getCurrent_card_1());
			mav.addObject("played_card_bottom",game.getCurrent_card_2());
			mav.addObject("played_card_right",game.getCurrent_card_3());
			mav.addObject("played_card_top",game.getCurrent_card_4());
			
			mav.addObject("player_bottom_cards",game.player2_getHand());
			mav.addObject("player_right_cards",game.player3_getHand());
			mav.addObject("player_top_cards",game.player4_getHand());
			mav.addObject("player_left_cards",game.player1_getHand());
			
		}
		else if( player.equals(game.getPlayer3())) {

			mav.addObject("player_top", game.getPlayer1());
			mav.addObject("player_left", game.getPlayer2());
			mav.addObject("player_bottom", game.getPlayer3());
			mav.addObject("player_right", game.getPlayer4());
			mav.addObject("played_card_top",game.getCurrent_card_1());
			mav.addObject("played_card_left",game.getCurrent_card_2());
			mav.addObject("played_card_bottom",game.getCurrent_card_3());
			mav.addObject("played_card_right",game.getCurrent_card_4());

			mav.addObject("player_top_cards",game.player1_getHand());
			mav.addObject("player_left_cards",game.player2_getHand());
			mav.addObject("player_bottom_cards",game.player3_getHand());
			mav.addObject("player_right_cards",game.player4_getHand());
			
		}
		else if( player.equals(game.getPlayer4())) {

			mav.addObject("player_right", game.getPlayer1());
			mav.addObject("player_top", game.getPlayer2());
			mav.addObject("player_left", game.getPlayer3());
			mav.addObject("player_bottom", game.getPlayer4());
			mav.addObject("played_card_right",game.getCurrent_card_1());
			mav.addObject("played_card_top",game.getCurrent_card_2());
			mav.addObject("played_card_left",game.getCurrent_card_3());
			mav.addObject("played_card_bottom",game.getCurrent_card_4());
			
			mav.addObject("player_right_cards",game.player1_getHand());
			mav.addObject("player_top_cards",game.player2_getHand());
			mav.addObject("player_left_cards",game.player3_getHand());
			mav.addObject("player_bottom_cards",game.player4_getHand());
		}
		else {
			
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR 1542, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
			
		}
		
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());
		mav.addObject("game_info",game.getGame_info());
		
		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping("show_gametable/")
	public ModelAndView show_game(HttpSession session) {
		/**
		 * 
		 *  This part represents the game accordingly to the player, so the player sees only his cards at the bottom place
		 * 
		 **/
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "connection.html");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		else if (session.getAttribute("gameid") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		String player = (String) session.getAttribute("login"); 
		//Simulation
		// all codes must be created in interafces and implementation, and it will be called from the controller
		long id = (long) session.getAttribute("gameid");
		Game game = new Game();
		game = belotInterface.gameById(id);
		
		ModelAndView mav = new ModelAndView();
		// the following conditions shows the cards accordingly to the fact that the current player always has his cards in the bottom position
		if( player.equals(game.getPlayer1())) {

			mav.addObject("player_bottom", game.getPlayer1());
			mav.addObject("player_right", game.getPlayer2());
			mav.addObject("player_top", game.getPlayer3());
			mav.addObject("player_left", game.getPlayer4());
			
			mav.addObject("played_card_bottom",game.getCurrent_card_1());
			mav.addObject("played_card_right",game.getCurrent_card_2());
			mav.addObject("played_card_top",game.getCurrent_card_3());
			mav.addObject("played_card_left",game.getCurrent_card_4());
			

			mav.addObject("player_bottom_cards",game.player1_getHand());
			mav.addObject("player_right_cards",game.player2_getHand());
			mav.addObject("player_top_cards",game.player3_getHand());
			mav.addObject("player_left_cards",game.player4_getHand());
			
		}
		
		else if( player.equals(game.getPlayer2())) {

			mav.addObject("player_left", game.getPlayer1());
			mav.addObject("player_bottom", game.getPlayer2());
			mav.addObject("player_right", game.getPlayer3());
			mav.addObject("player_top", game.getPlayer4());
			
			mav.addObject("played_card_left",game.getCurrent_card_1());
			mav.addObject("played_card_bottom",game.getCurrent_card_2());
			mav.addObject("played_card_right",game.getCurrent_card_3());
			mav.addObject("played_card_top",game.getCurrent_card_4());
			
			mav.addObject("player_bottom_cards",game.player2_getHand());
			mav.addObject("player_right_cards",game.player3_getHand());
			mav.addObject("player_top_cards",game.player4_getHand());
			mav.addObject("player_left_cards",game.player1_getHand());
			
		}
		else if( player.equals(game.getPlayer3())) {

			mav.addObject("player_top", game.getPlayer1());
			mav.addObject("player_left", game.getPlayer2());
			mav.addObject("player_bottom", game.getPlayer3());
			mav.addObject("player_right", game.getPlayer4());
			mav.addObject("played_card_top",game.getCurrent_card_1());
			mav.addObject("played_card_left",game.getCurrent_card_2());
			mav.addObject("played_card_bottom",game.getCurrent_card_3());
			mav.addObject("played_card_right",game.getCurrent_card_4());

			mav.addObject("player_top_cards",game.player1_getHand());
			mav.addObject("player_left_cards",game.player2_getHand());
			mav.addObject("player_bottom_cards",game.player3_getHand());
			mav.addObject("player_right_cards",game.player4_getHand());
			
		}
		else if( player.equals(game.getPlayer4())) {

			mav.addObject("player_right", game.getPlayer1());
			mav.addObject("player_top", game.getPlayer2());
			mav.addObject("player_left", game.getPlayer3());
			mav.addObject("player_bottom", game.getPlayer4());
			mav.addObject("played_card_right",game.getCurrent_card_1());
			mav.addObject("played_card_top",game.getCurrent_card_2());
			mav.addObject("played_card_left",game.getCurrent_card_3());
			mav.addObject("played_card_bottom",game.getCurrent_card_4());
			
			mav.addObject("player_right_cards",game.player1_getHand());
			mav.addObject("player_top_cards",game.player2_getHand());
			mav.addObject("player_left_cards",game.player3_getHand());
			mav.addObject("player_bottom_cards",game.player4_getHand());
		}
		else {
			
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR 1542, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
			
		}
		
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());
		mav.addObject("game_info",game.getGame_info());

		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping(value = "show_tablettable/", method = RequestMethod.POST)
	public ModelAndView show_tablet(HttpSession session, @RequestParam("device") String device) {
		//session.setAttribute("gameid", 38); // remove after simulation
		System.out.println("device: "+ device);
		
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "connection.html");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		else if (session.getAttribute("gameid") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		String player = (String) session.getAttribute("login"); 
		//Simulation
		// all codes must be created in interafces and implementation, and it will be called from the controller
		long id =  (long) session.getAttribute("gameid");
		
		Game game = new Game();
		game = belotInterface.gameById(id);
		
		ModelAndView mav = new ModelAndView();
		// the following conditions shows the cards accordingly to the fact that the current player always has his cards in the bottom position
		if( player.equals(game.getPlayer1())) {

			mav.addObject("player_bottom", game.getPlayer1());
			mav.addObject("player_right", game.getPlayer2());
			mav.addObject("player_top", game.getPlayer3());
			mav.addObject("player_left", game.getPlayer4());
			
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_4()));
			

			mav.addObject("player_bottom_cards",game.tabletConvert(game.player1_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
		}
		
		else if( player.equals(game.getPlayer2())) {

			mav.addObject("player_left", game.getPlayer1());
			mav.addObject("player_bottom", game.getPlayer2());
			mav.addObject("player_right", game.getPlayer3());
			mav.addObject("player_top", game.getPlayer4());
			
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_right",game.getCurrent_card_3());
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_4()));
			
			mav.addObject("player_left_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player2_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player4_getHand()));
			
		}
		else if( player.equals(game.getPlayer3())) {

			mav.addObject("player_top", game.getPlayer1());
			mav.addObject("player_left", game.getPlayer2());
			mav.addObject("player_bottom", game.getPlayer3());
			mav.addObject("player_right", game.getPlayer4());
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_4()));

			mav.addObject("player_top_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player3_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player4_getHand()));
			
		}
		else if( player.equals(game.getPlayer4())) {

			mav.addObject("player_right", game.getPlayer1());
			mav.addObject("player_top", game.getPlayer2());
			mav.addObject("player_left", game.getPlayer3());
			mav.addObject("player_bottom", game.getPlayer4());
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_4()));
			
			mav.addObject("player_right_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player4_getHand()));
		}
		else {
			
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR 1542, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
			
		}
		
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());
		mav.addObject("game_info",game.getGame_info());

		mav.setViewName("showTablet");
		return mav;
		
	}
	
	
	@RequestMapping(value = "show_tablettable/", method = RequestMethod.GET)
	public ModelAndView show_tablet(HttpSession session) {
		//session.setAttribute("gameid", 38); // remove after simulation
		
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "connection.html");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		else if (session.getAttribute("gameid") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		String player = (String) session.getAttribute("login"); 
		//Simulation
		// all codes must be created in interafces and implementation, and it will be called from the controller
		long id =  (long) session.getAttribute("gameid");
		
		Game game = new Game();
		game = belotInterface.gameById(id);
		
		ModelAndView mav = new ModelAndView();
		// the following conditions shows the cards accordingly to the fact that the current player always has his cards in the bottom position
		if( player.equals(game.getPlayer1())) {

			mav.addObject("player_bottom", game.getPlayer1());
			mav.addObject("player_right", game.getPlayer2());
			mav.addObject("player_top", game.getPlayer3());
			mav.addObject("player_left", game.getPlayer4());
			
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_4()));
			

			mav.addObject("player_bottom_cards",game.tabletConvert(game.player1_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
		}
		
		else if( player.equals(game.getPlayer2())) {

			mav.addObject("player_left", game.getPlayer1());
			mav.addObject("player_bottom", game.getPlayer2());
			mav.addObject("player_right", game.getPlayer3());
			mav.addObject("player_top", game.getPlayer4());
			
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_right",game.getCurrent_card_3());
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_4()));
			
			mav.addObject("player_left_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player2_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player4_getHand()));
			
		}
		else if( player.equals(game.getPlayer3())) {

			mav.addObject("player_top", game.getPlayer1());
			mav.addObject("player_left", game.getPlayer2());
			mav.addObject("player_bottom", game.getPlayer3());
			mav.addObject("player_right", game.getPlayer4());
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_4()));

			mav.addObject("player_top_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player3_getHand()));
			mav.addObject("player_right_cards",game.hidelist(game.player4_getHand()));
			
		}
		else if( player.equals(game.getPlayer4())) {

			mav.addObject("player_right", game.getPlayer1());
			mav.addObject("player_top", game.getPlayer2());
			mav.addObject("player_left", game.getPlayer3());
			mav.addObject("player_bottom", game.getPlayer4());
			mav.addObject("played_card_right",game.tabletConvert(game.getCurrent_card_1()));
			mav.addObject("played_card_top",game.tabletConvert(game.getCurrent_card_2()));
			mav.addObject("played_card_left",game.tabletConvert(game.getCurrent_card_3()));
			mav.addObject("played_card_bottom",game.tabletConvert(game.getCurrent_card_4()));
			
			mav.addObject("player_right_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_bottom_cards",game.tabletConvert(game.player4_getHand()));
		}
		else {
			
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("redirect", "");
			mav.addObject("message", "ERROR 1542, stop cheating, or spying, you don't belong to this game dude <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
			
		}
		
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());
		mav.addObject("game_info",game.getGame_info());

		mav.setViewName("showTablet");
		return mav;
		
	}
	
	
	@RequestMapping(value = "gamelistServer/", method = RequestMethod.GET)
	public ModelAndView gameListByUser(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'>register</a>");
			return mav;
		}
		
		String login = (String) session.getAttribute("login");
		List<Game> ListOfGamesFound = belotInterface.gameList("all", login); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGamesServer"); //jsp page
		return mav;
	}
	
	@RequestMapping(value = "game_available_seats/", method = RequestMethod.POST)
	public ModelAndView game_available_seats(HttpSession session, @RequestParam("gameid") long gameid ) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'>register</a>");
			return mav;
		}
		long id = (long) gameid;
		String login = (String) session.getAttribute("login");
		Game game = belotInterface.gameById(id); 
		//ArrayList<String> seats = new ArrayList<String>();
		
		String seatTop = "";
		String seatLeft = "";
		String seatBottom = "";
		String seatRight = "";
		
		if(game.getPlayer1() == null) {
			//seats.add("top");
			seatTop = "free";
		}
		else
			seatTop = game.getPlayer1();
		
		if(game.getPlayer2() == null) {
			//seats.add("left");
			seatLeft = "free";
		}
		else
			seatLeft = game.getPlayer2();
		if(game.getPlayer3() == null) {
			//seats.add("bottom");
			seatBottom = "free";
		}
		else
			seatBottom = game.getPlayer3();
		if(game.getPlayer4() == null) {
			//seats.add("right");
			seatRight = "free";
		}	
		else
			seatRight = game.getPlayer4();
		
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("seats", seats);
		mav.addObject("gameid", gameid);
		
		mav.addObject("seatTop", seatTop);
		mav.addObject("seatLeft", seatLeft);
		mav.addObject("seatBottom", seatBottom);
		mav.addObject("seatRight", seatRight);
		
		mav.setViewName("showGameSeats"); //jsp page
		return mav;
	}

	@RequestMapping(value = "game_available_seats_server/", method = RequestMethod.POST)
	public ModelAndView game_available_seats_server(HttpSession session, @RequestParam("gameid") long gameid ) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'>register</a>");
			return mav;
		}
		long id = (long) gameid;
		String login = (String) session.getAttribute("login");
		Game game = belotInterface.gameById(id); 
		//ArrayList<String> seats = new ArrayList<String>();
		
		String seatTop = "";
		String seatLeft = "";
		String seatBottom = "";
		String seatRight = "";
		
		if(game.getPlayer1() == null) {
			//seats.add("top");
			seatTop = "free";
		}
		else
			seatTop = game.getPlayer1();
		
		if(game.getPlayer2() == null) {
			//seats.add("left");
			seatLeft = "free";
		}
		else
			seatLeft = game.getPlayer2();
		if(game.getPlayer3() == null) {
			//seats.add("bottom");
			seatBottom = "free";
		}
		else
			seatBottom = game.getPlayer3();
		if(game.getPlayer4() == null) {
			//seats.add("right");
			seatRight = "free";
		}	
		else
			seatRight = game.getPlayer4();
		
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("seats", seats);
		mav.addObject("gameid", gameid);
		
		mav.addObject("seatTop", seatTop);
		mav.addObject("seatLeft", seatLeft);
		mav.addObject("seatBottom", seatBottom);
		mav.addObject("seatRight", seatRight);
		
		mav.setViewName("showGameSeatsServer"); //jsp page
		return mav;
	}
	
	@RequestMapping(value = "join_game/", method = RequestMethod.POST)
	public ModelAndView join_game(HttpSession session, @RequestParam("position") String position, @RequestParam("gameid") long gameid) {

		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}

		String player = (String) session.getAttribute("login");
		
		long id = belotInterface.joinGame(gameid, player, position);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirectPage"); //jsp page
		
		if(id == -2)
		{	
			mav.addObject("titre", "Error");
			mav.addObject("message", "Player "+player+" already in game");
		}
		else
		{
			if( id == -999 ) {
				// it is the last player
				System.out.println(gameid);
				belotInterface.startGame((gameid));
			}
			mav.addObject("redirect", "belot/show_gametable/");
			mav.addObject("titre", "Player join the game");
			mav.addObject("message", "The player "+player+" has joined the game "+gameid+" in position "+position);
			session.setAttribute("gameid", gameid);
		}		
		return mav;
	
	}
	
	@RequestMapping(value = "gamelist/", method = RequestMethod.POST)
	public ModelAndView gameList(HttpSession session, @RequestParam("status") String status) {
		
		System.out.println("login vaut: "+session.getAttribute("login"));
		
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String login = (String) session.getAttribute("login");
		
		System.out.println("Acc�s � la liste de status: "+ status);
		
		List<Game> ListOfGamesFound = belotInterface.gameList(status, login); 
		
		//List<Game> ListOfGamesFound = new ArrayList<>();
		//session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGames"); //jsp page
		return mav;
	
	}
	
	@RequestMapping(value = "gamelistServer/", method = RequestMethod.POST)
	public ModelAndView gameListServer(HttpSession session, @RequestParam("status") String status) {
		
		System.out.println("login vaut: "+session.getAttribute("login"));
		
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String login = (String) session.getAttribute("login");
		
		System.out.println("Acc�s � la liste de status: "+ status);
		
		List<Game> ListOfGamesFound = belotInterface.gameList(status, login); 
		
		//List<Game> ListOfGamesFound = new ArrayList<>();
		//session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGamesServer"); //jsp page
		return mav;
	
	}
	
	@RequestMapping(value = "joingame/", method = RequestMethod.POST)
	public ModelAndView joingame(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		String login = (String) session.getAttribute("login");
		List<Game> ListOfGamesFound = belotInterface.gameList("all", login); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGamesServer"); //jsp page
		return mav;
	}
	/*
	 *  PLAYING PROCESS DOWN HERE
	 * 
	 */
	
	@RequestMapping(value = "playcard/", method = RequestMethod.POST)
	public ModelAndView playCard(HttpSession session, @RequestParam("action") String action, @RequestParam("data") String data) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String login = (String) session.getAttribute("login");
		long gameid = (long) session.getAttribute("gameid");
		belotInterface.playcard(gameid,action,data);	
		//List<Game> ListOfGamesFound = new ArrayList<>();
		//session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirectPage");
		mav.addObject("redirect", "");
		mav.addObject("titre", "Action done");
		mav.addObject("message", "The action is done");
		return mav;
	
	}
	@RequestMapping(value = "playrfidcard/", method = RequestMethod.POST)
	public ModelAndView playRfidCard(HttpSession session, @RequestParam("action") String action, @RequestParam("data") String data) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		if ( session.getAttribute("gameid") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You don't belong to any game, sorry");
			return mav;
		}
		String login = (String) session.getAttribute("login");
		long gameid = (long) session.getAttribute("gameid");
		belotInterface.playcard(gameid,action,belotInterface.getRfidCard(data));	
		//List<Game> ListOfGamesFound = new ArrayList<>();
		//session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirectPage");
		mav.addObject("redirect", "rfid_simul.html");
		mav.addObject("titre", "Action done");
		mav.addObject("message", "Card added");
		return mav;
	
	}
}
