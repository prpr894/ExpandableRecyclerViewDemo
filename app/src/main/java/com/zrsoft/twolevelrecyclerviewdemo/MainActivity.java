package com.zrsoft.twolevelrecyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.zrsoft.twolevelrecyclerviewdemo.adapters.MyAdapter;
import com.zrsoft.twolevelrecyclerviewdemo.beans.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Student student = new Student();
            student.setName(" 学生 " + i);
            list.add(student);
        }
        MyAdapter adapter = new MyAdapter(list, this);
        mRecyclerView.setAdapter(adapter);
    }
}
