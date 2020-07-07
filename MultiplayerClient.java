import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import edu.uab.cs203.Objectmon;
import edu.uab.cs203.Team;
import edu.uab.cs203.network.GymClient;
import edu.uab.cs203.network.GymServer;
import edu.uab.cs203.network.NetworkGym;

public class MultiplayerClient extends UnicastRemoteObject implements GymClient{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private String registryName;
	private Team<Objectmon> team;
	
	protected MultiplayerClient(String arg0, int arg1, String arg2) throws RemoteException {
		super();
		host = arg0;
		port = arg1;
		registryName = arg2;
	}

	@Override
	public Team<Objectmon> getTeam() throws RemoteException {
		return this.team;
	}

	@Override
	public Objectmon networkApplyDamage(Objectmon arg0, Objectmon arg1, int arg2) throws RemoteException {
		try{
			System.out.println(arg0.getName() + " deals " + arg2 + " damage to " + arg1.getName() + ".");
			return arg1;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return arg1;
	}

	@Override
	public void networkTick() throws RemoteException {
		this.getTeam().tick();
		
	}

	@Override
	public Objectmon nextObjectmon() throws RemoteException {
		return this.getTeam().nextObjectmon();
	}
	
	@Override
	public void printMessage(String arg0) throws RemoteException {
		System.out.println (arg0);
		
	}

	@Override
	public void setTeam(Team arg0) throws RemoteException {
		this.team = arg0;
		
	}
	
	public String getHost() {
		return this.host;
	}
	
	public int getPort () {
		return this.port;
	}
	
	public String getRegistryName () {
		return this.registryName;
	}
	
	public int sendIntInput () throws RemoteException{
		try{
			Scanner input = new Scanner (System.in);
			int choice = input.nextInt();
			input.close();
			return choice;
		}
		catch (Exception e) {
			System.out.println("Please enter only integers from the above menu.");
			return this.sendIntInput();
		}
	}
	
	public static void main (String [] args) throws RemoteException {
		try {
			System.out.println("Choose between team a and team b.");
			Scanner teamDecision = new Scanner (System.in);
			String yourTeam = teamDecision.next();
			
			if (yourTeam.equalsIgnoreCase("a")) {
				try {
				MultiplayerClient client = new MultiplayerClient ("localhost", 11230, "MultiplayerClient");
				MultiplayerTeam tempTeam = new MultiplayerTeam ();
				MultiplayerTeam teamA = tempTeam.load("MultiplayerTeamA.txt");
				client.setTeam(teamA);
				Runtime.getRuntime().exec ("C:\\Program Files\\Java\\jre-9.0.1\\bin\\rmiregistry.exe " + client.port);
				Registry registry = LocateRegistry.createRegistry(client.port);
	        	registry.bind(client.getRegistryName(), client);
			
			// get the server stub from the remote registry
	        	Registry remoteRegistry;
				remoteRegistry = LocateRegistry.getRegistry (client.host, 11222);
				GymServer stub = (GymServer) remoteRegistry.lookup("MultiplayerServer");
				stub.registerClientA(client.getHost(), client.getPort(), client.getRegistryName());
				stub.setTeamAReady(true);
				
			}
				catch (ExportException a) {
					System.out.println("Too bad, you're team B.");
					MultiplayerClient client = new MultiplayerClient ("localhost", 11240, "MultiplayerClient");
					MultiplayerTeam tempTeam = new MultiplayerTeam ();
					MultiplayerTeam teamB = tempTeam.load("MultiplayerTeamB.txt");
					client.setTeam(teamB);
					Runtime.getRuntime().exec ("C:\\Program Files\\Java\\jre-9.0.1\\bin\\rmiregistry.exe " + client.port);
					Registry registry = LocateRegistry.createRegistry(client.port);
		        	registry.bind(client.getRegistryName(), client);
				
				// get the server stub from the remote registry
		        	Registry remoteRegistry; 
					remoteRegistry = LocateRegistry.getRegistry (client.host, 11222);
					GymServer stub = (GymServer) remoteRegistry.lookup("MultiplayerServer");
					stub.registerClientB(client.host, client.port, client.registryName);
					stub.setTeamBReady(true);
				}
			}
			else if (yourTeam.equalsIgnoreCase("b")) {
				try {
				MultiplayerClient client = new MultiplayerClient ("localhost", 11240, "MultiplayerClient");
				MultiplayerTeam tempTeam = new MultiplayerTeam ();
				MultiplayerTeam teamB = tempTeam.load("MultiplayerTeamB.txt");
				client.setTeam(teamB);
				Runtime.getRuntime().exec ("C:\\Program Files\\Java\\jre-9.0.1\\bin\\rmiregistry.exe " + client.port);
				Registry registry = LocateRegistry.createRegistry(client.port);
	        	registry.bind(client.getRegistryName(), client);
			
			// get the server stub from the remote registry
	        	Registry remoteRegistry; 
				remoteRegistry = LocateRegistry.getRegistry (client.host, 11222);
				GymServer stub = (GymServer) remoteRegistry.lookup("MultiplayerServer");
				stub.registerClientB(client.host, client.port, client.registryName);
				stub.setTeamBReady(true);
				}
				catch (ExportException b) {
					System.out.println("Too bad, you're team A.");
					MultiplayerClient client = new MultiplayerClient ("localhost", 11230, "MultiplayerClient");
					MultiplayerTeam tempTeam = new MultiplayerTeam ();
					MultiplayerTeam teamA = tempTeam.load("MultiplayerTeamA.txt");
					client.setTeam(teamA);
					Runtime.getRuntime().exec ("C:\\Program Files\\Java\\jre-9.0.1\\bin\\rmiregistry.exe " + client.port);
					Registry registry = LocateRegistry.createRegistry(client.port);
		        	registry.bind(client.getRegistryName(), client);
				
				// get the server stub from the remote registry
		        	Registry remoteRegistry;
					remoteRegistry = LocateRegistry.getRegistry (client.host, 11222);
					GymServer stub = (GymServer) remoteRegistry.lookup("MultiplayerServer");
					stub.registerClientA(client.getHost(), client.getPort(), client.getRegistryName());
					stub.setTeamAReady(true);
				}
			}	
			else {
				System.out.println("Please choose team a or team b");
				yourTeam = teamDecision.next();
			}
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		
	}
	
}