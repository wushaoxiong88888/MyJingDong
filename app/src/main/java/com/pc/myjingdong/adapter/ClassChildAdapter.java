package com.pc.myjingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.ClassRightBean;

import java.util.List;

/**
 * Created by pc on 2017/11/7.
 */

public class ClassChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ClassRightBean.DataBean.ListBean> list;

    private OnItemListener2 onItemListener2;
    public interface OnItemListener2{
        public void OnItemClick2(ClassRightBean.DataBean.ListBean listBean);
    }
    public void setOnItemListener2(OnItemListener2 onItemListener2){
        this.onItemListener2 = onItemListener2;
    }

    public ClassChildAdapter(Context context, List<ClassRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_child_rlv, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String icon = list.get(position).getIcon();
        Uri uri = Uri.parse(icon);
        myViewHolder.sdv.setImageURI(uri);
        myViewHolder.tv.setText(list.get(position).getName());

        //点击跳转
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener2.OnItemClick2(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.child_rlv_sdv);
            tv = itemView.findViewById(R.id.child_rlv_tv);
            ll = itemView.findViewById(R.id.child_rlv_ll);
        }
    }

}
