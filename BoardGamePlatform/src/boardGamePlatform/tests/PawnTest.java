/*
 * 
 */
package boardGamePlatform.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;

// TODO: Auto-generated Javadoc
/**
 * The Class PawnTest.
 */
public class PawnTest {

	/**
	 * Test constructors and set get.
	 */
	@Test
	public void testConstructorsAndSetGet() {
		Pawn testP = new Pawn(0);
		Map<String, Item> items = new HashMap<String, Item>();
		Item i = new Item(0);
		items.put("Item", i);
		
		
		Pawn pawn1 = new Pawn(0);
		Pawn pawn2 = new Pawn(0,items);
		
		assertEquals(0, pawn1.getPawnID());
		assertEquals(0, pawn1.getPawnID());
		assertEquals(new HashMap<String, Item>(), pawn1.getItems());
		
		assertEquals(0, pawn2.getPawnID());
		assertEquals(0, pawn2.getPawnID());
		assertEquals(items.get("Item"), pawn2.getItems().get("Item"));
		
		pawn1.setItems(items);
		assertEquals(0, pawn1.getPawnID());
		assertEquals(items.get("Item"), pawn1.getItems().get("Item"));
		
		items.put("Item2", i);
		pawn1.addItem("Item2", i);
		pawn1.removeItem("Item");
		assertEquals(null, pawn1.getItems().get("Item"));
		assertEquals(items.get("Item2"), pawn1.getItems().get("Item2"));
		
	}

}
