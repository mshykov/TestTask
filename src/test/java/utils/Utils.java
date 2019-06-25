package utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static String RapidApiUrl = "https://privatix-temp-mail-v1.p.rapidapi.com/request/";
    private static String RapidApiHost = "privatix-temp-mail-v1.p.rapidapi.com";
    private static String RapidApiKey = "665bba64d7mshb44e4431b2b18a3p1035f5jsn5bcdd219ce51";
    private static String RapidApiResponseFormat = "/format/json/";

    public static String strToMd5Hex(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static HttpResponse getTempMailResponse(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(RapidApiUrl + url + RapidApiResponseFormat);
        httpGet.setHeader("X-RapidAPI-Host", RapidApiHost);
        httpGet.setHeader("X-RapidAPI-Key", RapidApiKey);

        return client.execute(httpGet);
    }

    public static ArrayList<String> getEmailDomains() throws IOException {
        ArrayList<String> domains = new ArrayList<String>();
        HttpResponse response = getTempMailResponse("domains");

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        Pattern pattern = Pattern.compile("@[a-z,0-9]*.[a-z]{2,6}");
        Matcher matcher = pattern.matcher(responseString);

        int count = 0;
        while (matcher.find()) {
            domains.add(matcher.group(count));
            count++;
        }

        return domains;
    }

    public static String getTestEmailMessage(String md5) throws IOException {
        HttpResponse response = getTempMailResponse("mail/id/" + md5);

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