package android.com.demopagination;

import android.com.demopagination.bean.Item;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by amitrai on 19/7/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView>{

    private List<Item> list_items ;
    private Context context;

    public CustomAdapter(Context context, List<Item> list_items){
        this.list_items = list_items;
        this.context = context;
    }


    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.design_customview,parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        holder.txtDescription.setText(list_items.get(position).getTitle());
        Glide.with(context).load(list_items.get(position).getOwner().getProfileImage()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    class CustomView extends RecyclerView.ViewHolder{

        TextView txtDescription;
        ImageView imgView;
        public CustomView(View itemView) {
            super(itemView);
            txtDescription = (TextView) itemView.findViewById(R.id.txtdescription);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
        }
    }
}
