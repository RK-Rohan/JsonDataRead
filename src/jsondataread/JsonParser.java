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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Base64;
import java.util.Base64;

public class JsonParser {
   
    public static void main(String[] args) throws ParseException {
        JSONParser parser = new JSONParser();

        try {
            URL url = new URL("http://demoerp.rawntech.com/demoerp/org.openbravo.service.json.jsonrest/thr_attendanceraw");
            URLConnection uc = url.openConnection();
            String password = "jsonuser1!";
            String username = "jsonuser";
            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            uc.setRequestProperty ("Authorization", basicAuth);
            //BufferedReader in = uc.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
           
            String inputLine;
            while ((inputLine = in.readLine()) != null) {              
                JSONArray a = (JSONArray) parser.parse(inputLine);
               
                // Loop through each item
                for (Object o : a) {
                    JSONObject tutorials = (JSONObject) o;

                    Long id = (Long) tutorials.get("ID");
                    System.out.println("Post ID : " + id);

                    String title = (String) tutorials.get("post_title");
                    System.out.println("Post Title : " + title);

                    System.out.println("\n");
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }  
}
