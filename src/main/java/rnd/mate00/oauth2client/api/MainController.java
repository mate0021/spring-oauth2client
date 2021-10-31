package rnd.mate00.oauth2client.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
