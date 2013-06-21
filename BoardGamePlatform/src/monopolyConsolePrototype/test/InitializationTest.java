/*
 * 
 */
package monopolyConsolePrototype.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import monopolyConsolePrototype.states.StartGame;

import org.junit.Test;

import boardGamePlatform.game.*;
import boardGamePlatform.gameInitializers.*;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.platformExceptions.NotItemFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.PlayerWithGivenNickAlreadyExistsException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.utilities.*;

// TODO: Auto-generated Javadoc
class BoardInitializerImpl implements BoardInitializer{

	@Override
	public Board initializeBoard(int playersCount, Map<String, Item> items,
			Map<String, Pawn> pawns) {
		
		Board board = new Board();
		BoardHelper boardH = new BoardHelper(board);
		BoardBuilder boardB = new BoardBuilder(board); 
		
		try {
			boardB.makeSimpleLinearBoard(16);
			boardH.getFieldById(0).addPawn(pawns.get("Player1Pawn"));
			boardH.getFieldById(1).addPawn(pawns.get("Player2Pawn"));
		
			return board;
			
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}

class PawnsInitializerImpl implements PawnsInitializer{

	@Override
	public Map<String, Pawn> initializePawns(int playerCount,
			Map<String, Item> items) {
		Map<String, Pawn> pawns = new HashMap<String, Pawn>();

		pawns.put("Player1Pawn", new Pawn(0));
		pawns.put("Player2Pawn", new Pawn(0));
		
		return pawns;
	}
	
}

class ItemsInitializerImpl implements ItemsInitializer{

	@Override
	public Map<String, Item> initializeItems(int playersCount) {
		// TODO Auto-generated method stub
		return null;
	}

}

class PlayersInitializerImpl implements PlayersInitializer{

	@Override
	public List<Player> initializePlayers(int playersCount,
			Map<String, Item> items, Map<String, Pawn> pawns) {

		List<Player> players = new LinkedList<Player>();
		
		if(playersCount == 2){
			Player p1 = new Player(0);
			Player p2 = new Player(1);
			
			p1.addPawn(pawns.get("Player1Pawn"));
			p2.addPawn(pawns.get("Player2Pawn"));
			
			p1.setItem("Money", new Item(0, "Value", "1000"));
			
			players.add(p1);
			players.add(p2);
			
			return players;
		}
		
		return null;
	}
	
}

class MonopolyDetails implements GameDetails{

	@Override
	public boolean ifPlayersCountPossible(int playersCount) {
		if(playersCount == 2)
			return true;
		return false;
	}

	@Override
	public String getGameName() {
		return "Monopoly";
	}
	
}

class MonopolyGameFactory implements GameFactory {

	@Override
	public ItemsInitializer createItemsInitializer() {
		// TODO Auto-generated method stub
		return new ItemsInitializerImpl();
	}

	@Override
	public PawnsInitializer createPawnsInitializer() {
		// TODO Auto-generated method stub
		return new PawnsInitializerImpl();
	}

	@Override
	public BoardInitializer createBoardInitializer() {
		// TODO Auto-generated method stub
		return new BoardInitializerImpl();
	}

	@Override
	public PlayersInitializer createPlayersInitializer() {
		// TODO Auto-generated method stub
		return new PlayersInitializerImpl() ;
	}

	@Override
	public TurnHandler createCurrentTurnHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TurnHandler createOtherTurnHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameDetails createGameDetails() {
		// TODO Auto-generated method stub
		return new MonopolyDetails();
	}

	@Override
	public State createInitialState(Board board, List<Player> allPlayers,
			TurnLogic turnLogic) {
		// TODO Auto-generated method stub
		return new StartGame(board,allPlayers.get(0) , allPlayers,turnLogic);
	}
	
}

/**
 * The Class InitializationTest.
 */
public class InitializationTest {

	/**
	 * Test.
	 *
	 * @throws NotPawnFoundException the not pawn found exception
	 * @throws PlayerNotHasPawnsException the player not has pawns exception
	 */
	@Test
	public void test() throws NotPawnFoundException, PlayerNotHasPawnsException {
		Initializer monopolyInitializer = new Initializer( new MonopolyGameFactory() );
		
		assertEquals(true, monopolyInitializer.initialize(2));
		assertEquals(false, monopolyInitializer.ifAllPlayersReady());
		
		try {
			monopolyInitializer.addNewActivePlayer("Zbychu", new RemoteTurnHandler(new TurnHandler() ), new RemoteTurnHandler( new TurnHandler() ));
			assertEquals(false, monopolyInitializer.ifAllPlayersReady());
			monopolyInitializer.addNewActivePlayer("Stachu", new RemoteTurnHandler( new TurnHandler() ), new RemoteTurnHandler( new TurnHandler() ));
		} catch (PlayerWithGivenNickAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true, monopolyInitializer.ifAllPlayersReady());
		
		BoardHelper boardHelper = new BoardHelper(monopolyInitializer.getBoard());
		List<Player> players = monopolyInitializer.getPlayers();
		
		assertEquals(0, boardHelper.getPlayersFirstPawnField(players.get(0)).getID());
		assertEquals(1, boardHelper.getPlayersFirstPawnField(players.get(1)).getID());
		
		assertNotNull(players.get(0).getCurrentTurnHandler());
		assertNotNull(players.get(1).getCurrentTurnHandler());
		
		assertNotNull(players.get(0).getOtherTurnHandler());
		assertNotNull(players.get(1).getOtherTurnHandler());
		
		assertEquals("1000",players.get(0).getItem("Money").getAttribute("Value"));
		
		assertEquals("StartGame",monopolyInitializer.getTurnLogic().getContext().getName());
		
		
		assertEquals(false, monopolyInitializer.initialize(3));
		
		System.out.println(monopolyInitializer.getBoard());
	}

}
