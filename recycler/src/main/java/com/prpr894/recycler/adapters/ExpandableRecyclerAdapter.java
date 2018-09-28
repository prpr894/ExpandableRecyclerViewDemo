package com.prpr894.recycler.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prpr894.recycler.viewholders.ExpandableViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableRecyclerAdapter<P> extends RecyclerView.Adapter<ExpandableViewHolder> implements View.OnClickListener {

    public static final String TYPE_PARENT = "P";
    public static final String TYPE_CHILD = "C";

    private int mPResId;
    private int mCResId;

    private List<P> mPList;

    private Context mContext;

    private List<ExpanableBean> mExpandableBeanList;
    private static final int INT_DEFAULT = 23333;
    private int mExpandClickResId = INT_DEFAULT;

    public ExpandableRecyclerAdapter(int PResId, int CResId, List<P> pList, Context context) {
        mPResId = PResId;
        mCResId = CResId;
        mPList = pList;
        mContext = context;
        mExpandableBeanList = new ArrayList<>();
        for (int i = 0; i < mPList.size(); i++) {
            ExpanableBean bean = new ExpanableBean();
            bean.setP(mPList.get(i));
            bean.setIntPosition(i);
            bean.setStrType(TYPE_PARENT);
            bean.setResId(mPResId);
            mExpandableBeanList.add(bean);
        }
    }


    protected void setExpandClickResId(int resId) {
        mExpandClickResId = resId;
    }

    @NonNull
    @Override
    public ExpandableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(i, viewGroup, false);
        return new ExpandableViewHolder(view);
    }

    public abstract void onBindViewHolderNow(P p, ExpandableViewHolder vh, int position, int resId);

    @Override
    public void onBindViewHolder(@NonNull ExpandableViewHolder vh, int position) {
        if (mExpandableBeanList.get(position).getResId() == mPResId) {
            if (mExpandClickResId == INT_DEFAULT) {
                vh.getItemView().setOnClickListener(this);
                vh.getItemView().setTag(position);
            } else {
                vh.getViewById(mExpandClickResId).setOnClickListener(this);
                vh.getViewById(mExpandClickResId).setTag(position);
            }
        }
        onBindViewHolderNow(mExpandableBeanList.get(position).getP(), vh, position, mExpandableBeanList.get(position).getResId());
    }

    @Override
    public int getItemViewType(int position) {
        return mExpandableBeanList.get(position).getResId();
    }

    @Override
    public int getItemCount() {
        return mExpandableBeanList.size();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        onExpand(position);
    }

    public void onExpand(int position) {
        //TODO 展开或者收起
        Log.d("flag", "点击了： " + position);



    }


    public class ExpanableBean {
        private P mP;
        private String mStrType;
        private int mIntPosition;
        private int mResId;

        public int getResId() {
            return mResId;
        }

        public void setResId(int resId) {
            mResId = resId;
        }

        public P getP() {
            return mP;
        }

        public void setP(P p) {
            mP = p;
        }

        public String getStrType() {
            return mStrType;
        }

        public void setStrType(String strType) {
            mStrType = strType;
        }

        public int getIntPosition() {
            return mIntPosition;
        }

        public void setIntPosition(int intPosition) {
            mIntPosition = intPosition;
        }
    }


}
