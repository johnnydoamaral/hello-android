package com.johnny.helloandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FoodFragment extends DialogFragment {

    public static interface Rateable {
        void modifyRating(String foodName, int amount);
    }

    private Rateable rater;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        rater = (Rateable) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String foodName = getArguments().getString("foodName");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_food_rating_dialog).setIcon(android.R.drawable.star_on)
                .setMessage(String.format("Please provide a rating for %s:", foodName))
                .setNegativeButton("Negative, -1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rater.modifyRating(foodName, -1);
                    }
                })
                .setNeutralButton("Neutral, 0", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Neutral vote, rating remains the same.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Positive, +1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rater.modifyRating(foodName, 1);
                    }
                });
        return builder.create();
    }
}
