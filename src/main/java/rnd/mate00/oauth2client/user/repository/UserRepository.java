package rnd.mate00.oauth2client.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd.mate00.oauth2client.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
