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
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.utilities.BoardBuilder;




// TODO: Auto-generated Javadoc
/**
 * The Class BoardBuilderTest.
 */
public class BoardBuilderTest {

	/**
	 * Test make simple test board zero fields.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 */
	@Test(expected = UnsupportedBoardSizeException.class)
	public void testMakeSimpleTestBoardZeroFields() throws UnsupportedBoardSizeException{
		Board board = new Board();
		
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(0);
		assertEquals(new LinkedList<Field>(), board.getFields());
	}
	
	/**
	 * Test make simple test board one field.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 */
	@Test(expected = UnsupportedBoardSizeException.class)
	public void testMakeSimpleTestBoardOneField() throws UnsupportedBoardSizeException {
		Board board = new Board();
		
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(1);
		List<Field> fields = new LinkedList<Field>();
		fields.add(new Field(0));
		fields.get(0).addNeighbourField(fields.get(0));
		assertEquals(fields, board.getFields());
	}
	
	/**
	 * Test make simple test board two fields.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testMakeSimpleTestBoardTwoFields() throws UnsupportedBoardSizeException, FieldNotHasNeihgboursException{
		Board board = new Board();
		
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(2);

		assertEquals(board.getFields().get(0).getFirstNeighbour(), board.getFields().get(1));
		assertEquals(board.getFields().get(1).getFirstNeighbour(), board.getFields().get(0));
	}
	
	/**
	 * Test make simple test board three fields.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testMakeSimpleTestBoardThreeFields() throws UnsupportedBoardSizeException, FieldNotHasNeihgboursException{
		Board board = new Board();
		
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(3);

		assertEquals(board.getFields().get(0).getFirstNeighbour(), board.getFields().get(1));
		assertEquals(board.getFields().get(1).getFirstNeighbour(), board.getFields().get(2));
		assertEquals(board.getFields().get(2).getFirstNeighbour(), board.getFields().get(0));
		
		System.out.println(board);
	}

}
