package android.com.demopagination.ApiListeners;

import android.com.demopagination.bean.QuesitonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amitrai on 20/7/16.
 */
public interface StakApiService {
        @GET("/2.2/questions")
        Call<QuesitonBean> listRepos(@Query("pagesize") String pagesize,
                                     @Query("order") String order,
                                     @Query("sort") String sort,
                                     @Query("site") String site);
}
