package rnd.mate00.oauth2client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private static String authorizationRequestBaseUri = "oauth2/authorization";

    @Autowired
    private OAuth2AuthorizedClientService auth2AuthorizedClientService;

    @GetMapping({"/w", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("urls", authorizationRequestBaseUri + "google");

        return "welcome";
    }

    @GetMapping("/loginOk")
    public String loginOk(Model model, OAuth2AuthenticationToken token) {

        model.addAttribute("userName", "asdf");

        return "logged";
    }

}
