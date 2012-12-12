package presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import application.MonInterface;

@Controller
public class MonController {
	
	MonInterface monInterface;

	public MonInterface getMonInterface() {
		return monInterface;
	}

	public void setMonInterface(MonInterface monInterface) {
		this.monInterface = monInterface;
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
		monInterface.createEntity();					// appel � l'application
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation");			// affiche pageDeConfirmation.jsp
		mav.addObject("titre", "Message de r�ponse :");	// variable titre dans pageDeConfirmation.jsp
		mav.addObject("message", "Entity cr��e !");		// variable message dans pageDeConfirmation.jsp
		return mav;
	}
	
}
