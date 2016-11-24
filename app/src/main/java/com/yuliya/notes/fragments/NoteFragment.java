package com.yuliya.notes.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.yuliya.notes.R;
import com.yuliya.notes.adapters.NotesFragmentPagerAdapter;
import com.yuliya.notes.db.NotesContract;
import com.yuliya.notes.model.Note;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yuliya on 23.11.2016.
 */

public class NoteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static NoteFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(ProviGenBaseContract._ID, id);
        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.titleEditText)
    protected EditText mTitleEditText;
    @BindView(R.id.contentEditText)
    protected EditText mContentEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(
                R.layout.fragment_note,
                container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportLoaderManager()
        .initLoader(R.id.notes_fragment_loader_id, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        long noteId = getArguments().getLong(ProviGenBaseContract._ID);
        return new CursorLoader(
                getActivity(),
                Uri.withAppendedPath(NotesContract.CONTENT_URI, String.valueOf(noteId)),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) return;
        Note note = new Note(cursor);
        mTitleEditText.setText(note.getTitle());
        mContentEditText.setText(note.getText());

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
