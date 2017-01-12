package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import entity.field.*;
import entity.Player;
import entity.field.*;

import org.junit.Test;

public class ShippingTest {

	Player player1;
	Player player2;
	Shipping mols;
	Shipping ScaLineHH;
	Shipping ScaLineGR;
	Shipping ScaLineRP;

	@Before
	public void setUp() throws Exception {
		player1 = new Player("Mikkel");
		player2 = new Player("Simon");
		mols = new Shipping(16, "Mols-linjen", "Rederi", "Et færgeskibsselvskab", 4000);
		ScaLineHH = new Shipping(6, "ScaLine HH", "Rederi", "Et færgeskibsselvskab", 4000);
		ScaLineGR = new Shipping(26, "ScaLine GR", "Rederi", "Et færgeskibsselvskab", 4000);
		ScaLineRP = new Shipping(36, "ScaLine RP", "Rederi", "Et færgeskibsselvskab", 4000);

	}

	@After
	public void tearDown() throws Exception {
		mols = null;

	}

	/**
	 * Test-ID: XX XX The method test_singleShippingRent tests the credibility
	 * of the rent calculation when a shipping company is asked to calculate the
	 * rent when only a single brewery is owned by a player.
	 */

	@Test
	public void test_singleShippingRent() {
		mols.setOwner(player1);
		player1.setFields(mols);

		int expected = 500;
		int actual = mols.getRent();
		assertEquals(expected, actual);

	}

	/**
	 * Test-ID: XX XX The method test_singleShippingRent tests the credibility
	 * of the rent calculation when a shipping company is asked to calculate the
	 * rent when multiple shipping companies are owned by the same player.
	 */

	@Test
	public void test_multipleShippingRent() {
		mols.setOwner(player1);
		ScaLineHH.setOwner(player1);
		ScaLineRP.setOwner(player1);

		player1.setFields(mols);
		player1.setFields(ScaLineHH);
		player1.setFields(ScaLineRP);

		int expected = 2000;
		int actual = mols.getRent();
		assertEquals(expected, actual);

	}

	/**
	 * Test-ID: XX XX The method test_dualOwnersShippingRent tests the
	 * credibility of the rent calculation when a shipping company is asked to
	 * calculate the rent when multiple shipping companies are owned by two
	 * individual players.
	 */

	@Test
	public void test_dualOwnersShippingRent() {
		mols.setOwner(player1);
		ScaLineHH.setOwner(player1);
		ScaLineRP.setOwner(player2);
		ScaLineGR.setOwner(player2);

		player1.setFields(mols);
		player1.setFields(ScaLineHH);
		player2.setFields(ScaLineRP);
		player2.setFields(ScaLineGR);

		int expected = 1000;
		int actual = mols.getRent();
		int actual2 = ScaLineRP.getRent();

		assertEquals(expected, actual, actual2);

	}

}
