/*
 * 
 */
package monopolyConsolePrototype.states;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.StateChangable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class AtOwnerField.
 */
public class AtOwnerField extends State {

	/**
	 * Instantiates a new at owner field.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public AtOwnerField(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("AtOwnerField", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
		Map<Player, TurnResult> otherTurnResults) {
		BoardHelper boardH = new BoardHelper(board);
		try {
			Field currField = boardH.getPlayersFirstPawnField(currentPlayer);
			if((Integer.parseInt(currField.getItem("House").getAttribute("Amount")) == 0)
					&& ( Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount"))>= 150 ) ) {
				Map<String, String> attrs = new HashMap<String, String>();
				attrs.put("What", "House");
				stateChangable.changeState(new BuyingItem(board, currentPlayer, allPlayers, stateChangable, attrs));
				return;
			} else {
				currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
				stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));
				return;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
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
