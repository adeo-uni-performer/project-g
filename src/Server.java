import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {
	
	private static File Key_value_pairs = new File("H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Mapper/Key_value_pairs.txt");	
	private static Socket sock;
	private static File data_folder = new File("H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/data");
	private static Mapper mapper;
	private static Server servak;
	
	//--------------------------------------------------------------------------------------//
	public static void main(String[] args) throws Exception {
		servak = new Server();
		
		// Indexation 
		// Later need to put it in run() method when Master will be done
		mapper = new Mapper(data_folder, Key_value_pairs);
		mapper.mapCount();
		
		servak.run();

	}
	//--------------------------------------------------------------------------------------//
	
	public void run () throws Exception{
		
		ServerSocket servak = new ServerSocket(9999);
		
		while (true){
			sock = servak.accept();
			
			//--------------------------// Get request from client //--------------------------------//
			InputStream IS = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(IS);
			String client_message = (String) ois.readObject();
			
			
			//-----------------------------// Reply to client //-------------------------------------//
			String request_key_value_pairs = "Key_value_pairs.txt";
			String user_Query = "Search::: ";
			
			
			// Client: Reducer
			if (client_message.equals(request_key_value_pairs)){
				System.out.println("Send Mapper results to Reducer");
				sendFile(Key_value_pairs);
			
				
			// Client: Final user
			}else if (client_message.startsWith(user_Query)){
				String term = client_message.substring(10);
				System.out.println("Search: [" + term + "]\n");
				search(term);
			}
			
			
			ois.close();
			sock.close();
			
		}
		
	}
	
	
	
	public static void sendFile (File file_to_send) throws IOException{
		OutputStream OS = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(OS);
		
		if(!file_to_send.exists()){
			String file_not_found = "File does not exist!";
			System.out.println(file_not_found);
			oos.writeObject(file_not_found);
			
		}else{  // File exists
			oos.writeObject("File exists");
			oos.writeObject(file_to_send);
			oos.flush();
			oos.close();
		}
	}
	
	
	
	public static void search (String term) throws Exception {
		// Search term and send results to Final user
		
		// If client_message is a string that contains only 1 term
		HashMap<String, Integer> map_select_results = new HashMap<>();
		map_select_results.putAll((mapper.mapSelect(term)));
		
		OutputStream OS = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(OS);
		oos.writeObject(map_select_results);
		oos.flush();
		oos.close();
		
		
		
		// If client_message is a string that contains more then 1 term
		// TODO
	}
	
}
