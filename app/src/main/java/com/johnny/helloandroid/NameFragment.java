package com.johnny.helloandroid;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String name = getArguments().getString("name");
        builder.setTitle("Welcome Alert:")
                .setMessage(String.format("Welcome %s!", name))
                .setIcon(android.R.drawable.star_on)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Alert dialog dismissed.", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
