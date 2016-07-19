package android.com.demopagination;


import android.app.ProgressDialog;
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
    private List<ItemBean> list_items = new ArrayList<>();
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
    }

    /**
     * initialize view elements
     */
    private void init(){

        pd = new ProgressDialog(this);
        pd.setMessage("please wait while fetching data.");

        for (int i=0; i<10; i++){
            list_items.add(new ItemBean("url"+i, "descirption "+i));
        }
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = lastcount; i<lastcount+threshold; i++){
                    list_items.add(new ItemBean("url"+i, "descirption "+i));
                }
                try {
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
                if(show && pd != null && !pd.isShowing())
                    pd.show();
                else if(!show && pd != null && pd.isShowing()){
                    pd.dismiss();
                    adapter.notifyDataSetChanged();
                    loading = true;
                }
            }
        });
    }
}
