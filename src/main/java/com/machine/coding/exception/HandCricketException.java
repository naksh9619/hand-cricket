package com.machine.coding.exception;

public class HandCricketException extends RuntimeException {

    public HandCricketException(Throwable throwable) {
        super(throwable);
    }

    public HandCricketException(String msg) {
        super(msg);
    }

    public HandCricketException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
