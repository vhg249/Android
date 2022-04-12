package com.example.bth1.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;

import java.util.Calendar;

public class Utils {
    public static void openAlertDialog(Context context, Command command, boolean isHaveYesBtn, String title, String body) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(body);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        if(isHaveYesBtn) {
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    command.execute();
                }
            });
        }
        builder.create().show();
    }
}