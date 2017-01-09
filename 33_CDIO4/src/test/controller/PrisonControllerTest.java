package test.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.MainController;
import controller.PrisonController;
import entity.Player;

public class PrisonControllerTest {
	PrisonController prisonController;
	MainController mainController;
	Player player;

	@Before
	public void setUp() throws Exception {
		mainController = new MainController(null);
		prisonController = new PrisonController(mainController);
		player = new Player("TestPerson");
	}

	@After
	public void tearDown() throws Exception {
		mainController = null;
		prisonController = null;
		player = null;
	}

	@Test
	public void test_sendToPrison() {
		prisonController.sendToPrison(player);
		if(player.getInPrison() != true)
		{
			fail("The player's prison status was supposed to be true but was " + player.getInPrison());
		}
		if(player.getPosition() != 31)
		{
			fail("The players position on the board was supposed to be 31 but was " + player.getPosition());
		}
//		boolean expected = true;
//		boolean actual = player.getPrison();
//		
//		assertEquals(expected, actual);
	}

}
