package com.sixfor.betanalysis.entity;

public class OddInfo {
    private String oddtype;
    private double oddval;
    private boolean big;

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

    public boolean getBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return "OddInfo{" +
                "oddtype='" + oddtype + '\'' +
                ", oddval=" + oddval +
                ", big=" + big +
                '}';
    }
}


