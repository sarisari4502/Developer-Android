package com.example.implicitintentreceiver;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * The ImplicitIntentsReceiver app registers itself for implicit intents
 * that come from browsable links (URLs) with the scheme:http and
 * host:developer.android.com (see AndroidManifest.xml).
 * <p>
 * If it receives that intent, the app just prints the incoming URI
 * to a TextView.
 * <p>
 * ImplicitIntentsReceiver is intended to be used in conjunction with
 * the ImplicitIntents app, but will receive a matching implicit intent
 * from any app (for example, a link in an email.)
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String uri_string = getString(R.string.uri_label) + uri.toString();
            TextView textView = findViewById(R.id.text_uri_message);
            textView.setText(uri_string);
        }
    }
}
