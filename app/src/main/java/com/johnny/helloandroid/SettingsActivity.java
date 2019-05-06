package com.johnny.helloandroid;

import android.app.Activity;
import android.os.Bundle;

import com.johnny.helloandroid.util.AndroidUtil;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidUtil.displayActionBarBackButton(this);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
    }
}
