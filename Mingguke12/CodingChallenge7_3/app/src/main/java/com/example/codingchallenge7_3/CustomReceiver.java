package com.example.codingchallenge7_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        String intentAction = intent.getAction();
        if (intentAction != null) {
            Toast.makeText(context, "Headset plugged or unplugged", Toast.LENGTH_LONG).show();
        }
    }
}