package com.pc.myjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.ClassLeftBean;

import java.util.List;

/**
 * Created by pc on 2017/11/6.
 */

public class ClassLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ClassLeftBean.DataBean> list;
    private OnItemListener onItemListener;
    public interface OnItemListener{
        public void onItemClick(ClassLeftBean.DataBean dataBean);
    }

    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }

    public ClassLeftAdapter(Context context, List<ClassLeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.class_left_rlv, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Log.e("TAG----",list.size()+"");

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(list.get(position).getName());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemListener!=null){
                    onItemListener.onItemClick(list.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.class_left_tv);
            ll = itemView.findViewById(R.id.left_rlv_ll);
        }
    }

}
