package rnd.mate00.oauth2client.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import rnd.mate00.oauth2client.provider.OAuth2Provider;
import rnd.mate00.oauth2client.user.repository.UserRepository;

import java.time.LocalDateTime;
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

        if ("github".equals(userRequest.getClientRegistration().getRegistrationId())) {
            GitHubUser gitHubUser = new GitHubUser(oAuth2User);

            DbUser newUser = new DbUser();
            newUser.setProvider(OAuth2Provider.GITHUB);
            newUser.setName(gitHubUser.getName());
            newUser.setEmail(gitHubUser.getEmail());
            userRepository.save(newUser);

            return gitHubUser;

        } else {
            GoogleOAuthUser googleUser = new GoogleOAuthUser(oAuth2User);
            Optional<DbUser> dbUser = userRepository.findByEmailAndProvider(googleUser.getEmail(), OAuth2Provider.GOOGLE);

            if (dbUser.isEmpty()) {
                registerNewUser(googleUser);
            } else {
                updateExisting(dbUser.get(), googleUser);
            }

            return googleUser;
        }
    }

    private void registerNewUser(GoogleOAuthUser googleOAuthUser) {
        log.info("Adding new user to local registry");
        
        DbUser newUser = new DbUser();
        newUser.setProvider(OAuth2Provider.GOOGLE);
        newUser.setName(googleOAuthUser.getName());
        newUser.setEmail(googleOAuthUser.getEmail());

        userRepository.save(newUser);
    }

    private void updateExisting(DbUser user, GoogleOAuthUser googleOAuthUser) {
        user.setName(googleOAuthUser.getName());
        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);
    }
}
