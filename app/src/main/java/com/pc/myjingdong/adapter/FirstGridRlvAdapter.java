package com.pc.myjingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.HomeFirstBean;

import java.util.List;

/**
 * Created by pc on 2017/11/6.
 */

public class FirstGridRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeFirstBean.TuijianBean.ListBean> list1;
    private String img;

    public FirstGridRlvAdapter(Context context, List<HomeFirstBean.TuijianBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.first_gridrlv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        String images = list1.get(position).getImages();

        String[] imgs = images.split("\\|");

        img = imgs[0].toString();

        Uri uri = Uri.parse(img);
        myViewHolder.sdv.setImageURI(uri);
        myViewHolder.tvtitle.setText(list1.get(position).getTitle());
        myViewHolder.tvprice.setText("¥"+String.valueOf(list1.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tvtitle;
        private final TextView tvprice;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.first_grid_sdv);
            tvtitle = itemView.findViewById(R.id.first_grid_tvTitle);
            tvprice = itemView.findViewById(R.id.first_grid_tvPrice);
        }
    }

}
