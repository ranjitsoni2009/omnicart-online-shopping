package com.omnicart.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
public class LoginRequestDto {

    private String username;
    private String password;
}
