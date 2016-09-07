package com.kokabi.p.navigationsample.Components;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.R;
import com.rey.material.widget.ProgressView;

public class CProgressDialog extends ProgressDialog {
    ProgressView progressView;
    TextView download_tv, upload_tv;
    boolean isCancelable = true;
    boolean isUpload;

    public CProgressDialog(boolean isCancelable, boolean isUpload) {
        super(AppController.getCurrentContext());
        this.isCancelable = isCancelable;
        this.isUpload = isUpload;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(isCancelable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_general_loading);

        progressView = (ProgressView) findViewById(R.id.progress_view);

        download_tv = (TextView) findViewById(R.id.download_tv);
        upload_tv = (TextView) findViewById(R.id.upload_tv);

        if (isUpload) {
            upload_tv.setVisibility(View.VISIBLE);
            download_tv.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        if (isCancelable) {
            dismiss();
        }
        super.onBackPressed();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
