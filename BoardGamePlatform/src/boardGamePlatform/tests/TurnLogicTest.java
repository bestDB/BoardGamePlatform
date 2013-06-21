/*
 * 
 */
package boardGamePlatform.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.junit.Test;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.StateChangable;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.platformExceptions.UninitializedInitialStateException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.utilities.BoardBuilder;
import boardGamePlatform.utilities.StateHelper;



// TODO: Auto-generated Javadoc
class StartGameState extends State {

	public StartGameState(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
	}

	@Override
	public Context getContext() {
		return new Context("StartGame", board, super.currentPlayer , allPlayers) ;
	}

	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		stateChangable.changeState( new PlayerMove(board,currentPlayer,allPlayers, stateChangable) );
	}

	@Override
	public boolean gameEnded() {
		// TODO Auto-generated method stub
		return false;
	}
}

class PlayerMove extends State {

	public PlayerMove(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context getContext() {
		return new Context("PlayerMove", board, super.currentPlayer , allPlayers) ;
	}

	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		
	}

	@Override
	public boolean gameEnded() {
		// TODO Auto-generated method stub
		return false;
	}
	
}


/**
 * The Class TurnLogicTest.
 */
public class TurnLogicTest {

	/**
	 * Test.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 */
	@Test
	public void test() throws UnsupportedBoardSizeException {
		TurnLogic turnLogic = new TurnLogic();
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		Player currentPlayer = player2;
		List<Player> allPlayers = new LinkedList<Player>();
		for (Player player : new Player[] { player1, player2, player3 , player4 } )
			allPlayers.add( player );
		
		Board board = new Board();
		BoardBuilder boardBuilder = new BoardBuilder(board);
		boardBuilder.makeSimpleLinearBoard(5);
		
		State startState = new StartGameState(board, currentPlayer, allPlayers, turnLogic);
		turnLogic.setStartState(startState);
		
		Context firstContext = turnLogic.getContext();
		assertEquals("StartGame", firstContext.getName() );
		assertEquals( player2 , turnLogic.getCurrentPlayer() );
		
		turnLogic.makeTurn(null, null);
		
		Context secondContext = turnLogic.getContext();
		assertEquals("PlayerMove", secondContext.getName() );
		assertEquals( player3 , turnLogic.getCurrentPlayer() );
	}
	
	/**
	 * Test exception.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 */
	@Test(expected=UninitializedInitialStateException.class)
	public void testException() throws UnsupportedBoardSizeException {
		TurnLogic turnLogic = new TurnLogic();
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		Player currentPlayer = player2;
		List<Player> allPlayers = new LinkedList<Player>();
		for (Player player : new Player[] { player1, player2, player3 , player4 } )
			allPlayers.add( player );
		
		Board board = new Board();
		BoardBuilder boardBuilder = new BoardBuilder(board);
		boardBuilder.makeSimpleLinearBoard(5);
		
		State startState = new StartGameState(board, currentPlayer, allPlayers, turnLogic);
		//turnLogic.setStartState(startState);
		
		Context firstContext = turnLogic.getContext();
		assertEquals("StartGame", firstContext.getName() );
		assertEquals( player2 , turnLogic.getCurrentPlayer() );
		
		turnLogic.makeTurn(null, null);
		
		Context secondContext = turnLogic.getContext();
		assertEquals("PlayerMove", secondContext.getName() );
		assertEquals( player3 , turnLogic.getCurrentPlayer() );
	}

}
