package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.PrisonController;

public class PrisonControllerTest {

	PrisonController prisonController;
	
	@Before
	public void setUp() throws Exception {
		prisonController = new PrisonController();
	}

	@After
	public void tearDown() throws Exception {
		prisonController = null;
	}

	@Test
	public void inPrisonTest()
	{
		
	}

}
