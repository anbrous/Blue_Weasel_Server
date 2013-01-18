package presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		 System.out.println("bastien rentre:");
		 System.out.println("username "+username);
		 System.out.println("email: "+email);
		 System.out.println("password1: "+password1);
		 
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
	
	@RequestMapping(value = "checkEmail/", method = RequestMethod.POST)
	public ModelAndView checkEmail(HttpServletRequest req, HttpServletResponse resp) {

		String w = req.getParameter("email");
		//String pwd1 = req.getParameter("pwd1");
		//String pwd2 = req.getParameter("pwd2");
		Pattern EmailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

				String msg = "";

				Boolean emailCorrect = false;
				if(w != null)
				{
					Matcher matcher = EmailPattern.matcher(w);
					emailCorrect = matcher.matches();
				}

				if(w == "")
				{
					msg = "";
				}
				else if(!emailCorrect)
				{
					msg = "Email not correct...";
				}
				else
				{
					msg = "Email correct!";
				}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testEmail"); //jsp page
		mav.addObject("message", msg);
		
		/*if(!pwd2.equals("")) {
			
		}
		*/
		return mav;
	}

	
	
}
