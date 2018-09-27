package com.prpr894.recycler.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prpr894.recycler.viewholders.ChildViewHolder;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;
import com.prpr894.recycler.viewholders.ParentViewHolder;

import java.lang.reflect.Field;
import java.util.List;

public abstract class ExpandableRecyclerAdapter<P, C, VH extends ExpandableViewHolder> extends RecyclerView.Adapter<VH> {

    private ParentViewHolder mParentViewHolder;
    private ChildViewHolder mChildViewHolder;

    private int mPResId;
    private int mCResId;

    private List<P> mPList;
    private C mC;

    private Context mContext;


    public ExpandableRecyclerAdapter(int PResId, int CResId, List<P> pList, Context context) {
        mPResId = PResId;
        mCResId = CResId;
        mPList = pList;
        mContext = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(i, viewGroup, false);
        VH vh;
        if (i == mCResId) {
            vh = (VH) new ChildViewHolder(view);
        } else {
            vh = (VH) new ParentViewHolder(view);
        }
        return vh;
    }

    public abstract void onBindViewHolderNow(P p, VH vh, int i);

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        onBindViewHolderNow(mPList.get(i), vh, i);
    }

    @Override
    public int getItemViewType(int position) {
        //TODO 重写用来分辨布局文件
        return mPResId;
    }

    @Override
    public int getItemCount() {
        return mPList.size();
    }
}
