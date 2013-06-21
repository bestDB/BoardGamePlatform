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
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAvalaibleMoveException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.UnsupportedMoveNumberException;
import boardGamePlatform.utilities.BoardHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerMove.
 */
public class PlayerMove extends State {

	/**
	 * Instantiates a new player move.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public PlayerMove(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
	
		return new Context("PlayerMove", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		int moveLen = Integer.parseInt(currTurnResult.getProperty("Move"));
		BoardHelper boardH = new BoardHelper(board);
		
		try {
			Field startField = boardH.getPawnsField(currentPlayer.getPlayerFirstPawn());
			Field endField = boardH.movePlayerFirstPawnByNum(currentPlayer, moveLen);
			
			if(startField.getID() > endField.getID()) {
				stateChangable.changeState(new CrossingStartField(board, currentPlayer, allPlayers, stateChangable ));
				return;
			}
			
			switch(endField.getID()) {
				case 0	:	stateChangable.changeState(new StartField(board, currentPlayer, allPlayers, stateChangable));
							return;
				case 4	:	stateChangable.changeState(new Policeman(board, currentPlayer, allPlayers, stateChangable));
							return;
				case 8	:	stateChangable.changeState(new ChanceField(board, currentPlayer, allPlayers, stateChangable));
							return;
				case 12	:	stateChangable.changeState(new Prison(board, currentPlayer, allPlayers, stateChangable));
							return;
			}
			
			int ownerID = Integer.parseInt(endField.getAttribute("Owner"));
			
			if( ownerID == currentPlayer.getPlayerID()) {
				stateChangable.changeState(new AtOwnerField(board, currentPlayer, allPlayers, stateChangable));
				return;
			} else if(ownerID == 0) {
				stateChangable.changeState(new AtNobodysField(board, currentPlayer, allPlayers, stateChangable));
				return;
			} else {
				stateChangable.changeState(new AtEnemyField(board, currentPlayer, allPlayers, stateChangable));
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
		return false;
	}

}
