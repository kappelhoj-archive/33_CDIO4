package test.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.ChanceCardDeck;
import entity.Player;


public class ChanceCardDeckTest {

	ChanceCardDeck chanceCardDeck;
	Player player;
	
	@Before
	public void setUp() throws Exception 
	{
		chanceCardDeck = new ChanceCardDeck();
		player = new Player("tim");
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		chanceCardDeck = null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
