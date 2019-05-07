package com.johnny.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.johnny.helloandroid.util.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodQuizActivity extends Activity {

    private ListView listViewFoods;
    private static List<String> foods = new ArrayList<>();

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
        ArrayAdapter<String> foodItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foods);
        listViewFoods = findViewById(R.id.lv_foods);
        listViewFoods.setAdapter(foodItems);
        AndroidUtil.displayActionBarBackButton(this);
    }
}
