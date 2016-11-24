package com.yuliya.notes.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yuliya.notes.R;
import com.yuliya.notes.adapters.NotesAdapter;
import com.yuliya.notes.adapters.NotesAdapter.NotesViewHolder;
import com.yuliya.notes.db.NotesContract;
import com.yuliya.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    final int MENU_DELETE = 1;
    final int MENU_SHARE = 2;


    @BindView(R.id.notes_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
//    @BindView(R.id.fab_button)
//    protected FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        getSupportLoaderManager().initLoader(R.id.notes_loader, null, this);
//        registerForContextMenu(fab);

        for(int i = 0; i < 10; i++){
            ContentValues values = new ContentValues();
            values.put(NotesContract.TEXT_COLUMN, "ggggggggg " + i);
            getContentResolver().insert(NotesContract.CONTENT_URI, values);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU_DELETE, 0, "Delete");
        menu.add(0, MENU_SHARE, 0, "Share");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @OnClick(R.id.fab_button)
    public void onFabBtnClick() {
        startActivity(EditNoteActivity.newInstance(this));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Snackbar.make(recyclerView, R.string.settings, Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                Snackbar.make(
                        recyclerView,
                        R.string.help,
                        Snackbar.LENGTH_LONG)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                NotesContract.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<Note> dataSource = new ArrayList<>();
        while (data.moveToNext()) {
            dataSource.add(new Note(data));
        }
        NotesAdapter adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDataSource(dataSource);
        adapter.setOnItemClickListener( view -> {
            NotesViewHolder holder = (NotesViewHolder) recyclerView.findContainingViewHolder(view);
            if(holder == null) return;
            startActivity(EditNoteActivity.newInstance(this, holder.getNote().getId()));
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View view) {
        NotesViewHolder holder = (NotesViewHolder) recyclerView.findContainingViewHolder(view);
        if(holder == null) return;
        startActivity(EditNoteActivity.newInstance(this, holder.getNote().getId()));
    }
}
