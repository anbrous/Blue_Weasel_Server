package presenter;

import javax.servlet.http.HttpSession;

import org.ajaxanywhere.AAUtils;
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


	@RequestMapping("game_list/")
	public ModelAndView appelApplication() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation"); //jsp page
		mav.addObject("titre", "Liste des jeux !");
		mav.addObject("message", "Ici la liste des jeux ! (belote, uno, tarot etc.. )");
		return mav;
		
	}
	@RequestMapping("logout/")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("login");
		session.removeAttribute("email");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation"); //jsp page
		mav.addObject("titre", "Loging Out");
		mav.addObject("message", "you have successfully logged out, thanks");
		return mav;
		
	}
	@RequestMapping(value = "connection/", method = RequestMethod.POST) 
	public ModelAndView sendApplication( HttpSession session, @RequestParam("action") String action , 
			@RequestParam("username") String username , @RequestParam("email") String email ,
			@RequestParam("password1") String password1, @RequestParam("password2") String password2 ) {
		
		 
//		if (AAUtils.isAjaxRequest(request)){
//	        AAUtils.addZones(request, "countriesList");
//	    }
		
		String response = accountInterface.connection(action, username, email, password1, password2);
		ModelAndView mav = new ModelAndView();
		if ( response == "signedin") {
			// we use session variable to keep the user logged in
			session.setAttribute("login", username);
			session.setAttribute("email", email);
			//access to the page
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Connection");
			mav.addObject("message", "Connection succed, please Boris, make this part rock well");
		}
		else if ( response == "registered") {
			// we use session variable to keep the user logged in
			session.setAttribute("login", username);
			session.setAttribute("email", email);
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
