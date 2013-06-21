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
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class CrossingStartField.
 */
public class CrossingStartField extends State {


	/**
	 * Instantiates a new crossing start field.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public CrossingStartField(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("CrossingStartField",board,currentPlayer,allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
		Item newMoney = new Item(0);
		newMoney.setAttribute("Amount", String.valueOf(Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount")) + 100));
		currentPlayer.setItem("Money", newMoney);
		
		BoardHelper boardH = new BoardHelper(board);
		Field endField;
		try {
			endField = boardH.getPawnsField(currentPlayer.getPlayerFirstPawn());
		} catch (Exception e) {
			e.printStackTrace();
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
