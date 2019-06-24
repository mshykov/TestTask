package utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String strToMd5Hex(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String getTestEmailMessage(String md5) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/" + md5 + "/format/json/");
        httpGet.setHeader("X-RapidAPI-Host", "privatix-temp-mail-v1.p.rapidapi.com");
        httpGet.setHeader("X-RapidAPI-Key", "665bba64d7mshb44e4431b2b18a3p1035f5jsn5bcdd219ce51");

        HttpResponse response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        responseString = responseString.replaceAll("&amp;", "&");
        responseString = responseString.replaceAll("&lt;", "<");
        responseString = responseString.replaceAll("&gt;", ">");
        responseString = responseString.replaceAll("&quot;", "\"");
        responseString = responseString.replaceAll("&apos;", "'");
        responseString = responseString.replaceAll("\\\\/", "/");

        return responseString;
    }

    public static String getConfirmationLink(String text) {
        Pattern pattern = Pattern.compile("https://my\\.rozetka\\..*?Email");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }
}
