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
		assertEquals(new Integer(1), i.get(0));
	}
	
	//checking adding
	
	@Test
	public void CheckIfAdditionToExistingListWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0) );
		assertEquals(new Double(2.0), l.get(0));
		assertEquals(new Double(3.0), l.get(1));
	}
	
	
	//testing removal
	
	@Test
	public void CheckIfRemovalOfTailWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(2));
		assertEquals(new Double(3.0), l.get(1));
	}
	
	@Test
	public void CheckIfRemovalOfHeadWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(0));
		assertEquals(new Double(3.0), l.get(0));
	}
	
	@Test
	public void CheckIfRemovalfromMiddleWorks() {
		IList<Double> l = new IList<Double>();
		l.add(new Double(2.0));
		l.add(new Double(3.0));
		l.add(new Double(4.0));
		assertNotNull(l.remove(1));
		assertEquals(new Double(2.0), l.get(0));
		assertEquals(new Double(4.0), l.get(1));
	}
	
	
	//checking thrown exceptions
	@Test
	public void TestIllegalRemoveFromEmptyList() {
		IList<Character> l = new IList<Character>();
		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(0);
		expectedException.expect(ListOutOfBoundsException.class);
		l.remove(1);
	}
	
	@Test
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

	@Test
	public void TestIllegalGetFromEmptyList() {
		IList<Character> l = new IList<Character>();
		expectedException.expect(ListOutOfBoundsException.class);
		l.get(0);
		expectedException.expect(ListOutOfBoundsException.class);
		l.get(1);
	}
	
	@Test
	public void TestIllegalGetFromFullList() {
		IList<Character> l = new IList<Character>();
		l.add(new Character('a'));
		l.add(new Character('x'));
		l.add(new Character('z'));

		expectedException.expect(ListOutOfBoundsException.class);
		l.get(3);
		expectedException.expect(ListOutOfBoundsException.class);
		l.get(6);
	}
	
	@Test
	public void TestInsertIntoEmptyList() {
		IList<String> l = new IList<String>();
		l.insert("Hello", 0);
		l.insert("World", 1);
		assertEquals("Hello", l.get(0));
		assertEquals("World", l.get(1));
	}
	
	@Test
	public void TestInvalidInsertIntoEmptyList() {
		IList<String> l = new IList<String>();
		expectedException.expect(ListOutOfBoundsException.class);
		l.insert("Hello", 1);
	}
	
	@Test
	public void TestInsertBetweenTwoIndexes() {
		IList<String> l = new IList<String>();
		l.insert("Hello", 0);
		l.insert("World", 1);
		l.insert("Java", 1);
		assertEquals("Hello", l.get(0));
		assertEquals("Java", l.get(1));
		assertEquals("World", l.get(2));
	}
	
	@Test
	public void AttemptInvalidInsert() {
		IList<String> l = new IList<String>();
		l.insert("Hello", 0);
		l.insert("World", 1);
		expectedException.expect(ListOutOfBoundsException.class);
		l.insert("Hello", 4884);
	}
	
	//if you remove from an empty list, return false, and check to see if list is still empty
}