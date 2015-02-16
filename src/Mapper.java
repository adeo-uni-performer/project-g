import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class Mapper {
	
	private static File results_file;
	private static File data_folder;
	private static HashMap<String, String> filename_URL = new HashMap<>();
	
	private static String Index_file = "H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Index/Index.txt";
	
	Mapper(File data_folder, File file_for_results){
		results_file = file_for_results;
		this.data_folder = data_folder;
	}
	
	//----------------// Map count //---------------------------//
	public void mapCount() throws Exception{
			File[] list_of_Files = data_folder.listFiles();
			
			//-------------------------//Read_filenames//---------------------------------------//
			
			for (File file: list_of_Files){
				String file_name = file.getName();
				
				if (file_name.equals("Filename_URL.txt") ){
					BufferedReader read_filenames = new BufferedReader(
							new InputStreamReader(
							new FileInputStream(file)));
					
					String line = null;
					
					// Read line by line
					while ((line = read_filenames.readLine()) != null) {
						// Parse line
						String [] array = line.split(" ");
						// Write each word in results_file
						filename_URL.put(array[0], array[1]);
						//System.out.println (array[0] +"   "+ filename_URL.get(array[0]));
						
						
					}
				read_filenames.close();
				break;
				}
			}
			
			//-------------------------//WRITER//-----------------------------------------------//
			// if file doesn't exists, then create it
			if (!results_file.exists()) {
				results_file.createNewFile();
			}
						
			PrintStream theOut = new PrintStream(new BufferedOutputStream(
			 			 						 new FileOutputStream(results_file)));  
			
			//-----------------------//GO THROUGH FILES//----------------------------//
			for (File file: list_of_Files){
				
				String file_name = file.getName();
				
				
				
				if (!file_name.equals("Filename_URL.txt") ){
					System.out.println(filename_URL.get(file_name)); ///////////////////
					
					BufferedReader theIn = new BufferedReader(
							new InputStreamReader(
							new FileInputStream(file)));
					
					String line = null;
					
					// Read line by line
					while ((line = theIn.readLine()) != null) {
						// Parse line
						String [] array = line.split(" ");
						// Write each word in results_file
						for (String s: array){
							
							theOut.println(s + " " + filename_URL.get(file_name));
							theOut.flush();
						}
					}
					theIn.close();
				}
			}
			
			theOut.close();
		}
	
	//----------------// Map select //---------------------------//
	public HashMap<String, Integer> mapSelect(String term) throws Exception{
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(Index_file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HashMap<String, Integer> map_select_results = new HashMap<>();
		
		String line = null;
		while ((line = input.readLine()) != null) {
			// Parse line
			String [] array = line.split(" ");
			if (array[0].equals(term)){
				//System.out.println(array[1] + "    [" + array[2] + "]");
				map_select_results.put(array[1], Integer.parseInt(array[2]));
			}
		}
		
		return map_select_results;
		
	}
	
	
	
	
	
	// TEST
	public static void main(String[] args) throws Exception {
		File folder_name = new File("H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/data");
		File results_file = new File("H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Mapper/Key_value_pairs.txt");
		Mapper MrMapper = new Mapper(folder_name, results_file);
		MrMapper.mapCount();
		
		/*
		HashMap<String, Integer> map_select_results = new HashMap<>();
		String term = "map";
		map_select_results.putAll((MrMapper.mapSelect(term)));
		
		
		for (String key: map_select_results.keySet()){
			System.out.println(key + "    [" + map_select_results.get(key) + "]");
		}
		*/
	}

}
