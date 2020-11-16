package com.sixfor.betanalysis.entity;

public class OddInfo {
    private String oddtype;
    private double bigodd;
    private double smallodd;

    public String getOddtype() {
        return oddtype;
    }

    public void setOddtype(String oddtype) {
        this.oddtype = oddtype;
    }

    public double getBigodd() {
        return bigodd;
    }

    public void setBigodd(double bigodd) {
        this.bigodd = bigodd;
    }

    public double getSmallodd() {
        return smallodd;
    }

    public void setSmallodd(double smallodd) {
        this.smallodd = smallodd;
    }

    @Override
    public String toString() {
        return "OddInfo{" +
                "oddtype='" + oddtype + '\'' +
                ", bigodd=" + bigodd +
                ", smallodd=" + smallodd +
                '}';
    }
}


