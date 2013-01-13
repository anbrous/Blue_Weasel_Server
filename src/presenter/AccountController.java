package presenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import view.AccountInterface;


@Controller
public class AccountController {
	
	AccountInterface accountInterface;

	
	
	public AccountInterface getAccountInterface() {
		return accountInterface;
	}

	public void setAccountInterface(AccountInterface accountInterface) {
		this.accountInterface = accountInterface;
	}


	/**
	 * 	http://localhost:8080/JPA_SpringWebMVC/do/appelService
	 * 	avec 	<servlet-mapping>
	 * 				<servlet-name>do</servlet-name>
	 * 				<url-pattern>/do/*</url-pattern>
	 * 			</servlet-mapping>
	 *  dans web.xml
	 */
	
	@RequestMapping("game_list/")
	public ModelAndView appelApplication() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation"); //jsp page
		mav.addObject("titre", "Liste des jeux !");
		mav.addObject("message", "Ici la liste des jeux ! (belote, uno, tarot etc.. )");
		return mav;
		
	}

	@RequestMapping(value = "connection/", method = RequestMethod.POST) 
	public ModelAndView sendApplication(@RequestParam("action") String action , @RequestParam("nickname") String nickname , @RequestParam("bw_email") String email , @RequestParam("bw_pwd") String password ) {
		String response = accountInterface.connection(action, nickname, email, password);
		ModelAndView mav = new ModelAndView();
		if ( response == "signedin") {
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Connection");
			mav.addObject("message", "Connection succed, please Boris, make this part rock well");
		}
		else if ( response == "registered") {
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Signing Up");
			mav.addObject("message", "A player has been created, please Boris get this part Rockyyy !!");
		}
			
		else {
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Error");
			mav.addObject("message", response);
			
		}
		return mav;
	}

	
	
}
