package com.sixfor.betanalysis.entity;

public class OddInfo {
    private String oddname;
    private String oddtype;
    private double oddval;
    private boolean big;

    public String getOddname() {
        return oddname;
    }

    public void setOddname(String oddname) {
        this.oddname = oddname;
    }

    public String getOddtype() {
        return oddtype;
    }

    public void setOddtype(String oddtype) {
        this.oddtype = oddtype;
    }

    public double getOddval() {
        return oddval;
    }

    public void setOddval(double oddval) {
        this.oddval = oddval;
    }

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return "OddInfo{" +
                "oddname='" + oddname + '\'' +
                ", oddtype='" + oddtype + '\'' +
                ", oddval=" + oddval +
                ", big=" + big +
                '}';
    }
}


