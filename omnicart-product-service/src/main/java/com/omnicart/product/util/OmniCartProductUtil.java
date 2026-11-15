package com.omnicart.product.util;

import com.omnicart.product.exception.CustomBusinessException;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartProductUtil {

    public static String getErrorCode(String exceptionTag, String code) {
        return String.format(OmniCartProductConstant.ERROR_CODE, exceptionTag, code);
    }

    public static CustomBusinessException businessException(String message, String code) {
        return new CustomBusinessException(message,
                OmniCartProductUtil.getErrorCode(OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, code));
    }
}
