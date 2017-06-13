package com.xkyb.jf.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xkyb.jf.R;

/**
 * Created by Administrator on 2016/12/10.
 */

public class StoreContentViewHolder extends BaseViewHolder implements View.OnClickListener{

    private TextView content;

    public StoreContentViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.item);
    //    content = (TextView) $(R.id.tv);
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
    }

    @Override
    public void onClick(View v) {
        
    }
}
