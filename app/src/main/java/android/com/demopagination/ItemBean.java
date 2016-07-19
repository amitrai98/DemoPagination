package android.com.demopagination;

/**
 * Created by amitrai on 19/7/16.
 */
public class ItemBean {
    String img_url, description;

    public ItemBean(String img_url, String description){
        this.img_url = img_url;
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
