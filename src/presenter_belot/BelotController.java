package presenter_belot;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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



	@RequestMapping("test/")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pageDeConfirmation"); //jsp page
		mav.addObject("titre", "test");
		mav.addObject("message", "thanks");
		return mav;
		
	}

	
}
