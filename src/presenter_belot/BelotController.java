package presenter_belot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import model.Card;
import model.Game;
import model.CardIdTempo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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



	@RequestMapping("createGameForm")
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

	@RequestMapping(value = "createGame/", method = RequestMethod.POST)
	public ModelAndView createGame(HttpSession session, @RequestParam("gameid") String gameid) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showTable"); //jsp page
		return mav;
		
	}
	@RequestMapping("show_table/")
	public ModelAndView show_table(HttpSession session) {
		//Simulation
		belotInterface.simulation();
		// all codes must be created in interafces and implementation, and it will be called from the controller
		long id = 1;
		Game game = new Game();
		game.simulation();
		//thegame.setId(id);
		//game = entityManager.find(Game.class, id);
		System.out.println(game.getCurrent_card_bottom());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("player1", game.getPlayer1());
		mav.addObject("player2", game.getPlayer2());
		mav.addObject("player3", game.getPlayer3());
		mav.addObject("player4", game.getPlayer4());
		mav.addObject("played_card_top",game.getCurrent_card_top());
		mav.addObject("played_card_left",game.getCurrent_card_left());
		mav.addObject("played_card_bottom",game.getCurrent_card_bottom());
		mav.addObject("played_card_right",game.getCurrent_card_right());
		mav.addObject("game_status",game.getGameStatus());
		mav.addObject("current_trump",game.getCurrentTrump());
		mav.addObject("team1_score",""+game.getTeam1_score());
		mav.addObject("team2_score",""+game.getTeam2_score());

		mav.addObject("player1_cards",game.player1_getHand());
		mav.addObject("player2_cards",game.player1_getHand());
		mav.addObject("player3_cards",game.player1_getHand());
		mav.addObject("player4_cards",game.player1_getHand());
		
		mav.setViewName("showTable");
		return mav;
		
	}
	
	@RequestMapping(value = "gamelist/", method = RequestMethod.GET)
	public ModelAndView gameListByUser(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirectPage"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		List<Game> ListOfGamesFound = belotInterface.gameList("all"); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGames"); //jsp page
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
		
		
		System.out.println("Accès à la liste de status: "+ status);
		
		List<Game> ListOfGamesFound = belotInterface.gameList(status); 
		
		//session.setAttribute("gameid", gameid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("listOfGames", ListOfGamesFound);
		mav.setViewName("showGames"); //jsp page
		return mav;
	
	
	}
	
}
