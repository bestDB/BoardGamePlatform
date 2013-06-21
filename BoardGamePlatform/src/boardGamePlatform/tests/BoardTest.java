/*
 * 
 */
package boardGamePlatform.tests;
import static org.junit.Assert.*;


import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;


// TODO: Auto-generated Javadoc
/**
 * The Class BoardTest.
 */
public class BoardTest {

	/**
	 * Test set board.
	 */
	@Test
	public void testSetBoard() {
		List<Field> fieldList = new LinkedList<Field>();
		Board board = new Board(fieldList);
		assertEquals(fieldList, board.getFields());
	}
	
	/**
	 * Test set fields.
	 */
	@Test
	public void testSetFields() {
		List<Field> fieldList = new LinkedList<Field>();
		Board board = new Board();
		board.setFields(fieldList);
		assertEquals(fieldList, board.getFields());
	}
	
	/**
	 * Test board.
	 */
	@Test
	public void testBoard() {
		List<Field> fieldList = new LinkedList<Field>();
		Board board = new Board(fieldList);
		assertEquals(fieldList, board.getFields());
	}

}
