package com.kokabi.p.navigationsample.Help;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.splunk.mint.Mint;

public class AppController extends Application {

    private static Activity CurrentActivity = null;
    private static Context CurrentContext;

    @Override
    public void onCreate() {
        super.onCreate();
        /*Initial Crash Reporter*/
        Mint.initAndStartSession(this, "59f3692a");
    }

    public static void setCurrentContext(Context mCurrentContext) {
        AppController.CurrentContext = mCurrentContext;
    }

    public static void setActivityContext(Activity activity, Context context) {
        CurrentActivity = activity;
        CurrentContext = context;
    }

    public static Activity getCurrentActivity() {
        return CurrentActivity;
    }

    public static Context getCurrentContext() {
        return CurrentContext;
    }

}