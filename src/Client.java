import java.io.*;
import java.net.*;

public class Client {
	private static String Key_value_pairs = "H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Reducer/Key_value_pairs.txt";
	private static String request_Key_value_pairs = "Key_value_pairs.txt";
	private static String Index_file = "H:/Repositories/GitHub/JavaProjects/GoogleRelaxes/Index/Index.txt";
	private Socket sock;
	
	//--------------------------------------------------------------------------------------//
		public static void main(String[] args) throws Exception {
			Client client = new Client();
			client.run();
			Reducer reducer = new Reducer(Key_value_pairs, Index_file);
			reducer.reduceCount();
		}
	//--------------------------------------------------------------------------------------//
		
		
		public void run () throws Exception{
			
			sock = new Socket ("localhost", 9999);
			
			//--------------------------// Send message to server: request Mapper results  //--------------------------------//
			getFileFromServer(request_Key_value_pairs);
			
			
		    sock.close();
		   
		}
		
		
		public void getFileFromServer(String file_to_get) throws Exception{
			
			OutputStream OS = sock.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(OS);
			oos.writeObject(file_to_get);
			
			
			InputStream IS = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(IS);	
			String server_message = (String) ois.readObject();
			
			String file_not_found = "File does not exist!";
			if (!server_message.equals(file_not_found)){
				
				File recievedFile = (File) ois.readObject();
				
				FileInputStream in = new FileInputStream(recievedFile);
				FileOutputStream out = new FileOutputStream(new File(Key_value_pairs));
				
				int c;
				while ((c = in.read()) != -1){
					out.write(c);
				}
				
				out.close();
				in.close();
				
			}else{
				System.out.println(server_message);
			}
			
			
			oos.close();
			ois.close();
		}
		
}
