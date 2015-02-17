package com.imherolddev.dailytimekeeper.persistence;/*
 * Contributors:
 * Jason Hall - imherolddev
 *
 * Feb 17, 2015
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class Description
 */
public class CrudServiceImpl implements CrudService {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues = new ContentValues();
    private String query;

    private long RESULT = 0;

    public CrudServiceImpl(SQLiteDatabase db) {
        this.db =db;
    }

}
