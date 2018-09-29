package com.zrsoft.twolevelrecyclerviewdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.prpr894.recycler.adapters.BaseRecyclerAdapter;
import com.prpr894.recycler.viewholders.BaseViewHolder;
import com.zrsoft.twolevelrecyclerviewdemo.R;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Student;

import java.util.List;

public class MyAdapter extends BaseRecyclerAdapter<Student> {

    public MyAdapter(List<Student> list, Context context) {
        super(R.layout.item_child, list, context);
        Log.d("flag", " ===== List<Student> list的长度：" + list.size());
    }

    @Override
    public void onBindViewHolderNow(final Student student, @NonNull BaseViewHolder holder, int i) {
        holder.setText(R.id.tv_child, student.getName());
        RadioButton rbYes = (RadioButton) holder.getViewById(R.id.rb_yes);
        RadioButton rbNo = (RadioButton) holder.getViewById(R.id.rb_no);
        if (student.isYes()) {
            rbYes.setChecked(true);
        } else {
            rbNo.setChecked(true);
        }
        rbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    student.setYes(true);
                }
            }
        });
        rbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    student.setYes(false);
                }
            }
        });


    }
}
