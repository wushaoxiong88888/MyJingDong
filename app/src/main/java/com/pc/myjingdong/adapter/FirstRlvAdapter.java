package com.pc.myjingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.HomeFirstBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/11/3.
 */

public class FirstRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeFirstBean.MiaoshaBean.ListBeanX> list;
    private String img;
    private List<String> imglist = new ArrayList<>();

    public FirstRlvAdapter(Context context, List<HomeFirstBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.first_rlv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        /**
         * public static void main(String[] args) {
         String sourceStr = "https://m.360buyimg.com/n0/jfs/t6130/97/1370670410/180682/1109582a/593276b1Nd81fe723.jpg!q70.jpg";
         String[] sourceStrArray = sourceStr.split("\\|");
         for (int i = 0; i < sourceStrArray.length; i++) {
         System.out.println(sourceStrArray[i]);
         }
         */

        String images = list.get(position).getImages();
        String[] imgs = images.split("\\|");

        img = imgs[0].toString();
        //imglist.add(img);
        //Log.e("TAG-----", img);

        Uri uri = Uri.parse(img);
        myViewHolder.iv.setImageURI(uri);
        myViewHolder.tv_top.setText("¥" + String.valueOf(list.get(position).getBargainPrice()));
        myViewHolder.tv_bottom.setText("¥" + String.valueOf(list.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv_top;
        private final TextView tv_bottom;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.rlv_item_iv);
            tv_top = itemView.findViewById(R.id.rlv_item_tv_top);
            tv_bottom = itemView.findViewById(R.id.rlv_item_tv_bottom);
        }
    }

}
