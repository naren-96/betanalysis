package com.sixfor.betanalysis.entity;

import java.util.ArrayList;
import java.util.List;

public class TeamInfo {
    private String time="";//比赛进程
    private String name1="";//名称
    private String name2="";//名称
    private List<OddInfo> oddInfos=new ArrayList<OddInfo>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public List<OddInfo> getOddInfos() {
        return oddInfos;
    }

    public void setOddInfos(List<OddInfo> oddInfos) {
        this.oddInfos = oddInfos;
    }


    @Override
    public String toString() {
        return "TeamInfo{" +
                "time='" + time + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", oddInfos=" + oddInfos.toString() +
                '}';
    }
}
