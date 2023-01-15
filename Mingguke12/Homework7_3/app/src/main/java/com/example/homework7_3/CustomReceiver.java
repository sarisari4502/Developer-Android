package com.example.homework7_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String EXTRA_RANDOM_NUMBER = "id.ac.poliban.mi.va.sherli.homework073_sherli.RANDOM_NUMBER";
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        String intentAction = intent.getAction();
        String stringNumber = intent.getStringExtra(EXTRA_RANDOM_NUMBER);
        int number = Integer.valueOf(stringNumber);
        if (intentAction != null) {
            String toastMessage = context.getResources().getString(R.string.unknown_intent_action);
            switch (intentAction){
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = context.getResources().getString(R.string.custom_broadcast_received);
                    break;
            }
            Toast.makeText(context, toastMessage + " Square of the number is " + number * number, Toast.LENGTH_LONG).show();
        }
    }
}