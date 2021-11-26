package rnd.mate00.oauth2client.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class OAuth2UserImpl implements OAuth2User {

    private String name;

    private Map<String, Object> attributes;

    private OAuth2UserImpl() {
    }

    static OAuth2UserImpl create(DbUser dbUser, Map<String, Object> attributes) {
        return new OAuth2UserImpl();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
