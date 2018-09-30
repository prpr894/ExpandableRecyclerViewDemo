package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.prpr894.recycler.adapters.ExpandableRecyclerAdapter;
import com.prpr894.recycler.interfaces.OnExpandListener;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Teacher;

import java.util.List;

public class MyExRecyclerAdapter extends ExpandableRecyclerAdapter<Teacher> implements OnExpandListener {

    private Context mContext;

    public MyExRecyclerAdapter(List<Teacher> teachers, Context context) {
        super(R.layout.item, R.layout.child, teachers, context);
        mContext = context;
        setExpandClickResId(R.id.img_item);//设置点击展开的按钮，在onBindViewHolderNow如果再次设置点击事件会覆盖，不设置默认是整个条目
        setOnExpandListener(R.id.img_item, this);//设置展开监听
    }


    @Override
    public void onBindViewHolderNow(final Teacher teacher, final ExpandableViewHolder vh, final int position, int resId, ExpandFiled expandFiled) {
        switch (resId) {
            case R.layout.item:
                vh.setText(R.id.tv_item, teacher.getName());
                ImageView imageView = (ImageView) vh.getViewById(R.id.img_item);
                imageView.setImageResource(R.drawable.ic_right);
                //获取数据中存储的展开状态，回复ImageView因为复用导致的展开状态失效
                animationState(expandFiled.isExpanded(), imageView);
                final RadioButton rbYes = (RadioButton) vh.getViewById(R.id.rb_yes_parent);
                final RadioButton rbNo = (RadioButton) vh.getViewById(R.id.rb_no_parent);
                rbYes.setTag(vh);
                rbNo.setTag(vh);

                if (((ExpandableViewHolder) rbYes.getTag()).getAdapterPosition() == position && ((ExpandableViewHolder) rbNo.getTag()).getAdapterPosition() == position) {
                    teacher.setCanChange(false);
                    if (teacher.isYes()) {
                        rbYes.setChecked(true);
                    } else {
                        rbNo.setChecked(true);
                    }
                }

                rbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (((ExpandableViewHolder) rbYes.getTag()).getAdapterPosition() == position) {
                                if (teacher.isCanChange()) {
                                    expandChild(((ExpandableViewHolder) rbYes.getTag()));
                                    teacher.setYes(true);
                                } else {
                                    teacher.setCanChange(true);
                                }
                            }
                        }
                    }
                });


                rbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            if (((ExpandableViewHolder) rbNo.getTag()).getAdapterPosition() == position) {
                                if (teacher.isCanChange()) {
                                    expandChild(((ExpandableViewHolder) rbNo.getTag()));
                                    teacher.setYes(false);
                                } else {
                                    teacher.setCanChange(true);
                                }
                            }
                        }
                    }
                });


                break;
            case R.layout.child:
                RecyclerView recyclerView = (RecyclerView) vh.getViewById(R.id.recycler_child);
                Log.d("flag", " ----- List<Student> list的长度：" + teacher.getStudent().size());
                recyclerView.setTag(vh);
                if (((ExpandableViewHolder) recyclerView.getTag()).getAdapterPosition() == position) {
                    MyAdapter adapter = new MyAdapter(teacher.getStudent(), mContext);
                    recyclerView.setAdapter(adapter);
                }
                break;
        }
    }

    @Override
    public void onExpand(boolean isExpanding, View view, int position) {
        //此处可以做动画
        animationState(isExpanding, view);
    }

    private void animationState(boolean isExpanding, View view) {
        if (isExpanding) {
            view.setRotation(90);
        } else {
            view.setRotation(0);
        }
    }
}
