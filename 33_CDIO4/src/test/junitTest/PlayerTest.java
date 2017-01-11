package test.junitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Player;
import entity.field.*;

public class PlayerTest {
	Player player,fieldOwner;

	@Before
	public void setUp() throws Exception {
		player = new Player("Anders And");
		fieldOwner = new Player("Onkel Anders");
	
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		fieldOwner = null;
	}

	@Test
	public void testPayRent() {

		
		fail("Not yet implemented");
	}

	@Test
	public void testSetFields() {
		int[] houseRent ={1,2,3,4,5};
		Ownable field = new Brewery(1, "Brewery", "Brewery", "dette er en test", 2000);
		Ownable field1 = new Shipping(2, "Shipping", "Shipping", "dette er en test", 2000);
		Ownable field2 = new Street(3, "House", "House", "detter er en test", 2000, "yellow", 200, 4000,houseRent,1);
	
		
		
		
		
		Ownable expectedField = field;
		player.setFields(field);
		Ownable[] actualField = player.getFields();
		assertEquals(expectedField, actualField[0]);
		
		player.setFields(field1);
		Ownable expectedField1 = field1;
		player.setFields(field2);
		Ownable expectedField2 = field2;
		actualField = player.getFields();
		assertEquals("Expected: "+expectedField1+" Actual: "+actualField[1], expectedField1, actualField[1]);
		assertEquals(expectedField2, actualField[2]);
		assertEquals(expectedField, actualField[0]);
		
	}

	@Test
	public void testRemoveField() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuyField() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFortune() {
		fail("Not yet implemented");
	}

}
