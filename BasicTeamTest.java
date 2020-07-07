package lab5;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uab.cs203.Named;
import edu.uab.cs203.Objectmon;
import edu.uab.cs203.Team;
import edu.uab.cs203.TrainingGym;
import junit.framework.JUnit4TestAdapter;

class BasicTeamTest extends TrainingGym{
	private BasicTeam <Objectmon> team1;
	private BasicTeam <Objectmon> team2;
	private BasicTeam <Objectmon> team3;
	
	private Objectmon monster1;
	private Objectmon monster2;
	private Objectmon monster3;
	private Objectmon monster4;
	
	@BeforeEach
	void init() {
		
		team1 = new BasicTeam<Objectmon>("Team 1", 4);
		team2 = new BasicTeam <Objectmon>("Team 2", 4);
		team3 = new BasicTeam <Objectmon>("Team 3", 4);
		
		monster1 = new Objectmon ("Unus");
		monster2 = new Objectmon ("Duo");
		monster3 = new Objectmon ("Tres");
		monster4 = new Objectmon ("Quattuor");
	}
	
	@Test
	void testBasicTeam() {
		assertTrue (team1 instanceof BasicTeam, "Team 1 is not an instance of BasicTeam");
		assertTrue (team1 instanceof Named, "Team 1 is not an instance of Named.");
		assertTrue (team1 instanceof Team, "Team 1 is not an instance of Team");
	}

	@Test
	void testGetName() {
		String testName = "name";
		team1.setName(testName);
		assertEquals (team1.getName(), testName, "The name setters and getters failed");
	}


