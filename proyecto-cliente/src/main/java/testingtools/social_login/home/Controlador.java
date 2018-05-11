/**
 * 
 */
package testingtools.social_login.home;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@SessionAttributes("twitterProfile")
public class Controlador {

	private Twitter twitter;
	private ConnectionRepository connectionRepository;
	
	@Inject
	public Controlador(Twitter twitter,
			ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}
        
	@RequestMapping("/")
	public String home(Model model) {       
       if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
           return "home";
       }
       model.addAttribute(twitter.userOperations().getUserProfile());
       return "hello";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		return "signin";
	}
}
