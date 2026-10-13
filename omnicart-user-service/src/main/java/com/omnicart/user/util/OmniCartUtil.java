package com.omnicart.user.util;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartUtil {

    public static String getErrorCode(String exceptionTag, String code) {
        return String.format(OmniCartUserConstant.ERROR_CODE, exceptionTag, code);
    }
}
