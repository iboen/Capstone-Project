package id.gits.jetpackreader.api.core;


import android.text.TextUtils;
import android.util.Log;

import id.gits.jetpackreader.api.dao.BaseApiDao;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ibun on 16/09/15.
 */
public class MyCallback<T> implements Callback<T> {
    private static final String TAG = "API";
    private final ApiCallback<T> apiCallback;

    public MyCallback(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onResponse(Response<T> response) {
        if (response.body() == null) {
            apiCallback.onApiError("Internal Server Error");
            return;
        }
        try {
            BaseApiDao baseApiDao = (BaseApiDao) response.body();
            if (!TextUtils.isEmpty(baseApiDao.getMessage())) {
                apiCallback.onApiError(baseApiDao.getMessage());
                Log.d(TAG, "API ERROR: " + response.raw().request().url().toString());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiCallback.onApiSuccess(response.body());
        Log.d(TAG, "API CALL: " + response.raw().request().url().toString());
    }

    @Override
    public void onFailure(Throwable t) {
        apiCallback.onApiError(t.getMessage());
    }
}
