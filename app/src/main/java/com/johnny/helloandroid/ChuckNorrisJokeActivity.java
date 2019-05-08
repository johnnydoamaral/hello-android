package com.johnny.helloandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.johnny.helloandroid.service.JokeService;
import com.johnny.helloandroid.util.AndroidUtil;

public class ChuckNorrisJokeActivity extends Activity {

    private Button getJokeButton;
    private TextView jokeContainer;
    private JokeService service = new JokeService();
    private AsyncTask<Void, Void, String> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck_norris_joke);
        AndroidUtil.displayActionBarBackButton(this);

        getJokeButton = findViewById(R.id.btn_new_joke);
        jokeContainer = findViewById(R.id.tv_joke);

        getJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new JokeTask().execute();
            }
        });
    }

    private class JokeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return service.getRandomJoke();
        }
        @Override
        protected void onPostExecute(String result) {
            jokeContainer.setText(result);
        }
    }
}
