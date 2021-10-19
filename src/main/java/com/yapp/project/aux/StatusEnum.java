package com.yapp.project.aux;

public enum StatusEnum {
    SOCIAL_OK(200, "SOCIAL_OK"),
    ACCOUNT_OK(200, "ACCOUNT_OK"),
    GROUP_OK(200, "GROUP_OK"),
    TOKEN_OK(200, "TOKEN_OK"),
    SAYING_OK(200, "SAYING_OK"),
    MISSION_OK(200, "MISSION_OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    ROUTINE_OK(200, "OK"),
    ROUTINE_BAD_REQUEST(400, "BAD_REQUEST"),
    ROUTINE_NOT_FOUND(404, "ROUTINE_NOT_FOUND"),
    REFRESH_TOKEN_NOT_IN_REDIS(404,"REFRESH_TOKEN_NOT_IN_REDIS"),
    EMAIL_BAD_REQUEST(400,"EMAIL_BAD_REQUEST"),
    NICKNAME_BAD_REQUEST(400,"NICKNAME_BAD_REQUEST"),
    USER_NOT_FOUND(404,"USER_NOT_FOUND"),
    PASSWORD_BAD_REQUEST(400,"PASSWORD_BAD_REQUEST"),
    REFRESH_BAD_REQUEST(400,"REFRESH_BAD_REQUEST"),
    TOKEN_BAD_REQUEST(400,"TOKEN_BAD_REQUEST"),
    SAYING_BAD_REQUEST(400,"SAYING_BAD_REQUEST"),
    SAYING_NOT_FOUND(404,"SAYING_NOT_FOUND"),
    MISSION_NOT_FOUND(404, "MISSION_NOT_FOUND"),
    MISSION_ALREADY_EXISTS(400, "MISSION_ALREADY_EXISTS");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
