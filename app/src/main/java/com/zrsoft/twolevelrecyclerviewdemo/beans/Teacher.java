package com.zrsoft.twolevelrecyclerviewdemo.beans;

public class Teacher {

    private String name;
    private Student Student;
    private int Age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return Student;
    }

    public void setStudent(Student student) {
        Student = student;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
