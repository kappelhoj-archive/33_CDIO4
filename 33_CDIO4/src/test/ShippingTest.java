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
	
	@Before
	public void setUp() throws Exception{
		player1 = new Player ("Mikkel");
		player2 = new Player ("Simon");
		mols = new Shipping (16,"Mols-linjen","Rederi","Et skibsselvskab",4000);
	}
	
	
	@After
	public void tearDown() throws Exception {
		mols = null;
	}
	
	
	
	@Test
	public void test() {
	mols.setOwner(player1);
	player1.getShippingsOwned();
	mols.getOwner().getShippingsOwned();
	player2.changeAccountBalance(mols.getRent());
	
	int expected = 29500;
	int actual = player2.getAccountBalance();
	assertEquals(expected, actual);
	}

}
