package com.kokabi.p.navigationsample.Help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.kokabi.p.programmingmarathon1.BuildConfig;

public class Constants {

    static SharedPreferences pref;
    public static String TAG = "=========";

    /*Setting Parameters*/
    public static boolean isShortcutCreated = false;
    public static boolean initialData = false;
    public static int versionCode = BuildConfig.VERSION_CODE;

    public static String token = "";
    public static String URL_WEBSITE = "http://192.168.1.220:8090/mobile/";

    /*Fonts*/
    public interface font {
        String SANS = "fonts/sans.ttf";
        String SANS_MEDIUM = "fonts/sans_medium.ttf";
    }

    public static void loadPreferences() {
        pref = AppController.getCurrentContext().getSharedPreferences("i", Context.MODE_PRIVATE);

        Constants.isShortcutCreated = pref.getBoolean(GS.isShortcutCreated, Constants.isShortcutCreated);
        Constants.initialData = pref.getBoolean(GS.isDataLoaded, Constants.initialData);
        Constants.versionCode = pref.getInt(GS.versionCode, Constants.versionCode);
    }

    public static void savePreferences() {
        pref = AppController.getCurrentContext().getSharedPreferences("i", Context.MODE_PRIVATE);

        pref.edit().putBoolean(GS.isShortcutCreated, Constants.isShortcutCreated).apply();
        pref.edit().putBoolean(GS.isDataLoaded, Constants.initialData).apply();
        pref.edit().putInt(GS.versionCode, Constants.versionCode).apply();
    }

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    /*SnackBar Actions*/
    public interface SNACK {
        int ERROR = 0;
        int WARNING = 1;
        int SUCCESS = 2;
    }

    public static boolean isTablet() {
        return (AppController.getCurrentContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void preventRestart() {
        if (!AppController.getCurrentActivity().isTaskRoot()
                && AppController.getCurrentActivity().getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && AppController.getCurrentActivity().getIntent().getAction() != null
                && AppController.getCurrentActivity().getIntent().getAction().equals(Intent.ACTION_MAIN)) {
            AppController.getCurrentActivity().finish();
        }
    }

    public static boolean isOnline() {
        int NewStatus = NetworkUtil.getConnectivityStatus(AppController.getCurrentContext());
        return NewStatus != 0;
    }

    public static void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) AppController.getCurrentActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = AppController.getCurrentActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(AppController.getCurrentActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}