package server_com.crawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import server_com.database.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class CrawlerText {

    /**
     * Opening Connection at url
     * @param url
     */
    public void processPath(String url){
        //Requesting connection
        //Document doc = getConnectionViaProxy( url );
        Document doc = getConnectionWithoutProxy( url );

        DocProcessorText processor = new DocProcessorText();
        //Processing Document
        processor.processDocument(doc, url);
    }

    /**
     * Connect to the Internet without Proxy Settings
     * @param urlPath
     * @return
     */
    public Document getConnectionWithoutProxy(String urlPath){
        Document doc=null;
        try{
            doc = Jsoup.connect(urlPath).get();
        }catch (IOException e){

        }
        return doc;
    }

    /**
     * Connect to the Internet Via Proxy Settings
     * @param urlPath
     * @return
     */
    public  Document getConnectionViaProxy( String urlPath ){
        final String authUser = Parameters.Username();
        final String authPassword = Parameters.Password();
        StringBuffer tmp = new StringBuffer();
        Authenticator.setDefault(
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );
        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);
        try{
            URL url = new URL(urlPath);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.cergy.eisti.fr", 3128));

            HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);

            uc.connect();

            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = in.readLine()) != null) {
                tmp.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return Jsoup.parse(String.valueOf(tmp));
    }

}
