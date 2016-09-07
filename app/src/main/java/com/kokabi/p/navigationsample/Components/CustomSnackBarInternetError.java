package com.kokabi.p.navigationsample.Components;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.R;


public class CustomSnackBarInternetError {
    public Snackbar show(View mainContent) {
        final Snackbar snackbar = Snackbar.make(mainContent, "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        LayoutInflater inflater = LayoutInflater.from(AppController.getCurrentContext());
        View snackView = inflater.inflate(R.layout.snack_internet_error, null);

        Button gprs_btn = (Button) snackView.findViewById(R.id.gprs_btn);
        Button wifi_btn = (Button) snackView.findViewById(R.id.wifi_btn);

        gprs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ComponentName cn = new ComponentName("com.android.phone", "com.android.phone.MobileNetworkSettings");
                final Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                intent.addCategory(Intent.ACTION_MAIN);
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AppController.getCurrentContext().startActivity(intent);
                snackbar.dismiss();
            }
        });

        wifi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.getCurrentContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                snackbar.dismiss();
            }
        });

        layout.addView(snackView);
        layout.setBackgroundColor(ContextCompat.getColor(AppController.getCurrentContext(), R.color.white));
        snackbar.show();
        return snackbar;
    }
}
