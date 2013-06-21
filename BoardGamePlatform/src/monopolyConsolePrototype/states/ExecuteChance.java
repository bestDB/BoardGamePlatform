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
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ExecuteChance.
 */
public class ExecuteChance extends State {

	/**
	 * Instantiates a new execute chance.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 * @param attributes the attributes
	 */
	public ExecuteChance(Board board, Player currentPlayer,
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
		return new Context("ExecuteChance", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		int currMoney = Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount"));
		currentPlayer.getItem("Money").setAttribute("Amount", String.valueOf(currMoney));
		currMoney = Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount"));
		currMoney += 100;
		
		currentPlayer.getItem("Money").setAttribute("Amount", String.valueOf(currMoney));
		
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));

		
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
