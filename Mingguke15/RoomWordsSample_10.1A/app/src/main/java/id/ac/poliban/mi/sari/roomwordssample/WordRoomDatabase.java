package id.ac.poliban.mi.sari.roomwordssample;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase
{
    public abstract WordDao wordDao();


    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context)
    {

        if (INSTANCE == null)
        {
            synchronized (WordRoomDatabase.class)
            {
                if (INSTANCE == null)
                {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }


        return INSTANCE;
    }



    private static Callback sRoomDatabaseCallback =
            new Callback()
            {

                //Creating an AsyncTask to add the content to the database because it cannot be done on the UI thread
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {

        private final WordDao mDao;
        String[] words = {
                //"Dolphin", "Crocodle", "Cobra"
                };

        PopulateDbAsync(WordRoomDatabase db)
        {

            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            if(mDao.getAnyWord().length < 1)
            {
                for (int i = 0; i <= words.length - 1; i++)
                {
                    Word word = new Word(words[i]);
                    mDao.insert(word);
                }
            }
            return null;
        }
    }

}
