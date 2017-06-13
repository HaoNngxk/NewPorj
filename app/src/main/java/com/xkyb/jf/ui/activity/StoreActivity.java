package com.xkyb.jf.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xkyb.jf.R;
import com.xkyb.jf.adapter.HeaderBottomAdapter;
import com.xkyb.jf.widget.SimpleDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreActivity extends BaseThemeSettingActivity {

    @BindView(R.id.contact_us_toolbar)
    Toolbar toolbar;

    private HeaderBottomAdapter adapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutManager;

    private RecyclerView easy_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        setToolbar(toolbar);
    }

    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("茶社");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        easy_recyclerview = (RecyclerView) findViewById(R.id.easy_recyclerview);

        //Grid布局
        gridLayoutManager = new GridLayoutManager(StoreActivity.this, 1);
        easy_recyclerview.setLayoutManager(gridLayoutManager);//这里用线性宫格显示 类似于grid view
        easy_recyclerview.addItemDecoration(new SimpleDividerItemDecoration(this, OrientationHelper.VERTICAL));
        easy_recyclerview.setAdapter(adapter = new HeaderBottomAdapter(this));
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.isHeaderView(position)){
                    return gridLayoutManager.getSpanCount();
                }

                if (adapter.isTitleView(position)){
                    return gridLayoutManager.getSpanCount();
                }


                if (adapter.isBottomView(position)){
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
               // return (adapter.isHeaderView(position) ||adapter.isTitleView(position)|| adapter.isBottomView(position)) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
