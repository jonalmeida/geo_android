package io.evilolive.pseudochat;

/**
 * Created by jonathan on 2014-05-19.
 */
public class ResponseHandler {
    private static ResponseHandler ourInstance = new ResponseHandler();

    public static ResponseHandler getInstance() {
        return ourInstance;
    }

    private ResponseHandler() {
    }
}
