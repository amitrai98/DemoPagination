package android.com.demopagination;


import android.app.ProgressDialog;
import android.com.demopagination.ApiListeners.ApiListener;
import android.com.demopagination.backend.ApiManager;
import android.com.demopagination.bean.Item;
import android.com.demopagination.bean.QuesitonBean;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView = null;
    private CustomAdapter adapter = null;
    private List<Item> list_items = new ArrayList<>();
    private int firstVisibleItem;
    private int totalItemCount;
    private int visibleItemCount;
    private String TAG = getClass().getSimpleName();
    private int pastVisiblesItems ;

    private ProgressDialog pd = null;

    private boolean loading = true;

    LinearLayoutManager mLayoutManager;

    int lastcount = 0;
    int threshold = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadData();
    }

    /**
     * initialize view elements
     */
    private void init(){

        pd = new ProgressDialog(this);
        pd.setMessage("please wait while fetching data.");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CustomAdapter(this, list_items);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.e(TAG, "Last Item Wow !");
                            loadData();
                        }
                    }
                }


            }
        });

    }



    private void loadData(){
        pd.setMessage("loading next 10 questions.");
        if(pd != null && !pd.isShowing())
            pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = lastcount; i<lastcount+threshold; i++){
                }
                try {
                    new ApiManager().getQuestions(MainActivity.this, "10", new ApiListener() {
                        @Override
                        public void onCallSuccess(QuesitonBean quesitonBean) {
                            if(pd != null && pd.isShowing())
                                pd.dismiss();
                            Log.e(TAG, "Success"+quesitonBean.getItems().size());
                            for (int i = 0; i < quesitonBean.getItems().size(); i++) {
                                list_items.add(quesitonBean.getItems().get(i));
                                adapter.notifyDataSetChanged();
                                loading = true;
                            }
                        }

                        @Override
                        public void onError() {
                            if(pd != null && pd.isShowing())
                                pd.dismiss();

                            loading = true;
                            Log.e(TAG, "error");
                        }
                    });
                    showProgress(true);
                    Thread.sleep(2000);
                    showProgress(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    private void showProgress(final boolean show){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                if(show && pd != null && !pd.isShowing())
//                    pd.show();
//                else if(!show && pd != null && pd.isShowing()){
//                    pd.dismiss();
//                    adapter.notifyDataSetChanged();
//                    loading = true;
//                }
            }
        });
    }
}
