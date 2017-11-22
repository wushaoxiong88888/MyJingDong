package com.pc.myjingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.fragment.FragmentCart;

import java.util.List;

/**
 * Created by pc on 2017/11/9.
 */

public class CartChildRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FindCartBean.DataBean> list;
    private int p;

    private OnItemListener3 onItemListener3;

    public interface OnItemListener3 {
        public void OnItemClick3(FindCartBean.DataBean dataBean);
    }

    public void setOnItemListener3(OnItemListener3 onItemListener3) {
        this.onItemListener3 = onItemListener3;
    }

    public CartChildRlvAdapter(Context context, List<FindCartBean.DataBean> list, int p) {
        this.context = context;
        this.list = list;
        this.p = p;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_child_rlv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        //图片
        String images = list.get(p).getList().get(position).getImages();
        String[] imgs = images.split("\\|");
        String img = imgs[0].toString();
        Uri uri = Uri.parse(img);
        myViewHolder.sdv.setImageURI(uri);
        //文字
        myViewHolder.tv_title.setText(list.get(p).getList().get(position).getTitle());
        myViewHolder.tv_price.setText("￥" + String.valueOf(list.get(p).getList().get(position).getPrice()));

        //复选框
        myViewHolder.cb.setChecked(list.get(p).getList().get(position).isCheckChild());
        myViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = myViewHolder.cb.isChecked();
                panduan(position, checked);
            }
        });

        myViewHolder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onItemListener3.OnItemClick3(list.get(p));

                return false;
            }
        });

        //Button的点击加减
        myViewHolder.bt_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double numPrice = 0;
                boolean checkChild = list.get(p).getList().get(position).isCheckChild();

                if (checkChild) {
                    int num = list.get(p).getList().get(position).getNum();
                    myViewHolder.et.setText(String.valueOf(num + 1));
                    list.get(p).getList().get(position).setNum(num + 1);


                    numPrice = numPrice + list.get(p).getList().get(position).getNum() *
                            list.get(p).getList().get(position).getPrice();
                    FragmentCart.mCartTvCount.setText(String.valueOf(numPrice));
                }else {
                    Toast.makeText(context, "请先勾选", Toast.LENGTH_SHORT).show();
                }


            }
        });

        myViewHolder.bt_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = list.get(p).getList().get(position).getNum();
                if (num == 1) {
                    Toast.makeText(context, "不能再减了", Toast.LENGTH_SHORT).show();
                } else {
                    myViewHolder.et.setText(String.valueOf(num - 1));
                    list.get(p).getList().get(position).setNum(num - 1);
                }
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.get(p).getList().size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb;
        private final SimpleDraweeView sdv;
        private final TextView tv_title;
        private final TextView tv_price;
        private final Button bt_jian;
        private final Button bt_jia;
        private final EditText et;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cart_child_cb);
            sdv = itemView.findViewById(R.id.cart_child_sdv);
            tv_title = itemView.findViewById(R.id.cart_child_tv_title);
            tv_price = itemView.findViewById(R.id.cart_child_tv_price);
            bt_jian = itemView.findViewById(R.id.cart_child_bt_jian);
            bt_jia = itemView.findViewById(R.id.cart_child_bt_jia);
            et = itemView.findViewById(R.id.cart_child_et_num);
            ll = itemView.findViewById(R.id.cart_child_ll);
        }
    }

    private void panduan(int position, boolean checked) {
        //子选中,先把bean里面的状态改变
        list.get(p).getList().get(position).setCheckChild(checked);
        //判断改组下面的所有子是否选中
        boolean allChecked = true;
        //遍历改组下面的所有子 只要有一个没选中 那就赋false
        for (int i = 0; i < list.get(p).getList().size(); i++) {
            if (!list.get(p).getList().get(position).isCheckChild()) {
                allChecked = false;
            }
        }
        Toast.makeText(context, allChecked + "", Toast.LENGTH_SHORT).show();
        //给改组的状态赋值
        list.get(p).setCheckFather(allChecked);
        //判断所有组的状态是否被选中
        boolean zuAllChecked = true;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isCheckFather()) {
                zuAllChecked = false;
            }
        }

        //赋值
        FragmentCart.mCartCbAll.setChecked(zuAllChecked);
        //刷新适配器
        FragmentCart.adapter.notifyDataSetChanged();

    }

}
