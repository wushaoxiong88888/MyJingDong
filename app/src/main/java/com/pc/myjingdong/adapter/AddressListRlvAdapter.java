package com.pc.myjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.FindAddressBean;

import java.util.List;

/**
 * Created by pc on 2017/11/15.
 */

public class AddressListRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FindAddressBean.DataBean> list;

    public AddressListRlvAdapter(Context context, List<FindAddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.address_list_rlv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_name.setText(list.get(position).getName());
        myViewHolder.tv_phone.setText(String.valueOf(list.get(position).getMobile()));
        myViewHolder.tv_info.setText(list.get(position).getAddr());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_name;
        private final TextView tv_phone;
        private final TextView tv_info;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.add_rlv_name);
            tv_phone = itemView.findViewById(R.id.add_rlv_phone);
            tv_info = itemView.findViewById(R.id.add_rlv_info);
        }
    }
}
