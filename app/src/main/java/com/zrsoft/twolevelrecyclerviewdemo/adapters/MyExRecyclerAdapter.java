package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.prpr894.recycler.adapters.ExpandableRecyclerAdapter;
import com.prpr894.recycler.viewholders.ExpandableViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Student;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Teacher;

import java.util.List;

public class MyExRecyclerAdapter extends ExpandableRecyclerAdapter<Teacher, Student, ExpandableViewHolder> {


    public MyExRecyclerAdapter(List<Teacher> teachers, Context context) {
        super(R.layout.item, 2, teachers, context);
    }

    @Override
    public void onBindViewHolderNow(Teacher teacher, ExpandableViewHolder vh, int i) {
        vh.setText(R.id.tv_item, teacher.getName());
        ((ImageView) vh.getViewById(R.id.img_item)).setImageResource(R.mipmap.ic_launcher);
    }
}
