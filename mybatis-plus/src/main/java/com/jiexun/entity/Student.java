package com.jiexun.entity;

public class Student {
    private Integer id;
    private String stuName;
    private Integer stuNum;
    private String classNum;

    public Student() {
    }

    public Student(Integer id, String stuName, Integer stuNum, String classNum) {
        this.id = id;
        this.stuName = stuName;
        this.stuNum = stuNum;
        this.classNum = classNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", stuNum=" + stuNum +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}
