package rnd.mate00.oauth2client.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import rnd.mate00.oauth2client.provider.OAuth2Provider;
import rnd.mate00.oauth2client.user.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("Creating user data");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        GoogleOAuthUser googleUser = new GoogleOAuthUser(oAuth2User);
        Optional<DbUser> dbUser = userRepository.findByEmailAndProvider(googleUser.getEmail(), OAuth2Provider.GOOGLE);

        if (dbUser.isEmpty()) {
            registerNewUser(googleUser);
        }

        return googleUser;
    }

    private DbUser registerNewUser(GoogleOAuthUser googleOAuthUser) {
        DbUser newUser = new DbUser();
        newUser.setProvider(OAuth2Provider.GOOGLE);
        newUser.setName(googleOAuthUser.getName());
        newUser.setEmail(googleOAuthUser.getEmail());

        return userRepository.save(newUser);
    }
}
