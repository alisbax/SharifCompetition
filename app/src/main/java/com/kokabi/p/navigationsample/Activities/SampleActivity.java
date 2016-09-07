package com.kokabi.p.navigationsample.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.kokabi.p.navigationsample.Components.CActivity;
import com.kokabi.p.navigationsample.Components.CProgressDialog;
import com.kokabi.p.navigationsample.EventBuss.GeneralMSB;
import com.kokabi.p.navigationsample.Help.Constants;
import com.kokabi.p.navigationsample.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class SampleActivity extends CActivity {

    AppCompatImageButton back_imgbtn;
    /*Loading Parameters*/
    LinearLayout placeHolder, progress_ly, reload_ly, reload_btn;

    /*Activity Values*/
    int idCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        initialActivity();
        EventBus.getDefault().register(this);

        findViews();

        new CProgressDialog(false, true).show();

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            idCategory = bundle.getInt("idCat", 0);
//        }

//        new ServerResponse()
//                .setCall("signUp", ApiClient.getClient()
//                        .create(SampleApiInterface.class)
//                        .signUp(serviceParameters()), SampleActivity.this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Constants.freeMemory();
    }

    @Override
    public void onSuccess(String methodName, JsonObject data) {
        switch (methodName) {
            case "":

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_imgbtn:
                finish();
                break;
        }
    }

    @Override
    public void findViews() {
        placeHolder = (LinearLayout) findViewById(R.id.loadingFirstTime);
        getLayoutInflater().inflate(R.layout.loading_layout, placeHolder);
        progress_ly = (LinearLayout) placeHolder.findViewById(R.id.progress_ly);
        reload_ly = (LinearLayout) placeHolder.findViewById(R.id.reload_ly);
        reload_btn = (LinearLayout) placeHolder.findViewById(R.id.reload_btn);
        reload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload_ly.setVisibility(View.GONE);
                progress_ly.setVisibility(View.VISIBLE);
            }
        });

        back_imgbtn = (AppCompatImageButton) findViewById(R.id.back_imgbtn);

        setOnClickListenerForViews();
    }

    @Override
    public void setOnClickListenerForViews() {
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

    //Event for EvenBus
    public void onEvent(final GeneralMSB event) {
        switch (event.getMessage()) {
            case "testAnswered":
                break;
        }
    }
}
