package com.zrsoft.twolevelrecyclerviewdemo.beans;

import java.util.List;

public class Teacher {

    private String name;
    private List<Student> Student;
    private int Age;
    private boolean isYes;
    private boolean canChange;

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }

    public boolean isYes() {
        return isYes;
    }

    public void setYes(boolean yes) {
        isYes = yes;
    }

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
