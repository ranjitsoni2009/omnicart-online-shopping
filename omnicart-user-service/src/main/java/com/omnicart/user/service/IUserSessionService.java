package com.omnicart.user.service;

import com.omnicart.user.exception.CustomBusinessException;

import java.time.Instant;
import java.util.Date;

public interface IUserSessionService {

    void createUserSession(long userId, String refreshTokenHash, String loginIdentifier, Instant currentTime, String deviceInfo, String ipAddress) throws CustomBusinessException;
}
