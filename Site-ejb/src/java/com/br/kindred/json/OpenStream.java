/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author First Place
 */
public class OpenStream {

    public static String openURL(String uri) {

        String key = "api_key=3c323e05-a26a-4f3c-a56e-7b38430d1ce1";
        String content = "";
        try {

            URL url = new URL(uri + key);
            System.out.println(uri + key);
            //Mackenzie
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));

            HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            int code = con.getResponseCode();

            if (code == 400) {
                System.out.println("Bad request");
                return "400";
            } else if (code == 401) {
                System.out.println("Unauthorized");
                return "401";
            } else if (code == 403) {
                System.out.println("No summoner data found for any specified inputs");
                return "403";
            } else if (code == 404) {
                System.out.println("Not found");
                return "404";
            }else if(code==429){
                    System.out.println("Rate limit exceeded");
                return "429";
            }
            else if(code==500){
                System.out.println("Internal server error");
                return "500";
            }
            else if(code==503){
                System.out.println("Service unavailable");
                return "503";
            }
            else if(code==200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) !=null){
                    sb.append(line);
                }
                br.close();
                con.disconnect();
                
                content= sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(OpenStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        return content;
    }
}
