package rnd.mate00.oauth2client.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd.mate00.oauth2client.provider.OAuth2Provider;
import rnd.mate00.oauth2client.user.DbUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Long> {

    Optional<DbUser> findByEmailAndProvider(String name, OAuth2Provider provider);
}
