package com.kokabi.p.navigationsample.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kokabi.p.navigationsample.DB.DataBase;
import com.kokabi.p.navigationsample.EventBuss.GeneralMSB;
import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.R;

import de.greenrobot.event.EventBus;

public class SampleFragment extends Fragment implements View.OnClickListener {

    Context context;
    DataBase db;

    CoordinatorLayout mainContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sample, container, false);

        context = container.getContext();
        EventBus.getDefault().register(this);
        AppController.setCurrentContext(context);
        db = new DataBase();
        mainContent = (CoordinatorLayout) v.findViewById(R.id.mainContent);

        findViews(v);


        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {

    }

    private void findViews(View v) {

        setOnClick();
    }

    private void setOnClick() {
    }

    public void onEvent(final GeneralMSB event) {
        switch (event.getMessage()) {
            case "":

                break;
        }
    }
}
