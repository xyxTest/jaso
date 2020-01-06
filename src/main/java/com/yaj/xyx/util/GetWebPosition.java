package com.yaj.xyx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class GetWebPosition {
	/**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       /* String info = GetDataByTwo();
        System.out.println(info);*/
    }

    // 从指定的url中获取数据
    //https://www.marinetraffic.com/en/ais/details/ships/shipid:650235/mmsi:414726000/vessel:YU%20MING
        private static String HttpRequest(String requestUrl) {
   
            StringBuffer buffer = null;
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            InputStream inputStream = null;
            HttpsURLConnection httpUrlConn = null;
            // 建立并向网页发送请求
            try {
                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();

                URL url = new URL(requestUrl);
                // 描述状态
                httpUrlConn = (HttpsURLConnection) url.openConnection();
                httpUrlConn.setSSLSocketFactory(ssf);
                httpUrlConn
                .setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
                //防止报403错误。
                httpUrlConn.setDoOutput(true);
                httpUrlConn.setDoInput(true);
                httpUrlConn.setUseCaches(false);
                // 请求的类型
                httpUrlConn.setRequestMethod("GET");
                // 获取输入流
                inputStream = httpUrlConn.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                // 从输入流读取结果
                buffer = new StringBuffer();
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放资源
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
            }
            return buffer.toString();
        }
    private static String HtmlFiter(String html) {

        StringBuffer buffer = new StringBuffer();
        String str1 = "";
        String result=null;
        //取出所用的范围,
        //Pattern p = Pattern.compile("(.*)(<div class=\"panel panel-primary no-border vertical-offset-20\">)(.*)(</div>)(.*)");
        Pattern p = Pattern.compile("(.*)(og:image)(.*)");
        Matcher m = p.matcher(html);
        if (m.matches()) {
            str1 = m.group(3);
            int strStartIndex = str1.indexOf("http");
            int strEndIndex = str1.indexOf("jpeg");
            if(strStartIndex>0 && strEndIndex>0){
            	result = str1.substring(strStartIndex, strEndIndex);
            }
            //取得时间：Vessel's Local Time:
        }
        return result+"jpeg";
    }

        //封裝上述两个方法
        public static String GetDataByTwo(String htmls){
            //调用第一个方法,获得html字符串
            String html = HttpRequest(htmls);
            //调用第二个方法,过滤掉无用的信息
            String result = HtmlFiter(html);
            return result;
        }

}
