package com.omnicart.user.service;


import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.exception.CustomBusinessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface ITokenService {

    String generateToken(CustomUserDetails customUserDetails, Date currentTime) throws CustomBusinessException;
}
