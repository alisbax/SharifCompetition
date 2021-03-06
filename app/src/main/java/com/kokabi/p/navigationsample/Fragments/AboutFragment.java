package com.kokabi.p.navigationsample.Fragments;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.R;


public class AboutFragment extends Fragment {

    Context context;

    TextView version_tv;

    /*Fragment Values*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);

        context = container.getContext();
        AppController.setCurrentContext(context);

        findViews(v);

        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = "نسخه :     " + (info != null ? info.versionName : null);
        version_tv.setText(version);

        return v;
    }

    private void findViews(View v) {
        version_tv = (TextView) v.findViewById(R.id.version_tv);
    }

}
