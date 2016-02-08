package id.gits.jetpackreader.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SyncService extends Service {
    private static final String TAG = "SyncService";
    private static final Object sAdapterLock = new Object();
    private static SyncAdapter sAdapter = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        synchronized (sAdapterLock) {
            if (sAdapter == null) {
                sAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sAdapter.getSyncAdapterBinder();
    }
}