package com.hp.fb.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SampleJunitTest {

	private Collection collection;

	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
	}

	@Before
	public void setUp() {
		collection = new ArrayList();
		System.out.println("@Before - setUp");
	}

	@After
	public void tearDown() {
		collection.clear();
		System.out.println("@After - tearDown");
	}

	@Test
	public void emptyCollection() {
		assertTrue(collection.isEmpty());
		System.out.println("@Test - emptyCollection");
	}

	@Test
	public void oneItemCollection() {
		collection.add("itemA");
		assertEquals(1, collection.size());
		System.out.println("@Test - oneItemCollection");
		
	}
	@Test
	public void getValue() {
		int val=20;
		assertEquals(20, val);
	}
}
