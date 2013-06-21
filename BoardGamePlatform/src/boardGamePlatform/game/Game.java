/*
 * 
 */
package boardGamePlatform.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardGamePlatform.platformExceptions.NotSupportedContextException;
import boardGamePlatform.platformExceptions.PlayerNotRespondException;


// TODO: Auto-generated Javadoc
/**
 * Glowna klasa zarzadzajaca calym przebiegiem rozgrywki. Do poprawnego dzialania wymaga podania
 * logiki gry, ktora ma obslugiwac oraz pelnej listy graczy bioracych udzial w rozgrywce. 
 */
public class Game {
	
	/** Logika rozgrywki. */
	private TurnLogic turnLogic;
	
	/** Lista graczy bioracych udzial w rozgrywce. */
	private List<Player> players;
	
	/**
	 * Instancjonuje nowy obiekt gry.
	 *
	 * @param turnLogic logika rozgrywki
	 */
	public Game(TurnLogic turnLogic) {
		this.turnLogic = turnLogic;
	}
	
	/**
	 * Rozpoczyna rogrywke.
	 *
	 * @throws PlayerNotRespondException jezeli gracz ktorego proszono o wykonanie ruchu nie odpowiada 
	 */
	public void start() throws PlayerNotRespondException{
		while( !turnLogic.gameEnded() ) {
			Player currPlayer = turnLogic.getCurrentPlayer();
			Context context = turnLogic.getContext();
			TurnResult currTurnResult = null;
			
			Map<Player,TurnResult> otherTurnResult = new HashMap<Player,TurnResult>();
			for (Player p : turnLogic.getAllPlayers() )
				if ( p != currPlayer)
					otherTurnResult.put(p, p.makeOtherTurn(context) );
			
			currTurnResult = currPlayer.makeCurrentTurn(context);
			turnLogic.makeTurn(currTurnResult, otherTurnResult); 
		}
	}
	
	/**
	 * Zwraca obecna logike gry.
	 *
	 * @return obecna logika gry
	 */
	public TurnLogic getTurnLogic() {
		return turnLogic;
	}
}
