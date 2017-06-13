package com.xkyb.jf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xkyb.jf.R;
import com.xkyb.jf.config.Config;
import com.xkyb.jf.ui.activity.DetailStoreActivity;
import com.xkyb.jf.ui.activity.StoreActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lijizhou on 2016/2/24.
 * Blog:http://blog.csdn.net/leejizhou
 * QQ:3107777777
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    //模拟数据
    public String [] texts={"java","python","C++","Php",".NET","js","Ruby","Swift","OC"};
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=1;//底部View个数

    public IndexAdapter(Context context) {
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
        private GridView index_gridView;
        private GridViewAdapter adapter;
        public HeaderViewHolder(View itemView, final Context context) {
            super(itemView);
            index_gridView = (GridView) itemView.findViewById(R.id.index_gridView);

            ArrayList listitem = new ArrayList();

            // 将上述资源转化为list集合
            for (int i = 0; i < Config.recommdendListImg.length; i++) {
                HashMap map = new HashMap();
                map.put("image", Config.recommdendListImg[i]);
                //map.put("title", title[i]);
                listitem.add(map);
            }
            adapter = new GridViewAdapter(context,listitem);
            index_gridView.setAdapter(adapter);
            index_gridView.setClickable(false);
            index_gridView.setPressed(false);

            index_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent contactIntent = new Intent();
                    contactIntent.setClass(context, StoreActivity.class);
                    context.startActivity(contactIntent);
                }
            });

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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.index_top_view, parent, false),mContext);
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
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

}