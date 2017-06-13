package com.xkyb.jf.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.jude.utils.JActivityManager;
import com.xkyb.jf.R;
import com.xkyb.jf.config.Config;
import com.xkyb.jf.model.TabModel;
import com.xkyb.jf.utils.ExitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Anumbrella
 * Date：16/5/23 下午6:12
 */
public class MainActivity extends BaseThemeSettingActivity {

    protected FragmentTabHost tabHost;

    private ExitUtils exit = new ExitUtils();

    @BindView(R.id.llayout)
    LinearLayout llayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(getResources().getColor(R.color.addShopping_bg));
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        initTab();
    }

    private void initTab() {
        String[] tabTexts = TabModel.getTabTexts();
        for (int i = 0; i < tabTexts.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTexts[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabModel.getFragments()[i], null);
            tabHost.setOnTabChangedListener(new OnTabChangeListener());
            tabHost.setTag(i);
        }
    }

    private final class OnTabChangeListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(String tabId) {
            if (tabId.equals(Config.tabs[0])) {
                llayout.setVisibility(View.VISIBLE);
                updateTabs();
            } else if (tabId.equals(Config.tabs[2])) {
                llayout.setVisibility(View.GONE);
                updateTabs();
            }
        }
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_footer, null);
        if (position == 0) {
            (view.findViewById(R.id.ivImg)).setEnabled(true);
        } else if (position == 1) {
            ((ImageView) view.findViewById(R.id.ivImg)).setVisibility(View.GONE);
        } else {
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[position]);
            (view.findViewById(R.id.ivImg)).setEnabled(false);
        }
        return view;
    }

    @OnClick({R.id.scan_code,R.id.btn_location,R.id.set_iv})
    public void clickFab(View view) {

        switch (view.getId()) {
            case R.id.scan_code:
                Intent intent = new Intent();
                intent.setClass(this, CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_location:
                Intent contactIntent = new Intent();
                contactIntent.setClass(this, LocationActivity.class);
                startActivity(contactIntent);
                break;
            case R.id.set_iv:
                Intent setIntent = new Intent();
                setIntent.setClass(this, SetActivity.class);
                startActivity(setIntent);
                break;
        }
    }

    /*public void goToUp(int position) {
        recyclerView.scrollToPosition(position);
    }*/

    private void updateTabs() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.ivImg);
            if (i == tabHost.getCurrentTab()) {
                iv.setEnabled(true);
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[i]);
            } else {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[i]);
                iv.setEnabled(false);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pressAgainExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 双击返回键离开
     */
    private void pressAgainExit() {
        if (exit.isExit()) {
            for (Activity activity : JActivityManager.getActivityStack()) {
                activity.finish();
            }
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exit.doExitAction();
        }
    }
}