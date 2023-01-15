package id.ac.poliban.mi.sari.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;


    private static ClickListener clickListener;


    private List<Word> mWords;


    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }


    public Word getWordAtPosition(int position) { return mWords.get(position);}


    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else {

            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words)
    {
        mWords = words;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            wordItemView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        WordListAdapter.clickListener = clickListener;}

    public interface ClickListener { void onItemClick(int position, View v);}
}
