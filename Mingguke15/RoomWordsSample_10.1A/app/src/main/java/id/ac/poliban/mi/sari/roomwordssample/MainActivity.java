package id.ac.poliban.mi.sari.roomwordssample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private WordListAdapter mAdapter;
    private WordViewModel mWordViewModel;

    public static final String UPDATE_WORD = "word_to_be_updated";
    public static final String WORD_ID = "word_id";


    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Room Words Sample_puteri");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        mRecylerView = findViewById(R.id.recyclerview);

        mAdapter = new WordListAdapter(this);

        mRecylerView.setAdapter(mAdapter);

        mRecylerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);


        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {

            @Override
            public void onChanged(@Nullable final List<Word> words) {

                mAdapter.setWords(words);
            }
        });


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }



            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                Word myWord = mAdapter.getWordAtPosition(position);
                Toast.makeText(MainActivity.this, "Deleting " + myWord.getWord(), Toast.LENGTH_LONG).show();


                mWordViewModel.deleteWord(myWord);
            }
        });

        helper.attachToRecyclerView(mRecylerView);

        mAdapter.setOnItemClickListener(new WordListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Word myWord = mAdapter.getWordAtPosition(position);

                updateWord(myWord);
            }
        });
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));

            mWordViewModel.insert(word);
        }
        else if (requestCode == UPDATE_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            String word = data.getStringExtra(NewWordActivity.EXTRA_REPLY);
            int id = data.getIntExtra(NewWordActivity.EXTRA_WORD_ID, -1);

            if(id != -1)
                mWordViewModel.updateWord(new Word(word, id));
            else
                Toast.makeText(this, "Unable to update text", Toast.LENGTH_SHORT).show();
            Log.d("Word: ", word);
        }
        else
        {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();


        if (id == R.id.clear_data)
        {
            Toast.makeText(this, "Clearing the data...", Toast.LENGTH_SHORT).show();
            mWordViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void updateWord(Word myWord)
    {
        Intent intent = new Intent(this, NewWordActivity.class);
        intent.putExtra(UPDATE_WORD, myWord.getWord());
        intent.putExtra(WORD_ID, myWord.getId());
        startActivityForResult(intent, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
    }
}
