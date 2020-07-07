import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import edu.uab.cs203.Objectmon;
import edu.uab.cs203.Team;
import edu.uab.cs203.network.GymClient;
import edu.uab.cs203.network.GymServer;
import edu.uab.cs203.network.NetworkGym;

public class MultiplayerServer extends UnicastRemoteObject implements GymServer, NetworkGym{
	
	private Team<Objectmon> teamA;
	private Team<Objectmon> teamB;
	private GymClient clientA;
	private GymClient clientB;
	private String registryName;
	private boolean clientAReadiness;
	private boolean clientBReadiness;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultiplayerServer() throws RemoteException {
		super();
		registryName = "MultiplayerServer";
	}

	public String networkToString() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void broadcastMessage(String message) {
			try {
				this.clientA.printMessage(message);
				this.clientB.printMessage(message);
			} catch (RemoteException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		
	}

	

	@Override
	public void printMessage(String arg0) throws RemoteException {
		System.out.println(arg0);
	}

	@Override
	public void registerClientA(String arg0, int arg1, String arg2) throws RemoteException {
		try {
			GymClient newClientA =  (GymClient) LocateRegistry.getRegistry(arg0, arg1).lookup(arg2);
			this.clientA = newClientA;
			this.clientA.printMessage("You have connected to the arena!");
			System.out.println("Client A has connected to the server.");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}

	@Override
	public void registerClientB(String arg0, int arg1, String arg2) throws RemoteException {
		try {
			GymClient newClientB = (GymClient) LocateRegistry.getRegistry(arg0, arg1).lookup(arg2);
			this.clientB = newClientB;
			this.clientB.printMessage("You have connected to the arena!");
			System.out.println("Client B has connected to the server.");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}

	@Override
	public void setTeamA(Team arg0) throws RemoteException {
		this.teamA = arg0;	
	}

	@Override
	public void setTeamAReady(boolean arg0) throws RemoteException {
		if (arg0 == true) {
			this.clientAReadiness = arg0;
			this.setTeamA(this.getClientA().getTeam());
			System.out.println("Team A is ready to battle!");
			if (this.getClientBReadiness() == true) {
				System.out.println("Get ready to battle!");
				this.broadcastMessage("Get ready to battle!");
				this.fight (50);
			}
		}
	}

	@Override
	public void setTeamB(Team arg0) throws RemoteException {
		this.teamB = arg0;
		
	}

	@Override
	public void setTeamBReady(boolean arg0) throws RemoteException {
		if (arg0 == true) {
			this.clientBReadiness = arg0;
			this.setTeamB(this.getClientB().getTeam());
			System.out.println("Team B is ready to battle!");
			if (this.getClientAReadiness() == true){
				System.out.println("Get ready to battle!");
				this.broadcastMessage("Get ready to battle!");
				this.fight (50);
			}
		}
	}
	
	public int getIntInputClientA () throws RemoteException {
		this.getClientA().printMessage("Please enter an integer to show your choice: ");
		int input = ((MultiplayerClient) this.getClientA()).sendIntInput();
		return input;
	}
	
	public int getIntInputClientB () throws RemoteException {
		this.getClientB().printMessage("Please enter an integer to show your choice: ");
		int input = ((MultiplayerClient) this.getClientB()).sendIntInput();
		return input;
	}

	@Override
	public void executeTurn() {
			Objectmon combatantA = this.getTeamA().nextObjectmon();
			Objectmon combatantB = this.getTeamB().nextObjectmon();
			System.out.println("This round will be fought by " + combatantA.getName()
					+ " and " + combatantB.getName() + ".");
			this.broadcastMessage("This round will be fought by " + combatantA.getName()
					+ " and " + combatantB.getName() + ".");
			int aDamage = (int) combatantA.nextAttack().getDamage(combatantB);
			combatantB.setHp(combatantB.getHp() - aDamage);
			this.broadcastMessage(combatantA.getName() + " deals " + aDamage +
						" damage to " + combatantB.getName() + ".");
			int bDamage = combatantB.nextAttack().getDamage(combatantA);
			combatantA.setHp(combatantA.getHp() - bDamage);

			this.broadcastMessage(combatantB.getName() + " deals " + bDamage +
						" damage to " + combatantA.getName() + ".");
			this.broadcastMessage(combatantA.toString() + " " + combatantB.toString());
			for (int i = 0; i < this.getTeamA().size(); i ++) {
				if (((Objectmon) this.getTeamA().get(i)).getName().equals(combatantA.getName())){
					this.getTeamA().remove(i);
					this.getTeamA().add(i, combatantA);
					break;
				}
				else {
					continue;
				}	
			}
			for (int i = 0; i < this.getTeamB().size(); i ++) {
				if (((Objectmon) this.getTeamB().get(i)).getName().equals(combatantB.getName())){
					this.getTeamB().remove(i);
					this.getTeamB().add(i, combatantB);
					break;
				}
				else {
					continue;
				}
			}
			
		
	}

	@Override
	public void fight(int arg0) {
		for (int i = 0; i < arg0; i++) {
			if ((!this.getTeamA().canFight()) && (this.getTeamB().canFight())) {
				System.out.println("Team B is victorious.");
				this.broadcastMessage("Team B is victorious");
				break;
			}
			else if ((!this.getTeamB().canFight()) && (this.getTeamA().canFight())) {
				System.out.println("Team A is victorious.");
				this.broadcastMessage("Team A is victorious.");
				break;
			}
			else {
				this.executeTurn();
			}
		}
		
	}

	@Override
	public GymClient getClientA() {
		return this.clientA;
	}

	@Override
	public GymClient getClientB() {
		return this.clientB;
	}

	@Override
	public Team getTeamA() {
		return this.teamA;
	}

	@Override
	public Team getTeamB() {
		return this.teamB;
	}
	
	public boolean getClientAReadiness () {
		return this.clientAReadiness;
	}
	
	public boolean getClientBReadiness () {
		return this.clientBReadiness;
	}
	
	

	public String getRegistryName() {
		// TODO Auto-generated method stub
		return this.registryName;
	}
	
	
	
	public static void main (String [] args) {
		try {
			MultiplayerServer server = new MultiplayerServer();
			Runtime.getRuntime().exec("C:\\Program Files\\Java\\jre-9.0.1\\bin\\rmiregistry.exe " + 11220);
			Registry registry = LocateRegistry.getRegistry(11222);
			registry = LocateRegistry.createRegistry(11222);
			registry.bind(server.getRegistryName(), server);
			System.out.println("Ready for clients, maybe.");
			
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	
	}
}