package com.imherolddev.dailytimekeeper.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by imherolddev on 2/7/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "daily_time_keeper_db";

    public static final String JOB_TABLE = "`jobs`";
    public static final String JOB_ID_COL = "_jobID";
    public static final int JOB_ID_COL_POS = 0;
    public static final String JOB_NAME_COL = "jobName";
    public static final int JOB_NAME_COL_POS = 1;

    public static final String CLOCK_TIME_TABLE = "`clockTimes`";
    public static final String CLOCK_TIME_ID_COL = "_clockID";
    public static final int CLOCK_TIME_ID_COL_POS = 0;
    public static final String CLOCK_TIME_JOB_ID_COL = "`clockTimeJobID";
    public static final int CLOCK_TIME_JOB_ID_POS = 1;
    public static final String CLOCK_TIME_COL = "`clockTime`";
    public static final int CLOCK_TIME_COL_POS = 2;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createJobTable =

                "CREATE TABLE IF NOT EXISTS " + JOB_TABLE +
                        "(`" + JOB_ID_COL + "` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "`" +   JOB_NAME_COL + "` VARCHAR(45) UNIQUE NOT NULL);";

        String createClockTimeTable =

                "CREATE TABLE IF NOT EXISTS " + CLOCK_TIME_TABLE +
                        "(`" + CLOCK_TIME_ID_COL + "` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "`" + CLOCK_TIME_JOB_ID_COL + "` INTEGER NOT NULL, " +
                        "`" + CLOCK_TIME_COL + "` INTEGER NOT NULL);";

        db.execSQL(createJobTable);
        db.execSQL(createClockTimeTable);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //persist existing, drop and recreate
    }
}
