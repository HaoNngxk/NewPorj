package com.xkyb.jf.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.xkyb.jf.R;
import com.xkyb.jf.adapter.RecommendAdapter;
import com.xkyb.jf.db.DBManager;
import com.xkyb.jf.model.ProductDataModel;
import com.xkyb.jf.model.RecommendContentModel;
import com.xkyb.jf.model.RecommendModel;
import com.xkyb.jf.ui.viewholder.RollViewPagerItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * author：Anumbrella
 * Date：16/5/24 下午8:04
 */
public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.easy_recyclerview)
    public EasyRecyclerView recyclerView;

    private GridLayoutManager girdLayoutManager;

    private RecommendAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);
        adapter = new RecommendAdapter(getActivity());
        girdLayoutManager = new GridLayoutManager(getActivity(), 1);
        girdLayoutManager.setSpanSizeLookup(adapter.obtainTipSpanSizeLookUp());
        recyclerView.setErrorView(R.layout.view_net_error);
        adapter.addHeader(new RollViewPagerItemView(recyclerView.getSwipeToRefresh()));
        recyclerView.setLayoutManager(girdLayoutManager);
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);

        //打开首先从缓存获取数据显示
        getDataFromCache(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        }, 1000);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        RecommendModel.getProductsDataFromNet(new Subscriber<List<ProductDataModel>>() {
            @Override
            public void onCompleted() {
                recyclerView.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), "网络不给力", Toast.LENGTH_SHORT).show();
                if (adapter.getCount() == 0) {
                    recyclerView.showError();
                }
                recyclerView.setRefreshing(false);
            }

            @Override
            public void onNext(final List<ProductDataModel> productDataModels) {
                DBManager.getManager(getActivity()).removeAllProducts();
                DBManager.getManager(getActivity()).addAllProducts(productDataModels);
                getDataFromCache(true);
            }
        });
    }

    private void getDataFromCache(final boolean isUpadate) {
        RecommendModel.getProductsFromDB(getActivity()).subscribe(new Action1<List<RecommendContentModel>>() {
            @Override
            public void call(List<RecommendContentModel> listDatas) {
                if (listDatas.size() != 0 && listDatas != null) {
                    if (isUpadate && adapter.getCount() != 0) {
                        adapter.clear();
                    }
                    adapter.addAll(listDatas);
                    if (recyclerView != null && !isUpadate) {
                        recyclerView.setRefreshing(true);

                    }
                }
            }
        });
    }
}
