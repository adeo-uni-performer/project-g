import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Map.Entry;


public class Reducer {
	private static HashMap<String, Integer> key_value_pairs = new HashMap<>();
	private static File input_file;
	private static File results_file;
	
	Reducer(String mapper_file, String file_for_results){
		input_file = new File (mapper_file);
		results_file = new File(file_for_results);
	}
	
	
	//----------------// Reduce count//---------------------------//
	public void reduceCount() throws Exception{
		
		BufferedReader theIn  = new BufferedReader(new InputStreamReader(new FileInputStream(input_file)));
		String line = null;
		
		//-------------------------//WRITER//-----------------------------------------------//
		// if file doesn't exists, then create it
		if (!results_file.exists()) {
			results_file.createNewFile();
		}
					
		PrintStream theOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(results_file) ) );  
		
		//-----------------------//Read line by line//----------------------------//
			while ((line = theIn.readLine()) != null) {
				
				if (key_value_pairs.containsKey(line)){
					int value = 1 + key_value_pairs.get(line);
					key_value_pairs.put(line, value);
				}else{
					int value = 1;
					key_value_pairs.put(line, value);
					value++;
				}
			}
			
			SortedSet<String> keys = new TreeSet<String>(key_value_pairs.keySet());
			for (String key : keys) { 
			   Integer value = key_value_pairs.get(key);
			   System.out.println (key + " " + value);
			   theOut.println(key + " " + value);
			   theOut.flush();
			}
			theIn.close();
			theOut.close();
	}
	
	
	
	// TEST
	public static void main(String[] args) throws Exception {
		String mapper_file = "H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Mapper/Key_value_pairs.txt";
		String reducer_results = "H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Reducer/Index/Index.txt";
		Reducer MrReducer = new Reducer(mapper_file, reducer_results);
		MrReducer.reduceCount();
	}

}
