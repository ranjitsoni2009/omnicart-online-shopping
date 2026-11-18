package com.omnicart.product.util;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class OmniCartProductConstant {

    public static final String BEARER = "Bearer";
    public static final String INSUFFICIENT_INVENTORY_ERROR_MSG = "Insufficient inventory available";
    public static final String INSUFFICIENT_INVENTORY_ERROR_CODE = "3011";
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
    public static String PRODUCT_VARIANT_ALREADY_EXIST_ERROR_CODE = "3005";
    public static String PRODUCT_VARIANT_NOT_FOUND_ERROR_CODE = "3006";
    public static String INVENTORY_ALREADY_EXIST_ERROR_CODE = "3007";
    public static String INVENTORY_NOT_FOUND_ERROR_CODE = "3008";
    public static String INVENTORY_ACCESS_DENIED_ERROR_CODE = "3009";
    public static String INVALID_INVENTORY_QUANTITY_ERROR_CODE = "3010";

    public static String BRAND_ALREADY_EXIST_ERROR_MSG = "Brand already exist";
    public static String BRAND_NOT_FOUND = "Brand not found";
    public static String CATEGORY_ALREADY_EXIST_ERROR_MSG = "Brand already exist";
    public static String CATEGORY_NOT_FOUND = "Brand not found";
    public static String PRODUCT_ALREADY_EXIST_ERROR_MSG = "Product already exist";
    public static String PRODUCT_NOT_FOUND = "Product not found";
    public static String PRODUCT_VARIANT_ALREADY_EXIST_ERROR_MSG = "Product Variant already exist";
    public static String PRODUCT_VARIANT_NOT_FOUND = "Product Variant not found";
    public static String INVENTORY_ALREADY_EXIST_ERROR_MSG = "Inventory already exists for this product variant and vendor";
    public static String INVENTORY_NOT_FOUND = "Inventory not found";
    public static String INVENTORY_ACCESS_DENIED = "You are not allowed to access this inventory";
    public static String INVALID_INVENTORY_QUANTITY = "Inventory quantity cannot be less than reserved quantity";



    /* Microservice level Error Codes */
}
