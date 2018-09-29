package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.prpr894.recycler.adapters.ExpandableRecyclerAdapter;
import com.prpr894.recycler.interfaces.OnExpandListener;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Teacher;

import java.util.List;

public class MyExRecyclerAdapter extends ExpandableRecyclerAdapter<Teacher> implements OnExpandListener {


    public MyExRecyclerAdapter(List<Teacher> teachers, Context context) {
        super(R.layout.item, R.layout.item_child, teachers, context);
        setExpandClickResId(R.id.img_item);//设置点击展开的按钮，在onBindViewHolderNow如果再次设置点击事件会覆盖，不设置默认是整个条目
        setOnExpandListener(R.id.img_item,this);//设置展开监听
    }


    @Override
    public void onBindViewHolderNow(Teacher teacher, ExpandableViewHolder vh, int position, int resId) {
        switch (resId) {
            case R.layout.item:
                vh.setText(R.id.tv_item, teacher.getName());
                ImageView imageView = (ImageView) vh.getViewById(R.id.img_item);
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.layout.item_child:
                vh.setText(R.id.tv_child, teacher.getStudent().getName());
                break;
        }
    }

    @Override
    public void onExpand(boolean isExpanding, View view, int position) {
        //此处可以做动画
        if (isExpanding) {
            view.setRotation(90);
        } else {
            view.setRotation(0);
        }
    }
}
