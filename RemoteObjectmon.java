import java.util.Scanner;

import edu.uab.cs203.attacks.AbstractAttack;
import edu.uab.cs203.attacks.BasicAttack;
import edu.uab.cs203.effects.StatusEffect;
import edu.uab.cs203.lab08.Statusmon;

public class RemoteObjectmon extends Statusmon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RemoteObjectmon (String name, int Hp, int stamina, int weight) {
		super (name, Hp, stamina, weight);
	}
	
	@Override
	public AbstractAttack nextAttack(){
		return super.nextAttack();
	}
	public void clearStatusEffect () {
		this.getStatusEffects().clear();
	}

	@Override
	public boolean addStatusEffect (StatusEffect applied) {
		this.clearStatusEffect();
		this.getStatusEffects().add(applied);
		return true;
	}
	
	public void tick () {
		if (this.getStatusEffects().isEmpty()) {
			super.tick();
		}
		else {
			this.getStatusEffects().get(0).tick();
		}
	}
	
	public String toString() {
		String basicText = "{" + this.getName() + "," + this.getHp() + "," + this.getStamina() + "," + this.getWeight() + "}"; 
		try {
			return (basicText + this.getStatusEffects().get(0).toString());
		}
		catch (IndexOutOfBoundsException e) {
			return basicText + " No status effect";
		}
	}
}
	
