package com.omnicart.user.repo;

import com.omnicart.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmailIdOrPhoneNumber(String emailId, String phoneNumber);
}
