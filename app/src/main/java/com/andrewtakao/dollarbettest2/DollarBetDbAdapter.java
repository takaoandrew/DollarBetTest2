package com.andrewtakao.dollarbettest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by andrewtakao on 11/2/16.
 */

public class DollarBetDbAdapter {
    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    private String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY, COLUMN_DATE};

    public static final String CREATE_TABLE_NOTE = "create table " + NOTE_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private DollarBetDbHelper dollarBetDbHelper;

    public DollarBetDbAdapter(Context ctx) {
        context = ctx;
    }

    public DollarBetDbAdapter open() throws android.database.SQLException{
        dollarBetDbHelper = new DollarBetDbHelper(context);
        sqlDB = dollarBetDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dollarBetDbHelper.close();
    }

    public Friend createNote(String title, String message, Friend.Category category) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_MESSAGE, message);
        values.put(COLUMN_CATEGORY, category.name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        long insertId = sqlDB.insert(NOTE_TABLE, null, values);

        Cursor cursor = sqlDB.query(NOTE_TABLE, allColumns, COLUMN_ID + " = " + insertId, null,
        null, null, null);

        cursor.moveToFirst();
        Friend newFriend = cursorToNote(cursor);
        cursor.close();
        return newFriend;

    }

    public long deleteNote(long idToDelete) {
        return sqlDB.delete(NOTE_TABLE, COLUMN_ID + " = " + idToDelete, null);
    }

    public long updateNote(long idToUpdate, String newTitle, String newMessage, Friend.Category newCategory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newTitle);
        values.put(COLUMN_MESSAGE, newMessage);
        values.put(COLUMN_CATEGORY, newCategory.name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        return sqlDB.update(NOTE_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);
    }

    public ArrayList<Friend> getAllNotes(){
        ArrayList<Friend> friends = new ArrayList<Friend>();

        Cursor cursor = sqlDB.query(NOTE_TABLE, allColumns, null, null, null, null, null);

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Friend friend = cursorToNote(cursor);
            friends.add(friend);
        }

        cursor.close();

        return friends;

    }

    private Friend cursorToNote(Cursor cursor){
        Friend newFriend = new Friend(cursor.getString(1), cursor.getString(2),
                Friend.Category.valueOf(cursor.getString(3)), cursor.getLong(0), cursor.getLong(4));
        return newFriend;
    }

    private static class DollarBetDbHelper extends SQLiteOpenHelper{


        DollarBetDbHelper(Context ctx){
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_NOTE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);
        }
    }
}
