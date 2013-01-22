package presenter_belot;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import view_belot.BelotImplementation;
import view_belot.BelotInterface;


@Controller
public class BelotController {
	
	BelotInterface belotInterface;

	
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
		ModelAndView mav = new ModelAndView();
		mav.addObject("player1", "Android");
		mav.addObject("player2", "Bobo");
		mav.addObject("player3", "Lyvia");
		mav.addObject("player4", "B.");
		mav.addObject("played_card_top","0-1");
		mav.addObject("played_card_left","1-1");
		mav.addObject("played_card_bottom","2-1");
		mav.addObject("played_card_right","none");
		mav.addObject("game_status","Simulation");
		mav.addObject("current_trump","not set yet");
		mav.addObject("team1_score","350");
		mav.addObject("team2_score","570");
		
		String[] player1_cards = {"1-2","1-3","5-2","7-3","none","4-1","6-1","6-3"};
		String[] player2_cards = {"3-3","none","0-0","5-0","1-0","2-2","6-0","4-2"};
		String[] player3_cards = {"0-2","0-3","7-0","2-3","7-2","6-2","none","3-1"};
		String[] player4_cards = {"4-0","3-0","5-3","4-3","7-1","3-2","5-1","2-0"};

		mav.addObject("player1_cards",player1_cards);
		mav.addObject("player2_cards",player2_cards);
		mav.addObject("player3_cards",player3_cards);
		mav.addObject("player4_cards",player4_cards);
		
		mav.setViewName("showTable");
		return mav;
		
	}
	
}
