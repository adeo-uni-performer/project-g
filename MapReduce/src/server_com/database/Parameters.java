package server_com.database;


import java.io.File;

public class Parameters {

    /**
     * get Username
     * For Connection With Proxy
     * @return
     */
    public static String Username(){
        return "";
    }

    /**
     * get Password
     * For Connection With Proxy
     * @return
     */
    public static String Password(){
        return "";
    }

    /**
     * Shortcut for System.out.println()
     * @param content
     */
    public static void printf(Object content){
        System.out.println(content.toString());
    }

    /**
     * Getting Weak Words
     * @return
     */
    public static String[] weakWords(){
        String[] weak_words =
                {
                    "a","am","an","and","any","are","as","at","be","been","but","can","could","would",
                    "must","will","do","does","for","from","had","has","have","he","her","him","his",
                    "I","me","must","of","the","also","The","it","is","A","on","in","On","In","to",
                    "that","by","was","or","with","its","where","Where","i","I"
                }
                ;
        return weak_words;
    }

    /**
     * Getting Directory Structure
     * For DocProcessorText
     * @return
     */
    public static String[] directoryStructure(){
        String[] structure = {
                "task_1",
                "task_1/text_files",
                "task_1/text_files/wikipedia",
                "task_1/text_files/wikipedia/level_0",
                "task_1/text_files/wikipedia/level_0/files",
                "task_1/text_files/wikipedia/level_0/level_1",
                "task_1/text_files/wikipedia/level_0/level_1/files",
                "task_1/text_files/wikipedia/level_0/level_1/level_2",
                "task_1/text_files/wikipedia/level_0/level_1/level_2/files",
        };
        return structure;
    }

    /**
     * Check if file storage directory is correctly setup
     * For DocProcessor Text
     */
    public static void checkDirectorySetupCrawlerText(){
        String[] structure = directoryStructure();
        int i;
        for (i=0;i<structure.length;i++){
            File theDir = new File(structure[i]);
            // if the directory does not exist, create it
            if (!theDir.exists()) {
                System.out.println("File Structure not correct");
                boolean result = false;
                try{
                    theDir.mkdir();
                    result = true;
                } catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("Directory created: " + structure[i]);
                }
            }
        }
    }

}
