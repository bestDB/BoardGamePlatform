/*
 * 
 */
package boardGamePlatform.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.junit.Test;

import boardGamePlatform.gameResources.Field;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldTest.
 */
public class FieldTest {

	/**
	 * Test field constructors.
	 */
	@Test
	public void testFieldConstructors() {
		Field f = new Field(0);
		List<Field> neighbours = new LinkedList<Field>();
		List<Pawn> pawns = new LinkedList<Pawn>();
		Map<String, Item> items= new HashMap<String, Item>();
		pawns.add(new Pawn(0));
		items.put("name",new Item(0));
		
		f.setNeighbourFields(neighbours);
		f.setPawns(pawns);
		f.setItems(items);
		
		Field cons1 = new Field(0);
		assertEquals(cons1.getID(), 0);
		assertEquals(cons1.getNeighbourFields(), f.getNeighbourFields());
		
		Field cons2 = new Field(0,pawns);
		assertEquals(cons2.getID(), 0);
		assertEquals(cons2.getFieldsPawns(), pawns);
		assertEquals(cons2.getNeighbourFields(), f.getNeighbourFields());
		
		Field cons21 = new Field(0,null);
		assertEquals(cons21.getID(), 0);
		assertEquals(cons21.getFieldsPawns(), new LinkedList<Pawn>());
		assertEquals(cons21.getNeighbourFields(), f.getNeighbourFields());
		
		Field cons3 = new Field(0,pawns,items);
		assertEquals(cons3.getID(), 0);
		assertEquals(cons3.getFieldsPawns(), f.getFieldsPawns());
		assertEquals(cons3.getItems(), f.getItems());
		assertEquals(cons3.getNeighbourFields(), f.getNeighbourFields());
		
		Field cons31 = new Field(1,null,null);
		assertEquals(cons31.getID(), 1);
		assertEquals(cons31.getFieldsPawns(), new LinkedList<Pawn>());
		assertEquals(cons31.getItems(), new HashMap<String, Item>());
		assertEquals(cons31.getNeighbourFields(), f.getNeighbourFields());
		
	}
	
	/**
	 * Test getting setting neighbours.
	 *
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test(expected = FieldNotHasNeihgboursException.class )
	public void testGettingSettingNeighbours() throws FieldNotHasNeihgboursException {
		Field f = new Field(0);
		Field f1 = new Field(1);
		Field f2 = new Field(2);
		
		assertEquals(f.getFirstNeighbour(), null);
		assertEquals(f.getLastNeighbour(), null);
		
		f.addNeighbourField(f1);
		assertEquals(f.getFirstNeighbour(), f1);
		assertEquals(f.getLastNeighbour(), f1);
		
		f.addNeighbourField(f2);
		assertEquals(f.getFirstNeighbour(), f1);
		assertEquals(f.getLastNeighbour(), f2);
		
	}

}
