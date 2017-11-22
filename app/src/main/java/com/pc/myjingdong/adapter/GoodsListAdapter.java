package com.pc.myjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.GoodsListBean;

import java.util.List;

/**
 * Created by pc on 2017/11/7.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<GoodsListBean.DataBean> list;
    private String img;
    private OnItemListener3 onItemListener3;
    public interface OnItemListener3{
        public void OnItemCilck(GoodsListBean.DataBean dataBean);
    }
    public void setOnItemListener3(OnItemListener3 onItemListener3){
        this.onItemListener3 = onItemListener3;
    }

    public GoodsListAdapter(Context context, List<GoodsListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goodslist_rlv, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String images = list.get(position).getImages();
        String[] imgs = images.split("\\|");
        img = imgs[0].toString();
        myViewHolder.sdv.setImageURI(img);
        myViewHolder.tvTitle.setText(list.get(position).getTitle());
        myViewHolder.tvPrice.setText("￥"+String.valueOf(list.get(position).getPrice()));

        //跳转
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener3.OnItemCilck(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tvTitle;
        private final TextView tvPrice;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.list_rlv_sdv);
            tvTitle = itemView.findViewById(R.id.list_rlv_tvTitle);
            tvPrice = itemView.findViewById(R.id.list_rlv_tvPrice);
            ll = itemView.findViewById(R.id.list_rlv_ll);
        }
    }
}
