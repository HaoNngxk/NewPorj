package com.xkyb.jf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xkyb.jf.R;
import com.xkyb.jf.ui.activity.DetailStoreActivity;

/**
 * Created by Lijizhou on 2016/2/24.
 * Blog:http://blog.csdn.net/leejizhou
 * QQ:3107777777
 */
public class HeaderBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_TITLE = 3;
    //模拟数据
    public String [] texts={"java","python","C++","Php",".NET","js","Ruby","Swift","OC"};
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private int mTitleCount=1;//中部View个数
    private int mBottomCount=1;//底部View个数

    public HeaderBottomAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //内容长度
    public int getContentItemCount(){
        return texts.length;
    }
    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return  position ==0;
    }

    //判断当前item是否是HeadView
    public boolean isTitleView(int position) {
        return  position ==1;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return position == getItemCount()-1;
    }


    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (position ==0) {
            //头部View
            return ITEM_TYPE_HEADER;

        }else if(position==1){
            return ITEM_TYPE_TITLE;
        }else if (position == getItemCount()-1) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout index_item;
        public ContentViewHolder(View itemView) {
            super(itemView);
            index_item = (LinearLayout) itemView.findViewById(R.id.index_item);
        }
    }
    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    //标题 ViewHolder
    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        public TitleViewHolder(View itemView) {
            super(itemView);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.store_top_view, parent, false));

        }else if (viewType == ITEM_TYPE_TITLE){
            return new TitleViewHolder(mLayoutInflater.inflate(R.layout.store_title, parent, false));
        }else if (viewType == ITEM_TYPE_CONTENT) {
            return  new ContentViewHolder(mLayoutInflater.inflate(R.layout.item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.progress_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {


        }else if(holder instanceof TitleViewHolder){

        }else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).index_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, DetailStoreActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } else if (holder instanceof BottomViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount +mTitleCount+ getContentItemCount() + mBottomCount;
    }

}