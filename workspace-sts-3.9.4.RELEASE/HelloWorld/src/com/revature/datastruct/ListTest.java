package com.revature.datastruct;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ListTest {
	//emptylist

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void CheckIfInsertingInEmptyListWorks() {
		IList<Integer> i = new IList<Integer>();
		i.add(new Integer(1));
		assertEquals(new Integer(1), i.indexOf(0));
	}
	
	//checking adding
	
	@Test
	public void CheckIfAdditionToExistingListWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0) );
		assertEquals(new Double(2.0), l.indexOf(0));
		assertEquals(new Double(3.0), l.indexOf(1));
	}
	
	
	//testing removal
	
	@Test
	public void CheckIfRemovalOfTailWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(2));
		assertEquals(new Double(3.0), l.indexOf(1));
	}
	
	@Test
	public void CheckIfRemovalOfHeadWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(0));
		assertEquals(new Double(3.0), l.indexOf(0));
	}
	
	@Test
	public void CheckIfRemovalfromMiddleWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(1));
		assertEquals(new Double(2.0), l.indexOf(0));
		assertEquals(new Double(4.0), l.indexOf(1));
	}
	
	
	//checking thrown exceptions
	
	public void TestIllegalRemoveFromEmptyList() {
		IList<Character> l = new IList<Character>();
		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(0);
		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(1);
	}
	
	public void TestIllegalRemoveFromFullList() {
		IList<Character> l = new IList<Character>();
		l.add(new Character('a'));
		l.add(new Character('x'));
		l.add(new Character('z'));

		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(3);
		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(6);
	}

	public void TestIllegalIndexOfFromEmptyList() {
		IList<Character> l = new IList<Character>();
		expectedException.expect(ListOutOfBoundsException.class);
		l.indexOf(0);
		expectedException.expect(ListOutOfBoundsException.class);
		l.indexOf(1);
	}
	
	public void TestIllegalIndexOfFromFullList() {
		IList<Character> l = new IList<Character>();
		l.add(new Character('a'));
		l.add(new Character('x'));
		l.add(new Character('z'));

		expectedException.expect(ListOutOfBoundsException.class);
		l.indexOf(3);
		expectedException.expect(ListOutOfBoundsException.class);
		l.indexOf(6);
	}
	
	
	
	//if you remove from an empty list, return false, and check to see if list is still empty
}