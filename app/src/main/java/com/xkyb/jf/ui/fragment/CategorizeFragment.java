package com.xkyb.jf.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xkyb.jf.R;
import com.xkyb.jf.adapter.DetailStoreAdapter;
import com.xkyb.jf.adapter.HeaderBottomAdapter;
import com.xkyb.jf.adapter.IndexAdapter;
import com.xkyb.jf.model.OrderAllDataModel;
import com.xkyb.jf.model.bean.HomeType;
import com.xkyb.jf.model.bean.MsgData;
import com.xkyb.jf.ui.activity.StoreActivity;
import com.xkyb.jf.widget.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * author：Anumbrella
 * Date：16/5/24 下午8:04
 */
public class CategorizeFragment extends Fragment {
    @BindView(R.id.easy_recyclerview)
    RecyclerView easy_recyclerview;

    LinearLayoutManager linearLayoutManager;
    private IndexAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorize, container, false);
        ButterKnife.bind(this, view);
        getData();
        init();
        return view;
    }

    private void init() {
        //Grid布局
        linearLayoutManager = new GridLayoutManager(getActivity(), 1);
        easy_recyclerview.setLayoutManager(linearLayoutManager);//这里用线性宫格显示 类似于grid view
        easy_recyclerview.addItemDecoration(new SimpleDividerItemDecoration(getActivity(),OrientationHelper.VERTICAL));
        easy_recyclerview.setAdapter(adapter = new IndexAdapter(getActivity()));
    }

    private void getData(){
        OrderAllDataModel.getProducts(new Subscriber<MsgData<List<HomeType>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MsgData<List<HomeType>> listMsgData) {
                Log.e("","");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
