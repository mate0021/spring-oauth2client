package rnd.mate00.oauth2client.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import rnd.mate00.oauth2client.provider.OAuth2Provider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractOAuthUser implements OAuth2User {

    Map<String, Object> attributes;

    public AbstractOAuthUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public abstract String getName();

    public abstract String getEmail();

    public abstract OAuth2Provider getProvider();
}
