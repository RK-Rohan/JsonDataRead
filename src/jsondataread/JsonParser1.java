/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsondataread;

/**
 *
 * @author kazi
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Scanner;
//from  w  ww  .  java  2  s  . c o m
public class JsonParser1 {
  public static void main(String[] args) {
    System.out
        .println(jsonGetRequest("http://jsonuser:jsonuser1!@demoerp.rawntech.com/demoerp/org.openbravo.service.json.jsonrest/thr_attendanceraw"));
  }

  private static String streamToString(InputStream inputStream) {
    String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
    return text;
  }

  public static String jsonGetRequest(String urlQueryString) {
    String json = null;
    try {
        URL url = new URL("http://demoerp.rawntech.com/demoerp/org.openbravo.service.json.jsonrest/thr_attendanceraw");
        URLConnection urlConnection = url.openConnection();

        if (url.getUserInfo() != null) {
            String password = "jsonuser1!";
            String username = "jsonuser";
            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            urlConnection.setRequestProperty("Authorization", basicAuth);
        }

        InputStream inputStream = urlConnection.getInputStream();
        
        
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("charset", "utf-8");
      connection.connect();
      InputStream inStream = connection.getInputStream();
      json = streamToString(inStream); // input stream to string
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return json;
  }
}