package id.gits.jetpackreader.api.jetpack;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import id.gits.jetpackreader.api.core.CancelTask;
import id.gits.jetpackreader.api.core.JetpackApi;
import id.gits.jetpackreader.api.dao.BaseApiDao;
import id.gits.jetpackreader.api.dao.CategoryDao;
import retrofit2.Call;


/**
 * Created by ibun on 01/16/16.
 */
public class GetCategoriesApi {

    private static Call<ApiDao> mCall;


    public static ApiDao callSyncApi(Context context) {
        if (mCall != null)
            new CancelTask().execute(mCall);

        mCall = JetpackApi.getInstance(context).getService().getCategories();
        try {
            return mCall.execute().body();
        } catch (IOException e) {
            Log.e(GetCategoriesApi.class.getSimpleName(), "callSyncApi: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Class for catch the result
     */
    public class ApiDao extends BaseApiDao {
        private int found;
        private List<CategoryDao> categories;

        public int getFound() {
            return found;
        }

        public List<CategoryDao> getCategories() {
            return categories;
        }
    }

    public class Links {
        private String self;
        private String help;
        private String site;

        public String getSelf() {
            return self;
        }

        public String getHelp() {
            return help;
        }

        public String getSite() {
            return site;
        }
    }

    public class Meta {
        private Links links;

        public Links getLinks() {
            return links;
        }
    }


}
