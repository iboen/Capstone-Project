package id.gits.jetpackreader.api.jetpack;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import id.gits.jetpackreader.api.core.ApiCallback;
import id.gits.jetpackreader.api.core.CancelTask;
import id.gits.jetpackreader.api.core.JetpackApi;
import id.gits.jetpackreader.api.core.MyCallback;
import id.gits.jetpackreader.api.dao.PostListApiDao;


/**
 * Created by ibun on 01/16/16.
 */
public class GetPostsApi extends BaseApi {
    public static PostListApiDao callSyncApi(Context context) {
        cancel();

        mCall = JetpackApi.getInstance(context).getService().getPosts(null, null);
        try {
            return mCall.execute().body();
        } catch (IOException e) {
            Log.e(GetPostsApi.class.getSimpleName(), "callSyncApi: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void callSyncApi(Context context, String categorySlug, String dateBefore, ApiCallback<PostListApiDao> apiCallback) {
        if (mCall != null)
            new CancelTask().execute(mCall);

        mCall = JetpackApi.getInstance(context).getService().getPosts(categorySlug, dateBefore);
        mCall.enqueue(new MyCallback(apiCallback));
    }

}
