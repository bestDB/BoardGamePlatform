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
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.NotPlayerExistsException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerPays.
 */
public class PlayerPays extends State {


	/**
	 * Instantiates a new player pays.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 * @param attributes the attributes
	 */
	public PlayerPays(Board board, Player currentPlayer,
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
		return new Context("PlayerPays", board, currentPlayer, allPlayers);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.State#makeTurn(boardGamePlatform.game.TurnResult, java.util.Map)
	 */
	@Override
	public void makeTurn(TurnResult currTurnResult,
			Map<Player, TurnResult> otherTurnResults) {
			
		BoardHelper boardH = new BoardHelper(board);
		Field currField;
		try {
			currField = boardH.getPlayersFirstPawnField(currentPlayer);
			int ownerID = Integer.parseInt(currField.getAttribute("Owner"));
			int toPay = Integer.parseInt(attributes.get("ToPay"));
			int currMoney = Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount")) - toPay;
			
			currentPlayer.getItem("Money").setAttribute("Amount", String.valueOf(currMoney));
			
			Player receiver = StateHelper.getPlayerByID(ownerID, allPlayers);
		
			int ownerMoney = Integer.parseInt(receiver.getItem("Money").getAttribute("Amount")) + toPay;
			receiver.getItem("Money").setAttribute("Amount", String.valueOf(ownerMoney));
			
			currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
			stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));
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
