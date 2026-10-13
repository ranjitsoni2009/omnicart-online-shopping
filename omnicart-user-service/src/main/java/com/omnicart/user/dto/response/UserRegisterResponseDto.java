package com.omnicart.user.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Data
@Builder
public class UserRegisterResponseDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
}
