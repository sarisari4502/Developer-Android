package com.example.helloconstraint;

import android.graphics.Color;
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

        TextView tvMessage = findViewById(R.id.tv_message);
        Button btToast = findViewById(R.id.button_toast);
        Button btZero = findViewById(R.id.button_zero);
        Button btCount = findViewById(R.id.button_count);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("mCount");
            tvMessage.setText(String.valueOf(mCount));
        }
        btToast.setOnClickListener(v -> {
            mCount = 0;
            tvMessage.setText(String.valueOf(mCount));
            Toast.makeText(this, "Hello Toast", Toast.LENGTH_SHORT).show();
        });
        btZero.setOnClickListener(v -> {
            if ((mCount % 2) == 0){
                btZero.setBackgroundColor(Color.CYAN);
            }
            if ((mCount == 0)) {
                btZero.setBackgroundColor(Color.GRAY);
            }
            if ((mCount % 2) != 0) {
                btZero.setBackgroundColor(Color.GREEN);
            }
        });
        btCount.setOnClickListener(v -> {
            mCount++;
            tvMessage.setText(String.valueOf(mCount));
        });
    }
    @Override
    protected  void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mCount", mCount);
    }
}