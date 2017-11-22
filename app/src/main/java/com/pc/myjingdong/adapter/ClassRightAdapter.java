package com.pc.myjingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.activity.GoodsListActivity;
import com.pc.myjingdong.bean.ClassRightBean;

import java.util.List;

/**
 * Created by pc on 2017/11/7.
 */

public class ClassRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ClassRightBean.DataBean> data;

    public ClassRightAdapter(Context context, List<ClassRightBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_right_rlv, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(data.get(position).getName());

        //设置子rlv
        List<ClassRightBean.DataBean.ListBean> list = data.get(position).getList();

        myViewHolder.rlvChild.setLayoutManager(new GridLayoutManager(context,3));

        ClassChildAdapter adapter = new ClassChildAdapter(context, list);
        myViewHolder.rlvChild.setAdapter(adapter);

        //点击跳转到列表界面
        adapter.setOnItemListener2(new ClassChildAdapter.OnItemListener2() {
            @Override
            public void OnItemClick2(ClassRightBean.DataBean.ListBean listBean) {
                //得到pscid传到列表界面
                List<ClassRightBean.DataBean.ListBean> list = data.get(position).getList();
                int pscid = list.get(position).getPscid();

                Intent intent = new Intent(context, GoodsListActivity.class);
                intent.putExtra("pscid",String.valueOf(pscid));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvName;
        private final RecyclerView rlvChild;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.class_right_goodsName);
            rlvChild = itemView.findViewById(R.id.right_rlv_child);
        }
    }

}
