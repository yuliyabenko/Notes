package com.yuliya.notes.providers;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.tjeannin.provigen.ProviGenOpenHelper;
import com.tjeannin.provigen.ProviGenProvider;
import com.yuliya.notes.db.NotesContract;

/**
 * Created by Yuliya on 20.11.2016.
 */

public class NotesContentProvider extends ProviGenProvider {

    private static final Class[] CONTRACTS = new Class[]{ NotesContract.class };

    public static final String DB_NAME = "notes_database";

    public static final int DB_VERSION = 1;

    @Override
    public SQLiteOpenHelper openHelper(Context context) {
        return new ProviGenOpenHelper(getContext(), DB_NAME, null, DB_VERSION, CONTRACTS);
    }

    @Override
    public Class[] contractClasses() {
        return CONTRACTS;
    }

}