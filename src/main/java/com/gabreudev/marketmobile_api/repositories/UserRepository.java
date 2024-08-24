package com.gabreudev.marketmobile_api.repositories;
import com.gabreudev.marketmobile_api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByEmail(String email);
}
