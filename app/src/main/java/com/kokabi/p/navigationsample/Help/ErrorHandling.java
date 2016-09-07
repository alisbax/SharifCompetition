package com.kokabi.p.navigationsample.Help;

import com.kokabi.p.navigationsample.Objects.ServerObj;

public class ErrorHandling {

    String message;
    int statuesCode;

    public ErrorHandling() {
    }

    public String ErrorCode(ServerObj getServerObj) {

        message = getServerObj.getMessage();
        statuesCode = getServerObj.getStatus();

        if (statuesCode != 200) {
            switch (statuesCode) {
                case 500:
                    break;
            }
        } else {
            message = "OK";
        }
        return message;
    }
}
