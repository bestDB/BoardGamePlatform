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
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.utilities.BoardHelper;
import boardGamePlatform.utilities.StateHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerBought.
 */
public class PlayerBought extends State {

	/**
	 * Instantiates a new player bought.
	 *
	 * @param board the board
	 * @param currentPlayer the current player
	 * @param allPlayers the all players
	 * @param stateChangable the state changable
	 * @param attributes the attributes
	 */
	public PlayerBought(Board board, Player currentPlayer,
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
		return new Context("PlayerBought", board, currentPlayer, allPlayers);
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
			if(attributes.get("What").equals("Field")) {
				int price = Integer.parseInt(currField.getAttribute("Price"));
				int currMoney = Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount")) - price;
				
				currentPlayer.getItem("Money").setAttribute("Amount", String.valueOf(currMoney));
				currField.setAttribute("Owner", String.valueOf(currentPlayer.getPlayerID()));
				
				currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
				stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));
				return;
				
			} else {
				int price = 150;
				int currMoney = Integer.parseInt(currentPlayer.getItem("Money").getAttribute("Amount")) - price;
				
				currentPlayer.getItem("Money").setAttribute("Amount", String.valueOf(currMoney));
				
				currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
				stateChangable.changeState(new PlayerMove(board, currentPlayer, allPlayers, stateChangable));
				return;
			}
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
