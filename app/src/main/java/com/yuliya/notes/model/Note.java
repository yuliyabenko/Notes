package com.yuliya.notes.model;

import android.database.Cursor;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.yuliya.notes.db.NotesContract;

/**
 * Created by Yuliya on 20.11.2016.
 */

public class Note {

    private String mTitle = null;

    private String mText = null;

    private String mTime = null;

    private long mId;

    public Note() {}

    public Note(Cursor data) {
        mId = data.getLong(data.getColumnIndex(ProviGenBaseContract._ID));
        mTitle = data.getString(data.getColumnIndex(NotesContract.TITLE_COLUMN));
        mText = data.getString(data.getColumnIndex(NotesContract.TEXT_COLUMN));
        mTime = data.getString(data.getColumnIndex(NotesContract.TIME_COLUMN));
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public long getId() {
        return mId;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Note)){
//            return false;
//        }
//        Note note = (Note) obj;
//        return mId == note.getId();
//    }
}

