package com.kokabi.p.navigationsample.Help;

/**
 * Created by Payam on 8/10/15.
 */
public class Validation {
    public static boolean phoneValidation(String number) {
        if (!number.equals("")) {
            if (number.length() >= 11) {
                if (number.substring(0, 4).contains("091") || number.substring(0, 4).contains("91") || number.substring(0, 4).contains("090")
                        || number.substring(0, 4).contains("90") || number.substring(0, 4).contains("093") || number.substring(0, 4).contains("93")
                        || number.substring(0, 4).contains("092") || number.substring(0, 4).contains("92"))
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }
}