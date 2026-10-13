package com.omnicart.user.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Data
@Builder
public class NotificationDto {

    private String errorCode;
    private String message;
    private String status;
    private Instant timestamp;
}
