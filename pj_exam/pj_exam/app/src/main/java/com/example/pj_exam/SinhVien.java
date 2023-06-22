package com.example.pj_exam;

public class SinhVien {
    private int id;
    private String sId;
    private String name;
    private double math;
    private double physical;
    private double chemistry;

    public SinhVien() {
    }

    public SinhVien(String sId, String name, double math, double physical, double chemistry) {
        this.sId = sId;
        this.name = name;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getPhysical() {
        return physical;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }
    public double getDiem(){
        return this.math + this.physical + this.chemistry;
    }
}
