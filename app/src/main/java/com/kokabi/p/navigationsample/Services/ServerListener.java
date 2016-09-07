package com.kokabi.p.navigationsample.Services;

import com.google.gson.JsonObject;

/**
 * Created by R.Miri on 8/13/2016.
 */

public interface ServerListener {

    void onSuccess(String methodName, JsonObject data);
    void onFailure(String methodName, String message);

}
