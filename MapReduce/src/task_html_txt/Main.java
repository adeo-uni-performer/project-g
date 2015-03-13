package task_html_txt;

import server_com.crawler.CrawlerText;

public class Main {

    /**
     * Main Function
     * The List of URL have to be defined under urlPaths
     * @param args
     */
    public static void main(String[] args){
        System.out.println("Started Crawler Text");
        String[] urlPaths = {
                "http://en.wikipedia.org/wiki/Main_Page",
                "http://en.wikipedia.org/wiki/Hugh_Walpole"
        };
        CrawlerText crawler = new CrawlerText();
        int i;
        for(i=0; i<urlPaths.length; i++){
            crawler.processPath(urlPaths[i]);
        }

    }

}
