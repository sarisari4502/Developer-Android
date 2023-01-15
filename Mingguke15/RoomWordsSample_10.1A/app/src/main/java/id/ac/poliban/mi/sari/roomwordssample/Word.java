/**
 *                                  Creating the Entity class
 *
 * The data for this app is words, and each word is represented by an entity in the database.
 * In this task you create the Word class and annotate it so Room can create a database table
 * from it. The diagram below shows a word_table database table. The table has one word column,
 * which also acts as the primary key, and two rows, one each for "Hello" and "World."
 *
 * **/



package id.ac.poliban.mi.sari.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull //Can't be null
    @ColumnInfo(name = "word") //Column it is under
    private String mWord;


    @Ignore
    public Word(@NonNull String word, int id) {this.mWord = word; this.id = id; }

    public Word(@NonNull String word) {this.mWord = word;}

    public String getWord() {return this.mWord; }

    public int getId() {return this.id; }


    public void setId(int id) { this.id = id;}
}
