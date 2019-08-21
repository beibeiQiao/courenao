package com.example.mygit.adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.mygit.Listener.OnItemClickListener;
import com.example.mygit.R;
import com.example.mygit.ViewHolder.GridThreeViewHolder;
import com.example.mygit.ViewHolder.GridTwoViewHolder;
import com.example.mygit.ViewHolder.ListViewHolder;
import com.example.mygit.ViewHolder.TitleViewHolder;
import com.example.mygit.bean.Item;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements View.OnClickListener{
    private List<Item> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public RecyclerAdapter(Context context, List<Item> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder = null;
        if (viewType == Item.TYPE.TYPE_GRID_THREE) {
            view = mInflater.inflate(R.layout.item_grid_three, parent, false);
            mViewHolder = new GridThreeViewHolder(view);
        } else if (viewType == Item.TYPE.TYPE_GRID_TWO) {
            view = mInflater.inflate(R.layout.item_grid_two, parent, false);
            mViewHolder = new GridTwoViewHolder(view);
        } else if (viewType == Item.TYPE.TYPE_LIST) {
            view = mInflater.inflate(R.layout.item_list, parent, false);
            mViewHolder = new ListViewHolder(view);
        } else if (viewType == Item.TYPE.TYPE_TITLE) {
            view = mInflater.inflate(R.layout.item_title, parent, false);
            mViewHolder = new TitleViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Item.TYPE.TYPE_GRID_THREE:
                GridThreeViewHolder gHolder_three = (GridThreeViewHolder) holder;
                gHolder_three.tv_content.setText(mList.get(position).title);
                gHolder_three.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_three.itemView.setOnClickListener(this);
                gHolder_three.itemView.setTag(position);
                break;
            case Item.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gHolder_two = (GridTwoViewHolder) holder;
                gHolder_two.tv_content.setText(mList.get(position).title);
                gHolder_two.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_two.itemView.setOnClickListener(this);
                gHolder_two.itemView.setTag(position);
                break;
            case Item.TYPE.TYPE_LIST:
                ListViewHolder lHolder = (ListViewHolder) holder;
                lHolder.tv_content.setText(mList.get(position).title);
                lHolder.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                lHolder.itemView.setOnClickListener(this);
                lHolder.itemView.setTag(position);
                break;
            case Item.TYPE.TYPE_TITLE:
                TitleViewHolder tHolder = (TitleViewHolder) holder;
                tHolder.tv_content.setText(mList.get(position).title);
                //点击事件
                tHolder.itemView.setOnClickListener(this);
                tHolder.itemView.setTag(position);
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = (int) v.getTag();
          mOnItemClickListener.onItemClick(position);
        }
    }



}
