package com.omnicart.user.repo;

import com.omnicart.user.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSessionRepo extends JpaRepository<UserSession, Long> {


    Optional<UserSession> findByRefreshTokenHash(String refreshTokenHash);
}
