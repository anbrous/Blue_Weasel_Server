package presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import application.InitialisationManuelleInterface;
import application.MonInterface;
import application.RfidInterface;

@Controller
public class MonController {
	
	MonInterface monInterface;
	RfidInterface rfidInterface;
	InitialisationManuelleInterface initialisationManuelleInterface;

	
	public MonInterface getMonInterface() {
		return monInterface;
	}

	public void setMonInterface(MonInterface monInterface) {
		this.monInterface = monInterface;
	}
	public RfidInterface getRfidInterface() {
		return rfidInterface;
	}

	public void setRfidInterface(RfidInterface rfidInterface) {
		this.rfidInterface = rfidInterface;
	}
	
	
	public InitialisationManuelleInterface getInitialisationManuelleInterface() {
		return initialisationManuelleInterface;
	}

	public void setInitialisationManuelleInterface(InitialisationManuelleInterface initialisationManuelleInterface) {
		this.initialisationManuelleInterface = initialisationManuelleInterface;
	}

	/**
	 * 	http://localhost:8080/JPA_SpringWebMVC/do/appelService
	 * 	avec 	<servlet-mapping>
	 * 				<servlet-name>do</servlet-name>
	 * 				<url-pattern>/do/*</url-pattern>
	 * 			</servlet-mapping>
	 *  dans web.xml
	 */
	@RequestMapping("/appelService")
	public ModelAndView appelApplication() {
		
		initialisationManuelleInterface.MakeTable();
		
		monInterface.createEntity();					// appel à l'application
		monInterface.createGame();
		monInterface.createPlayer();
		monInterface.cardsInitialisation();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation");			// affiche pageDeConfirmation.jsp
		mav.addObject("titre", "Message de réponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Entity créée !");		// variable message dans pageDeConfirmation.jsp
		return mav;
	}
	
	@RequestMapping(value = "/sendCardId", method = RequestMethod.POST) 
	public ModelAndView sendApplication(@RequestParam("idRFID") String idRFID) {
		rfidInterface.setCurrentCard(idRFID);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation");			// affiche pageDeConfirmation.jsp
		mav.addObject("titre", "Message de réponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Entity créée !");		// variable message dans pageDeConfirmation.jsp
		return mav;
	}
	
}
