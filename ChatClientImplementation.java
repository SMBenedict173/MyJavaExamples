package Chat;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatClientImplementation extends UnicastRemoteObject implements ChatClient {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChatClientImplementation() throws RemoteException {
		
	}
	@Override
	public void printMessage(String message) throws RemoteException {
		System.out.println("Chat message: " + message);
	}
	
	public static void main(String[] args) {
		String serverHost = args[0];
		int serverPort = Integer.parseInt(args[1]);
		int localPort = Integer.parseInt(args[2]);
		
		
		try {
			
			// put ourselves into our local registry...
			ChatClient client = new ChatClientImplementation();
			
			Runtime.getRuntime().exec("rmiregistry " + localPort);
	        Registry registry = LocateRegistry.createRegistry(localPort);
	        registry.bind("ChatClient", client);
			
			// get the server stub from the remote registry
	        Registry remoteRegistry;
			remoteRegistry = LocateRegistry.getRegistry(serverHost, serverPort);
			ChatServer server = (ChatServer)remoteRegistry.lookup("ChatServer");
			
			server.registerClient("localhost", localPort, "ChatClient");
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String msg = scanner.nextLine();
				server.chat(msg);
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	    
	}

}
