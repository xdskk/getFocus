import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetFocus {
	static String Cookie = "";
    public static void main(String[] args) throws IOException {
		 Cookie = args[0];
        String biliUrl = "https://api.bilibili.com/x/space/acc/info?mid=107489721&jsonp=jsonp";
        String sendResult = getFocus(Cookie,biliUrl);
        System.out.println(sendResult);
        sendMessage("https://sc.ftqq.com/SCU114626Tbe6635964a7678562ba694d97d1d75aa5f6755d57fad5.send",sendResult);
    }
    public static String getFocus(String Cookie,String biliUrl) throws IOException {
        URL url = new URL(biliUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setRequestProperty("Cookie",Cookie);
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String sendResult = in.readLine();
        System.out.println(sendResult);
        return sendResult;

    }
    public static String sendMessage(String messageUrl,String sendResult) throws IOException {
        String value = toURLEncoded(sendResult);
        messageUrl = messageUrl+"?text=1111&desp="+value;
        System.out.println(messageUrl);
        URL url = new URL(messageUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String sendMessageResult = in.readLine();
        System.out.println(sendMessageResult);
        return sendMessageResult;
    }

    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            System.out.println("toURLEncoded error:"+paramString);
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            //System.out.println("toURLEncoded error:"+paramString, localException);
        }

        return "";
    }

    }
