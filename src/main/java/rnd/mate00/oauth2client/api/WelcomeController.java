package rnd.mate00.oauth2client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private static String authorizationRequestBaseUri = "oauth2/authorization";


    @GetMapping({"/", "/w", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("urls", authorizationRequestBaseUri + "google");

        return "welcome";
    }

    @GetMapping("/loginOk")
    public String loginOk(Model model,
                          @AuthenticationPrincipal OAuth2User oAuth2User) {

        String userName = oAuth2User.getName();
        String userEmail = (String) oAuth2User.getAttributes().get("email");
        model.addAttribute("userName", userName);
        model.addAttribute("userEmail", userEmail);

        return "logged";
    }

}
