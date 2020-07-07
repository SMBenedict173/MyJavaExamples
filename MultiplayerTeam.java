import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.TypeMismatch;

import edu.uab.cs203.Loadable;
import edu.uab.cs203.Objectmon;
import edu.uab.cs203.Savable;
import edu.uab.cs203.attacks.AbstractAttack;
import edu.uab.cs203.attacks.BasicAttack;
import edu.uab.cs203.lab05.BasicTeam;
import edu.uab.cs203.lab08.BadPoisonAttack;
import edu.uab.cs203.lab08.FreezingAttack;
import edu.uab.cs203.lab08.ParalyzingAttack;
import edu.uab.cs203.lab08.PoisonAttack;
import edu.uab.cs203.lab08.SleepAttack;

public class MultiplayerTeam extends BasicTeam <Objectmon> implements Savable, Loadable <Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultiplayerTeam() throws RemoteException {
		super ();
	}
	
	public MultiplayerTeam (String name) throws RemoteException{
		super(name);
	}
	
	
	public MultiplayerTeam (String name, int maxSize) throws RemoteException{
		super (name, maxSize);
	}
	
	@Override
	public Objectmon nextObjectmon(){
		return super.nextObjectmon();
		}
	
	@Override
	public boolean canFight() {
		for (Objectmon tempmon : this) {
			if (tempmon.isFainted() != true){
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
	
	
	public void tick() {
		for (Objectmon teamMembers : this) {
			teamMembers.tick();
		}
	}

	@Override
	public boolean save(String arg0) {
		File savedTeam = new File (arg0);
		try (PrintWriter info = new PrintWriter (savedTeam)){
			String teamInfo = this.getName() + " " + this.getMaxSize();
			for (int i = 0; i < this.size(); i++) {
				Objectmon tempmon = this.get(i);
				teamInfo = teamInfo + " " + tempmon.getName() + " " + tempmon.getHp() + " ";
				teamInfo = teamInfo + tempmon.getStamina() + " " + tempmon.getWeight();
			}
			info.println(teamInfo);
			info.println("\n");
			String attackInfo = "";
			for (int i = 0; i < this.size(); i++) {
				for (int j = 0; j < this.get(i).getAttacks().size(); j ++) {
					attackInfo = attackInfo + this.get(i).getAttacks().get(j).getClass() + " " + 
							this.get(i).getAttacks().get(j).getBelongsTo().getName() + " " + 
							this.get(i).getAttacks().get(j).getName() + " ";
				}
			}
			info.println(attackInfo);
			info.close();
			return true;
		} 
		catch (FileNotFoundException e) {
			System.out.println("This FileTeam could not be saved because it did not have a corresponding file.");
			e.printStackTrace();
			return false;
		}
	}
	

	@Override
	public MultiplayerTeam load(String arg0) {
		File loadedTeam = new File (arg0);
		try (Scanner info = new Scanner (loadedTeam)){
			MultiplayerTeam loaded = new MultiplayerTeam (info.next(), info.nextInt());
			try{
				for(int i = 0; i < loaded.getMaxSize(); i++) {
				RemoteObjectmon tempmon = new RemoteObjectmon (info.next(), info.nextInt(), info.nextInt(), info.nextInt());
				List <AbstractAttack> tempmonAttacks = new ArrayList <>();
				tempmon.setAttacks(tempmonAttacks);
				loaded.add ((RemoteObjectmon) tempmon);
				
				}
				info.nextLine();
			}
			catch (NoSuchElementException d) {
				
			}
			while (info.hasNext()) {
				String className = (info.next() + " " + info.next());
				String belongsToName = info.next();
				String attackName = info.next();
				
				if (className.equals("class edu.uab.cs203.lab08.BadPoisonAttack")) {
					BadPoisonAttack tempAttack = new BadPoisonAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
				else if (className.equals("class edu.uab.cs203.lab08.PoisonAttack")) {
					PoisonAttack tempAttack = new PoisonAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
				else if (className.equals("class edu.uab.cs203.lab08.FreezingAttack")) {
					FreezingAttack tempAttack = new FreezingAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
				else if (className.equals("class edu.uab.cs203.lab08.SleepAttack")) {
					SleepAttack tempAttack = new SleepAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
				else if (className.equals("class edu.uab.cs203.lab08.ParalyzingAttack")) {
					ParalyzingAttack tempAttack = new ParalyzingAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
				else {
					BasicAttack tempAttack = new BasicAttack (loaded.matchAttack(belongsToName), attackName);
					tempAttack.getBelongsTo().getAttacks().add(tempAttack);
				}
			}
			info.close();
			return loaded;
		} 
		catch (FileNotFoundException e) {
			System.out.println("This FileTeam could not be loaded because it did not have a corresponding file.");
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Objectmon matchAttack (String name) {
		for (Objectmon match : this) {
			if (match.getName().equals(name)) {
				return match;
			}
		}
		return null;
	}
}