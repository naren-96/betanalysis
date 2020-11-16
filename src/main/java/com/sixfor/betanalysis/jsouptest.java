package com.sixfor.betanalysis;

import com.sixfor.betanalysis.Proccess.TimerProccess;
import com.sixfor.betanalysis.entity.OddInfo;
import com.sixfor.betanalysis.entity.TeamInfo;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;

import javax.net.ssl.*;

import static java.lang.Thread.sleep;

public class jsouptest {
    public static void main(String[] args) {
        try{
            /*String str1 = "Qizilqum Zarafshon (W)";
            String str2 = "Qizilqum Zarafshon Women";
            levenshtein(str1.toLowerCase(),str2.toLowerCase());*/

            WaitLoading("https://vwin158.com/cn/zh/Game/SBsport");



            //withLog("https://fbw.l0079.vwfa888.com/(S(5tb5khmdavzpdqfnlrr1xvts4jdt3EgrskimNSK5T_kWfFLMhcQZZ))/Sports/?market=L&mode=m0");
            //all( "https://vwin158.com/cn/zh/Game/SBsport");//"https://vwin158.com/cn/zh/Game/SBsport"
            //HaveATry();
            //WebSocket();
            //newWebSocket();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    static void WaitLoading(String url){
        try{

            Timer timer=new Timer();

            String chrome_driver = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";  //chromedriver的文件位置
            //url="https://fbw.l0079.vwfa888.com/(S(0we1c1l35o3nyo2goupupy2knex1rEgrskimNSK5T_kWfFLMhcQZZ))/Sports/?market=L&mode=m0";
            System.setProperty("webdriver.chrome.driver", chrome_driver);
            WebDriver webDriver = new ChromeDriver();
            webDriver.get(url);

            WebDriverWait wait = new WebDriverWait(webDriver, 60);

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

            try{
                sleep(2000);
                List<WebElement> webElements = webDriver.findElements(By.xpath("//div[@ class ='multiOdds multiOdds--more-lines']"));//GetWebElementFromElement(_webElement,"div","class ='multiOdds multiOdds--more-lines'");// _webElement.findElement(By.xpath(".//div[@ class ='multiOdds multiOdds--more-lines']"));
                for (WebElement _webElement:webElements) {
                    _webElement.click();
                }
            }catch (Exception ex){

            }



            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@ class ='leagueGroup']")));
            List<WebElement> webElementList= webDriver.findElements(By.xpath("//div[@ class ='leagueGroup']"));///child::button[@ class ='accent icon-soccer smallBtn']
            List<WebElement> webElementList_real=new ArrayList<WebElement>();
            for (WebElement _webElement:webElementList) {
                try{
                    //WebElement webElement_temp=_webElement.findElement(By.xpath(".//div[@ class ='leagueName']"));//这个.很重要，不加的话从全部文档中查找
                    WebElement webElement_temp=GetWebElementFromElement(_webElement,"div","class ='leagueName'");
                    String raceName=_webElement.getAttribute("innerHTML");
                    if(!raceName.contains("虚拟")){
                        webElementList_real.add(_webElement);
                    }
                }catch (Exception ex){

                }
            }

            timer.schedule(new TimerProccess(webElementList_real),0,2000);

            /*for (WebElement _webElement:webElementList_real) {
                List<TeamInfo> teamInfoList =new ArrayList<TeamInfo>();
                List<WebElement> webElementList_teamdivs=_webElement.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']/parent::div/parent::div/parent::div/parent::div/parent::div"));
                TeamInfo teamInfo =new TeamInfo();
                for (WebElement _webElement_teamdiv:webElementList_teamdivs) {

                    List<WebElement> webElementList_teaminfos=_webElement_teamdiv.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']"));

                    int seq=0;
                    for (WebElement _webElementList_teaminfo:webElementList_teaminfos) {
                        String teamname =_webElementList_teaminfo.getAttribute("innerText");
                        if(teamname.length()>0 && !teamname.contains("和局")){
                            if(seq==0){
                                teamInfo.setName1(teamname);
                                seq++;
                            }else{
                                teamInfo.setName2(teamname);
                                teamInfoList.add(teamInfo);
                                seq=0;
                            }
                        }
                    }

                    List<WebElement> webElementList_subtxts=_webElement_teamdiv.findElements(By.xpath(".//div[@ class ='odds subtxt']/div[@ class ='betArea']/span[contains(@class,'txt')]/parent::div/parent::div"));
                    for (WebElement _webElementList_subtxt:webElementList_subtxts) {
                        List<WebElement> webElementList_betAreaList=_webElementList_subtxt.findElements(By.xpath(".//div[@ class ='betArea']"));
                        OddInfo oddInfo=new OddInfo();
                        for (WebElement _webElement_betArea:webElementList_betAreaList) {
                            try{//betArea里面的有时候是没有元素的
                                String oddtype=_webElement_betArea.findElement(By.xpath(".//span[@ class ='txt']")).getAttribute("innerText");
                                String odd=_webElement_betArea.findElement(By.xpath(".//div[@ class ='oddsBet']")).getAttribute("innerText");
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
                    }
                }

                teamInfoList.add(teamInfo);
                System.out.println("teamInfo=" + teamInfo.toString());


            }*/


            /*List<TeamInfo> teamInfoList =new ArrayList<TeamInfo>();
            for (WebElement _webElement:webElementList_real) {
                //开始分析球队名称
                int seq=0;
                List<WebElement> webElementList_teams=_webElement.findElements(By.xpath(".//div[@ class ='team']/div/div[@ class ='text']"));//GetWebElementsFromElement(_webElement,"div","class ='team'");//


                TeamInfo teamInfo =new TeamInfo();
                for (WebElement _webElement_team:webElementList_teams) {
                    String teamname =_webElement_team.getAttribute("innerText");
                    if(teamname.length()>0 && !teamname.contains("和局")){
                        if(seq==0){
                            teamInfo.setName1(teamname);
                            seq++;
                        }else{
                            teamInfo.setName2(teamname);
                            teamInfoList.add(teamInfo);
                            seq=0;
                        }

                    }
                }




                //开始分析赔率
                if(teamInfo.getName1().length()>0 && teamInfo.getName2().length()>0){
                    List<OddInfo> oddInfoList=new ArrayList<OddInfo>();
                    List<WebElement> webElementList_odds=GetWebElementsFromElement(_webElement,"div","class ='multiOdds'");//_webElement.findElements(By.xpath(".//div[@ class ='multiOdds']"));
                    for (WebElement _webElementList_odd:webElementList_odds) {
                        List<WebElement> webElementList_subtxts=GetWebElementsFromElement(_webElementList_odd,"div","class ='odds subtxt'");//_webElement.findElements(By.xpath(".//div[@ class ='odds subtxt']"));
                        for (WebElement _webElementList_subtxt:webElementList_subtxts) {
                            List<WebElement> webElementList_betAreas=_webElementList_subtxt.findElements(By.xpath(".//div[@ class ='betArea']/span[contains(@class,'txt')]/parent::div"));//GetWebElementsFromElement(_webElementList_subtxt,"div","class ='betArea'");
                            OddInfo oddInfo=new OddInfo();
                            for (WebElement _webElementList_betArea:webElementList_betAreas) {
                                try{//betArea里面的有时候是没有元素的
                                    String oddtype=GetWebElementFromElement(_webElementList_betArea,"span","class ='txt'").getAttribute("innerText");
                                    String odd=GetWebElementFromElement(_webElementList_betArea,"div","class ='oddsBet'").getAttribute("innerText");
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
                                oddInfoList.add(oddInfo);
                            }

                        }
                    }
                    teamInfo.setOddInfos(oddInfoList);
                    System.out.println("teamInfo=" + teamInfo.toString());
                }



            }*/











        }catch (Exception ex){
            ex.printStackTrace();
        }



    }





