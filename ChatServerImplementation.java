package Chat;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class ChatServerImplementation extends UnicastRemoteObject implements ChatServer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClient> clients;
	
	public ChatServerImplementation() throws RemoteException {
		this.clients = new ArrayList<>();
	}
	
	public void broadcastMessage(String message) {
		for (ChatClient c : this.clients) {
			try {
				c.printMessage(message);
			} catch (RemoteException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	@Override
	public void chat(String message) throws RemoteException {
		System.out.println("Received message from client: " + message);
		broadcastMessage(message);
		
	}

	@Override
	  public void registerClient(String host, int port, String registryName) throws RemoteException {
		System.out.println("Registering client: " + host + ":" + port + ":" + registryName);
		try {
			ChatClient client;
			client = (ChatClient)LocateRegistry.getRegistry(host, port).lookup(registryName);
			this.clients.add(client);
			client.printMessage("You have connected!");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
	
		try {
			ChatServer server = new ChatServerImplementation();
			ChatServer stub = (ChatServer) UnicastRemoteObject.exportObject(server, port);
			Runtime.getRuntime().exec("rmiregistry " + port);
			Registry registry = LocateRegistry.createRegistry(port);
	        registry.bind("ChatServer", stub);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

//	@Override
//	public void registerClient(ChatClient client) throws RemoteException {
//		this.clients.add(client);
//	}

}
