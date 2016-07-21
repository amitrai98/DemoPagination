package android.com.demopagination.backend;

import android.com.demopagination.ApiListeners.ApiListener;
import android.content.Context;

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

//        if(context != null && listener != null){
//            Retrofit request_topdomain = new Retrofit.Builder()
//                    .baseUrl(StakBrowserUrlBuilder.apiUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            TopSearchApi topSearchApi = request_topdomain.create(TopSearchApi.class);
//            Call<TopSearchList> topSearchListCall = topSearchApi.loadTopSearch(query);
//
//            topSearchListCall.enqueue(new Callback<TopSearchList>() {
//                @Override
//                public void onResponse(Response<TopSearchList> response, Retrofit retrofit) {
//                    TopSearchList searchList = response.body();
//                    if(searchList != null){
//                        List<TopSearchModal> responselist = searchList.getTopSearchModal();
//                        listener.onTopSearchSuccess(responselist);
//                    }
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    listener.onTopSearchError("api not responding");
//                }
//            });
//        }
    }
}
