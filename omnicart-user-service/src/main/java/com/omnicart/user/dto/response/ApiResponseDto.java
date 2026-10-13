package com.omnicart.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Data
@AllArgsConstructor
public class ApiResponseDto<T> {

    private T data;
    private List<NotificationDto> notifications;
}
