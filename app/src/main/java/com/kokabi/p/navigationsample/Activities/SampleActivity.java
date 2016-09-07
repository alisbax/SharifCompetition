package com.kokabi.p.navigationsample.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.kokabi.p.navigationsample.EventBuss.GeneralMSB;
import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.Help.Constants;
import com.kokabi.p.navigationsample.Services.ApiClient;
import com.kokabi.p.navigationsample.Services.Sample.SampleApiInterface;
import com.kokabi.p.navigationsample.Services.ServerListener;
import com.kokabi.p.navigationsample.Services.ServerResponse;
import com.kokabi.p.programmingmarathon1.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class SampleActivity extends AppCompatActivity implements ServerListener, View.OnClickListener {

    Context context;
    public static SampleActivity instance = null;

    CoordinatorLayout mainContent;
    AppCompatImageButton back_imgbtn;

    /*Activity Values*/
    int idCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        context = this;
        AppController.setActivityContext(this, this);
        Constants.preventRestart();
        EventBus.getDefault().register(this);
        Constants.preventRestart();
        mainContent = (CoordinatorLayout) findViewById(R.id.mainContent);

        findViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idCategory = bundle.getInt("idCat", 0);
        }

        new ServerResponse()
                .setCall("signUp", ApiClient.getClient()
                        .create(SampleApiInterface.class)
                        .signUp(serviceParameters()), SampleActivity.this);
    }

    @Override
    public void onPause() {
        //To be able to finish the activity from another page
        instance = this;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Constants.freeMemory();
    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    @Override
    public void onSuccess(String methodName, JsonObject data) {
        switch (methodName) {
            case "":

                break;
        }
    }

    @Override
    public void onFailure(String methodName, String message) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_imgbtn:
                finish();
                break;
        }
    }

    private void findViews() {
        back_imgbtn = (AppCompatImageButton) findViewById(R.id.back_imgbtn);

        setOnClick();
    }

    private void setOnClick() {
        back_imgbtn.setOnClickListener(this);
    }

    public HashMap<String, String> serviceParameters() {
        HashMap<String, String> entity = new HashMap<>();
        try {
            entity.put("firstName", "");
            entity.put("lastName", "");

            Log.i("SendServer", entity.toString());
        } catch (Exception e) {
            Log.i(Constants.TAG, e.toString());
        }
        return entity;
    }

    public void onEvent(final GeneralMSB event) {
        switch (event.getMessage()) {
            case "testAnswered":
                break;
        }
    }
}
