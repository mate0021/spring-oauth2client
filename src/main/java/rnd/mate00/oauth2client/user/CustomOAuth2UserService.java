package rnd.mate00.oauth2client.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import rnd.mate00.oauth2client.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Map;
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

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> parameters = oAuth2User.getAttributes();

        AbstractOAuthUser authUser = OAuthUserProvider.getUserFor(registrationId, parameters);
        Optional<DbUser> dbUser = userRepository.findByEmailAndProvider(authUser.getEmail(), authUser.getProvider());

        dbUser.map(this::update).orElseGet(() -> register(authUser));

        return authUser;
    }

    private DbUser register(AbstractOAuthUser authUser) {
        log.info("Adding new user ({}) to local registry", authUser.getEmail());

        DbUser user = new DbUser();
        user.setName(authUser.getName());
        user.setEmail(authUser.getEmail());
        user.setProvider(authUser.getProvider());

        return userRepository.save(user);
    }

    private DbUser update(DbUser dbUser) {
        log.info("Updating user {}", dbUser.getEmail());
        dbUser.setLastLogin(LocalDateTime.now());
        userRepository.save(dbUser);

        return dbUser;
    }
}
