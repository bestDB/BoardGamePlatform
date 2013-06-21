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
import boardGamePlatform.platformExceptions.NotAttributeFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class AtEnemyField.
 */
public class AtEnemyField extends State {

	/**
	 * Instantiates a new at enemy field.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 */
	public AtEnemyField(Board board, Player currentPlayer,
			List<Player> allPlayers, StateChangable stateChangable) {
		super(board, currentPlayer, allPlayers, stateChangable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#getContext()
	 */
	@Override
	public Context getContext() {
		return new Context("AtEnemyField", board, currentPlayer, allPlayers);
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
			int toPay = Integer.parseInt(currField.getAttribute("Price"));
			
			if(Integer.parseInt(currField.getItem("House").getAttribute("Amount")) == 1) 
				toPay += 150;	
			if(Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount")) >= toPay) {
				Map<String, String> attrs = new HashMap<String, String>();
				attrs.put("ToPay", Integer.toString(toPay));
				stateChangable.changeState(new PlayerPays(board, currentPlayer, allPlayers, stateChangable, attrs));
				return;
			} else {
				stateChangable.changeState(new PlayerBankrupt(board, currentPlayer, allPlayers, stateChangable));
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
