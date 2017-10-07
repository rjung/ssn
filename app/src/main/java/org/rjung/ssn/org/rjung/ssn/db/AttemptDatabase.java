package org.rjung.ssn.org.rjung.ssn.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class AttemptDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "attempts.db";
    public static final String DATABASE_TABLE = "attempts";
    public static final String DATABASE_COLUMN_STARTED = "started";
    public static final String DATABASE_COLUMN_UPDATED = "updated";
    public static final String DATABASE_COLUMN_FINISHED = "finished";
    public static final int DATABASE_VERSION = 1;

    public AttemptDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (INTEGER " + DATABASE_COLUMN_STARTED + " NOT NULL, INTEGER updated NOT NULL, INTEGER ended NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List getColumns() {
        return Arrays.asList(DATABASE_COLUMN_STARTED, DATABASE_COLUMN_UPDATED, DATABASE_COLUMN_FINISHED);
    }

    public Attempt getCurrentAttempt() {
        Attempt result = new Attempt();
        try (Cursor cursor = getReadableDatabase().query(DATABASE_TABLE, null, null, null, null, null, DATABASE_COLUMN_STARTED + " DESC LIMIT 1")) {
            if (cursor.moveToLast()) {
                result.setStartedStore(getLongFromCursorField(cursor, DATABASE_COLUMN_STARTED));
                result.setUpdatedStore(getLongFromCursorField(cursor, DATABASE_COLUMN_UPDATED));
                result.setFinishedStore(getLongFromCursorField(cursor, DATABASE_COLUMN_FINISHED));
            } else {
                Date now = new Date();
                result.setStarted(now);
                result.setUpdated(now);
            }
        }
        return result;
    }

    private Long getLongFromCursorField(Cursor cursor, String field) {
        int column = cursor.getColumnIndex(field);
        return cursor.isNull(column) ? new Date().getTime() : cursor.getLong(column);
    }
}
