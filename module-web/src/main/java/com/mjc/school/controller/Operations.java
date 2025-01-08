package com.mjc.school.controller;

public enum Operations {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    CREATE_NEWS(3, "Create news."),
    UPDATE_NEWS(4, "Update news."),
    DELETE_NEWS_BY_ID(5, "Delete news by id."),

    GET_ALL_AUTHORS(6, "Get all authors."),
    GET_AUTHOR_BY_ID(7, "Get authors by id."),
    CREATE_AUTHOR(8, "Create authors."),
    UPDATE_AUTHOR(9, "Update authors."),
    DELETE_AUTHOR_BY_ID(10, "Delete author by id."),

    GET_ALL_TAGS(11, "Get all tags."),
    GET_TAG_BY_ID(12, "Get tag by id."),
    CREATE_TAG(13, "Create tag."),
    UPDATE_TAG(14, "Update tag."),
    DELETE_TAG_BY_ID(15, "Delete tag by id."),

    EXIT(0, "Exit.");

    private final int operationNumber;
    private final String operation;

    Operations(int i, String operation) {
        this.operationNumber = i;
        this.operation = operation;
    }

    public String getOperation() {
        return "Operation: " + operation;
    }

    public String getOperationWithNumber(){
        return operationNumber + ". " +operation;
    }
}