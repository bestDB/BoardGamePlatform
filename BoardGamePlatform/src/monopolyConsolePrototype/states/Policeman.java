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
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class Policeman.
 */
public class Policeman extends State {

	/**
	 * Instantiates a new policeman.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public Policeman(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("Policeman", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		BoardHelper boardH = new BoardHelper(board);
		try {
			boardH.movePawn(currentPlayer.getPlayerFirstPawn(), boardH.getFieldById(4), boardH.getFieldById(12));
			stateChangable.changeState(new Prison(board, currentPlayer, allPlayers, stateChangable));
			return;
		} catch (Exception e){
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
