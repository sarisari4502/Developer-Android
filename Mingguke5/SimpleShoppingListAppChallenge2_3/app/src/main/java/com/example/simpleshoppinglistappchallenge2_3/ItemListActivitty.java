package com.example.simpleshoppinglistappchallenge2_3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;


import android.os.Bundle;
import com.example.simpleshoppinglistappchallenge2_3.databinding.ActivityItemListBinding;

public class ItemListActivity extends AppCompatActivity {
    public final static String ITEM_TAG =
            "com.example.simpleshoppinglistappchallenge2_3.EXTRA.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemListBinding itemListBinding = ActivityItemListBinding.inflate(getLayoutInflater());
        setContentView(itemListBinding.getRoot());

        Button[] mButton = new Button[]{
                itemListBinding.button,
                itemListBinding.button2,
                itemListBinding.button3,
                itemListBinding.button4,
                itemListBinding.button5,
                itemListBinding.button6,
        };

        for (Button button : mButton) {
            button.setOnClickListener(v -> {
                Button btn = (Button) v;

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(ITEM_TAG, btn.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            });
        }
    }
}
