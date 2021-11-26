package rnd.mate00.oauth2client.user;

import java.util.Map;

public class OAuthUserProvider {

    static AbstractOAuthUser getUserFor(String registration, Map<String, Object> attributes) {
        switch (registration.toLowerCase()) {
            case "google":
                return new GoogleUser(attributes);
            case "github":
                return new GitHubUser(attributes);
            default:
                throw new IllegalArgumentException(String.format("Registration %s not implemented", registration));
        }
    }
}
