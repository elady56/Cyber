package Cyber_Community.repositories;

import Cyber_Community.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByNickname(String nickname);
}
