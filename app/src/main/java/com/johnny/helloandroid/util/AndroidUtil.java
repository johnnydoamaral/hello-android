package com.johnny.helloandroid.util;

import android.app.Activity;

public class AndroidUtil {

    public static void displayActionBarBackButton(Activity activity) {
        if (activity.getActionBar() != null)
            activity.getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
