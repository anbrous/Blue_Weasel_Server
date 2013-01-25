package presenter_admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Card;
import model.Card.Color;
import model.Card.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import view_admin.AdminImplementation;
import view_admin.AdminInitialisationManuelleInterface;
import view_admin.AdminInterface;
import view_admin.AdminRfidInterface;



@Controller
public class AdminController {
	
	AdminInterface adminInterface;
	AdminRfidInterface adminRfidInterface;
	AdminInitialisationManuelleInterface adminInitialisationManuelleInterface;

	

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}

	public AdminRfidInterface getAdminRfidInterface() {
		return adminRfidInterface;
	}

	public void setAdminRfidInterface(AdminRfidInterface adminRfidInterface) {
		this.adminRfidInterface = adminRfidInterface;
	}

	public AdminInitialisationManuelleInterface getAdminInitialisationManuelleInterface() {
		return adminInitialisationManuelleInterface;
	}

	public void setAdminInitialisationManuelleInterface(
			AdminInitialisationManuelleInterface adminInitialisationManuelleInterface) {
		this.adminInitialisationManuelleInterface = adminInitialisationManuelleInterface;
	}

	@RequestMapping("saisieAutomatique/appelService")
	public ModelAndView appelApplication(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String member = (String) session.getAttribute("login");
		adminInterface.cardsInitialisation(member);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirectPage");			// affiche pageDeConfirmation.jsp
		mav.addObject("titre", "Message de réponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Auto scan done!");		// variable message dans pageDeConfirmation.jsp
		return mav;
		
	}
	
	@RequestMapping("readerRFID/")
	public ModelAndView readerRFID(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageSimulationReaderRFID");			// affiche pageDeConfirmation.jsp
		return mav;
	}
	
	@RequestMapping(value = "sendCardAJAX/", method = RequestMethod.POST) 
	public ModelAndView sendApplication(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String idRFID = req.getParameter("idRFID");
		System.out.println("idRFID: "+ idRFID);
		System.out.println("TestSendCard: login= " + session.getAttribute("login"));
		
		adminRfidInterface.setCurrentCard(idRFID);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testEmail"); //jsp page
		mav.addObject("message", "Card: "+idRFID);
		
		return mav;
	}
	
	@RequestMapping(value = "showCardToScanAJAX/", method = RequestMethod.POST) 
	public ModelAndView showCardToScanAJAX(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		System.out.println("ajax show card to scan");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testEmail"); //jsp page
		mav.addObject("message", AdminImplementation.cardValueArecuperer);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/saisieManuelle", method = RequestMethod.POST)
	public ModelAndView appelPageSaisieManuelle(HttpSession session) {
		if ( session.getAttribute("login") == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		
		//adminInitialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
													// les cartes : color, value, rfid = null

		List<String> ListOfColor = new ArrayList<String>();
		for(Color color : Color.values()){
			ListOfColor.add(color.toString());
		}
		
		List<String> ListOfValue = new ArrayList<String>();
		for(Value value : Value.values()){
			ListOfValue.add(value.toString());
		}
		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeSaisieManuelle");			// affiche pageDeSaisieManuelle.jsp

		mav.addObject("Colors", ListOfColor);
		mav.addObject("Values", ListOfValue); // il faut les charger dans les combobox dans le fichier jsp
		
		
		return mav;
	}
	
	@RequestMapping("/saisieManuelle/validationSaisieManuelle")
	public ModelAndView validationSaisieManuelle(HttpSession session, @RequestParam("ColorSelected") String colorSelected, 
			@RequestParam("ValueSelected") String valueSelected, @RequestParam("idRFID") String idRFID) {
	
		
		adminInitialisationManuelleInterface.ManualCardConfiguration(Card.StringValueToEnum(valueSelected), Card.StringColorToEnum(colorSelected), idRFID);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageValidationSaisieManuelle");
		
		System.out.println("color selected:"+colorSelected);
		System.out.println("value selected:"+valueSelected );
		System.out.println("RFID scaned:"+idRFID );
		
		return mav;
	}
	
	
	@RequestMapping("/saisieAutomatique")
	public ModelAndView appelPageSaisieAutomatique(HttpSession session) {
		if ( session.getAttribute("login") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		//initialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
													// les cartes : color, value, rfid = null
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeSaisieAutomatique");			// affiche pageDeSaisieAutomatique.jsp
		
		return mav;
		
	}

	@RequestMapping("showCards")
	public ModelAndView showCards(HttpSession session) {
		if ( session.getAttribute("login") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", "You need to be logged to access this page, sorry <br> <a href='/Blue_Weasel_Server/connection.html'> connection</a>");
			return mav;
		}
		String player = (String) session.getAttribute("login");
		String [][] cardslist = adminInterface.showCards(player);
		/*
		int v,c;
		for(v=0;v<=13;v++){
			for (c=0;c<=3;c++){
				System.out.print(""+v+"-"+c+":"+cardslist[v][c]+"|" );
			}
			System.out.println("");
		}
		*/
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showCards");			
		mav.addObject("titre", "List of Scanned cards");			
		mav.addObject("player", player);	
		mav.addObject("cardslist", cardslist);		
		return mav;
	}
	@RequestMapping("saisieAutomatique/quitService")
	public ModelAndView quitterSaisieAutomatique() {
		
		//initialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
													// les cartes : color, value, rfid = null
		AdminImplementation.pooling = false;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirectPage");			// affiche pageDeSaisieAutomatique.jsp
		mav.addObject("titre", "Message de réponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Auto scan done!");
		
		return mav;
	}
	
	
}
