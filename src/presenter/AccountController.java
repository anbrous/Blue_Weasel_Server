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
		
		 System.out.println("username "+username);
		 System.out.println("email: "+email);
		 System.out.println("password1: "+password1);
		 
		String response = accountInterface.connection(action, username, email, password1, password2);
		ModelAndView mav = new ModelAndView();
		if ( response == "signedin") {
			
			mav.setViewName("redirectPage");
			mav.addObject("titre", "Connection");
			
			if(accountInterface.checkEmailExistance(email))
			{	
				if(accountInterface.checkPasswordCorrespondance(email, password1))
				{
					username = accountInterface.getUsernameByEmail(email);
					mav.addObject("message", "The player "+ username +" is connected!");
				}
				else
					mav.addObject("message", "Incorrect password!");
					mav.addObject("redirect", "connection.html");
			}
			else
				mav.addObject("message", "The Email is not registered!");
			
			// we use session variable to keep the user logged in
			session.setAttribute("login", username);
			session.setAttribute("email", email);
			mav.addObject("redirect", "connection.html");
		}
		else if ( response == "registered") {
			// we use session variable to keep the user logged in
			session.setAttribute("login", username);
			session.setAttribute("email", email);
			mav.setViewName("pageDeConfirmation"); //jsp page
			mav.addObject("titre", "Signing Up");
			mav.addObject("message", "Your account has been successfully created, "+username+" !!!");
			mav.addObject("redirect", "connection.html");
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
		String action = req.getParameter("action");
		String w = req.getParameter("email");
		String pwd1 = req.getParameter("password1");
		String pwd2 = req.getParameter("password2");
		String username = req.getParameter("username");
		
		Pattern EmailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Pattern userNamePattern = Pattern.compile("^[_A-Za-z0-9-\\+]+$");

				String msg = "";
		if(action.equals("email"))
		{
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
				msg = "<font color = 'red'>Wrong email</font>";
			}
			else
			{
				msg = "<font color = 'green'>Correct email!</font>";
			}
		
		}
		else if (action.equals("password1")||action.equals("password2"))
		{
			Boolean passwordCorrect = false;
			if(pwd1 != null)
			{
				Matcher matcher = userNamePattern.matcher(pwd1);
				passwordCorrect = matcher.matches();
			}
			
			if(pwd1 == "")
			{
				msg = "";
			}
			else if (!passwordCorrect)
			{
				msg = "<font color = 'red'>Password mismatches the pattern!</font>";
			}
			else
			{
				if(pwd2 != null && !pwd2.equals("")) {
					if(!pwd1.equals(pwd2)){
						msg = "<font color = 'red'>2nd Password mismatches the 1st one!</font>";
					}
					else
						msg = "<font color = 'green'>Correct password</font>";	
				}
				else
					msg = "";
			}
						
		}
		else if (action.equals("username"))
		{
			
			Boolean userNameCorrect = false;
			if(username != null)
			{
				Matcher matcher = userNamePattern.matcher(username);
				userNameCorrect = matcher.matches();
			}
			if(username == "")
			{
				msg = "";
			}
			else if(!userNameCorrect)
			{
				msg = "<font color = 'red'>Wrong username</font>";
			}
			else
			{
				msg = "<font color = 'green'>Correct username!</font>";
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testEmail"); //jsp page
		mav.addObject("message", msg);
		return mav;
	}

	
	
}
