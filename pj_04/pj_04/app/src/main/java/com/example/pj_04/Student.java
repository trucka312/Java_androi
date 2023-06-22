package com.example.pj_04;

public class Student {
    private int ID;
    private String Name;
    private float Point;

    public Student(String name, float point) {
        this.Name = name;
        this.Point = point;
    }

    public Student() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public float getPoint() {
        return Point;
    }

    public void setPoint(float point) {
        this.Point = point;
    }
}
