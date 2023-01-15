package id.ac.poliban.mi.sari.roomwordssample; /**
 *                                              Dao (Data Access Object)
 *
 * The data access object, or Dao, is an annotated class where you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL for errors, then generates queries from the annotations. For common queries, the libraries
 * provide convenience annotations such as @Insert.
 * **/


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll(); //deletes all words


    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords(); //gets all words


    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();


    @Delete
    void deleteWord(Word word);

    @Update
    void update(Word... word);
}

