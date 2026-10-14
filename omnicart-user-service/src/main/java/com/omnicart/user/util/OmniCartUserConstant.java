package com.omnicart.user.util;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartUserConstant {

    public static String ERROR_CODE = "OMNI_USER_%s_%s";

    /* Request level Error Codes */
    public static String REQUEST_EXCEPTION_TAG = "REQ";
    public static String REQUIRED_FIELD_ERROR_CODE = "1001";
    public static String SESSION_TIMEOUT_ERROR_CODE = "1002";

    /* Business exception level Error Codes & Message */
    public static String BUSINESS_EXCEPTION_TAG = "BUS";
    public static String USER_ALREADY_REGISTERED_ERROR_CODE = "2001";
    public static String INCORRECT_CREDENTIALS_ERROR_CODE = "2002";
    public static String USER_NOT_FOUND_ERROR_CODE = "2003";

    public static String USER_ALREADY_REGISTERED_ERROR_MSG = "User already registered with given request";
    public static String USER_NOT_FOUND = "User not found";

    /* Microservice level Error Codes */
}
