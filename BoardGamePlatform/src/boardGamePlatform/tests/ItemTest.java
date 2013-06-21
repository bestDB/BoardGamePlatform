/*
 * 
 */
package boardGamePlatform.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import boardGamePlatform.gameResources.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemTest.
 */
public class ItemTest {

	/**
	 * Test constructors and get set.
	 */
	@Test
	public void testConstructorsAndGetSet() {
		Item testI = new Item(0);
		Map<String, String> attrs = new HashMap<String, String>();
		testI.setName("item1");
		testI.setAttributes(attrs);
		
		Item item1 = new Item(0,"item1");
		Item item2 = new Item(0,"item1",attrs);
		
		assertEquals(testI.getName(), item1.getName());
		assertEquals(testI.getAttributes(),item1.getAttributes());
		assertEquals(testI.getAttributes(),item2.getAttributes());
		assertEquals(testI.getID(),item1.getID());
		
		item1.setAttribute("attr", "5");
		assertEquals(item1.containsAttribute("attr"), true);
		assertEquals(item1.getAttribute("attr"), "5");
		
	}

}
