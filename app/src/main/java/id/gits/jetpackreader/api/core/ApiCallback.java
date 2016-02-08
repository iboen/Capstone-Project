package id.gits.jetpackreader.api.core;

/**
 * Created by ibun on 16/09/15.
 */
public interface ApiCallback<T> {
    void onApiSuccess(T result);

    void onApiError(String errorMessage);
}
