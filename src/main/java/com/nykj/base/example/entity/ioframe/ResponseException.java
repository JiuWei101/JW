package com.nykj.base.example.entity.ioframe;

/**
 * Created by cq on 3/30/16.
 */
public class ResponseException extends Exception {

    private ResponseCode responseCode;

    public ResponseException(ResponseCode response) {
        this.responseCode = response;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
