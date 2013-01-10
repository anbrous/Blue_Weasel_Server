package presentation;

import java.util.ArrayList;
import java.util.List;

import metier.Card.Color;
import metier.Card.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import application.InitialisationManuelleInterface;
import application.MonInterface;
import application.MyImplementation;
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
	@RequestMapping("saisieAutomatique/appelService")
	public ModelAndView appelApplication() {
		
		//monInterface.createEntity();					// appel à l'application
		//monInterface.createGame();
		//monInterface.createPlayer();
		monInterface.cardsInitialisation();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation");			// affiche pageDeConfirmation.jsp
		mav.addObject("titre", "Message de réponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Entity créée !");		// variable message dans pageDeConfirmation.jsp
		return mav;
	}
	
	@RequestMapping(value = "saisieAutomatique/sendCard", method = RequestMethod.POST) 
	public ModelAndView sendApplication(@RequestParam("idRFID") String idRFID) {
		rfidInterface.setCurrentCard(idRFID);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeSaisieAutomatique");			// il faut rester dans la meme page jsp apres submit
		mav.addObject("status", "EnCours");
		
		return mav;
	}
	
	@RequestMapping("/saisieManuelle")
	public ModelAndView appelPageSaisieManuelle() {
		
		initialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
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
		mav.addObject("Values", ListOfColor); // il faut les charger dans les combobox dans le fichier jsp
		
		return mav;
	}
	
	@RequestMapping("/saisieAutomatique")
	public ModelAndView appelPageSaisieAutomatique() {
		
		//initialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
													// les cartes : color, value, rfid = null
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeSaisieAutomatique");			// affiche pageDeSaisieAutomatique.jsp
		
		return mav;
		
	}
	
	@RequestMapping("saisieAutomatique/quitService")
	public ModelAndView quitterSaisieAutomatique() {
		
		//initialisationManuelleInterface.MakeTable(); // on créé dans la base de données une table contenant 
													// les cartes : color, value, rfid = null
		MyImplementation.pooling = false;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeSaisieAutomatique");			// affiche pageDeSaisieAutomatique.jsp
		
		return mav;
		
	}
	
	
}
