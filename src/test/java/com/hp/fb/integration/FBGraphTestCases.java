package com.hp.fb.integration;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FBGraphTestCases {

	private static FBGraph fBGraph;

	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		fBGraph = new FBGraph(null);
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		fBGraph = null;
		System.out.println("@AfterClass - oneTimeTearDown");
	}

	@Test
	public void getMessage() {
		String name = fBGraph.getMessage("Name");
		assertEquals("Name, Welcome to DevOps", name);
		//assertEquals(false);
	
		System.out.println("@Test - getMessage");
	}
	
}
