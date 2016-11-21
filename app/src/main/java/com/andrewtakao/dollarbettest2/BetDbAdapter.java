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

public class BetDbAdapter {
    private static final String DATABASE_NAME = "dollarbet.db";
    private static final int DATABASE_VERSION = 1;

    public static final String BET_TABLE = "bet";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "betterName";
    public static final String COLUMN_REQUESTED = "bet";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    private String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_REQUESTED, COLUMN_CATEGORY, COLUMN_DATE};

    public static final String CREATE_TABLE_NOTE = "create table " + BET_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_REQUESTED + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private BetDbHelper betDbHelper;

    public BetDbAdapter(Context ctx) {
        context = ctx;
    }

    public BetDbAdapter open() throws android.database.SQLException {
        betDbHelper = new BetDbHelper(context);
        sqlDB = betDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        betDbHelper.close();
    }

    public Friend createNote(String name, String requested, Friend.Category category) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REQUESTED, requested);
        values.put(COLUMN_CATEGORY, category.name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        long insertId = sqlDB.insert(BET_TABLE, null, values);

        Cursor cursor = sqlDB.query(BET_TABLE, allColumns, COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        Friend newFriend = cursorToFriend(cursor);
        cursor.close();
        return newFriend;

    }

    public long deleteNote(long idToDelete) {
        return sqlDB.delete(BET_TABLE, COLUMN_ID + " = " + idToDelete, null);
    }

    public long updateFriend(long idToUpdate, String name, String requested, Friend.Category newCategory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REQUESTED, requested);
        values.put(COLUMN_CATEGORY, newCategory.name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        return sqlDB.update(BET_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);
    }

    public ArrayList<Friend> getAllBets() {
        ArrayList<Friend> friends = new ArrayList<Friend>();

        Cursor cursor = sqlDB.query(BET_TABLE, allColumns, null, null, null, null, null);

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            Friend friend = cursorToFriend(cursor);
            friends.add(friend);
        }

        cursor.close();

        return friends;

    }

    private Friend cursorToFriend(Cursor cursor) {
        Friend newFriend = new Friend(cursor.getString(1), cursor.getString(2),
                Friend.Category.valueOf(cursor.getString(3)), cursor.getLong(0), cursor.getLong(4));
        return newFriend;
    }

    private static class BetDbHelper extends SQLiteOpenHelper {


        BetDbHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_NOTE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + BET_TABLE);
            onCreate(db);
        }
    }
}
