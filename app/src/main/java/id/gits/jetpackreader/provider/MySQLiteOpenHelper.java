package id.gits.jetpackreader.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import id.gits.jetpackreader.BuildConfig;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.post.PostColumns;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "jetpack.db";
    private static final int DATABASE_VERSION = 1;
    private static MySQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MySQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
            + CategoryColumns.TABLE_NAME + " ( "
            + CategoryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryColumns.S_ID + " INTEGER NOT NULL, "
            + CategoryColumns.NAME + " TEXT, "
            + CategoryColumns.SLUG + " TEXT NOT NULL, "
            + CategoryColumns.DESCRIPTION + " TEXT, "
            + CategoryColumns.POST_COUNT + " INTEGER NOT NULL, "
            + CategoryColumns.PARENT + " INTEGER NOT NULL, "
            + CategoryColumns.LINK + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_INDEX_CATEGORY_SLUG = "CREATE INDEX IDX_CATEGORY_SLUG "
            + " ON " + CategoryColumns.TABLE_NAME + " ( " + CategoryColumns.SLUG + " );";

    public static final String SQL_CREATE_TABLE_POST = "CREATE TABLE IF NOT EXISTS "
            + PostColumns.TABLE_NAME + " ( "
            + PostColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PostColumns.S_ID + " INTEGER NOT NULL, "
            + PostColumns.TITLE + " TEXT, "
            + PostColumns.SLUG + " TEXT NOT NULL, "
            + PostColumns.CONTENT + " TEXT, "
            + PostColumns.EXCERPT + " TEXT, "
            + PostColumns.AUTHOR_NAME + " TEXT, "
            + PostColumns.AUTHOR_IMAGE + " TEXT NOT NULL, "
            + PostColumns.DATE_CREATED + " TEXT NOT NULL, "
            + PostColumns.DATE_MODIFIED + " TEXT NOT NULL, "
            + PostColumns.IMAGE + " TEXT, "
            + PostColumns.COMMENT_COUNT + " INTEGER NOT NULL, "
            + PostColumns.LIKE_COUNT + " INTEGER NOT NULL, "
            + PostColumns.LINK + " TEXT NOT NULL, "
            + PostColumns.CATEGORY + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_INDEX_POST_AUTHOR_IMAGE = "CREATE INDEX IDX_POST_AUTHOR_IMAGE "
            + " ON " + PostColumns.TABLE_NAME + " ( " + PostColumns.AUTHOR_IMAGE + " );";

    // @formatter:on

    public static MySQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MySQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static MySQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context);
    }

    private MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MySQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MySQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MySQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MySQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_INDEX_CATEGORY_SLUG);
        db.execSQL(SQL_CREATE_TABLE_POST);
        db.execSQL(SQL_CREATE_INDEX_POST_AUTHOR_IMAGE);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
