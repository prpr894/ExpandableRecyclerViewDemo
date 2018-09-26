package com.prpr894.recycler.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prpr894.recycler.viewholders.BaseViewHolder;

import java.util.List;

public abstract class BaseRecyclerAdapter<D> extends RecyclerView.Adapter<BaseViewHolder> {

    private int resId;
    private List<D> mList;
    private Context mContext;

    public BaseRecyclerAdapter(int resId, List<D> list, Context context) {
        mList = list;
        mContext = context;
        this.resId = resId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(resId, viewGroup, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int i) {
        onBindViewHolderNow(mList.get(i), holder, i);
    }

    public abstract void onBindViewHolderNow(D d, @NonNull BaseViewHolder viewHolder, int i);

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
