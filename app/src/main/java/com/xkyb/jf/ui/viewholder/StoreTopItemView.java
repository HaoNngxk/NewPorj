package com.xkyb.jf.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xkyb.jf.R;

/**
 * Created by Administrator on 2016/12/10.
 */

public class StoreTopItemView implements RecyclerArrayAdapter.ItemView {

    private ImageView head_image;

    @Override
    public View onCreateView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_top_view, parent, false);
      //  head_image = (ImageView) view.findViewById(R.id.head_image);
        return head_image;
    }

    @Override
    public void onBindView(View headerView) {

    }
}
