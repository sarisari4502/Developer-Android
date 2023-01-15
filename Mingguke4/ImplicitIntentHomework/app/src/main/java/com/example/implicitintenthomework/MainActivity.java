package com.example.implicitintenthomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public class MainActivity extends AppCompatActivity {

        /**
         * The ImplicitIntents app contains three buttons for sending implicit intents:
         * - Open a URL in a browser
         * - Find a location on a map
         * - Share a text string
         */
        public class MainActivity extends AppCompatActivity {
            private EditText mWebsiteEditText;
            private EditText mLocationEditText;
            private EditText mShareTextEditText;

            /**
             * Initializes the activity.
             *
             * @param savedInstanceState The current state data.
             */
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                mWebsiteEditText = findViewById(R.id.website_edittext);
                mLocationEditText = findViewById(R.id.location_edittext);
                mShareTextEditText = findViewById(R.id.share_edittext);
                //mButtonPicture = findViewById(R.id.button_picture);
            }

            /**
             * Handles the onClick for the "Open Website" button. Gets the URI
             * from the edit text and sends an implicit intent for that URL.
             *
             * @param view The view (Button) that was clicked.
             */

            public void openWebsite(View view) {
                // Get the URL text.
                String url = mWebsiteEditText.getText().toString();
                // Parse the URI and create the intent.
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                // Find and activity to hand the intent and start that activity.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntentsHomework", "Can't handle this!");
                }
            }

            /**
             * Handles the onClick for the "Open Location" button. Gets the location
             * text from the edit text and sends an implicit intent for that location.
             * <p>
             * The location text can be any searchable geographic location.
             *
             * @param view The view (Button) that was clicked.
             */
            public void openLocation(View view) {
                // Get the string indicating a location. Input is not validated; it is
                // passed to the location handler intact.
                String loc = mLocationEditText.getText().toString();
                // Parse the location and create the intent.
                Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
                // Find an activity to handle the intent, and start that activity.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntentsHomework", "Can't handle this intent!");
                }
            }

            /**
             * Handles the onClick for the "Share This Text" button. The
             * implicit intent here is created by the  {@link androidx.core.app.ShareCompat.IntentBuilder}
             * class. An app chooser appears with the available options for sharing.
             * <p>
             * ShareCompat.IntentBuilder is from the v4 Support Library.
             *
             * @param view The view (Button) that was clicked.
             */
            public void shareText(View view) {
                String txt = mShareTextEditText.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        // Yang Activitymeluncurkan share ini Intent( this).
                        .from(this)
                        // Jenis MIME item yang akan dibagikan.
                        .setType(mimeType)
                        // Judul yang muncul di pemilih aplikasi sistem.
                        .setChooserTitle(R.string.share_text_with)
                        // Teks sebenarnya yang akan dibagikan.
                        .setText(txt)
                        //Tampilkan pemilih aplikasi sistem dan kirim file Intent.
                        .startChooser();
            }

            public void takePicture(View view) {
                // Parse the takePicture and create the intent.
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Find and activity to hand the intent and start that activity.
                if (takePicture.resolveActivity(getPackageManager()) != null) {
                    startActivity(takePicture);
                }
            }