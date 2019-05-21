package com.jiexun.entity;

public class Student {
    private Integer id;
    private String stu_name;
    private Integer stu_num;
    private String class_num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Integer getStu_num() {
        return stu_num;
    }

    public void setStu_num(Integer stu_num) {
        this.stu_num = stu_num;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_num=" + stu_num +
                ", class_num='" + class_num + '\'' +
                '}';
    }
}
