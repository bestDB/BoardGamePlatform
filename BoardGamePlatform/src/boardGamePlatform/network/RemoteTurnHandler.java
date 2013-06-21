/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.RemoteException;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnResult;

// TODO: Auto-generated Javadoc
/**
 * Klasa implementujaca RemoteTurnHandlable. Obudowuje ona TurnHandler definiowany przez tworce gry w momencie okreslania modelu gry, w celu udostepnienia go serwerowi,
 * aby dac mozliwosc zdalnego odpytywania klientow.
 */
public class RemoteTurnHandler implements RemoteTurnHandlable{
	
	/** Obiekt TurnHandler ktory ma zostac obudowany. */
	private TurnHandler turnHandler;
	
	/**
	 * Instancjonuje nowy obiekt RemoteTurnHandler
	 *
	 * @param turnHandler obiekt TurnHandler ktory ma zostac obudowany
	 */
	public RemoteTurnHandler(TurnHandler turnHandler) {
		this.turnHandler = turnHandler;
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.network.RemoteTurnHandlable#makeTurn(boardGamePlatform.game.Context)
	 */
	@Override
	public TurnResult makeTurn(Context context) throws RemoteException {
		// TODO Auto-generated method stub
		return turnHandler.makeTurn(context);
	}

}