    private static WebElement GetWebElementFromElement(WebElement webElement,String eleType,String path){
        try {
            return webElement.findElement(By.xpath(".//" + eleType + "[@ " + path + "]"));//这个.很重要，不加的话从全部文档中查找
        }catch (Exception ex){
            return null;
        }
    }

    private static List<WebElement> GetWebElementsFromElement(WebElement webElement,String eleType,String path){
        try {
            return webElement.findElements(By.xpath(".//" + eleType + "[@ " + path + "]"));//这个.很重要，不加的话从全部文档中查找
        }catch (Exception ex){
            return new ArrayList<WebElement>();
        }
    }



    static void all(String url){
        try{
            String chrome_driver = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";  //chromedriver的文件位置
            //url="https://fbw.l0079.vwfa888.com/(S(0we1c1l35o3nyo2goupupy2knex1rEgrskimNSK5T_kWfFLMhcQZZ))/Sports/?market=L&mode=m0";
            System.setProperty("webdriver.chrome.driver", chrome_driver);

            DesiredCapabilities caps = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


            ChromeOptions options=new ChromeOptions();
            //设置 chrome 的无头模式
/*            options.addArguments("--headless");
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-maximized");

            options.addArguments("--window-size=1280,4300");//因为报表页面必须滚动才能全部展示，这里直接给个很大的高度

            */
            //启动一个 chrome 实例

            WebDriver webDriver = new ChromeDriver(options);
            WebElement webElement =null;
            Actions action = new Actions(webDriver);


            webDriver.get(url);
            //Document document = Jsoup.parse(webDriver.getPageSource());

            //Document document = Jsoup.connect(url).timeout(6000).get();
            //Element element = document.getElementById("maincontent");//.getElementById("sportsFrame");
            webElement = webDriver.findElement(By.id("maincontent"));
            url=webElement.getAttribute("src");
            System.out.println(url);
            webDriver.get(url);

            while(webDriver.findElement(By.id("sportsFrame"))==null){
                sleep(1000);
            }
            webElement = webDriver.findElement(By.id("sportsFrame"));
            url=webElement.getAttribute("src");
            System.out.println(url);
            webDriver.get(url);

            url=webDriver.getCurrentUrl();

            while(!url.contains("market=T")){
                sleep(1000);
            }


            url=url.replace("market=T","market=L");
            System.out.println(url);
            webDriver.get(url);



            sleep(10000);
            webElement = webDriver.findElement(By.xpath("//li[@ class ='category-sportList icon-sportAll']"));//.findElement(By.xpath("//div[@ class ='checkbox checkbox-checked']"));
            webElement.click();
            //action.click(webElement).perform();
            //action.release(webElement);

            sleep(10000);
            //webElement = webDriver.findElement(By.xpath("//li[@ class ='category-sportList icon-sport997']")).findElement(By.xpath("//div[@ class ='checkbox']"));
            webElement = webDriver.findElement(By.xpath("//li[@ class ='category-sportList icon-sport1']"));
            System.out.println(webElement.getAttribute("class"));
            webElement.click();
            action.click(webElement).perform();
            action.release(webElement);

            action.click(webElement).perform();
            action.release().perform();



        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    //
    static void withLog(String url){
        try{
            String chrome_driver = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chrome_driver);

            DesiredCapabilities cap = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            WebDriver driver = new ChromeDriver(cap);

            driver.get(url);
            sleep(30000);
            LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);

            for (LogEntry entry : logEntries) {
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " ======" + entry.getMessage());
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel().INFO + "  INFO======== " + entry.getMessage());
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel().ALL + " ALL ======== " + entry.getMessage());
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel().SEVERE + " SEVERE ======== " + entry.getMessage());
                System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel().WARNING + " WARNING ======== " + entry.getMessage());
                //do something useful with the data
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    static void HaveATry(){
        try{
            WebDriver driver;
            System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
            LoggingPreferences loggingprefs = new LoggingPreferences();
            loggingprefs.enable(LogType.PERFORMANCE, Level.ALL);

            DesiredCapabilities cap = new DesiredCapabilities().chrome();
            cap.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);

            driver = new ChromeDriver(cap);
            driver.navigate().to("https://vwin158.com/cn/zh/Game/SBsport");
            Thread.sleep(5000);
            LogEntries logEntries = driver.manage().logs().get(LogType.CLIENT);

            driver.close();
            driver.quit();
            logEntries.forEach(entry->{
                JSONObject messageJSON = new JSONObject(entry.getMessage());
                String method = messageJSON.getJSONObject("message").getString("method");
                if(method.equalsIgnoreCase("Network.webSocketFrameSent")){
                    System.out.println("Message Sent: " + messageJSON.getJSONObject("message").getJSONObject("params").getJSONObject("response").getString("payloadData"));
                }else if(method.equalsIgnoreCase("Network.webSocketFrameReceived")){
                    System.out.println("Message Received: " + messageJSON.getJSONObject("message").getJSONObject("params").getJSONObject("response").getString("payloadData"));
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }




    static void WebSocket() {

        try {
            WebSocketClientInst chatclient = new WebSocketClientInst(new URI("wss://3qvsm5.64sf8.cn/socket.io/?gid=d2d58185c2e31437&token=72c990a0-a71e-4929-a575-557343ffa170&id=5tb5kq1lrrojqg0bx535wgqvotn5xEgrskimNSK5T_kWfFLMhcQZZ&rid=0&EIO=3&transport=websocket&sid=XAboa0wZ_YrJ2bAQAFXX"));


            chatclient.addHeader("Request Method","GET");
            chatclient.addHeader("Accept-Encoding","gzip, deflate, br");
            chatclient.addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");
            chatclient.addHeader("Cache-Control","no-cache");
            chatclient.addHeader("Connection","Upgrade");
            chatclient.addHeader("Host","3qvsm5.64sf8.cn");
            chatclient.addHeader("Origin","https://fbw.l0079.vwfa888.com");
            chatclient.addHeader("Pragma","no-cache");
            chatclient.addHeader("Sec-WebSocket-Extensions","permessage-deflate; client_max_window_bits");
            //chatclient.addHeader("Sec-WebSocket-Key","vBOvpRwA2Nk5TuOPqUmPsA==");
            chatclient.addHeader("Sec-WebSocket-Version","13");
            chatclient.addHeader("Upgrade","websocket");
            chatclient.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36");



            /*// load up the key store
            String KEYSTORE = "xx/xxxx/my.store";  //基于证书生成的store秘钥文件的路径
            String STOREPASSWORD = "123456";  //使用keytool工具时，输入的密码
            String KEYPASSWORD = "123456";

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            File kf = new File(KEYSTORE);
            ks.load(new FileInputStream(kf), STOREPASSWORD.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

            kmf.init(ks, KEYPASSWORD.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

            chatclient.setSocketFactory(factory);*/

            trustAllHosts(chatclient);
            chatclient.connectBlocking();


        } catch (Exception ex) {

        }
    }

    private static void trustAllHosts(WebSocketClientInst chatclient) {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }


            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }


            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};


        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory factory = sc.getSocketFactory();

            chatclient.setSocketFactory(factory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void newWebSocket(){
        try{
            WebSocketClientInst chatclient = new WebSocketClientInst( new URI( "wss://3qvsm5.64sf8.cn/socket.io/?gid=6325adee610eeae6&token=1751a189-9493-4972-81a1-c60a272c5438&id=5tb5kmuaoa4o2yzistqsrd4ii5yiuEgrskimNSK5T_kWfFLMhcQZZ&rid=1&EIO=3&transport=websocket&sid=ddGWqaoDwjYnE33tAFRC" ) );

            // load up the key store
            String KEYSTORE = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\my.store";  //基于证书生成的store秘钥文件的路径
            String STOREPASSWORD = "198211";  //使用keytool工具时，输入的密码
            String KEYPASSWORD = "198211";

            KeyStore ks = KeyStore.getInstance( KeyStore.getDefaultType() );
            File kf = new File( KEYSTORE );
            ks.load( new FileInputStream( kf ), STOREPASSWORD.toCharArray() );

            KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );

            kmf.init( ks, KEYPASSWORD.toCharArray() );
            TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
            tmf.init( ks );

            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance( "TLS" );
            sslContext.init( kmf.getKeyManagers(), tmf.getTrustManagers(), null );

            SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

            chatclient.setSocketFactory( factory );

            chatclient.connectBlocking();

/*            boolean loop = true;
            int times = 0;
            while (loop){

            }*/
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }



    public static void levenshtein(String str1,String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
        System.out.println("差异步骤："+dif[len1][len2]);
        //计算相似度
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        System.out.println("相似度："+similarity);
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }



}
