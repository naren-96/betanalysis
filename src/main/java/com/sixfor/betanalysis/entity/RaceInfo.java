package com.sixfor.betanalysis.entity;

import java.util.ArrayList;
import java.util.List;

public class RaceInfo {
    private String time="";//比赛进程
    private TeamInfo teamInfo1;
    private TeamInfo teamInfo2;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TeamInfo getTeamInfo1() {
        return teamInfo1;
    }

    public void setTeamInfo1(TeamInfo teamInfo1) {
        this.teamInfo1 = teamInfo1;
    }

    public TeamInfo getTeamInfo2() {
        return teamInfo2;
    }

    public void setTeamInfo2(TeamInfo teamInfo2) {
        this.teamInfo2 = teamInfo2;
    }

    @Override
    public String toString() {
        return "RaceInfo{" +
                "time='" + time + '\'' +
                ", teamInfo1=" + teamInfo1 +
                ", teamInfo2=" + teamInfo2 +
                '}';
    }
}
