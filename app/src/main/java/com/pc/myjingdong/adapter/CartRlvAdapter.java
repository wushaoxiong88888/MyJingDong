package com.pc.myjingdong.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.fragment.FragmentCart;
import com.pc.myjingdong.presenter.DelCartPres;
import com.pc.myjingdong.utils.MessageEvent;
import com.pc.myjingdong.view.DelCartView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by pc on 2017/11/9.
 */

public class CartRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FindCartBean.DataBean> data;
    private CartChildRlvAdapter adapter;

    /*private OnItemListener3 onItemListener3;
    public interface OnItemListener3{
        public void OnItemClick3(FindCartBean.DataBean dataBean);
    }
    public void setOnItemListener3(OnItemListener3 onItemListener3){
        this.onItemListener3 = onItemListener3;
    }*/

    public CartRlvAdapter(Context context, List<FindCartBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_rlv_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(data.get(position).getSellerName());
        //点击复选框
        myViewHolder.cb.setChecked(data.get(position).isCheckFather());
        //设置子rlv
        //final List<FindCartBean.DataBean.ListBean> data = this.data.get(position).getList();
        myViewHolder.rlv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CartChildRlvAdapter(context, data, position);
        myViewHolder.rlv.setAdapter(adapter);

        //点击删除商品
        adapter.setOnItemListener3(new CartChildRlvAdapter.OnItemListener3() {

            private int pid;

            @Override
            public void OnItemClick3(final FindCartBean.DataBean dataBean) {

                //弹出一个对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定删除该商品吗?");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //得到子条目pid
                        List<FindCartBean.DataBean.ListBean> list = dataBean.getList();
                        for (int i = 0; i < list.size(); i++) {
                            pid = dataBean.getList().get(i).getPid();
                        }

                        //得到用户的uid和token
                        SharedPreferences preferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
                        String uid = preferences.getString("uid", "");
                        String token = preferences.getString("token", "");

                        //Toast.makeText(context,"1234567",Toast.LENGTH_SHORT).show();
                        DelCartPres delCartPres = new DelCartPres(new DelCartView() {
                            @Override
                            public void delCartShow(BaseBean baseBean) {
                                String msg = baseBean.getMsg();
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                        delCartPres.delMandV(uid, String.valueOf(pid), token);

                        //删除条目并刷新适配器
                        data.remove(position);
                        notifyDataSetChanged();

                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

        //给复选框赋值


        //一级选中或取消,二级跟着改变
        myViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double numPrice = 0;

                boolean checked = myViewHolder.cb.isChecked();
                if (checked) {
                    //选中
                    data.get(position).setCheckFather(checked);
                    for (int i = 0; i < data.get(position).getList().size(); i++) {
                        data.get(position).getList().get(i).setCheckChild(checked);
                        if (checked) {
                            numPrice = numPrice + data.get(position).getList().get(i).getPrice() *
                                    data.get(position).getList().get(i).getNum();
                        }
                        FragmentCart.mCartTvCount.setText(String.valueOf(numPrice));
                    }
                    notifyDataSetChanged();
                } else {
                    data.get(position).setCheckFather(checked);
                    for (int i = 0; i < data.get(position).getList().size(); i++) {
                        data.get(position).getList().get(i).setCheckChild(checked);
                        FragmentCart.mCartTvCount.setText(String.valueOf(numPrice));
                    }
                    notifyDataSetChanged();
                }

                //判断其他组的复选框是否选中
                if (myViewHolder.cb.isChecked()) {
                    if (isAllChecked()) {
                        //状态值为选中发送过去
                        MessageEvent msg = new MessageEvent();
                        msg.setChecked(true);
                        EventBus.getDefault().post(msg);
                    } else {
                        //未选中
                        MessageEvent msg = new MessageEvent();
                        msg.setChecked(false);
                        EventBus.getDefault().post(msg);
                    }
                } else {
                    data.get(position).setCheckFather(false);
                    //取消全选
                    MessageEvent msg = new MessageEvent();
                    msg.setChecked(false);
                    EventBus.getDefault().post(msg);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb;
        private final TextView tvName;
        private final RecyclerView rlv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cart_rlv_cb);
            tvName = itemView.findViewById(R.id.cart_rlv_tvName);
            rlv = itemView.findViewById(R.id.cart_rlv_rlv);
            ll = itemView.findViewById(R.id.cart_rlv_ll);
        }
    }

    //判断其他组的复选框是否选中
    private boolean isAllChecked() {
        for (FindCartBean.DataBean dataBean : data) {
            if (!dataBean.isCheckFather()) {
                //表示有某个checkbox未选中
                return false;
            }
        }
        return true;
    }

    //点击全选让这一层的复选框全选中
    public void Allchecked(boolean flag) {

        double AllPrice = 0;

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setCheckFather(flag);
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                data.get(i).getList().get(j).setCheckChild(flag);
                if (flag) {
                    AllPrice = AllPrice + data.get(i).getList().get(j).getPrice() * data.get(i).getList().get(j).getNum();
                }
                FragmentCart.mCartTvCount.setText(String.valueOf(AllPrice));
            }
        }
        notifyDataSetChanged();
    }

}
