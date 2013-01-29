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
		
		mav.setViewName("gameTable");
		return mav;
		
	}
	
	@RequestMapping("show_table/")
	public ModelAndView show_table(HttpSession session) {
 		//Simulation
		belotInterface.simulation();
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
		
		mav.setViewName("showTable");
		return mav;
		
	}

	@RequestMapping("show_admintable/")
	public ModelAndView show_admintable(HttpSession session) {
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
			
			mav.addObject("player_bottom_cards",game.player1_getHand());
			mav.addObject("player_right_cards",game.player2_getHand());
			mav.addObject("player_top_cards",game.player3_getHand());
			mav.addObject("player_left_cards",game.player4_getHand());
			
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
		
		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping("show_gametable/")
	public ModelAndView show_game(HttpSession session) {
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
			mav.addObject("player_right_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
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
			
			mav.addObject("player_bottom_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_right_cards",game.player2_getHand());
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
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

			mav.addObject("player_top_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_bottom_cards",game.player3_getHand());
			mav.addObject("player_right_cards",game.hidelist(game.player4_getHand()));
			
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
			
			mav.addObject("player_right_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player3_getHand()));
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

		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping("show_tablettable/")
	public ModelAndView show_tablet(HttpSession session) {
		session.setAttribute("gameid", 1); // remove after simulation
		
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
		long id = 1;//(long) session.getAttribute("gameid");
		
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
			mav.addObject("player_right_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
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
			
			mav.addObject("player_bottom_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_right_cards",game.player2_getHand());
			mav.addObject("player_top_cards",game.hidelist(game.player3_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player4_getHand()));
			
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

			mav.addObject("player_top_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_bottom_cards",game.player3_getHand());
			mav.addObject("player_right_cards",game.hidelist(game.player4_getHand()));
			
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
			
			mav.addObject("player_right_cards",game.hidelist(game.player1_getHand()));
			mav.addObject("player_top_cards",game.hidelist(game.player2_getHand()));
			mav.addObject("player_left_cards",game.hidelist(game.player3_getHand()));
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
		ArrayList<String> seats = new ArrayList<String>();
		if(game.getPlayer1() == null) {
			seats.add("top");
		}
		if(game.getPlayer2() == null) {
			seats.add("left");
		}
		if(game.getPlayer3() == null) {
			seats.add("bottom");
		}
		if(game.getPlayer4() == null) {
			seats.add("right");
		}	
		ModelAndView mav = new ModelAndView();
		mav.addObject("seats", seats);	
		mav.setViewName("showGameSeats"); //jsp page
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
		
		System.out.println("Accès à la liste de status: "+ status);
		
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
		
		System.out.println("Accès à la liste de status: "+ status);
		
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
	
}
