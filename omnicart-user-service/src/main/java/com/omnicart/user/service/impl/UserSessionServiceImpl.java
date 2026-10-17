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
import java.util.Date;

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
                .build();

        userSessionRepo.save(userSession);
    }
}
