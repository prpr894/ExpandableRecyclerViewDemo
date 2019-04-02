package com.prpr894.recycler.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prpr894.recycler.interfaces.OnExpandListener;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableRecyclerAdapter<P> extends RecyclerView.Adapter<ExpandableViewHolder> implements View.OnClickListener {

    private static final String TYPE_PARENT = "P";
    private static final String TYPE_CHILD = "C";
    private int mPResId;
    private int mCResId;
    private List<P> mPList;
    private Context mContext;
    private List<ExpandableBean> mExpandableBeanList;
    private static final int INT_DEFAULT = 23333;
    private int mExpandClickResId = INT_DEFAULT;
    private RecyclerView mRecyclerView;
    private OnExpandListener mOnExpandListener;
    private int mExpandResId;

    protected void setOnExpandListener(int expandResId, OnExpandListener onExpandListener) {
        mExpandResId = expandResId;
        mOnExpandListener = onExpandListener;
    }

    public ExpandableRecyclerAdapter(int PResId, int CResId, List<P> pList, Context context) {
        mPResId = PResId;
        mCResId = CResId;
        mPList = pList;
        mContext = context;
        mExpandableBeanList = new ArrayList<>();
        for (int i = 0; i < mPList.size(); i++) {
            ExpandableBean bean = new ExpandableBean();
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

    public abstract void onBindViewHolderNow(P p, ExpandableViewHolder vh, int position, int resId, ExpandFiled expandFiled);

    @Override
    public void onBindViewHolder(@NonNull ExpandableViewHolder vh, int position) {
        if (mExpandableBeanList.get(position).getResId() == mPResId) {
            if (mExpandClickResId == INT_DEFAULT) {
                vh.getItemView().setOnClickListener(this);
                vh.getItemView().setTag(vh);
            } else {
                vh.getViewById(mExpandClickResId).setOnClickListener(this);
                vh.getViewById(mExpandClickResId).setTag(vh);
            }
        }
        ExpandFiled expandFiled = new ExpandFiled();
        expandFiled.setDataListPosition(mExpandableBeanList.get(position).getIntPosition());
        expandFiled.setExpanded(mExpandableBeanList.get(position).isExpanded);
        onBindViewHolderNow(mExpandableBeanList.get(position).getP(), vh, position, mExpandableBeanList.get(position).getResId(), expandFiled);
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
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public void onClick(View view) {
        ExpandableViewHolder vh = (ExpandableViewHolder) view.getTag();
        int position = vh.getAdapterPosition();
        onExpand(position, vh);
    }

    public void expandChild(ExpandableViewHolder vh) {
        int position = vh.getAdapterPosition();
        int expand = isExpand(position);
        if (expand == -1) {
            expand(position, vh);
        }else {
            notifyItemChanged(expand);
        }
    }

    public void onExpand(int position, ExpandableViewHolder vh) {
        if (mExpandableBeanList.get(position).getStrType().equals(TYPE_PARENT)) {
            int expand = isExpand(position);
            if (expand == -1) {
                expand(position, vh);
            } else {
                hide(position, vh, expand);
            }
        }
    }

    private void hide(int position, ExpandableViewHolder vh, int expand) {
        if (expand >= 1) {
            mExpandableBeanList.get(expand - 1).setExpanded(false);
        }
        mExpandableBeanList.remove(expand);
        notifyItemRemoved(expand);
        if (mOnExpandListener != null) {
            mOnExpandListener.onExpand(false, vh.getViewById(mExpandResId), position);
        }
    }

    private void expand(int position, ExpandableViewHolder vh) {
        ExpandableBean bean = new ExpandableBean();
        bean.setP(mExpandableBeanList.get(position).getP());
        bean.setIntPosition(mExpandableBeanList.get(position).getIntPosition());
        bean.setStrType(TYPE_CHILD);
        bean.setResId(mCResId);
        mExpandableBeanList.add(position + 1, bean);
        mExpandableBeanList.get(position).setExpanded(true);
        notifyItemInserted(position + 1);
        mRecyclerView.smoothScrollToPosition(position + 1);//此处的滚动动画可以自定义LayoutManager实现
        if (mOnExpandListener != null) {
            mOnExpandListener.onExpand(true, vh.getViewById(mExpandResId), position);
        }
    }


    /**
     * @param position 当前点击的条目的position
     * @return -1为未展开状态，其他为已经展开的条目的position
     */
    private int isExpand(int position) {
        for (int i = 0; i < mExpandableBeanList.size(); i++) {
            if (mExpandableBeanList.get(i).getStrType().equals(TYPE_CHILD)
                    && mExpandableBeanList.get(i).getIntPosition() == mExpandableBeanList.get(position).getIntPosition()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 记录一些属性的辅助类
     */
    public class ExpandFiled {
        //对应原始数据的List的位置
        private int mDataListPosition;
        //是否为展开状态
        private boolean mIsExpanded;
        //...可以扩展其他属性

        public int getDataListPosition() {
            return mDataListPosition;
        }

        public void setDataListPosition(int dataListPosition) {
            mDataListPosition = dataListPosition;
        }

        public boolean isExpanded() {
            return mIsExpanded;
        }

        public void setExpanded(boolean expanded) {
            mIsExpanded = expanded;
        }
    }


    /**
     * 处理使用的自定义辅助类
     */
    public class ExpandableBean {
        private P mP;
        private String mStrType;
        private int mIntPosition;
        private int mResId;
        private boolean isExpanded;

        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
        }

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
