package id.gits.jetpackreader.api.core;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import id.gits.jetpackreader.api.JetpackService;
import id.gits.jetpackreader.api.util.Constant;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by ibun on 16/09/15.
 */
public class JetpackApi {
    private static JetpackApi INSTANCE;
    private Retrofit retrofit;

    private JetpackService jetpackService;
    private final Context mContext;


    private JetpackApi(Context context) {
        mContext = context;

    }

    public static JetpackApi getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new JetpackApi(context);
            INSTANCE.init();
        }
        return INSTANCE;
    }


//    public static JetpackApi getInstance(Context context, String url) {
//        JetpackApi jetpackApi = new JetpackApi();
//        jetpackApi.init(url);
//
//        return jetpackApi;
//}

    public void init() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .cache(new Cache(mContext.getExternalCacheDir(), 1024 * 1024 * 10))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JetpackService getService() {
        if (jetpackService == null) {
            jetpackService = retrofit.create(JetpackService.class);
        }
        return jetpackService;
    }

}
