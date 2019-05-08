package com.johnny.helloandroid;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.johnny.helloandroid.util.AndroidUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodQuizActivity extends Activity implements FoodFragment.Rateable {

    private ListView listViewFoods;
    private static List<String> foods = new ArrayList<>();
    private Map<String, Integer> ratings = new HashMap<>();
    private String foodNameClicked;

    static {
        foods.add("Pizza");
        foods.add("Barbecue");
        foods.add("Hot-Dog");
        foods.add("Pasta");
        foods.add("Cheeseburger");
        foods.add("French fries");
        foods.add("Meat Loaf");
        foods.add("Burrito");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quiz);
        AndroidUtil.displayActionBarBackButton(this);
        ArrayAdapter<String> foodItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foods);
        listViewFoods = findViewById(R.id.lv_foods);
        listViewFoods.setAdapter(foodItems);
        bindListViewItemClick();
    }

    private void bindListViewItemClick() {
        listViewFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment fragment = new FoodFragment();
                Bundle arguments = new Bundle();
                foodNameClicked = foods.get(position);
                arguments.putString("foodName", foodNameClicked);
                fragment.setArguments(arguments);
                fragment.show(getFragmentManager(), null);
            }
        });
    }

    @Override
    public void modifyRating(String foodName, int amount) {
        if (ratings.get(foodName) != null)
            ratings.put(foodName, ratings.get(foodName) + amount);
        else
            ratings.put(foodName, amount);
        Toast.makeText(this, String.format("%s has rating of %d", foodName, ratings.get(foodName)), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] foodNames = ratings.keySet().toArray(new String[ratings.keySet().size()]);
        outState.putStringArray("foodNames", foodNames);
        for (String foodName : foodNames)
            outState.putInt(foodName, ratings.get(foodName));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String[] foodNames = savedInstanceState.getStringArray("foodNames");
        for (String foodName : foodNames)
            ratings.put(foodName, savedInstanceState.getInt(foodName));
    }
}
