package android.com.demopagination.ApiListeners;

import android.com.demopagination.bean.QuesitonBean;

/**
 * Created by amitrai on 19/7/16.
 */
public interface ApiListener {
    public void onCallSuccess(QuesitonBean questions);
    public void onError();
}
