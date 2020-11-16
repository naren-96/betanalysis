package com.sixfor.betanalysis.entity;

import java.util.ArrayList;
import java.util.List;

public class TeamInfo {
    private String name="";//名称
    private List<OddInfo> oddInfos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OddInfo> getOddInfos() {
        return oddInfos;
    }

    public void setOddInfos(List<OddInfo> oddInfos) {
        this.oddInfos = oddInfos;
    }

    public TeamInfo(String name) {
        this.name = name;
        this.oddInfos=new ArrayList<OddInfo>();
    }

    @Override
    public String toString() {
        return "TeamInfo{" +
                "name='" + name + '\'' +
                ", oddInfos=" + oddInfos.toString() +
                '}';
    }
}
