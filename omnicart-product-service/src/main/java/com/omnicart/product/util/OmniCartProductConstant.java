package com.omnicart.product.util;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartProductConstant {

    public static final String BEARER = "Bearer";
    public static String ERROR_CODE = "OMNI_PRODUCT_%s_%s";

    /* Request level Error Codes */
    public static String REQUEST_EXCEPTION_TAG = "REQ";
    public static String REQUIRED_FIELD_ERROR_CODE = "001";
    public static String SESSION_TIMEOUT_ERROR_CODE = "1002";

    /* Business exception level Error Codes & Message */
    public static String BUSINESS_EXCEPTION_TAG = "BUS";

    public static String BRAND_ALREADY_EXIST_ERROR_CODE = "3001";
    public static String BRAND_NOT_FOUND_ERROR_CODE = "3002";
    public static String CATEGORY_ALREADY_EXIST_ERROR_CODE = "3003";
    public static String CATEGORY_NOT_FOUND_ERROR_CODE = "3004";
    public static String PRODUCT_ALREADY_EXIST_ERROR_CODE = "3003";
    public static String PRODUCT_NOT_FOUND_ERROR_CODE = "3004";

    public static String BRAND_ALREADY_EXIST_ERROR_MSG = "Brand already exist";
    public static String BRAND_NOT_FOUND = "Brand not found";
    public static String CATEGORY_ALREADY_EXIST_ERROR_MSG = "Brand already exist";
    public static String CATEGORY_NOT_FOUND = "Brand not found";
    public static String PRODUCT_ALREADY_EXIST_ERROR_MSG = "Product already exist";
    public static String PRODUCT_NOT_FOUND = "Product not found";


    /* Microservice level Error Codes */
}
