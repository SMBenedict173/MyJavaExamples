import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import edu.uab.cs203.Objectmon;
import edu.uab.cs203.attacks.AbstractAttack;
import edu.uab.cs203.attacks.BasicAttack;
import edu.uab.cs203.lab08.BadPoisonAttack;
import edu.uab.cs203.lab08.FreezingAttack;
import edu.uab.cs203.lab08.Lab08StatusGym;
import edu.uab.cs203.lab08.ParalyzingAttack;
import edu.uab.cs203.lab08.PoisonAttack;
import edu.uab.cs203.lab08.SleepAttack;

public class TeamCreator{
	
	public static void main (String [] args) {
		RemoteObjectmon pikachu = new RemoteObjectmon ("Pikachu", 45, 3, 14);
		ParalyzingAttack thundershock = new ParalyzingAttack (pikachu, "Thundershock");
		thundershock.setName("Thundershock");
		BasicAttack tackle = new BasicAttack (pikachu, "Tackle");
		List <AbstractAttack> pikachuattacks = new ArrayList <> ();
		pikachuattacks.add(thundershock);
		pikachuattacks.add(tackle);
		pikachu.setAttacks(pikachuattacks);
		System.out.println(thundershock.getClass());
		
		RemoteObjectmon drowzee = new RemoteObjectmon ("Drowzee", 60, 2, 56);
		BasicAttack pound = new BasicAttack (drowzee, "Pound");
		SleepAttack hypnotize = new SleepAttack (drowzee, "Hypnotize");
		List <AbstractAttack> drowzeeattacks = new ArrayList <> ();
		drowzeeattacks.add(hypnotize);
		drowzeeattacks.add(pound);
		drowzee.setAttacks(drowzeeattacks);
		
		RemoteObjectmon articuno = new RemoteObjectmon ("Articuno", 89, 6, 30);
		BasicAttack wingAttack = new BasicAttack (articuno, "WingAttack");
		wingAttack.setName("WingAttack");
		FreezingAttack blizzard = new FreezingAttack (articuno, "Blizzard");
		FreezingAttack iceBeam = new FreezingAttack (articuno, "iceBeam");
		BasicAttack peck = new BasicAttack (articuno, "Peck");
		List <AbstractAttack> articunoattacks = new ArrayList <> ();
		articunoattacks.add(peck);
		articunoattacks.add(blizzard);
		articunoattacks.add(iceBeam);
		articunoattacks.add(wingAttack);
		articuno.setAttacks(articunoattacks);
		
		RemoteObjectmon muk = new RemoteObjectmon ("Muk", 72, 3, 12);
		BadPoisonAttack toxic = new BadPoisonAttack (muk, "Toxic");
		PoisonAttack sludge = new PoisonAttack (muk, "Sludge");
		BasicAttack lick = new BasicAttack (muk, "Lick");
		List <AbstractAttack> mukattacks = new ArrayList <> ();
		mukattacks.add(toxic);
		mukattacks.add(sludge);
		mukattacks.add(lick);
		muk.setAttacks(mukattacks);
		
		RemoteObjectmon onyx = new RemoteObjectmon ("Onyx", 142, 1, 1211);
		BasicAttack earthquake = new BasicAttack (onyx, "Earthquake");
		BasicAttack tailswipe = new BasicAttack (onyx, "Tailswipe");
		BasicAttack rockslide = new BasicAttack (onyx, "Rockslide");
		List <AbstractAttack> onyxattacks = new ArrayList <>();
		onyxattacks.add(earthquake);
		onyxattacks.add(tailswipe);
		onyxattacks.add(rockslide);
		onyx.setAttacks(onyxattacks);
		
		RemoteObjectmon venusaur = new RemoteObjectmon ("Venusaur", 120, 2, 254);
		BasicAttack  vinewhip = new BasicAttack (venusaur, "VineWhip");
		PoisonAttack poisonpowder = new PoisonAttack (venusaur, "PoisonPowder");
		BasicAttack pummel = new BasicAttack (venusaur, "Pummel");
		BasicAttack solarbeam = new BasicAttack (venusaur, "SolarBeam");
		List <AbstractAttack> venusaurattacks = new ArrayList <>();
		venusaurattacks.add(vinewhip);
		venusaurattacks.add(poisonpowder);
		venusaurattacks.add(pummel);
		venusaurattacks.add(solarbeam);
		venusaur.setAttacks(venusaurattacks);
		
		RemoteObjectmon jynx = new RemoteObjectmon ("Jynx", 70, 4, 86);
		BasicAttack psychic = new BasicAttack (jynx, "Psychic");
		FreezingAttack flurry = new FreezingAttack (jynx, "Flurry");
		SleepAttack sing = new SleepAttack (jynx, "Sing");
		List<AbstractAttack> jynxattacks = new ArrayList <> ();
		jynxattacks.add(psychic);
		jynxattacks.add(flurry);
		jynxattacks.add(sing);
		jynx.setAttacks(jynxattacks);
		
		RemoteObjectmon beedrill = new RemoteObjectmon ("Beedrill", 65, 6, 10);
		BasicAttack furyattack = new BasicAttack (beedrill, "FuryAttack");
		BadPoisonAttack deadlystinger = new BadPoisonAttack (beedrill, "DeadlyStinger");
		PoisonAttack poisonsting = new PoisonAttack (beedrill, "PoisonSting");
		List <AbstractAttack> beedrillattacks = new ArrayList <>();
		beedrillattacks.add(furyattack);
		beedrillattacks.add(deadlystinger);
		beedrillattacks.add(poisonsting);
		beedrill.setAttacks(beedrillattacks);
		
		RemoteObjectmon charizard = new RemoteObjectmon ("Charizard", 120, 4, 190);
		BasicAttack gust = new BasicAttack (charizard, "Gust");
		BasicAttack hyperbeam = new BasicAttack (charizard, "Hyperbeam");
		PoisonAttack smoke = new PoisonAttack (charizard, "Smoke");
		BasicAttack flamethrower = new BasicAttack (charizard, "Flamethrower");
		List <AbstractAttack> charizardattacks = new ArrayList <>();
		charizardattacks.add(gust);
		charizardattacks.add(hyperbeam);
		charizardattacks.add(smoke);
		charizardattacks.add(flamethrower);
		charizard.setAttacks(charizardattacks);
		
		RemoteObjectmon electabuzz = new RemoteObjectmon ("Electabuzz", 70, 5, 106);
		ParalyzingAttack thunder = new ParalyzingAttack (electabuzz, "Thunder");
		ParalyzingAttack lightningpunch = new ParalyzingAttack (electabuzz, "LightningPunch");
		BasicAttack slap = new BasicAttack (electabuzz, "Slap");
		List <AbstractAttack> electabuzzattacks = new ArrayList <>();
		electabuzzattacks.add(thunder);
		electabuzzattacks.add(lightningpunch);
		electabuzzattacks.add(slap);
		electabuzz.setAttacks(electabuzzattacks);
		
		RemoteObjectmon diglet = new RemoteObjectmon ("Diglet", 45, 5, 12);
		BasicAttack magnitude = new BasicAttack (diglet, "Magnitude");
		BasicAttack headbutt = new BasicAttack (diglet, "Headbutt");
		List <AbstractAttack> digletattacks = new ArrayList <>();
		digletattacks.add(magnitude);
		digletattacks.add(headbutt);
		diglet.setAttacks(digletattacks);
		
		RemoteObjectmon machop = new RemoteObjectmon ("Machop", 55, 5, 45);
		BasicAttack karatechop = new BasicAttack (machop, "KarateChop");
		BasicAttack strength = new BasicAttack (machop, "Strength");
		List <AbstractAttack> machopattacks = new ArrayList <>();
		machopattacks.add(karatechop);
		machopattacks.add(strength);
		machop.setAttacks(machopattacks);
		
		try {
			MultiplayerTeam teamA = new MultiplayerTeam ("TeamA", 6);
			teamA.add(pikachu);
			teamA.add(drowzee);
			teamA.add(articuno);
			teamA.add(muk);
			teamA.add(onyx);
			teamA.add(venusaur);
			teamA.save("MultiplayerTeamA.txt");
			
			MultiplayerTeam teamB = new MultiplayerTeam ("TeamB", 6);
			teamB.add(jynx);
			teamB.add(beedrill);
			teamB.add(charizard);
			teamB.add(electabuzz);
			teamB.add(diglet);
			teamB.add(machop);
			teamB.save("MultiplayerTeamB.txt");
			
			teamA.clear();
			teamA = teamA.load("MultiplayerTeamA.txt");
			
			teamB.clear();
			teamB = teamB.load("MultiplayerTeamB.txt");
			
			System.out.println(teamA.get(0).getName().toString());
			System.out.println(teamA.get(0).getAttacks().get(0).toString());
			
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
}