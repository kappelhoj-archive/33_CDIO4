package test_i_Junit;

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

		//player pays a rent of 2000 to fieldOwner
		player.payRent(fieldOwner, 2000);

		//Checks if the payment went thru.
		assertEquals("Ja hej1",32000,fieldOwner.getAccountBalance());
		assertEquals("Ja hej2", 28000,player.getAccountBalance());
		
		//Player pays so he ends up with 0 in wallet.
		player.payRent(fieldOwner, 28000);
		assertEquals("Ja hej3", 0,player.getAccountBalance());
	}

	@Test
	public void testSetFields() {
		int[] houseRent ={1,2,3,4,5};
		//creats 3 fields
		Ownable field = new Brewery(1, "Brewery", "Brewery", "dette er en test", 2000);
		Ownable field1 = new Shipping(2, "Shipping", "Shipping", "dette er en test", 2000);
		Ownable field2 = new Street(3, "House", "House", "detter er en test", 2000, "yellow", 200, 4000,houseRent,1);



		//sets a owner for 1 field
		Ownable expectedField = field;
		player.setFields(field);
		
		//Checks if the player has the field
		Ownable[] actualField = player.getFields();
		assertEquals(expectedField, actualField[0]);

		// sets some more field for the same owner. 
		player.setFields(field1);
		Ownable expectedField1 = field1;

		player.setFields(field2);
		Ownable expectedField2 = field2;

		//checks if the players houses has stacked correctly
		actualField = player.getFields();

		assertEquals("Expected: "+expectedField1+" Actual: "+actualField[1], expectedField1, actualField[1]);
		assertEquals(expectedField2, actualField[2]);
		assertEquals(expectedField, actualField[0]);

	}

	@Test
	public void testRemoveField() {
		//creats a field
		Ownable field = new Brewery(1, "Brewery", "Brewery", "dette er en test", 2000);

		//creats a field and checks the owner.
		field.setOwner(player);
		Ownable expectedField = field;
		player.setFields(field);
		Ownable[] actualField = player.getFields();
		actualField = player.getFields();
		assertEquals(expectedField,actualField[0]);

		//removes the field/owner
		Ownable[] removedField = player.getFields();
		player.removeField(removedField[0]);
		
		//Checks if it removed the field form the owner.
		Ownable expectedFieldRemoved =null;
		Ownable[] actualFieldRemoved = player.getFields();
		assertEquals("expected "+expectedFieldRemoved+" actual "+actualFieldRemoved,expectedFieldRemoved,actualFieldRemoved);


	}

	@Test
	public void testBuyField() {
		//creats a field
		Ownable field = new Brewery(1, "Brewery", "Brewery", "dette er en test", 2000);
		
		//buys a field
		fieldOwner.buyField(field);

		assertEquals("Onkel Anders",field.getOwner().getName());
		assertEquals(28000,fieldOwner.getAccountBalance());
	}

	@Test
	public void testGetFortune() {
		//creats 3 fields
		int[] houseRent ={1,2,3,4,5};
		Ownable field = new Brewery(1, "Brewery", "Brewery", "dette er en test", 2000);
		Ownable field1 = new Shipping(2, "Shipping", "Shipping", "dette er en test", 2000);
		Street field2 = new Street(3, "House", "House", "detter er en test", 2000, "yellow", 200, 4000,houseRent,1);
		
		//buys 3 fields
		fieldOwner.buyField(field);
		fieldOwner.buyField(field1);
		fieldOwner.buyField(field2);
		
		//gives him 2 houses on field 2
		field2.changeNumbOfHouses(2);
		
		//he buys 3 houses and gets 2 houses for free = earning of 2 houses of 4000 = 38000.
		assertEquals(24000,fieldOwner.getAccountBalance());
		assertEquals(38000,fieldOwner.getFortune());


	}

}
