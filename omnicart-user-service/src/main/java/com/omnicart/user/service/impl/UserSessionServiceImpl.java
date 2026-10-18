package com.omnicart.user.service.impl;

import com.omnicart.user.entity.User;
import com.omnicart.user.entity.UserSession;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.repo.IUserRepo;
import com.omnicart.user.repo.IUserSessionRepo;
import com.omnicart.user.service.IUserSessionService;
import com.omnicart.user.util.OmniCartUserConstant;
import com.omnicart.user.util.OmniCartUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ranjit Soni on 16-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class UserSessionServiceImpl implements IUserSessionService {

    private final IUserSessionRepo userSessionRepo;
    private final IUserRepo userRepo;

    @Override
    public void createUserSession(
            long userId, String refreshTokenHash, String loginIdentifier, Instant currentTime, String deviceInfo, String ipAddress) throws CustomBusinessException {

        User user = userRepo.findById(userId)
                                .orElseThrow(() ->
                                        new CustomBusinessException("User Not Found",
                                                OmniCartUtil.getErrorCode(
                                                        OmniCartUserConstant.BUSINESS_EXCEPTION_TAG, OmniCartUserConstant.USER_NOT_FOUND)));

        UserSession userSession = UserSession.builder()
                .user(user)
                .refreshTokenHash(refreshTokenHash)
                .loginIdentifier(loginIdentifier)
                .loggedInAt(currentTime)
                .expiredAt(currentTime.plus(2, ChronoUnit.DAYS))
                .deviceInfo(deviceInfo)
                .ipAddress(ipAddress)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        userSessionRepo.save(userSession);
    }

    @Override
    public UserSession getUserSession(String refreshTokenHash) throws CustomBusinessException {
        return userSessionRepo.findByRefreshTokenHash(refreshTokenHash)
                .orElseThrow(() -> new CustomBusinessException(
                        OmniCartUserConstant.BUSINESS_EXCEPTION_TAG,
                        OmniCartUtil.getErrorCode(OmniCartUserConstant.USER_SESSION_NOT_FOUND, OmniCartUserConstant.USER_SESSION_NOT_FOUND_ERROR_CODE)));
    }

    @Override
    public String validateAndReturnUserName(String refreshTokenHash) throws CustomBusinessException {
        Optional<UserSession> optionalUserSession = userSessionRepo.findByRefreshTokenHash(refreshTokenHash);

        if (optionalUserSession.isEmpty()
                || !Objects.isNull(optionalUserSession.get().getLoggedOutAt())
                || !Objects.isNull(optionalUserSession.get().getRevokedAt())) {
            throw new CustomBusinessException(
                    OmniCartUserConstant.BUSINESS_EXCEPTION_TAG,
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.INVALID_SESSION, OmniCartUserConstant.INVALID_SESSION_ERROR_CODE));
        }

        UserSession userSession = optionalUserSession.get();
        Instant expiredAt = userSession.getExpiredAt();
        if (Instant.now().isAfter(expiredAt)) {
            Instant currentTime = Instant.now();
            userSession.setRevokedAt(currentTime);
            userSession.setUpdatedAt(currentTime);
            userSessionRepo.save(userSession);

            throw new CustomBusinessException(
                    OmniCartUserConstant.BUSINESS_EXCEPTION_TAG,
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.INVALID_SESSION, OmniCartUserConstant.INVALID_SESSION_ERROR_CODE));
        }

        return userSession.getLoginIdentifier();
    }
}
