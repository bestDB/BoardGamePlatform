/*
 * 
 */
package monopolyConsolePrototype.test.simpleClientServer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import monopolyConsolePrototype.states.StartGame;
import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.game.TurnMakeable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameInitializers.BoardInitializer;
import boardGamePlatform.gameInitializers.GameDetails;
import boardGamePlatform.gameInitializers.GameFactory;
import boardGamePlatform.gameInitializers.ItemsInitializer;
import boardGamePlatform.gameInitializers.PawnsInitializer;
import boardGamePlatform.gameInitializers.PlayersInitializer;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;
import boardGamePlatform.utilities.BoardBuilder;
import boardGamePlatform.utilities.BoardHelper;

// TODO: Auto-generated Javadoc
class BoardInitializerImpl1 implements BoardInitializer{

	@Override
	public Board initializeBoard(int playersCount, Map<String, Item> items,
			Map<String, Pawn> pawns) {
		

		Board board = new Board();
		
		BoardBuilder boardBuilder = new BoardBuilder(  board );
		
		try {
			boardBuilder.makeSimpleLinearBoard(16);
		} catch (UnsupportedBoardSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for ( Field f : board.getFields() ) {
			f.addAttribute("Owner", "0");
			f.addAttribute("Price", "100");
			f.addItem( "House" , new Item(0,"Amount","0") );
		}
		
		BoardHelper boardHelper = new BoardHelper(board);
		try {
			boardHelper.placePawnOnField(pawns.get("Player1Pawn"), 0);
		} catch (NotFieldFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			boardHelper.placePawnOnField(pawns.get("Player2Pawn"), 0);
		} catch (NotFieldFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return board;
	}
	
}

class PawnsInitializerImpl1 implements PawnsInitializer{

	@Override
	public Map<String, Pawn> initializePawns(int playerCount,
			Map<String, Item> items) {
		Map<String, Pawn> pawns = new HashMap<String, Pawn>();

		pawns.put("Player1Pawn", new Pawn(1));
		pawns.put("Player2Pawn", new Pawn(2));
		
		return pawns;
	}
	
}

class ItemsInitializerImpl1 implements ItemsInitializer{

	@Override
	public Map<String, Item> initializeItems(int playersCount) {
		// TODO Auto-generated method stub
		return null;
	}

}

class PlayersInitializerImpl1 implements PlayersInitializer{

	@Override
	public List<Player> initializePlayers(int playersCount,
			Map<String, Item> items, Map<String, Pawn> pawns) {

		List<Player> players = new LinkedList<Player>();
		
		if(playersCount == 2){
			Player p1 = new Player(1);
			Player p2 = new Player(2);
			
			p1.addPawn(pawns.get("Player1Pawn"));
			p2.addPawn(pawns.get("Player2Pawn"));
			
			p1.setItem("Money", new Item(0, "Amount", "1000"));
			p2.setItem("Money", new Item(0, "Amount", "1000"));
			
			players.add(p1);
			players.add(p2);
			
			return players;
		}
		
		
		return null;
	}
	
}

class MonopolyDetails1 implements GameDetails{

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

/**
 * The Class MonopolyGameFactory1.
 */
public class MonopolyGameFactory1 implements GameFactory {

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createItemsInitializer()
	 */
	@Override
	public ItemsInitializer createItemsInitializer() {
		// TODO Auto-generated method stub
		return new ItemsInitializerImpl1();
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createPawnsInitializer()
	 */
	@Override
	public PawnsInitializer createPawnsInitializer() {
		// TODO Auto-generated method stub
		return new PawnsInitializerImpl1();
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createBoardInitializer()
	 */
	@Override
	public BoardInitializer createBoardInitializer() {
		// TODO Auto-generated method stub
		return new BoardInitializerImpl1();
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createPlayersInitializer()
	 */
	@Override
	public PlayersInitializer createPlayersInitializer() {
		// TODO Auto-generated method stub
		return new PlayersInitializerImpl1() ;
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createCurrentTurnHandler()
	 */
	@Override
	public TurnHandler createCurrentTurnHandler() {
		TurnMakeable currTurnMaker = new TurnMakeable() {
			@Override
			public TurnResult makeTurn(Context context){
				System.out.println(context.getCurrPlayer().getNick());
				if(context.getName().equals("BuyingItem")){
					System.out.println(context.getBoard().toString());
					for(Player p : context.getAllPlayers())
						System.out.println(p.toString());
					
					System.out.println("Decision (Yes/No): ");
					Scanner scanner = new Scanner(System.in);
					String decision = scanner.next();
					
					return new TurnResult("Decision", decision);
					
				} else if(context.getName().equals("ChanceField")){ 
					System.out.println(context.getBoard().toString());
					for(Player p : context.getAllPlayers())
						System.out.println(p.toString());
					System.out.println("Youre standing on chance field. 100 $ get.");
					Scanner scanner = new Scanner(System.in);
					String moveVal = scanner.next();
					return new TurnResult("Chance","TheChance");
					
				} else if(context.getName().equals("PlayerMove")) {
					System.out.println(context.getBoard().toString());
					for(Player p : context.getAllPlayers())
						System.out.println(p.toString());
					
					System.out.println("Move:");
					Scanner scanner = new Scanner(System.in);
					String moveVal = scanner.next();
					
					return new TurnResult("Move", moveVal);
					
				} else {
					
					System.out.println(context.getName());
					System.out.println(context.getBoard().toString());
					for(Player p : context.getAllPlayers())
						System.out.println(p.toString());
					System.out.println("Write sthing");
					Scanner scanner = new Scanner(System.in);
					String moveVal = scanner.next();
					return new TurnResult();
				}
			}
			
		};
		return new TurnHandler(currTurnMaker);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createOtherTurnHandler()
	 */
	@Override
	public TurnHandler createOtherTurnHandler() {
		TurnMakeable otherTurnMaker = new TurnMakeable() {
			@Override
			public TurnResult makeTurn(Context context){
					System.out.println(context.getName());
					System.out.println(context.getBoard().toString());
					for(Player p : context.getAllPlayers())
						System.out.println(p.toString());
					return new TurnResult();
			}
		};
		return new TurnHandler(otherTurnMaker);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createGameDetails()
	 */
	@Override
	public GameDetails createGameDetails() {
		// TODO Auto-generated method stub
		return new MonopolyDetails1();
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.gameInitializers.GameFactory#createInitialState(boardGamePlatform.gameResources.Board, java.util.List, boardGamePlatform.game.TurnLogic)
	 */
	@Override
	public State createInitialState(Board board, List<Player> allPlayers,
			TurnLogic turnLogic) {
		// TODO Auto-generated method stub
		return new StartGame(board,allPlayers.get(0) , allPlayers,turnLogic);
	}
	
}