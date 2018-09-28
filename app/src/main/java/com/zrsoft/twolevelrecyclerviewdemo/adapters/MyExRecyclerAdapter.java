package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.prpr894.recycler.adapters.ExpandableRecyclerAdapter;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Teacher;

import java.util.List;

public class MyExRecyclerAdapter extends ExpandableRecyclerAdapter<Teacher> {


    public MyExRecyclerAdapter(List<Teacher> teachers, Context context) {
        super(R.layout.item, R.layout.item_child, teachers, context);
        setExpandClickResId(R.id.img_item);//设置点击展开的按钮，在onBindViewHolderNow如果再次设置点击事件会覆盖，不设置默认是整个条目
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
}
