package com.pc.myjingdong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pc.myjingdong.R;
import com.pc.myjingdong.adapter.ClassLeftAdapter;
import com.pc.myjingdong.adapter.ClassRightAdapter;
import com.pc.myjingdong.bean.ClassLeftBean;
import com.pc.myjingdong.bean.ClassRightBean;
import com.pc.myjingdong.presenter.ClassLeftPres;
import com.pc.myjingdong.presenter.ClassRightPres;
import com.pc.myjingdong.view.ClassLeftView;
import com.pc.myjingdong.view.ClassRightView;

import java.util.List;

/**
 * Created by pc on 2017/10/31.
 */

public class FragmentClass extends Fragment {
    private View view;
    private RecyclerView mFragClassRlvLeft;
    private RecyclerView mRightRlvFather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_class, container, false);

        initView(view);

        //左边分类关联
        ClassLeftPres classLeftPres = new ClassLeftPres(new ClassLeftView() {
            @Override
            public void classLeftShow(ClassLeftBean classLeftBean) {
                List<ClassLeftBean.DataBean> list = classLeftBean.getData();
                //Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
                mFragClassRlvLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
                //mFragClassRlvLeft.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                ClassLeftAdapter adapter = new ClassLeftAdapter(getActivity(), list);
                mFragClassRlvLeft.setAdapter(adapter);

                //先默认显示右边第一条数据
                int cid = list.get(0).getCid();
                rightClass(cid);
                //得到cid
                adapter.setOnItemListener(new ClassLeftAdapter.OnItemListener() {
                    @Override
                    public void onItemClick(ClassLeftBean.DataBean dataBean) {
                        int cid1 = dataBean.getCid();
                        rightClass(cid1);
                    }
                });

            }
        });
        classLeftPres.classLeftMandV();

        return view;
    }

    private void initView(View view) {
        mFragClassRlvLeft = (RecyclerView) view.findViewById(R.id.frag_class_rlv_left);
        mRightRlvFather = (RecyclerView) view.findViewById(R.id.right_rlv_father);
    }

    private void rightClass(int cid) {

        //右边分类关联
        ClassRightPres classRightPres = new ClassRightPres(new ClassRightView() {
            @Override
            public void classRightShow(ClassRightBean classRightBean) {
                List<ClassRightBean.DataBean> data = classRightBean.getData();

                //设置右边rlv
                mRightRlvFather.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRightRlvFather.setAdapter(new ClassRightAdapter(getActivity(),data));

               /* Log.e("TAG----",data.size()+"");*/
                /*String name = classRightBean.getData().get(0).getName();
                Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();*/
            }
        });
        classRightPres.classRightMandV(cid);
    }


}
