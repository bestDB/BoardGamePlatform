/*
 * 
 */
package monopolyConsolePrototype.states;

import java.util.List;
import java.util.Map;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.StateChangable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class BuyingItem.
 */
public class BuyingItem extends State {


	/**
	 * Instantiates a new buying item.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 * @param attributes the attributes
	 */
	public BuyingItem(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable,
			Map<String, String> attributes) {
		super(board, currentPlayer, allPlayers, stateChangable, attributes);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("BuyingItem", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		
		BoardHelper boardH = new BoardHelper(board);
		if(currTurnResult.getProperty("Decision").equals("Yes")) {
			stateChangable.changeState(new PlayerBought(board, currentPlayer, allPlayers, stateChangable,attributes));
			return;
		} else {
			currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
			stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));
			return;
		}

	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#gameEnded()
	 */
	@Override
	public boolean gameEnded() {
		// TODO Auto-generated method stub
		return false;
	}

}
