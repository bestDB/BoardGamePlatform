/*
 * 
 */
package boardGamePlatform.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import boardGamePlatform.game.Player;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAvalaibleMoveException;
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.platformExceptions.UnsupportedMoveNumberException;
import boardGamePlatform.utilities.BoardBuilder;
import boardGamePlatform.utilities.BoardHelper;




// TODO: Auto-generated Javadoc
/**
 * The Class BoardHelperTest.
 */
public class BoardHelperTest {

	/**
	 * Test board helper.
	 */
	@Test
	public void testBoardHelper() {
		Board board = new Board();
		BoardHelper boardH = new BoardHelper(board);
		
		assertEquals(board, boardH.getBoard());
	}
	
	/**
	 * Test place pawn.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 */
	@Test
	public void testPlacePawn() throws UnsupportedBoardSizeException, NotFieldFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.placePawnOnField(p, 0);
		
		assertEquals(boardH.getFieldById(0).getFirstPawn(), p);
		
	}
	
	/**
	 * Test place pawn on field.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 */
	@Test(expected = NotFieldFoundException.class)
	public void testPlacePawnOnField() throws UnsupportedBoardSizeException, NotFieldFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.placePawnOnField(p, 6);

	}
	
	/**
	 * Test move pawn.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws NotPawnFoundException the not pawn found exception
	 */
	@Test
	public void testMovePawn() throws UnsupportedBoardSizeException, NotFieldFoundException, NotPawnFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.placePawnOnField(p, 0);
		boardH.movePawn(p, boardH.getFieldById(0), boardH.getFieldById(3));
		
		assertEquals(boardH.getFieldById(0).getFirstPawn(), null);
		assertEquals(boardH.getFieldById(3).getFirstPawn(), p);

	}
	
	/**
	 * Test move pawn not pawn found other pawn exists.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws NotPawnFoundException the not pawn found exception
	 */
	@Test(expected = NotPawnFoundException.class)
	public void testMovePawnNotPawnFoundOtherPawnExists() throws UnsupportedBoardSizeException, NotFieldFoundException, NotPawnFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.placePawnOnField(p, 0);
		boardH.movePawn(new Pawn(1), boardH.getFieldById(0), boardH.getFieldById(3));
	
	}
	
	/**
	 * Test move pawn not pawn found other pawn not exists.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws NotPawnFoundException the not pawn found exception
	 */
	@Test(expected = NotPawnFoundException.class)
	public void testMovePawnNotPawnFoundOtherPawnNotExists() throws UnsupportedBoardSizeException, NotFieldFoundException, NotPawnFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		boardH.movePawn(new Pawn(1), boardH.getFieldById(0), boardH.getFieldById(3));
	
	}
	
	/**
	 * Test get field by id.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 */
	@Test 
	public void testGetFieldById() throws UnsupportedBoardSizeException, NotFieldFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		
		assertEquals(boardH.getFieldById(0), board.getFields().get(0));
	}
	
	/**
	 * Test get not existing field by id.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 */
	@Test(expected =  NotFieldFoundException.class) 
	public void testGetNotExistingFieldById() throws UnsupportedBoardSizeException, NotFieldFoundException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		boardH.getFieldById(-1);
	}
	
	/**
	 * Test move pawn by num.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testMovePawnByNum() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.getFieldById(1).addPawn(p);
		boardH.movePawn(p, 3);
		
		assertEquals(boardH.getFieldById(1).getFirstPawn(), null);
		assertEquals(boardH.getFieldById(4).getFirstPawn(), p);
	}
	
	/**
	 * Test move pawn by num crossing start.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testMovePawnByNumCrossingStart() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.getFieldById(3).addPawn(p);
		boardH.movePawn(p, 3);
		
		assertEquals(boardH.getFieldById(3).getFirstPawn(), null);
		assertEquals(boardH.getFieldById(1).getFirstPawn(), p);
	}
	
	/**
	 * Test move pawn by num pawn null.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test(expected = NotPawnFoundException.class)
	public void testMovePawnByNumPawnNull() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.getFieldById(3).addPawn(p);
		boardH.movePawn(null, 3);
	
	}
	
	/**
	 * Test move pawn by num num less than zero.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test(expected = UnsupportedMoveNumberException.class)
	public void testMovePawnByNumNumLessThanZero() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.getFieldById(3).addPawn(p);
		boardH.movePawn(p, -2);
	
	}
	
	/**
	 * Test move pawn by num num pawn doesnt exist.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test(expected = NotPawnFoundException.class)
	public void testMovePawnByNumNumPawnDoesntExist() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		boardH.movePawn(p, 3);
	
	}
	
	
	/**
	 * Test move pawn by player.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testMovePawnByPlayer() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException, UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		Board board = new Board();
		BoardBuilder boardB = new BoardBuilder(board);
		boardB.makeSimpleLinearBoard(5);
		BoardHelper boardH = new BoardHelper(board);
		Pawn p = new Pawn(0);
		Player pl = new Player(0);
		pl.addPawn(p);
		boardH.getFieldById(3).addPawn(p);
		boardH.movePlayerFirstPawnByNum(pl, 3);
		
		assertEquals(boardH.getFieldById(3).getFirstPawn(), null);
		assertEquals(boardH.getFieldById(1).getFirstPawn(), p);
	}

}
