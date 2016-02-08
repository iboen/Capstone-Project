package id.gits.jetpackreader.api.core;


import android.os.AsyncTask;

import retrofit2.Call;

public class CancelTask extends AsyncTask<Call, Void, Void> {
    @Override
    protected Void doInBackground(Call... params) {
        Call call = params[0];
        call.cancel();
        return null;
    }
}