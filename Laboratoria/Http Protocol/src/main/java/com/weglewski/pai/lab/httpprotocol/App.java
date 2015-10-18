package com.weglewski.pai.lab.httpprotocol;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by piotr on 12.10.15.
 */
public class App {
    private static String debianURL = "http://debian.org/";

    private static String listToString(List<String> list){
        String s = "";
        for(String o : list){
            s+=", "+o;
        }
        return s.substring(2);
    }

    private static void printNestedMap(Map<String, List<String>> map){
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            if(entry.getKey()!=null){
                System.out.println("\t"+entry.getKey() + ": " + listToString(entry.getValue()));
            }
        }
    }

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect(debianURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elements = doc.getElementsByAttribute("href");

        List<Element> emails = new ArrayList<Element>();
        List<Element> links = new ArrayList<Element>();
        List<Element> images = new ArrayList<Element>();
        Element e;
        for (Element element : elements){
            links.add(element);
            e = element.getElementsByAttributeValueStarting("href", "mailto").first();
            if(e!=null){
                emails.add(e);
            }
        }

        System.out.println("##EMAILS##");
        for (Element email : emails){
            System.out.println(email.attr("href"));
            links.remove(email);
        }
        System.out.println();

        System.out.println("##LINKS##");
        for (Element link : links){
            String url = link.attr("abs:href");
            System.out.println(link.attr("abs:href"));
        }
        System.out.println();

        System.out.println("##IMAGES##");
        Elements imgs = doc.select("img");
        for (Element img: imgs ){
            String src = img.attr("abs:src");
            System.out.println(src);
        }
        System.out.println();

        System.out.println("##MANUAL CONNECTION##");
        try {
            URL url = new URL(debianURL);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection;
            connection = (HttpURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while((current = in.readLine()) != null)
            {
                urlString += current+"\n";
            }
            //Printing the html file
            //System.out.println(urlString);
            //Printing connection headers
            System.out.println("General");
            System.out.println("\t"+"Request URL: "+connection.getURL());
            System.out.println("\t"+"Request Method: "+connection.getRequestMethod());
            System.out.println("\t"+"Status Code: "+connection.getResponseCode());
            System.out.println("Response Headers");
            printNestedMap(connection.getHeaderFields());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
