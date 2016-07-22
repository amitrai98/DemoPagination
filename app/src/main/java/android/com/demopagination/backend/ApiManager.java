package android.com.demopagination.backend;

import android.com.demopagination.ApiListeners.ApiListener;
import android.com.demopagination.ApiListeners.StakApiService;
import android.com.demopagination.Constants;
import android.com.demopagination.bean.QuesitonBean;
import android.content.Context;
import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by amitrai on 19/7/16.
 */
public class ApiManager {

    private String TAG = getClass().getSimpleName();

    public void getQuestions(Context context, String page_no, ApiListener listener){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .build();
//        try {
//            StakApiService service = retrofit.create(StakApiService.class);
//            retrofit2.Call<QuesitonBean> response =
//                    service.listRepos("10",Constants.ORDER, Constants.SORT, Constants.SITE);
//
//
//            response.enqueue(new Callback<QuesitonBean>() {
//                @Override
//                public void onResponse(Call<QuesitonBean> call, Response<QuesitonBean> response) {
//                    Log.e(TAG, "success");
//                }
//
//                @Override
//                public void onFailure(Call<QuesitonBean> call, Throwable t) {
//                    Log.e(TAG, "error");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if(context != null && listener != null){
            try {
                Retrofit request_topdomain = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                StakApiService topSearchApi = request_topdomain.create(StakApiService.class);
                Call<QuesitonBean> topSearchListCall = topSearchApi.listRepos(page_no,Constants.ORDER,Constants.SORT, Constants.SITE);

                topSearchListCall.enqueue(new Callback<QuesitonBean>() {
                    @Override
                    public void onResponse(Response<QuesitonBean> response, Retrofit retrofit) {
                        Log.e(TAG, "success");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "error");
                    }
                });
            }catch (Exception exp){
                exp.printStackTrace();
            }

        }
    }
}
