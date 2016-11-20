package com.yuliya.notes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliya.notes.R;
import com.yuliya.notes.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yuliya on 20.11.2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> mDataSource = null;

    public void setDataSource(List<Note> dataSource) {
        this.mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_notes_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = mDataSource.get(position);
        holder.bindView(note);
    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.primary_text_view)
        protected TextView mPrimaryTextView;
        @BindView(R.id.secondary_text_view)
        protected TextView mSecondaryTextView;
        @BindView(R.id.date_text_view)
        protected TextView mDateTextView;

        public NotesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Note note) {
            mPrimaryTextView.setText(note.getTitle());
            mSecondaryTextView.setText(note.getText());
            mDateTextView.setText(note.getTime());
        }
    }

}
