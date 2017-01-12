package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import entity.field.Brewery;
import entity.Player;

import org.junit.Test;

public class BreweryTest {

	Player player;
	Player player2;
	Brewery tuborg;
	Brewery cola;

	@Before
	public void setUp() throws Exception {
		player = new Player("Mikkel");
		player2 = new Player("Simon");
		tuborg = new Brewery(13, "Tuborg", "Tapperi", "Bryggeri", 3000);
		cola = new Brewery(29, "Cocal Cola", "Tapperi", "Bryggeri", 3000);

	}

	@After
	public void tearDown() throws Exception {
		tuborg = null;
	}

	/**
	 * Test-ID: XX XX The method test_singleBreweryRent tests the credibility of
	 * the rent calculation when a brewery is asked to calculate the rent when
	 * the diceSum is 10, and only a single brewery is owned.
	 */
	@Test
	public void test_singleBreweryRent() {
		tuborg.setOwner(player);
		player.setFields(tuborg);
		tuborg.setDiceSum(10);
		int tuborgRent = tuborg.getRent();

		int expected = 1000;
		int actual = tuborgRent;
		assertEquals(expected, actual);
	}

	/**
	 * Test-ID: XX XX The method test_multipleBreweriesRent tests the
	 * credibility of the rent calculation when a brewery is asked to calculate
	 * the rent when the diceSum is 10, and more than one brewery is owned by
	 * the same player.
	 */
	@Test
	public void test_multipleBreweriesRent() {
		tuborg.setOwner(player);
		cola.setOwner(player);
		player.setFields(tuborg);
		player.setFields(cola);
		tuborg.setDiceSum(10);
		int tuborgRent = tuborg.getRent();

		int expected = 2000;
		int actual = tuborgRent;
		assertEquals(expected, actual);
	}

	/**
	 * Test-ID: XX XX The method test_dualOwnersBreweriesRent tests the
	 * credibility of the rent calculation when a brewery is asked to calculate
	 * the rent when the diceSum is 10, and each brewery is owned by two
	 * different players.
	 */
	@Test
	public void test_dualOwnersBreweriesRent() {
		tuborg.setOwner(player);
		cola.setOwner(player2);
		player.setFields(tuborg);
		player2.setFields(cola);
		tuborg.setDiceSum(10);

		int tuborgRent = tuborg.getRent();
		int colaRent = cola.getRent();

		int expected = 1000;
		int actual = tuborgRent;
		int actual2 = colaRent;
		assertEquals(expected, actual, actual2);
	}

}
