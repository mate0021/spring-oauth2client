package rnd.mate00.oauth2client.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rnd.mate00.oauth2client.user.CurrentLoggedUser;

@RestController
public class MainController {

    @GetMapping({"/allowed", "/a"})
    public String allow() {
        return "for everyone";
    }

    @GetMapping({"/restricted", "/r"})
    public String disallow() {
        return "for elite";
    }


    @GetMapping("/who")
    public String whoAmI(@CurrentLoggedUser OAuth2User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(user);
    }
}
