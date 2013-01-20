package presenter_belot;

import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		//long gameid = (long) session.getAttribute("game");
		ModelAndView mav = new ModelAndView();
		//mav.addObject("gameide", gameid);
		mav.setViewName("showTable");
		return mav;
		
	}
	
}
