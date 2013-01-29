
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
import view_belot.GamingInterface;


@Controller
public class GamingController {
	
	GamingInterface gamingInterface;

	EntityManager entityManager;
	
	public GamingInterface getGamingInterface() {
		return gamingInterface;
	}



	public void setGamingInterface(GamingInterface gamingInterface) {
		this.gamingInterface = gamingInterface;
	}
	@RequestMapping("gamingtest/")
	public ModelAndView createGameForm(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("emptyPage"); //jsp page
		return mav;
		
	}
}
