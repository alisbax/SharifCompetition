package com.kokabi.p.navigationsample.Help;

import android.content.Intent;

import com.kokabi.p.navigationsample.Activities.SplashActivity;
import com.kokabi.p.programmingmarathon1.R;

/**
 * Created by P.kokabi on 8/31/2016.
 */
public class AddShortCut {
    public AddShortCut() {
        Intent shortcutIntent = new Intent(AppController.getCurrentContext(), SplashActivity.class);

        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent installIntent = new Intent();
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "آزمون باز");
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE
                , Intent.ShortcutIconResource.fromContext(AppController.getCurrentContext(), R.mipmap.ic_launcher));
        int flags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;
        shortcutIntent.addFlags(flags);
        installIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        AppController.getCurrentContext().sendBroadcast(installIntent);

        Constants.isShortcutCreated = true;
        Constants.savePreferences();

    }
}
