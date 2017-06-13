package com.xkyb.jf.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xkyb.jf.R;
import com.xkyb.jf.config.Config;
import com.xkyb.jf.model.RecommendContentModel;
import com.xkyb.jf.ui.activity.MainActivity;


/**
 * author：Anumbrella
 * Date：16/5/25 下午9:38
 */
public class RecommendTipVewHolder extends BaseViewHolder<RecommendContentModel> {

    private TextView tip;


    private RecommendContentModel data;


    public RecommendTipVewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_tip);
        tip = $(R.id.recommend_tip);
    }


    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data = data;
        tip.setText(data.getTip());

    }
}

