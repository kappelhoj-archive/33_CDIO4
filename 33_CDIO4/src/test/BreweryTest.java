package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import entity.field.*;
import entity.Player;
import entity.field.*;


import org.junit.Test;

public class BreweryTest {

	Player player;
	Player player2;
	Brewery tuborg;
	
	
	
	@Before
	public void setUp() throws Exception{
		player = new Player ("Mikkel");
		player2 = new Player ("Simon");
		tuborg = new Brewery (13,"Tuborg","Brewery","Bryggeri",3000);
	}
	
	
	@After
	public void tearDown() throws Exception {
		tuborg = null;
	}
	
	@Test
	
	public void test() {
		tuborg.setOwner(player2);
		int baseRent = 100;
		int diceSum = 10;
		int numbOfBreweries = 1;
		tuborg.setDiceSum(diceSum);
		player.payRent(player2, tuborg.getRent());
		
		int expected = 29000;
		int actual = player.getAccountBalance();
		System.out.println(player.getAccountBalance());
		assertEquals(expected, actual);
		
	}

}
