// Name: Stephen Max Benedict
package lab5;

import edu.uab.cs203.Objectmon;
import edu.uab.cs203.TrainingGym;
import edu.uab.cs203.AbstractGym;
import edu.uab.cs203.Named;


public class TeamTester extends TrainingGym {
	
	public TeamTester () {
		Objectmon pikachu = new Objectmon ("Pikachu", 50, 100, 12);
		Objectmon dratini = new Objectmon ("Dratini", 70, 100, 245);
		Objectmon gloom = new Objectmon ("Gloom", 60, 100, 28);
		Objectmon diglet = new Objectmon ("Diglet", 40, 100, 10);
		
		setTeamA(new BasicTeam<Objectmon> ("Team A", 2));
		getTeamA().add(pikachu);
		getTeamA().add(0, dratini);
		
		setTeamB(new BasicTeam<Objectmon> ("Team B", 2));
		getTeamB().add(gloom);
		getTeamB().add (0, diglet);
		
		this.setMaxRounds(25);
	}
	
	public static void main (String [] args) {
		TeamTester colorTown = new TeamTester ();
		colorTown.fight(colorTown.getMaxRounds());
	}
	
	
}