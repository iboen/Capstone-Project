package id.gits.jetpackreader.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.gits.jetpackreader.api.dao.CategoryDao;
import id.gits.jetpackreader.api.dao.PostDao;
import id.gits.jetpackreader.api.dao.PostListApiDao;
import id.gits.jetpackreader.api.jetpack.GetCategoriesApi;
import id.gits.jetpackreader.api.jetpack.GetPostsApi;
import id.gits.jetpackreader.provider.category.CategoryColumns;
import id.gits.jetpackreader.provider.category.CategoryContentValues;
import id.gits.jetpackreader.provider.post.PostColumns;

/**
 * Handle the transfer of data between a server and an
 * app, using the Android sync adapter framework.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "SyncAdapter";
    public static final int TYPE_CAT = 1;
    public static final int TYPE_POST = 2;
    // Global variables
    private final AccountManager mAccountManager;
    private Context mContext;

    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mAccountManager = AccountManager.get(context);
        init(context);
    }


    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs, AccountManager accountManager) {
        super(context, autoInitialize, allowParallelSyncs);
        mAccountManager = accountManager;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        if (extras.getInt("type") == TYPE_CAT) {
            syncCat(provider, syncResult);
        } else if (extras.getInt("type") == TYPE_POST) {
            syncPost(provider, syncResult);
        } else {
            syncCat(provider, syncResult);
            syncPost(provider, syncResult);
        }
    }

    private void syncCat(ContentProviderClient provider, SyncResult syncResult) {
        try {
            GetCategoriesApi.ApiDao categoryApiDao = GetCategoriesApi.callSyncApi(mContext);
            Log.d(TAG, "onPerformSync: Category");
            if (categoryApiDao != null) {
                provider.delete(CategoryColumns.CONTENT_URI, null, null);
                //input to database via content provider
                List<ContentValues> contentValList = new ArrayList<>();
                for (CategoryDao category : categoryApiDao.getCategories()) {
                    CategoryContentValues cv = CategoryDao.toContentValues(category);
                    contentValList.add(cv.values());
                }
                provider.bulkInsert(CategoryColumns.CONTENT_URI,
                        contentValList.toArray(new ContentValues[contentValList.size()]));

                // notify content provider listeners
                mContentResolver.notifyChange(CategoryColumns.CONTENT_URI, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            syncResult.hasHardError();
        }
    }

    private void syncPost(ContentProviderClient provider, SyncResult syncResult) {
        try {
            //Get Post
            PostListApiDao postApiDao = GetPostsApi.callSyncApi(mContext);
            Log.d(TAG, "onPerformSync: Post");
            if (postApiDao != null) {
                //input to database via content provider
                for (PostDao post : postApiDao.getPosts()) {
                    post.saveToDb(provider);
                }

                // notify content provider listeners
                mContentResolver.notifyChange(PostColumns.CONTENT_URI, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            syncResult.hasHardError();
        }
    }
}