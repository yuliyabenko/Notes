package com.yuliya.notes.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.yuliya.notes.R;
import com.yuliya.notes.db.NotesContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditNoteActivity extends AppCompatActivity {

    private static final String SHARE_TYPE = "text/plain";
    public static final String RESULT = "RESULT";

    @BindView(R.id.titleEditText)
    protected EditText mTitleEditText;
    @BindView(R.id.contentEditText)
    protected EditText mContentEditText;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    public static Intent newInstance(Context context) {
        return new Intent(context, EditNoteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.action_share: {
                share();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, prepareNoteForSharing());
        shareIntent.setType(SHARE_TYPE);
        startActivity(shareIntent);
    }

    private String prepareNoteForSharing() {
        return getString(
                R.string.sharing_template,
                mTitleEditText.getText(),
                mContentEditText.getText());
    }

    @OnClick(R.id.saveBtn)
    public void onSaveBtnClick() {
        insertNote();
        finish();
    }

    private void insertNote() {
        ContentValues values = new ContentValues();
        values.put(NotesContract.TITLE_COLUMN, mTitleEditText.getText().toString());
        values.put(NotesContract.TEXT_COLUMN, mContentEditText.getText().toString());
        values.put(NotesContract.TIME_COLUMN, String.valueOf(System.currentTimeMillis()));
        getContentResolver().insert(NotesContract.CONTENT_URI, values);
    }

}