package org.rjung.ssn.org.rjung.ssn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AttemptDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "attempts.db";
    public static final int DATABASE_VERSION = 1;

    AttemptDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE attempts (INTEGER started NOT NULL, INTEGER updated NOT NULL, INTEGER ended NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
