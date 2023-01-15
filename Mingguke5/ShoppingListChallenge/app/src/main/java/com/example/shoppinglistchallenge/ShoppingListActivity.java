package com.example.shoppinglistchallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;

import com.example.shoppinglistchallenge.databinding.ActivityShoppingListBinding;

public class ShoppingListActivity extends AppCompatActivity {

    public final static String ITEM_TAG = "com.example.shoppinglistchallenge.EXTRA.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShoppingListBinding shoppingListBinding = ActivityShoppingListBinding.inflate(getLayoutInflater());
        setContentView(shoppingListBinding.getRoot());

        Button[] mButton = new Button[]{
                shoppingListBinding.button,
                shoppingListBinding.button2,
                shoppingListBinding.button3,
                shoppingListBinding.button4,
                shoppingListBinding.button5,
                shoppingListBinding.button6,
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
