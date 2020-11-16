package com.sixfor.betanalysis.entity;

public class BetInfo {
    private String bettype;
    private double betval;
    private boolean big;

    public String getBettype() {
        return bettype;
    }

    public void setBettype(String bettype) {
        this.bettype = bettype;
    }

    public double getBetval() {
        return betval;
    }

    public void setBetval(double betval) {
        this.betval = betval;
    }

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return "BetInfo{" +
                "bettype='" + bettype + '\'' +
                ", betval=" + betval +
                ", big=" + big +
                '}';
    }
}
