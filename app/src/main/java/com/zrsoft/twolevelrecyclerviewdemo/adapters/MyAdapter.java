package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.prpr894.recycler.adapters.BaseRecyclerAdapter;
import com.prpr894.recycler.viewholders.BaseViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Student;

import java.util.List;

public class MyAdapter extends BaseRecyclerAdapter<Student> {

    public MyAdapter(List<Student> list, Context context) {
        super(R.layout.item, list, context);
    }

    @Override
    public void onBindViewHolderNow(Student student, @NonNull BaseViewHolder holder, int i) {
        ((ImageView) holder.getViewById(R.id.img_item)).setImageResource(R.mipmap.ic_launcher);
        holder.setText(R.id.tv_item, student.getName());
    }
}
