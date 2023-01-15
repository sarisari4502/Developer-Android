package id.ac.poliban.sari.e020320115.homework_10a;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }


    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note Note) {
        mRepository.insert(Note);
    }
}
