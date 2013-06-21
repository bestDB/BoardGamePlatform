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
import boardGamePlatform.game.Game;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.StateChangable;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.game.TurnMakeable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAvalaibleMoveException;
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.PlayerNotRespondException;
import boardGamePlatform.platformExceptions.UninitializedTurnHandlerException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.platformExceptions.UnsupportedMoveNumberException;
import boardGamePlatform.utilities.BoardBuilder;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;



// TODO: Auto-generated Javadoc
class StartState extends State {

	public StartState(Board board, Player currentPlayer,
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
		Item item = new Item(0);
		item.setAttribute("Amount", "300");
		currentPlayer.setItem("Money", item );
		BoardHelper boardHelper = new BoardHelper( board ) ;
		try {
			boardHelper.movePlayerFirstPawnByNum( currentPlayer, Integer.parseInt( currTurnResult.getProperty("Move") ) );
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotPawnFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAvalaibleMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedMoveNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlayerNotHasPawnsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FieldNotHasNeihgboursException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		stateChangable.changeState( new GameEnd(board,currentPlayer,allPlayers, stateChangable) );
	}

	@Override
	public boolean gameEnded() {
		// TODO Auto-generated method stub
		return false;
	}
}

class GameEnd extends State {

	public GameEnd(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context getContext() {
		return new Context("GameEnd", board, super.currentPlayer , allPlayers) ;
	}

	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		
	}

	@Override
	public boolean gameEnded() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

/**
 * The Class GameTest.
 */
public class GameTest {

	/**
	 * Test constructor.
	 */
	@Test
	public void testConstructor() {
		TurnLogic turnLogic = new TurnLogic();
		Game game = new Game(turnLogic);
		assertEquals(game.getTurnLogic(), turnLogic);
	}
	
	/**
	 * Test start game_ one state exception.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws PlayerNotRespondException the player not respond exception
	 */
	@Test(expected = UninitializedTurnHandlerException.class )
	public void testStartGame_OneStateException() throws UnsupportedBoardSizeException, PlayerNotRespondException {
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
		
		State startState = new StartState(board, currentPlayer, allPlayers, turnLogic);
		turnLogic.setStartState(startState);
		
		Game game = new Game(turnLogic);
		game.start();
	}
	
	/**
	 * Test start game_ one state.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 */
	@Test
	public void testStartGame_OneState() throws UnsupportedBoardSizeException, NotFieldFoundException, PlayerNotHasPawnsException {
		TurnLogic turnLogic = new TurnLogic();
		
		Board board = new Board();
		BoardBuilder boardBuilder = new BoardBuilder(board);
		BoardHelper boardHelper = new BoardHelper(board);
		boardBuilder.makeSimpleLinearBoard(9);
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		Player currentPlayer = player2;
		List<Player> allPlayers = new LinkedList<Player>();
		for (Player player : new Player[] { player1, player2, player3 , player4 } ) {
			Pawn pawn = new Pawn(0);
			player.addPawn(pawn);
			boardHelper.placePawnOnField(pawn, 0);
			allPlayers.add( player );
		}
		
		
		
		State startState = new StartState(board, currentPlayer, allPlayers, turnLogic);
		turnLogic.setStartState(startState);
		
		TurnHandler currentTurnHandler = new TurnHandler();
		TurnHandler otherTurnHandler = new TurnHandler();
		
		currentTurnHandler.setTurnMaker("StartGame", new TurnMakeable() {
			
			@Override
			public TurnResult makeTurn(Context context) {
				// TODO Auto-generated method stub
				TurnResult turnResult = new TurnResult();
				turnResult.setProperty("Move", "10");
				return turnResult;
			}
		});
		
		otherTurnHandler.setTurnMaker("StartGame", new TurnMakeable() {
			
			@Override
			public TurnResult makeTurn(Context context) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		for (Player p : allPlayers) {
			p.setCurrentTurnHandler( new RemoteTurnHandler(currentTurnHandler) );
			p.setOtherTurnHandler(new RemoteTurnHandler(otherTurnHandler) );
		}
		
		
		/*Game game = new Game(turnLogic);
		game.start();
		assertFalse( boardHelper.getFieldById(0).containPawn( player2.getPlayerFirstPawn() ) );
		assertEquals( true , game.getTurnLogic().gameEnded() );
		assertEquals( "GameEnd" , game.getTurnLogic().getContext().getName() );
		assertEquals( player3 , game.getTurnLogic().getCurrentPlayer() );
		assertEquals( 300 , Integer.parseInt( player2.getPlayerItems().get("Money").getAttribute("Amount") ) );
		assertEquals( player2.getPlayerFirstPawn() ,boardHelper.getFieldById(1).getFirstPawn() );
		*/
	}

}







