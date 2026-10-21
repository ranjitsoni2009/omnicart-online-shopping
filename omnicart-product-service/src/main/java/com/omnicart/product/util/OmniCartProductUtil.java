package com.omnicart.product.util;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartProductUtil {

    public static String getErrorCode(String exceptionTag, String code) {
        return String.format(OmniCartProductConstant.ERROR_CODE, exceptionTag, code);
    }
}
