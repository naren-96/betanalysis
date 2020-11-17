package com.sixfor.betanalysis;

import com.sixfor.betanalysis.entity.OddInfo;
import com.sixfor.betanalysis.entity.RaceInfo;
import com.sixfor.betanalysis.entity.TeamInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import static java.lang.Thread.sleep;

public class jsouptest {


    private static List<WebElement> webElementList=new ArrayList<WebElement>();
    private static List<WebElement> webElementList_real = Collections.synchronizedList(webElementList);
    private static WebDriver webDriver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        try{

            //沙巴 断线        filter oddsTableStatus-offline
            //     连线       filter oddsTableStatus-connecting


/*            String str1 = "Qizilqum Zarafshon (W)";
            String str2 = "Qizilqum Zarafshon Women";
            levenshtein(str1.toLowerCase(),str2.toLowerCase());*/

            String chrome_driver = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";  //chromedriver的文件位置
            System.setProperty("webdriver.chrome.driver", chrome_driver);
            webDriver = new ChromeDriver();
            wait = new WebDriverWait(webDriver, 60);

            WaitLoading("https://vwin158.com/cn/zh/Game/SBsport");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    static void WaitLoading(String url){
        try{

            Timer timergetrealrace=new Timer();
            Timer timergetodds=new Timer();

            webDriver.get(url);

            wait = new WebDriverWait(webDriver, 60);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maincontent")));

            WebElement webElement =webDriver.findElement(By.id("maincontent"));//.findElement(By.id("sportsFrame"));
            webDriver.switchTo().frame(webElement);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sportsFrame")));

            webElement=webDriver.findElement(By.id("sportsFrame"));
            webDriver.switchTo().frame(webElement);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ class ='item  icon-sportsMenu-live']")));

            webElement =webDriver.findElement(By.xpath("//div[@ class ='item  icon-sportsMenu-live']"));
            webElement.click();

            sleep(2000);
            webElement = webDriver.findElement(By.xpath("//li[@ class ='category-sportList icon-sportAll']"));
            webElement.click();

            sleep(2000);
            webElement = webDriver.findElement(By.xpath("//li[@ class ='category-sportList icon-sport1']"));
            webElement.click();

            sleep(2000);
            webElement = webDriver.findElement(By.xpath("//div[@ class ='selected icon-malayOdds']"));
            webElement.click();

            sleep(2000);
            webElement = webDriver.findElement(By.xpath("//div[@ class ='content icon-decimalOdds']"));
            webElement.click();

/*            try{
                sleep(2000);
                List<WebElement> webElements = webDriver.findElements(By.xpath("//div[@ class ='multiOdds multiOdds--more-lines']"));//GetWebElementFromElement(_webElement,"div","class ='multiOdds multiOdds--more-lines'");// _webElement.findElement(By.xpath(".//div[@ class ='multiOdds multiOdds--more-lines']"));
                for (WebElement _webElement:webElements) {
                    _webElement.click();
                }
            }catch (Exception ex){

            }*/

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ class ='leagueGroup']")));
            List<WebElement> webElementList= webDriver.findElements(By.xpath("//div[@ class ='leagueGroup']"));///child::button[@ class ='accent icon-soccer smallBtn']

            for (WebElement _webElement:webElementList) {
                try{
                    String raceName=_webElement.getAttribute("innerHTML");
                    if(!raceName.contains("虚拟")){
                        try{
                            //sleep(2000);
                            WebElement webElement_more = _webElement.findElement(By.xpath(".//div[@ class ='multiOdds multiOdds--more-lines']"));
                            webElement_more.click();
                        }catch (Exception ex){

                        }
                        webElementList_real.add(_webElement);
                    }
                }catch (Exception ex){

                }
            }

            timergetodds.schedule(new TimerGetOdds(),0,2000);
            timergetrealrace.schedule(new TimerGetRealRace(),0,2000);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    static class TimerGetRealRace extends TimerTask {

        @Override
        public void run() {


            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ class ='leagueGroup']")));
            List<WebElement> webElementList= webDriver.findElements(By.xpath("//div[@ class ='leagueGroup']"));///child::button[@ class ='accent icon-soccer smallBtn']

            for (WebElement _webElement:webElementList) {
                try{
                    String raceName=_webElement.getAttribute("innerHTML");
                    if(!raceName.contains("虚拟")){
                        if(!webElementList_real.contains(_webElement)){

                            try{
                                //sleep(2000);
                                WebElement webElement_more = _webElement.findElement(By.xpath(".//div[@ class ='multiOdds multiOdds--more-lines']"));
                                webElement_more.click();
                            }catch (Exception ex){

                            }
                            webElementList_real.add(_webElement);
                            System.out.println("update");
                        }
                    }
                }catch (Exception ex){

                }
            }
        }
    }


    static class TimerGetOdds extends TimerTask {
        @Override
        public void run() {
            for (int i=0;i<webElementList_real.size();i++) {
                WebElement _webElement=webElementList_real.get(i);
                try {
                    List<RaceInfo> raceInfoList =new ArrayList<RaceInfo>();
                    List<WebElement> webElementList_teamdivs=_webElement.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']/parent::div/parent::div/parent::div/parent::div/parent::div"));

                    for (WebElement _webElement_teamdiv:webElementList_teamdivs) {
                        RaceInfo raceInfo =new RaceInfo();
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
                        int step=0;//用于标注是哪个类型的赔率 全场让球 全场大/小 上半场让球 上半场大/小
                        for (WebElement _webElementList_subtxt:webElementList_subtxts) {
                            seq=0;
                            List<WebElement> webElementList_betAreaList=_webElementList_subtxt.findElements(By.xpath(".//div[@ class ='betArea']"));
                            for (WebElement _webElement_betArea:webElementList_betAreaList) {
                                OddInfo oddInfo=new OddInfo();
                                switch (step%4){
                                    case 0:
                                        oddInfo.setOddname("全场让球");
                                        break;
                                    case 1:
                                        oddInfo.setOddname("全场大/小");
                                        break;
                                    case 2:
                                        oddInfo.setOddname("上半场让球");
                                        break;
                                    case 3:
                                        oddInfo.setOddname("上半场大/小");
                                        break;
                                }

                                try{//betArea里面的有时候是没有元素的
                                    String oddtype=_webElement_betArea.findElement(By.xpath(".//span[@ class ='txt']")).getAttribute("innerText");
                                    String odd=_webElement_betArea.findElement(By.xpath(".//div[contains(@class,'oddsBet')]")).getAttribute("innerText");

                                    if(seq==0){//两行数据的上面的数据
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
                                    }else{//两行数据的下面的数据
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
                                    if(seq==0){//两行数据的上面的数据
                                        raceInfo.getTeamInfo1().getOddInfos().add(oddInfo);
                                        seq++;
                                    }else{//两行数据的下面的数据
                                        raceInfo.getTeamInfo2().getOddInfos().add(oddInfo);
                                        seq=0;
                                    }
                                }


                            }
                            step++;
                        }
                        System.out.println("raceInfo=" + raceInfo.toString());
                    }


                }catch (Exception ex){
                    webElementList_real.remove(_webElement);
                }
            }
        }
    }
}
