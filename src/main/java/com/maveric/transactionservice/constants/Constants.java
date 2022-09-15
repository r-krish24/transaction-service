package com.maveric.transactionservice.constants;

import java.time.LocalDateTime;


public class Constants {

    private Constants() {
    }

    public static final String TRANSACTION_NOT_FOUND_CODE="404";
    public static final String TRANSACTION_NOT_FOUND_MESSAGE="Transaction not found for Id-";
    public static final String TRANSACTION_DELETED_SUCCESS="Transaction deleted successfully.";
    public static final String METHOD_NOT_ALLOWED_CODE="405";
    public static final String METHOD_NOT_ALLOWED_MESSAGE="Method Not Allowed. Kindly check the Request URL and Request Type.";
    public static final String BAD_REQUEST_CODE="400";
    public static final String INCORRECT_URL_CODE="404";
    public static final String INCORRECT_URL_MESSAGE="The server can not find the requested resource.";

    public static final String INVALID_INPUT_TYPE="Type should be - 'CREDIT' or 'DEBIT'";

    public static final String INVALID_INPUT_MESSAGE = "Kindly re-check the inputs provided";


}
