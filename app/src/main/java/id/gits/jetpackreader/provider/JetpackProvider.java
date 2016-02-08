package id.gits.jetpackreader.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Arrays;

import id.gits.jetpackreader.BuildConfig;
import id.gits.jetpackreader.provider.base.BaseContentProvider;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.post.PostColumns;

public class JetpackProvider extends BaseContentProvider {
    private static final String TAG = JetpackProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "id.gits.jetpackreader.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_CATEGORY = 0;
    private static final int URI_TYPE_CATEGORY_ID = 1;

    private static final int URI_TYPE_POST = 2;
    private static final int URI_TYPE_POST_ID = 3;


    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME, URI_TYPE_CATEGORY);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME + "/#", URI_TYPE_CATEGORY_ID);
        URI_MATCHER.addURI(AUTHORITY, PostColumns.TABLE_NAME, URI_TYPE_POST);
        URI_MATCHER.addURI(AUTHORITY, PostColumns.TABLE_NAME + "/#", URI_TYPE_POST_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return MySQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_CATEGORY:
                return TYPE_CURSOR_DIR + CategoryColumns.TABLE_NAME;
            case URI_TYPE_CATEGORY_ID:
                return TYPE_CURSOR_ITEM + CategoryColumns.TABLE_NAME;

            case URI_TYPE_POST:
                return TYPE_CURSOR_DIR + PostColumns.TABLE_NAME;
            case URI_TYPE_POST_ID:
                return TYPE_CURSOR_ITEM + PostColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG)
            Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG)
            Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_CATEGORY:
            case URI_TYPE_CATEGORY_ID:
                res.table = CategoryColumns.TABLE_NAME;
                res.idColumn = CategoryColumns._ID;
                res.tablesWithJoins = CategoryColumns.TABLE_NAME;
                res.orderBy = CategoryColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_POST:
            case URI_TYPE_POST_ID:
                res.table = PostColumns.TABLE_NAME;
                res.idColumn = PostColumns._ID;
                res.tablesWithJoins = PostColumns.TABLE_NAME;
                res.orderBy = PostColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_CATEGORY_ID:
            case URI_TYPE_POST_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
