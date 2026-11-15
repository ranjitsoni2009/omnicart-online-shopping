package com.omnicart.order.util;

import com.omnicart.order.exception.CustomBusinessException;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartOrderUtil {

    public static String getErrorCode(String exceptionTag, String code) {
        return String.format(OmniCartOrderConstant.ERROR_CODE, exceptionTag, code);
    }

    public static CustomBusinessException businessException(String message, String code) {
        return new CustomBusinessException(message,
                OmniCartOrderUtil.getErrorCode(OmniCartOrderConstant.BUSINESS_EXCEPTION_TAG, code));
    }
}
