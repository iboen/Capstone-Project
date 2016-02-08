package id.gits.jetpackreader.api.jetpack;

import id.gits.jetpackreader.api.core.CancelTask;
import id.gits.jetpackreader.api.dao.PostListApiDao;
import retrofit2.Call;

/**
 * Created by ibun on 17/01/16.
 */
public class BaseApi {
    static Call<PostListApiDao> mCall;

    public static void cancel() {
        if (mCall != null)
            new CancelTask().execute(mCall);
    }
}
