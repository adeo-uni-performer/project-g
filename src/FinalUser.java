import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;


public class FinalUser {
	public static HashMap<String, Integer> search_Result = new HashMap<>();
	private static Socket sock;
	public static String user_Query;
	
	public static void main(String[] args) {
		FinalUser client = new FinalUser();
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter your query:  ");
		user_Query = scan.nextLine();
		
		
		
		System.out.println("Search: [" + user_Query + "]\n");
		client.search(user_Query);
		

	}
	
	public static void search (String query) {
		try {
			sock = new Socket ("localhost", 9999);

			//=================// Send request to the server //================//
			OutputStream OS = sock.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(OS);
			//-----------------------------------------------------------------//
			oos.writeObject("Search::: "+query);
			oos.flush();
			
			
			
			//=================// Response from server //======================//
			InputStream IS = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(IS);
			//-----------------------------------------------------------------//
			HashMap<String, Integer> search_results = new HashMap<>();
			search_results.putAll((HashMap<String, Integer>) ois.readObject());
			
			for (String key: search_results.keySet()){
				System.out.println(key + "    [" + search_results.get(key) + "]");
				
			}
			
			ois.close();
			oos.close();
			sock.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't connect to the server");
		} 
		
	}

}