	@Test
	void testAddE() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(monster3);
		assertTrue (team1.add(monster4), "The add method added an Objectmon to an available slot on the team.");
		assertFalse (team1.add(new Objectmon ("Failchu")), "The add method ignored the team size limit.");
	}

	@Test
	void testAddIntE() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(1, monster3);
		assertEquals (team1.get(1), monster3, "The objectmon was not added in the correct index location.");
		team1.add(monster4);
		team1.add(1, new Objectmon ("Failchu"));
		assertEquals (team1.get(1), monster3, "The add method ignored the team size limit.");
	}

	@Test
	void testAddAllCollectionOfQextendsE() {
		team1.add(monster1);
		team1.add(monster2);
		team2.add(monster3);
		team2.add(monster4);
		assertTrue (team1.addAll(team2), "The add all method failed.");
		team3.add(monster1);
		team3.add(monster2);
		assertFalse (team1.addAll(team3), "The add all method ignored the team size limit.");
	}

	@Test
	void testAddAllIntCollectionOfQextendsE() {
		team1.add(monster1);
		team1.add(monster2);
		team2.add(monster3);
		team2.add(monster4);
		assertTrue (team1.addAll(0, team2), "The add all method failed.");
		team3.add(monster1);
		team3.add(monster2);
		assertFalse (team1.addAll(0, team3), "The add all method ignored the team size limit.");
	}

	@Test
	void testClear() {
		team1.add(monster1);
		team1.clear();
		assertFalse (team1.get(0).equals(monster1), "The clear method failed.");
	}

	@Test
	void testContains() {
		team1.add(monster1);
		assertTrue (team1.contains(monster1), "The contains method failed.");
	}

	@Test
	void testContainsAll() {
		team1.add(monster1);
		team1.add(monster2);
		team2.add(monster3);
		team2.add(monster4);
		team1.addAll(0, team2);
		assertTrue (team1.containsAll(team2), "The contains all method failed.");
	}

	@Test
	void testGet() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(1, monster3);
		assertEquals (team1.get(1), monster3, "The get method failed.");
	}

	@Test
	void testIndexOf() {
		team1.add(monster1);
		assertEquals (team1.indexOf(monster1), 0, "The index of method failed.");
		assertFalse (team1.indexOf(monster1) == 1, "The index of method failed.");
	
	}

	@Test
	void testIsEmpty() {
		assertTrue (team1.isEmpty(), "The is empty method failed.");
		team1.add(monster1);
		assertFalse (team1.isEmpty(), "The is empty method failed.");
	}

	@Test
	void testIterator() {
		Iterator <Objectmon> it = team1.iterator();
		assertFalse (it.hasNext(), "Returned true on an empty list.");
		team1.add(monster1);
		team1.add(monster2);
		team1.add(1, monster3);
		assertTrue (it.hasNext(), "The iterator failed.");
		it.next();
		it.next();
		assertEquals (it.next(), monster2, "The iterator failed.");
		assertFalse (it.hasNext(), "Returned true when there were no more entries in the list.");
	}

	@Test
	void testLastIndexOf() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(monster3);
		assertEquals (team1.lastIndexOf(monster1), 0, "The last index of method failed.");
	}

	@Test
	void testListIterator() {
		ListIterator <Objectmon> it = team1.listIterator();
		assertFalse (it.hasNext(), "Returned true on an empty list.");
		team1.add(monster1);
		team1.add(monster2);
		team1.add(1, monster3);
		assertTrue (it.hasNext(), "The iterator failed.");
		it.next();
		it.next();
		assertEquals (it.next(), monster2, "The iterator failed.");
		assertFalse (it.hasNext(), "Returned true when there were no more entries in the list.");
		
		Iterator <Objectmon> intIt = team1.listIterator(1);
		assertFalse (intIt.hasNext(), "Returned true on an empty list.");
		team1.add(monster1);
		team1.add(monster2);
		team1.add(1, monster3);
		assertTrue (intIt.hasNext(), "The iterator failed.");
		intIt.next();
		assertEquals (intIt.next(), monster2, "The iterator failed.");
		assertFalse (intIt.hasNext(), "Returned true when there were no more entries in the list.");
	}


	@Test
	void testRemoveObject() {
		team1.add(monster1);
		team1.remove(monster1);
		assertFalse (team1.contains(monster1), "The Remove method failed.");
	}

	@Test
	void testRemoveInt() {
		team1.add(monster1);
		team1.remove(0);
		assertFalse (team1.contains(monster1), "The Remove method failed.");
	}

	@Test
	void testRemoveAll() {
		team1.add(monster1);
		team1.add(monster2);
		team2.add(monster3);
		team2.add(monster4);
		team1.addAll(team2);
		team1.removeAll (team2);
		assertFalse (team1.contains(monster3), "The Remove method failed.");
		assertFalse (team1.contains(monster4), "The Remove method failed.");
	}

	@Test
	void testRetainAll() {
		team1.add(monster1);
		team1.add(monster2);
		team2.add(monster3);
		team2.add(monster4);
		team1.addAll(team2);
		team1.retainAll (team2);
		assertFalse (team1.contains(monster1), "The Remove method failed.");
		assertFalse (team1.contains(monster1), "The Remove method failed.");
		assertTrue (team1.contains(monster3), "The Remove method failed.");
		assertTrue (team1.contains(monster4), "The Remove method failed.");
	}

	@Test
	void testSet() {
		team1.add(monster1);
		team1.set (0, monster2);
		assertEquals (team1.get(0), monster2, "The set method failed.");
	}

	@Test
	void testSize() {
		team1.add(monster1);
		assertEquals (team1.size(), 1, "The size method failed.");
	}

	@Test
	void testSubList() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(monster3);
		List subTeam1 = new List ();
		subTeam1 = (List) team1.subList(0, 1);
		assertEquals (((java.util.List<Objectmon>) subTeam1).get(0), monster1, "The sublist method failed.");
		assertEquals (((java.util.List<Objectmon>) subTeam1).get(1), monster2, "The sublist method failed.");
	}

	@Test
	void testToArray() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(monster3);
		Object [] array1 = team1.toArray();
		assertEquals (team1.get(0), array1 [0], "The to array method failed.");
		assertEquals (team1.get(1), array1 [1], "The to array method failed.");
	}

	@Test
	void testToArrayTArray() {
		team1.add(monster1);
		team1.add(monster2);
		team1.add(monster3);
		Objectmon [] array1 = team1.toArray(new Objectmon [1]);
		assertEquals (team1.get(1), array1 [1], "The to array method failed.");
		assertEquals (team1.get(2), array1 [2], "The to array method failed.");
	}

	@Test
	void testCanFight() {
		Objectmon faintedMonster = new Objectmon ("KOed", 0, 1, 5);
		team1.add(faintedMonster);
		assertFalse (team1.canFight(), "The can fight method failed.");
		team1.add(monster1);
		assertTrue (team1.canFight(), "The can fight method failed.");
	}

	@Test
	void testGetMaxSize() {
		assertEquals (team1.getMaxSize(), 4, "The get max size method failed.");
	}

	@Test
	void testNextObjectmon() {
		Objectmon faintedMonster = new Objectmon ("KOed", 0, 1, 5);
		team1.add(faintedMonster);
		assertNull (team1.nextObjectmon (), "The next Objectmon method failed.");
		team2.add(monster1);
		assertEquals (team2.nextObjectmon(), monster1, "The next Objectmon method failed.");
	}

	@Test
	void testSetMaxSize() {
		team1.setMaxSize(5);
		assertEquals (team1.getMaxSize(), 5, "The set max size method failed.");
	}
	
	public static void main (String [] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(BasicTeamTest.class));
	}
	
	public BasicTeamTest () {
		
	}
}
