package com.omnicart.user.service;

import com.omnicart.user.adapter.UserAdapter;
import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import com.omnicart.user.entity.User;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.repo.IUserRepo;
import com.omnicart.user.util.OmniCartUserConstant;
import com.omnicart.user.util.OmniCartUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepo userRepo;
    private UserAdapter userAdapter;
    private PasswordEncoder passwordEncoder;

    /**
     * @return UserRegisterResponseDto
     */
    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) throws CustomBusinessException {

        //check if user is already registered or not
        Optional<User> optionalUser = userRepo.findByEmailIdOrPhoneNumber(userRegisterRequestDto.getEmailId(), userRegisterRequestDto.getPhoneNumber());
        if (optionalUser.isPresent()) {
            throw new CustomBusinessException(OmniCartUserConstant.USER_ALREADY_REGISTERED_ERROR_MSG,
                                OmniCartUtil.getErrorCode(OmniCartUserConstant.BUSINESS_EXCEPTION_TAG, OmniCartUserConstant.USER_ALREADY_REGISTERED_ERROR_CODE));
        }

        //convert password into encrypted password
        String hashedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());

        //convert request to entity
        User user = userAdapter.convertUserRegisterRequestToEntity(userRegisterRequestDto, hashedPassword);

        user = userRepo.save(user);

        //convert back response entity to response dto
        //return response dto
        return userAdapter.convertEntityToUserRegisterResponse(user);
    }
}
