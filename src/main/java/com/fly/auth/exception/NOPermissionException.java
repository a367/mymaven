package com.fly.auth.exception;

public class NOPermissionException extends Exception {

    public NOPermissionException() {
        super("无权访问");
    }


    public NOPermissionException(String message) {
        super(message);
    }
}
