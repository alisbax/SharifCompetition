package com.kokabi.p.navigationsample.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.WindowManager;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.Components.DialogGeneral;
import com.kokabi.p.navigationsample.Help.AddShortCut;
import com.kokabi.p.navigationsample.Help.Constants;
import com.kokabi.p.programmingmarathon1.R;


public class SplashActivity extends AppCompatActivity {

    Context context;

    /*Activity Values*/
    int SPLASH_TIME_OUT = 500;
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    String[] mPermission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_splash);

        context = this;
        AppController.setActivityContext(SplashActivity.this, this);

        Constants.loadPreferences();

        permissionCheck();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length == 2 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == MockPackageManager.PERMISSION_GRANTED) {
                welcomeScreen();
            } else {
                new DialogGeneral(getString(R.string.permissionDeniedTitle)
                        , getString(R.string.tryAgain), getString(R.string.ok), true, false) {
                    @Override
                    public void onConfirm() {
                        permissionCheck();
                    }

                    @Override
                    public void onCancel() {
                        welcomeScreen();
                    }
                }.show();
            }
        } else {
            welcomeScreen();
        }
    }

    private void welcomeScreen() {
        loadApplication();
        /*Create Shortcut on home screen*/
        if (!Constants.isShortcutCreated) {
            new AddShortCut();
        }
    }

    private void loadApplication() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    /*Permission Check*/
    private void permissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                if (ActivityCompat.checkSelfPermission(context, mPermission[0]) != MockPackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[1]) != MockPackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, mPermission, REQUEST_CODE_PERMISSIONS);
                } else {
                    welcomeScreen();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            welcomeScreen();
        }
    }
}