package com.johnny.helloandroid;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.johnny.helloandroid.dao.DatabaseAdapter;
import com.johnny.helloandroid.util.AndroidUtil;

import java.util.List;

public class WelcomeActivity extends Activity {

    public static final String TAG = WelcomeActivity.class.getSimpleName();
    private DatabaseAdapter adapter;
    private TextView nameTextView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        AndroidUtil.displayActionBarBackButton(this);

        String name = getIntent().getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);
        nameTextView = findViewById(R.id.tv_welcome);
        nameTextView.setText(String.format(getString(R.string.greeting), name));

        adapter = new DatabaseAdapter(this);
        adapter.open();

        if (!adapter.exists(name))
            adapter.insertName(name);

        populateNamesListView();

        DialogFragment fragment = new NameFragment();
        Bundle arguments = new Bundle();
        arguments.putString("name", name);
        fragment.setArguments(arguments);
        fragment.show(getFragmentManager(), null);
    }

    private void populateNamesListView() {
        final List<String> names = adapter.getAllNames();
        ListView listView = findViewById(R.id.lv_names);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

        bindListViewItemClick(names, listView);
    }

    private void bindListViewItemClick(final List<String> names, ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item at index " + position + " clicked");
                name = names.get(position);
                nameTextView.setText(String.format(getString(R.string.greeting), name));
                Toast.makeText(getApplicationContext(), name + " was clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
