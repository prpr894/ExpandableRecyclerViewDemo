package com.zrsoft.twolevelrecyclerviewdemo.beans;

import java.util.List;

public class Teacher {

    private String name;
    private List<Student> Student;
    private int Age;

    public List<Student> getStudent() {
        return Student;
    }

    public void setStudent(List<Student> student) {
        Student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
