package com.project.markpollution.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.project.markpollution.R;

/**
 * Created by Le Duc Thanh on 11/13/2016.
 * Developer Android
 * m.me/leducthanh93
 */

public class AlertDialogManager {
    public void showAlertDialog(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);
        alertDialog.setIcon(R.mipmap.ic_launcher);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
