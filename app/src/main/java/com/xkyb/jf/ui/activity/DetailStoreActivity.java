package com.xkyb.jf.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xkyb.jf.R;
import com.xkyb.jf.adapter.DetailStoreAdapter;
import com.xkyb.jf.adapter.HeaderBottomAdapter;
import com.xkyb.jf.widget.SimpleDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailStoreActivity extends AppCompatActivity {

    private DetailStoreAdapter adapter;
    LinearLayoutManager layoutManager;

    private RecyclerView easy_recyclerview;

    @BindView(R.id.btn_return)
    ImageView btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            // window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(getResources().getColor(R.color.addShopping_bg));
        }
        setContentView(R.layout.activity_detail_store);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        easy_recyclerview = (RecyclerView) findViewById(R.id.easy_recyclerview);
        //List布局
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        easy_recyclerview.setLayoutManager(layoutManager);
        easy_recyclerview.addItemDecoration(new SimpleDividerItemDecoration(this, OrientationHelper.VERTICAL));
        easy_recyclerview.setAdapter(adapter=new DetailStoreAdapter(this));


    }

    @OnClick({R.id.btn_return})
    public void OnClickB(View view){
        switch (view.getId()){
            case R.id.btn_return:
                finish();
                break;
        }
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

}