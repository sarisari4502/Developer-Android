package id.ac.poliban.sari.e020320115.homework_10b;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    private EditText mInstanceStateEditText;
    private EditText mSharedPrefEditText;
    private EditText mRoomEditText;
    private TextView mInstanceStateTextView;
    private TextView mSharedPrefTextView;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = "id.ac.poliban.sari.e020320115.homework_10b";
    private String instanceStateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Data Storage Types");


        mInstanceStateEditText = findViewById(R.id.instanceStateEditText);
        mSharedPrefEditText = findViewById(R.id.sharedPrefEditText);
        mRoomEditText = findViewById(R.id.roomEditText);
        mInstanceStateTextView = findViewById(R.id.instanceStateTextView);
        mSharedPrefTextView = findViewById(R.id.sharedPrefTextView);


        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                adapter.setWords(words);
            }
        });


        mSharedPrefTextView.setText("Text Saved: " + sharedPreferences.getString("sharedPrefString", ""));


        if (savedInstanceState!= null){
            mInstanceStateTextView.setText("Text Saved: " + savedInstanceState.getString("instanceStateKey", ""));
        }
    }

    public void onSaveClick(View view) {

        instanceStateText = mInstanceStateEditText.getText().toString();
        mInstanceStateTextView.setText("Text Saved: " + instanceStateText);

        String sharedPrefText = mSharedPrefEditText.getText().toString();
        mSharedPrefTextView.setText("Text Saved: " + sharedPrefText);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sharedPrefString", sharedPrefText).apply();


        String roomText = mRoomEditText.getText().toString();
        Word word = new Word(roomText);
        mWordViewModel.insert(word);


        mInstanceStateEditText.setText("");
        mSharedPrefEditText.setText("");
        mRoomEditText.setText("");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("instanceStateKey", instanceStateText);
    }
}
