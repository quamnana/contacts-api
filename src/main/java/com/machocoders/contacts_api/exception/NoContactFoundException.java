package com.machocoders.contacts_api.exception;

public class NoContactFoundException extends RuntimeException {
    public NoContactFoundException(String id) {
        super("The id '" + id + "' does not exist in our records");
    }

}
