package com.mjc.school.service.exceptions;

public enum ServiceErrorCode {

    NEWS_ID_DOES_NOT_EXIST("01", "News with id: %d isn`t exist"),
    AUTHOR_ID_NOT_FOUND("02", "Author with id: %d isn`t exist"),
    INCORRECT_NEWS_TITLE("03", "News title can not be less than 5 characters and more than 30. Your title length is: %d"),
    INCORRECT_NEWS_CONTENT("04", "Content can not be less than 5 characters and more than 255. Your content length is: %d"),
    INCORRECT_AUTHOR_NAME("05", "Author name can not be less than 3 characters and more than 15. Your name length is: %d"),
    NULL_AUTHOR_ID("06", "Author id can`t be null");

    private final String errorMessage;

    ServiceErrorCode(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorMessage;
    }
}
