package com.omnicart.user.dto.request;

import lombok.Data;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Data
public class UserRegisterRequestDto {

    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String password;
}
