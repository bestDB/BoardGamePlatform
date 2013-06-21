/*
 * 
 */
package monopolyConsolePrototype.states;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.StateChangable;
import boardGamePlatform.game.TurnResult;
import boardGamePlatform.gameResources.Board;

// TODO: Auto-generated Javadoc
/**
 * The Class ChanceField.
 */
public class ChanceField extends State {

	/**
	 * Instantiates a new chance field.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public ChanceField(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("ChanceField", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		Map<String, String> attrs = new HashMap<String, String>();
		attrs.put("Chance", currTurnResult.getProperty("Chance"));
		
		stateChangable.changeState(new ExecuteChance(board, currentPlayer, allPlayers, stateChangable, attrs));
		
		
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
