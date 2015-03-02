package server_com.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import server_com.database.Parameters;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DocProcessorText {

    /**
     * Processing Document Main Function
     * @param doc
     * @param url
     */
    public void processDocument( Document doc , String url ){
        int level = getLevel(url);
        File file = getLinkFile(level);
        Parameters.checkDirectorySetupCrawlerText();
        System.out.println("[ level= " + level + " , url= " +url +" ]");
        storingDocument(doc , level, url);
    }

    /**
     * Processing Document Level 1
     * @param doc
     * @param level
     * @param url
     */
    private void storingDocument(Document doc, int level, String url){
        String[] fileName = url.split("/");
        String filePath = getPathLevelDataFolder(level)+".txt";
        File file = new File(filePath);
        System.out.println("filePath: " + filePath);
        try {
            PrintStream theOut = new PrintStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            System.out.println("Storing Document in: " + filePath +"\n" +
                    "url----> " + fileName[fileName.length-1]);
            System.out.println("Check Storing");

            Elements textArray = doc.select("p");
            writeToFile(textArray, theOut);

        }catch(IOException e ){
            System.out.println("Error Print Stream");
            System.out.println("Check Directory Structure");
        }
    }

    /**
     * Processing Document Final Level
     * @param textArray
     * @param theOut
     */
    private void writeToFile(Elements textArray , PrintStream theOut ){
        ArrayList<String> stop_words = new ArrayList<String>(Arrays.asList(Parameters.weakWords()));
        for(Element container : textArray){
            String text = container.text();
            for(String s : text.split(" ")){
                if( ! stop_words.contains(s) ){
                    if( ! isNumeric(s)){
                        String str = s.replaceAll("[^\\p{Alpha}]+","").trim().toLowerCase();
                        if(str!="" || str!=" "){
                            theOut.println(str);
                        }
                    }
                }
            }
        }
        theOut.flush();
    }

    /**
     * If string value is numeric
     * @param str
     * @return
     */
    private static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     * Getting level structure of Wikipedia
     * @param url
     * @return
     */
    private int getLevel(String url){
        String pattern0= ".*Main_Page.*";
        String pattern1= ".*Portal.*";
        if(url.matches(pattern0)) return 0;
        else if(url.matches(pattern1)) return 1;
        else return 2;
    }

    /**
     * Get file path for storing logs
     * @param level
     * @return
     */
    private File getLinkFile(int level){
        switch (level){
            case 0:
                return new File("task_1/text_files/wikipedia/level_0/links.html");
            case 1:
                return new File("task_1/text_files/wikipedia/level_0/level_1/links.html");
            case 2:
                return new File("task_1/text_files/wikipedia/level_0/level_1/level_2/links.html");
            default:
                System.err.println("Error in getLinkFile, level not recognised");
                System.exit(1);
        }
        return null;
    }

    /**
     * Get file path for storing extracted words
     * @param level
     * @return
     */
    private String getPathLevelDataFolder(int level){
        Random randomGenerator = new Random();
        switch (level){
            case 0:
                return "task_1/text_files/wikipedia/level_0/files/"+randomGenerator.nextInt(100000000);
            case 1:
                return "task_1/text_files/wikipedia/level_0/level_1/files/"+randomGenerator.nextInt(100000000);
            case 2:
                return  "task_1/text_files/wikipedia/level_0/level_1/level_2/files/"+randomGenerator.nextInt(100000000);
            default:
                System.err.println("Error in getPathLevelDataFolder, level not recognised");
                System.exit(1);
        }
        return null;
    }

}
