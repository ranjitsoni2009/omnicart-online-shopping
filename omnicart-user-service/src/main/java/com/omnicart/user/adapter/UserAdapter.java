package com.omnicart.user.adapter;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import com.omnicart.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
public class UserAdapter {

    public User convertUserRegisterRequestToEntity(UserRegisterRequestDto userRegisterRequestDto, String hashedPassword) {
        return User.builder()
                .firstName(userRegisterRequestDto.getFirstName())
                .lastName(userRegisterRequestDto.getLastName())
                .passwordHash(hashedPassword)
                .emailId(userRegisterRequestDto.getEmailId())
                .isActive(true) //default until functionality added to make active externally
                .phoneNumber(userRegisterRequestDto.getPhoneNumber())
                .createdAt(Instant.now(Clock.systemDefaultZone()))
                .updatedAt(Instant.now(Clock.systemDefaultZone()))
                .build();
    }

    public UserRegisterResponseDto convertEntityToUserRegisterResponse(User user) {
        return UserRegisterResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobile(user.getPhoneNumber())
                .email(user.getEmailId())
                .build();
    }
}
