package com.example.hello_toastchallange;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMessage = findViewById(R.id.show_count);
        Button btToast= findViewById(R.id.button_toast);
        Button btCount = findViewById(R.id.button_count);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("mCount");
            tvMessage.setText(String.valueOf(mCount));
        }

        btToast.setOnClickListener(v -> {
            mCount = 0;
            tvMessage.setText(this, "Hello Toast", Toast.LENGTH_SHORT).show();
        });

        btCount.setOnClickListener(v -> {
            mCount++;
            tvMessage.setText(String.valueOf(mCount));
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt("mCount", mCount);

    }
}