/*
 * 
 */
package monopolyConsolePrototype.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import boardGamePlatform.*;
import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.game.TurnMakeable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAvalaibleMoveException;
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.PlayerNotRespondException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.platformExceptions.UnsupportedMoveNumberException;
import boardGamePlatform.utilities.BoardBuilder;
import boardGamePlatform.utilities.BoardHelper;

import monopolyConsolePrototype.states.Policeman;
import monopolyConsolePrototype.states.Prison;
import monopolyConsolePrototype.states.StartGame;
 
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
class CurrentStartGameTurnHandler implements TurnMakeable {

	@Override
	public TurnResult makeTurn(Context context) {
		return null;
	}
	
}

class OtherStartGameTurnHandler implements TurnMakeable {
	
	public TurnResult makeTurn(Context context) {
		return null;
	}
}


class CurrentMakeMoveTurnHandler implements TurnMakeable {
	public TurnResult makeTurn(Context context) {
		TurnResult turnResult = new TurnResult();
		turnResult.setProperty("Move", "6");
		return turnResult;
	}
}

class OtherMakeMoveTurnHandler implements TurnMakeable {

	@Override
	public TurnResult makeTurn(Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class CurrentAtNobodysFieldTurnHandler implements TurnMakeable {

	@Override
	public TurnResult makeTurn(Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class OtherAtNobodysFieldTurnHandler implements TurnMakeable {

	@Override
	public TurnResult makeTurn(Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

/**
 * The Class BeginningFirstPlay.
 */
public class BeginningFirstPlay {
	
	/** The turn logic. */
	TurnLogic turnLogic;
	
	/** The board. */
	Board board;
	
	/** The one. */
	Player one;
	
	/** The two. */
	Player two;
	
	/**
	 * Sets the up.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 */
	@Before
	public void setUp() throws UnsupportedBoardSizeException, NotFieldFoundException {
		board = new Board();
		
		BoardBuilder boardBuilder = new BoardBuilder(  board );
		
		boardBuilder.makeSimpleLinearBoard(16);
		
		for ( Field f : board.getFields() ) {
			f.addAttribute("Owner", "0");
			f.addAttribute("Price", "100");
			f.addItem( "House" , new Item(0,"Amount","0") );
		}
			
			
		TurnHandler oneCurrTurnHandler = new TurnHandler();
		TurnHandler oneOtherTurnHandler = new TurnHandler();
		
		TurnHandler twoCurrTurnHandler = new TurnHandler();
		TurnHandler twoOtherTurnHandler = new TurnHandler();
		
		Pawn onePawn = new Pawn(1);
		Pawn twoPawn = new Pawn(2);
		
		BoardHelper boardHelper = new BoardHelper(board);
		boardHelper.placePawnOnField(onePawn, 0);
		boardHelper.placePawnOnField(twoPawn, 0);
		
		one = new Player(1,new RemoteTurnHandler(oneCurrTurnHandler) , new RemoteTurnHandler(oneOtherTurnHandler ) );
		one.addPawn(onePawn);
		two = new Player(2 ,new RemoteTurnHandler(twoCurrTurnHandler) , new RemoteTurnHandler( twoOtherTurnHandler ) );
		two.addPawn(twoPawn);
		
		Item oneMoney = new Item(0);
		oneMoney.setAttribute("Amount", "1000");
		one.setItem("Money", oneMoney );
		
		Item twoMoney = new Item(0);
		twoMoney.setAttribute("Amount", "1000");
		two.setItem("Money", twoMoney );
		
		turnLogic = new TurnLogic();
		//turnLogic.setStartState( new StartGame(board, one, Arrays.asList(one,two) , turnLogic) );
	}
	
	/**
	 * Test buying field bankrupt.
	 *
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 */
	@Test
	public void testBuyingFieldBankrupt() throws NotPawnFoundException, PlayerNotHasPawnsException {
		BoardHelper boardHelper = new BoardHelper( board );
		turnLogic.setStartState( new StartGame( board,one , new LinkedList<Player>( Arrays.asList(one,two) ), turnLogic ) );
		
		Map<Player,TurnResult> results = new HashMap<Player,TurnResult>();
		results.put( two , new TurnResult() );
		turnLogic.makeTurn(new TurnResult() , results );
		
		
		assertEquals( "PlayerMove", turnLogic.getContext().getName() );
		turnLogic.makeTurn( new TurnResult("Move","3") , results);
		assertEquals("AtNobodysField", turnLogic.getContext().getName() );
		assertEquals( 3 , boardHelper.getPlayersFirstPawnField(one).getID() );
		assertEquals( 0 , boardHelper.getPlayersFirstPawnField(two).getID() );
		
		turnLogic.makeTurn(new TurnResult() , results);
		assertEquals( "BuyingItem" , turnLogic.getContext().getName() );
		
		turnLogic.makeTurn( new TurnResult("Decision","Yes" ) , results);
		assertEquals( "PlayerBought" , turnLogic.getContext().getName() );
		turnLogic.makeTurn(new TurnResult() , results);
		assertEquals( "PlayerMove" , turnLogic.getContext().getName() );
		assertEquals( "900" , one.getItem("Money").getAttribute("Amount") );
		assertEquals( "1000" , two.getItem("Money").getAttribute("Amount") );
		assertEquals( "1" , boardHelper.getPlayersFirstPawnField(one).getAttribute("Owner") );
		assertEquals( two , turnLogic.getCurrentPlayer() );
		
		two.getItem("Money").setAttribute("Amount", "90" );
		
		turnLogic.makeTurn(new TurnResult("Move","3") , results);
		assertEquals( "AtEnemyField" , turnLogic.getContext().getName() );
		turnLogic.makeTurn( new TurnResult() , results );
		assertEquals( "PlayerBankrupt" , turnLogic.getContext().getName() );
		turnLogic.makeTurn( new TurnResult() , results );
		assertEquals("GameEnded" , turnLogic.getContext().getName() );
		assertFalse( turnLogic.getAllPlayers().contains(two) ); 
	}
	
	/**
	 * Test buying field fee.
	 *
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 */
	@Test
	public void testBuyingFieldFee() throws NotPawnFoundException, PlayerNotHasPawnsException {
		BoardHelper boardHelper = new BoardHelper( board );
		turnLogic.setStartState( new StartGame( board,one , new LinkedList<Player>( Arrays.asList(one,two) ), turnLogic ) );
		
		Map<Player,TurnResult> results = new HashMap<Player,TurnResult>();
		results.put( two , new TurnResult() );
		turnLogic.makeTurn(new TurnResult() , results );
		
		
		assertEquals( "PlayerMove", turnLogic.getContext().getName() );
		turnLogic.makeTurn( new TurnResult("Move","3") , results);
		assertEquals("AtNobodysField", turnLogic.getContext().getName() );
		assertEquals( 3 , boardHelper.getPlayersFirstPawnField(one).getID() );
		assertEquals( 0 , boardHelper.getPlayersFirstPawnField(two).getID() );
		
		turnLogic.makeTurn(new TurnResult() , results);
		assertEquals( "BuyingItem" , turnLogic.getContext().getName() );
		
		turnLogic.makeTurn( new TurnResult("Decision","Yes" ) , results);
		assertEquals( "PlayerBought" , turnLogic.getContext().getName() );
		turnLogic.makeTurn(new TurnResult() , results);
		assertEquals( "PlayerMove" , turnLogic.getContext().getName() );
		assertEquals( "900" , one.getItem("Money").getAttribute("Amount") );
		assertEquals( "1000" , two.getItem("Money").getAttribute("Amount") );
		assertEquals( "1" , boardHelper.getPlayersFirstPawnField(one).getAttribute("Owner") );
		assertEquals( two , turnLogic.getCurrentPlayer() );
		
		two.getItem("Money").setAttribute("Amount", "200" );
		
		turnLogic.makeTurn(new TurnResult("Move","3") , results);
		assertEquals( "AtEnemyField" , turnLogic.getContext().getName() );
		turnLogic.makeTurn( new TurnResult() , results );
		assertEquals( "PlayerPays" , turnLogic.getContext().getName() );
		turnLogic.makeTurn(new TurnResult() , results );
		assertEquals( "PlayerMove" , turnLogic.getContext().getName() );
		assertEquals( "1000" , one.getItem("Money").getAttribute("Amount") );
		assertEquals( "100" , two.getItem("Money").getAttribute("Amount") );
		
	}
	
	/**
	 * Test special fields.
	 *
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws UnsupportedMoveNumberException the unsupported move number exception
	 * @throws NotAvalaibleMoveException the not avalaible move exception
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	@Test
	public void testSpecialFields() throws NotPawnFoundException, PlayerNotHasPawnsException, NotFieldFoundException, UnsupportedMoveNumberException, NotAvalaibleMoveException, FieldNotHasNeihgboursException {
		BoardHelper boardHelper = new BoardHelper( board );
		turnLogic.setStartState( new Policeman( board,one , new LinkedList<Player>( Arrays.asList(one,two) ), turnLogic ) );
		
		Map<Player,TurnResult> results = new HashMap<Player,TurnResult>();
		results.put( two , new TurnResult() );
		
		boardHelper.movePawn(one.getPlayerFirstPawn(), 4);
		boardHelper.movePawn(two.getPlayerFirstPawn(), 8);
		assertEquals("Policeman",turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult( ) , results );
		assertEquals(12, boardHelper.getPlayersFirstPawnField(one).getID());
		assertEquals("Prison",turnLogic.getContext().getName());
		assertEquals(boardHelper.getFieldById(8),boardHelper.getPlayersFirstPawnField(two));
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Move","0"), results);
		assertEquals("ChanceField",turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Chance", "newChance"), results);
		assertEquals("ExecuteChance", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("1100", two.getItem("Money").getAttribute("Amount"));
		assertEquals(one, turnLogic.getCurrentPlayer());
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Move", "4"), results);
		assertEquals("CrossingStartField", turnLogic.getContext().getName());
		assertEquals(0, boardHelper.getPlayersFirstPawnField(one).getID());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("StartField", turnLogic.getContext().getName());
		assertEquals("1100", one.getItem("Money").getAttribute("Amount"));
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		assertEquals(two, turnLogic.getCurrentPlayer());
		assertEquals(8, boardHelper.getPlayersFirstPawnField(two).getID());
		
		turnLogic.makeTurn(new TurnResult("Move", "1"), results);
		assertEquals("AtNobodysField", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("BuyingItem", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Decision", "Yes"), results);
		assertEquals("PlayerBought", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(),  results);
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		assertEquals("1000", two.getItem("Money").getAttribute("Amount"));
		
		
		
		//player two doesnt buy house
		turnLogic.makeTurn(new TurnResult("Move", "0"), results);
		assertEquals("StartField", turnLogic.getContext().getName());
		assertEquals(one, turnLogic.getCurrentPlayer());
		
		turnLogic.makeTurn(new TurnResult(),  results);
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		assertEquals(two, turnLogic.getCurrentPlayer());
		
		turnLogic.makeTurn(new TurnResult("Move", "0"), results);
		assertEquals("AtOwnerField", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("BuyingItem", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Decision", "No"), results);
		assertEquals("1000", two.getItem("Money").getAttribute("Amount"));
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		
		
		
		//player two buys house
		turnLogic.makeTurn(new TurnResult("Move", "0"), results);
		assertEquals("StartField", turnLogic.getContext().getName());
		assertEquals(one, turnLogic.getCurrentPlayer());
		
		turnLogic.makeTurn(new TurnResult(),  results);
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		assertEquals(two, turnLogic.getCurrentPlayer());
		
		turnLogic.makeTurn(new TurnResult("Move", "0"), results);
		assertEquals("AtOwnerField", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("BuyingItem", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult("Decision", "Yes"), results);
		assertEquals("PlayerBought", turnLogic.getContext().getName());
		
		turnLogic.makeTurn(new TurnResult(), results);
		assertEquals("850", two.getItem("Money").getAttribute("Amount"));
		assertEquals("PlayerMove", turnLogic.getContext().getName());
		
		
	}
	
	/**
	 * Test simple scenario.
	 *
	 * @throws UnsupportedBoardSizeException the unsupported board size exception
	 * @throws NotFieldFoundException the not field found exception
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 * @throws PlayerNotRespondException the player not respond exception
	 */
	@Test
	public void testSimpleScenario() throws UnsupportedBoardSizeException, NotFieldFoundException, NotPawnFoundException, PlayerNotHasPawnsException, PlayerNotRespondException {
		Board board = new Board();
		
		BoardBuilder boardBuilder = new BoardBuilder(  board );
		
		boardBuilder.makeSimpleLinearBoard(16);
		
		for ( Field f : board.getFields() ) {
			f.addAttribute("Owner", "0");
			f.addAttribute("Price", "100");
		}
			
			
		TurnHandler oneCurrTurnHandler = new TurnHandler();
		TurnHandler oneOtherTurnHandler = new TurnHandler();
		
		TurnHandler twoCurrTurnHandler = new TurnHandler();
		TurnHandler twoOtherTurnHandler = new TurnHandler();
		
		Pawn onePawn = new Pawn(1);
		Pawn twoPawn = new Pawn(2);
		
		BoardHelper boardHelper = new BoardHelper(board);
		boardHelper.placePawnOnField(onePawn, 0);
		boardHelper.placePawnOnField(twoPawn, 0);
		
		oneCurrTurnHandler.setTurnMaker("StartGame", new CurrentStartGameTurnHandler() );
		oneOtherTurnHandler.setTurnMaker("StartGame", new OtherStartGameTurnHandler() );
		
		oneCurrTurnHandler.setTurnMaker("PlayerMove", new CurrentMakeMoveTurnHandler() );
		oneOtherTurnHandler.setTurnMaker("PlayerMove", new OtherMakeMoveTurnHandler() );
		
		oneCurrTurnHandler.setTurnMaker("AtNobodysField", new CurrentAtNobodysFieldTurnHandler() );
		oneOtherTurnHandler.setTurnMaker("AtNobodysField", new OtherAtNobodysFieldTurnHandler() );
		
		oneCurrTurnHandler.setTurnMaker("BuyingField", new CurrentAtNobodysFieldTurnHandler() );
		oneOtherTurnHandler.setTurnMaker("BuyingField", new OtherAtNobodysFieldTurnHandler() );
		
		twoCurrTurnHandler.setTurnMaker("StartGame", new CurrentStartGameTurnHandler() );
		twoOtherTurnHandler.setTurnMaker("StartGame", new OtherStartGameTurnHandler() );
		
		twoCurrTurnHandler.setTurnMaker("PlayerMove", new CurrentMakeMoveTurnHandler() );
		twoOtherTurnHandler.setTurnMaker("PlayerMove", new OtherMakeMoveTurnHandler() );
		
		twoCurrTurnHandler.setTurnMaker("AtNobodysField", new CurrentAtNobodysFieldTurnHandler() );
		twoOtherTurnHandler.setTurnMaker("AtNobodysField", new OtherAtNobodysFieldTurnHandler() );
		
		twoCurrTurnHandler.setTurnMaker("BuyingField", new CurrentAtNobodysFieldTurnHandler() );
		twoOtherTurnHandler.setTurnMaker("BuyingField", new OtherAtNobodysFieldTurnHandler() );
		
		Player one = new Player(1,new RemoteTurnHandler(oneCurrTurnHandler) , new RemoteTurnHandler( oneOtherTurnHandler ) );
		one.addPawn(onePawn);
		Player two = new Player(2 ,new RemoteTurnHandler( twoCurrTurnHandler ), new RemoteTurnHandler( twoOtherTurnHandler ) );
		two.addPawn(twoPawn);
		
		Item oneMoney = new Item(0);
		oneMoney.setAttribute("Amount", "1000");
		one.setItem("Money", oneMoney );
		
		Item twoMoney = new Item(0);
		twoMoney.setAttribute("Amount", "1000");
		two.setItem("Money", twoMoney );
		
		TurnLogic turnLogic = new TurnLogic();
		turnLogic.setStartState( new StartGame(board, one, Arrays.asList(one,two) , turnLogic) );
		
		Player currPlayer = turnLogic.getCurrentPlayer();
		Context context = turnLogic.getContext();
		TurnResult currTurnResult = null;
		currTurnResult = currPlayer.makeCurrentTurn( context );

		Map<Player,TurnResult> otherTurnResult = new HashMap<Player,TurnResult>();
		for (Player p : turnLogic.getAllPlayers() )
			if ( p != currPlayer)
				otherTurnResult.put(p, p.makeOtherTurn(context) );
		
		turnLogic.makeTurn(currTurnResult, otherTurnResult);
		
		
		assertEquals( "PlayerMove" , turnLogic.getContext().getName());
		
		currPlayer = turnLogic.getCurrentPlayer();
		context = turnLogic.getContext();
		currTurnResult = currPlayer.makeCurrentTurn( context );

		otherTurnResult = new HashMap<Player,TurnResult>();
		for (Player p : turnLogic.getAllPlayers() )
			if ( p != currPlayer)
				otherTurnResult.put(p, p.makeOtherTurn(context) );
		turnLogic.makeTurn(currTurnResult, otherTurnResult);
		
		assertEquals( "AtNobodysField" , turnLogic.getContext().getName());
		assertEquals( 6 , boardHelper.getPlayersFirstPawnField(currPlayer).getID() );
		
		currPlayer = turnLogic.getCurrentPlayer();
		context = turnLogic.getContext();
		currTurnResult = currPlayer.makeCurrentTurn( context );

		otherTurnResult = new HashMap<Player,TurnResult>();
		for (Player p : turnLogic.getAllPlayers() )
			if ( p != currPlayer)
				otherTurnResult.put(p, p.makeOtherTurn(context) );
		turnLogic.makeTurn(currTurnResult, otherTurnResult);
		
		assertEquals( "BuyingItem" , turnLogic.getContext().getName());
		assertEquals( 6 , boardHelper.getPlayersFirstPawnField(currPlayer).getID() );
		assertEquals( one , turnLogic.getCurrentPlayer() );
	}
}





