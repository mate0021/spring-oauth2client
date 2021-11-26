package rnd.mate00.oauth2client.user;

import rnd.mate00.oauth2client.provider.OAuth2Provider;

import java.util.Map;

public class GoogleUser extends AbstractOAuthUser {


    public GoogleUser(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getName() {
        return (String) getAttributes().get("name");
    }

    @Override
    public String getEmail() {
        return (String) getAttributes().get("email");
    }

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.GOOGLE;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
