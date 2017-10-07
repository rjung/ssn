package org.rjung.ssn.org.rjung.ssn.db;

import android.content.ContentValues;
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
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_STARTED = "started";
    public static final String DATABASE_COLUMN_UPDATED = "updated";
    public static final String DATABASE_COLUMN_FINISHED = "finished";
    public static final int DATABASE_VERSION = 1;

    public AttemptDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + DATABASE_COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + DATABASE_COLUMN_STARTED + " INTEGER NOT NULL, " + DATABASE_COLUMN_UPDATED + " INTEGER NOT NULL, " + DATABASE_COLUMN_FINISHED + " INTEGER NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List getColumns() {
        return Arrays.asList(DATABASE_COLUMN_ID, DATABASE_COLUMN_STARTED, DATABASE_COLUMN_UPDATED, DATABASE_COLUMN_FINISHED);
    }

    public Attempt getCurrentAttempt() {
        Attempt result = new Attempt();
        try (Cursor cursor = getReadableDatabase().query(DATABASE_TABLE, null, null, null, null, null, DATABASE_COLUMN_UPDATED + " DESC LIMIT 1")) {
            if (cursor.moveToLast()) {
                result.setId(getLongFromCursorField(cursor, DATABASE_COLUMN_ID));
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

    private long persist(Attempt attempt) {
        if (attempt.isPersisted()) {
            return getWritableDatabase().update(DATABASE_TABLE, getContentValues(attempt), "id = ?", new String[]{Long.toString(attempt.getId())});
        } else {
            return getWritableDatabase().insert(DATABASE_TABLE, null, getContentValues(attempt));
        }
    }

    public void noCigaretteTheLastDay() {
        Attempt attempt = getCurrentAttempt();
        if (attempt.isFinished()) {
            attempt = new Attempt();
            long now = new Date().getTime();
            attempt.setStartedStore(now - 1000 * 60 * 60 * 24);
            attempt.setUpdatedStore(now);
        } else {
            attempt.setUpdated(new Date());
        }
        persist(attempt);
    }

    public void cigaretteTheLastDay() {
        Attempt attempt = getCurrentAttempt();
        attempt.setUpdated(new Date());
        persist(attempt);
    }

    private Long getLongFromCursorField(Cursor cursor, String field) {
        int column = cursor.getColumnIndex(field);
        return cursor.isNull(column) ? null : cursor.getLong(column);
    }

    private ContentValues getContentValues(Attempt attempt) {
        ContentValues result = new ContentValues();
        result.put(DATABASE_COLUMN_STARTED, attempt.getStartedStore());
        result.put(DATABASE_COLUMN_UPDATED, attempt.getUpdatedStore());
        result.put(DATABASE_COLUMN_FINISHED, attempt.getFinishedStore());
        return result;
    }
}
