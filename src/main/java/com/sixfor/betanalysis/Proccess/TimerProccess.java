package com.sixfor.betanalysis.Proccess;

import com.sixfor.betanalysis.entity.OddInfo;
import com.sixfor.betanalysis.entity.RaceInfo;
import com.sixfor.betanalysis.entity.TeamInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class TimerProccess extends TimerTask {
    private List<WebElement> webElementList;

    public TimerProccess(List<WebElement> webElementList) {
        this.webElementList=webElementList;
    }
    @Override
    public void run() {
        for (int i=0;i<webElementList.size();i++) {
            WebElement _webElement=webElementList.get(i);
            try {
                List<RaceInfo> raceInfoList =new ArrayList<RaceInfo>();
                List<WebElement> webElementList_teamdivs=_webElement.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']/parent::div/parent::div/parent::div/parent::div/parent::div"));
                RaceInfo raceInfo =new RaceInfo();
                for (WebElement _webElement_teamdiv:webElementList_teamdivs) {

                    List<WebElement> webElementList_teaminfos=_webElement_teamdiv.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']"));

                    int seq=0;
                    for (WebElement _webElementList_teaminfo:webElementList_teaminfos) {
                        String teamname =_webElementList_teaminfo.getAttribute("innerText");
                        if(teamname.length()>0 && !teamname.contains("和局")){
                            if(seq==0){
                                raceInfo.setTeamInfo1(new TeamInfo(teamname));
                                seq++;
                            }else{
                                raceInfo.setTeamInfo2(new TeamInfo(teamname));
                                seq=0;
                            }
                        }
                    }

                    List<WebElement> webElementList_subtxts=_webElement_teamdiv.findElements(By.xpath(".//div[@ class ='odds subtxt']/div[@ class ='betArea']/parent::div"));
                    for (WebElement _webElementList_subtxt:webElementList_subtxts) {
                        seq=0;
                        List<WebElement> webElementList_betAreaList=_webElementList_subtxt.findElements(By.xpath(".//div[@ class ='betArea']"));
                        for (WebElement _webElement_betArea:webElementList_betAreaList) {
                            try{//betArea里面的有时候是没有元素的
                                String oddtype=_webElement_betArea.findElement(By.xpath(".//span[@ class ='txt']")).getAttribute("innerText");
                                String odd=_webElement_betArea.findElement(By.xpath(".//div[contains(@class,'oddsBet')]")).getAttribute("innerText");
                                OddInfo oddInfo=new OddInfo();
                                if(seq==0){
                                    if(oddtype.length()==0 || oddtype.contains("小")){ ;
                                        oddInfo.setOddval(Double.parseDouble(odd));
                                        oddInfo.setBig(false);
                                        raceInfo.getTeamInfo1().getOddInfos().add(oddInfo);
                                    }else{
                                        oddInfo.setOddtype(oddtype);
                                        oddInfo.setOddval(Double.parseDouble(odd));
                                        oddInfo.setBig(true);
                                        raceInfo.getTeamInfo1().getOddInfos().add(oddInfo);
                                    }
                                    seq++;
                                }else{
                                    if(oddtype.length()==0 || oddtype.contains("小")){ ;
                                        oddInfo.setOddval(Double.parseDouble(odd));
                                        oddInfo.setBig(false);
                                        raceInfo.getTeamInfo2().getOddInfos().add(oddInfo);
                                        oddInfo.setOddtype(raceInfo.getTeamInfo1().getOddInfos().get(raceInfo.getTeamInfo1().getOddInfos().size()-1).getOddtype());
                                    }else{
                                        oddInfo.setOddtype(oddtype);
                                        oddInfo.setOddval(Double.parseDouble(odd));
                                        oddInfo.setBig(true);
                                        raceInfo.getTeamInfo2().getOddInfos().add(oddInfo);
                                        raceInfo.getTeamInfo1().getOddInfos().get(raceInfo.getTeamInfo1().getOddInfos().size()-1).setOddtype(oddtype);
                                    }
                                    seq=0;

                                }
                            }catch (Exception ex){
                                if(seq==0){
                                    raceInfo.getTeamInfo1().getOddInfos().add(new OddInfo());
                                    seq++;
                                }else{
                                    raceInfo.getTeamInfo2().getOddInfos().add(new OddInfo());
                                    seq=0;
                                }


                            }
                        }
                    }


                /*List<WebElement> webElementList_subtxts=_webElement_teamdiv.findElements(By.xpath(".//div[@ class ='odds subtxt']/div[@ class ='betArea']/span[contains(@class,'txt')]/parent::div/parent::div"));
                for (WebElement _webElementList_subtxt:webElementList_subtxts) {
                    List<WebElement> webElementList_betAreaList=_webElementList_subtxt.findElements(By.xpath(".//div[@ class ='betArea']"));
                    OddInfo oddInfo=new OddInfo();
                    for (WebElement _webElement_betArea:webElementList_betAreaList) {
                        try{//betArea里面的有时候是没有元素的
                            String oddtype=_webElement_betArea.findElement(By.xpath(".//span[@ class ='txt']")).getAttribute("innerText");
                            String odd=_webElement_betArea.findElement(By.xpath(".//div[contains(@class,'oddsBet')]")).getAttribute("innerText");
                            if(oddtype.length()==0 || oddtype.contains("小")){ ;
                                oddInfo.setSmallodd(Double.parseDouble(odd));
                            }else{
                                oddInfo.setOddtype(oddtype);
                                oddInfo.setBigodd(Double.parseDouble(odd));
                            }

                        }catch (Exception ex){

                        }
                    }
                    if(oddInfo.getOddtype()!=null){
                        teamInfo.getOddInfos().add(oddInfo);
                    }
                }*/
                }

                System.out.println("raceInfo=" + raceInfo.toString());
            }catch (Exception ex){
                webElementList.remove(_webElement);
            }
        }
    }
}
