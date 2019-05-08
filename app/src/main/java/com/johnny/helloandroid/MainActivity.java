package com.johnny.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String MAIN_ACTIVITY_EXTRA = "name";
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        textView = findViewById(R.id.tv_label);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText("Hello " + sharedPreferences.getString("username", "World"));
        editText = findViewById(R.id.et_name);

        Button helloButton = findViewById(R.id.btn_name);
        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayHello(v);
            }
        });

        Button jokeButton = findViewById(R.id.btn_main_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuckNorrisJokeActivity.class));
            }
        });
    }

    public void sayHello(View v) {
        String name = editText.getText().toString();
        goToWelcomePage(name);
    }

    public void sayHi(View v) {
        String name = editText.getText().toString();
        goToWelcomePage(name);
    }

    private void goToWelcomePage(String name) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(MAIN_ACTIVITY_EXTRA, name);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_joke:
                startActivity(new Intent(this, ChuckNorrisJokeActivity.class));
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Overriding the below methods only to log the different states of the application, depending on the device state

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
