package com.softcell.adminservice.domain;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManagerCircularLinkedListTest {

	static Organization org;
	
	@BeforeClass
	public static void setup(){
		org = new Organization();
		org.setOrgId(1L);
	}
	
	@Test
	public void testSingleAdd(){
		CircularLinkedList<Manager> list = new CircularLinkedList<>();
	
		Manager m1 = new Manager(1L, ApplicationType.HOME_LOAN, (byte)1, org, "L1");
		list.add(m1);
		assertEquals(m1, list.getNext());
		assertEquals(m1, list.getNext());
	}
	
	@Test
	public void testMultipleAdd(){
		CircularLinkedList<Manager> list = new CircularLinkedList<>();
	
		Manager m1 = new Manager(1L, ApplicationType.HOME_LOAN, (byte)1, org, "L1");
		Manager m2 = new Manager(2L, ApplicationType.HOME_LOAN, (byte)1, org, "L2");
		Manager m3 = new Manager(3L, ApplicationType.HOME_LOAN, (byte)1, org, "L3");
		list.add(m1);
		list.add(m2);
		list.add(m3);
		assertEquals(m1, list.getNext());
		assertEquals(m2, list.getNext());
		assertEquals(m3, list.getNext());
		assertEquals(m1, list.getNext());
	}
	
	@Test
	public void testSingleDelete(){
		CircularLinkedList<Manager> list = new CircularLinkedList<>();
	
		Manager m1 = new Manager(1L, ApplicationType.HOME_LOAN, (byte)1, org, "L1");
		list.add(m1);
		list.delete(m1);
		assertEquals(null, list.getNext());
		
	}
	
	@Test
	public void testMultipleDelete(){
		CircularLinkedList<Manager> list = new CircularLinkedList<>();
	
		Manager m1 = new Manager(1L, ApplicationType.HOME_LOAN, (byte)1, org, "L1");
		Manager m2 = new Manager(2L, ApplicationType.HOME_LOAN, (byte)1, org, "L2");
		Manager m3 = new Manager(3L, ApplicationType.HOME_LOAN, (byte)1, org, "L3");
		Manager m4 = new Manager(4L, ApplicationType.HOME_LOAN, (byte)1, org, "L4");
		Manager m5 = new Manager(5L, ApplicationType.HOME_LOAN, (byte)1, org, "L5");
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		
		list.delete(m3);
		
		assertEquals(m1, list.getNext());
		assertEquals(m2, list.getNext());
		assertEquals(m4, list.getNext());
		assertEquals(m5, list.getNext());
		assertEquals(m1, list.getNext());
		
		list.delete(m5);
		
		assertEquals(m2, list.getNext());
		assertEquals(m4, list.getNext());
		assertEquals(m1, list.getNext());
		
	}
}
