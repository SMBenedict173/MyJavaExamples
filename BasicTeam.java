// Name: Stephen Max Benedict
package lab5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import edu.uab.cs203.Named;
import edu.uab.cs203.Objectmon;
import edu.uab.cs203.Team;

public class BasicTeam <E extends Objectmon> implements Team <E> {
	private String name;
	private int maxSize;
	private List <E> list;
	
	public BasicTeam (String teamName, int teamSize) {
		this.name = teamName;
		this.maxSize = teamSize;
		this.list = new ArrayList <E> ();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String arg0) {
		this.name = arg0;
	}

	@Override
	public boolean add(E arg0) {
		if (this.list.size() < this.maxSize) {
			this.list.add(arg0);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void add(int arg0, E arg1) {
		if (this.list.size() < this.maxSize) {
			this.list.add(arg0, arg1);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		if (this.list.size() <= (this.maxSize + arg0.size ())) {
				this.list.addAll(arg0);
				return true;
			}
		else {
			return false;
		}
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		if (this.list.size() <= (this.maxSize + arg1.size ())) {
			this.list.addAll(arg0, arg1);
			return true;
		}
	else {
		return false;
	}
}

	@Override
	public void clear() {
		this.list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.list.containsAll(arg0);
	}

	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		return this.list.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return this.list.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return this.list.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<E> listIterator() {
		return this.list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		return this.list.listIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public E remove(int arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.list.retainAll(arg0);
	}

	@Override
	public E set(int arg0, E arg1) {
		return this.list.set(arg0, arg1);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		return this.list.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.list.toArray(arg0);
	}

	@Override
	public boolean canFight() {
		if (this.nextObjectmon() != null){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int getMaxSize() {
		return this.maxSize;
	}

	@Override
	public E nextObjectmon() {
		for (int i = 0; i < this.list.size(); i++) {
			if (!this.list.get(i).isFainted()) {
				return this.list.get(i);
			}
		}
		return null;
	}

	@Override
	public void setMaxSize(int arg0) {
		this.maxSize = arg0;
	}
}