package com.kokabi.p.navigationsample.EventBuss;

/**
 * Created by Payam on 10/3/15.
 */
public class GeneralMSB {

    String title, message;

    public GeneralMSB(String message) {
        this.message = message;
    }

    public GeneralMSB(String message, String title) {
        this.message = message;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}