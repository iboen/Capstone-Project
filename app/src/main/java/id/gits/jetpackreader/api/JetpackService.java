package id.gits.jetpackreader.api;

import id.gits.jetpackreader.api.dao.PostListApiDao;
import id.gits.jetpackreader.api.jetpack.GetCategoriesApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ibun on 01/16/16.
 */
public interface JetpackService {

    @GET("categories")
    Call<GetCategoriesApi.ApiDao> getCategories();

    @GET("posts")
    Call<PostListApiDao> getPosts(
            @Query("category") String categorySlug,
            @Query("before") String dateBefore);
}
