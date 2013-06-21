/*
 * 
 */
package boardGamePlatform.game;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import boardGamePlatform.gameResources.Field;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.network.RemoteTurnHandlable;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.platformExceptions.NotItemFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.PlayerNotRespondException;
import boardGamePlatform.platformExceptions.UninitializedTurnHandlerException;



// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca obiekt gracza bioracego udzial w rozgrywce. Zawiera wszystkie elementy niezbedne
 * do komunikowania sie z graczem, informowania go o obecnym stanie rozgrywki oraz pobierania od niego
 * wejscia, w razie potrzeby. Poza tym przechowuje informacje o jego nicku, przedmiotach dodatkowych
 * i pionkach ktore zostaly do niego przypisane.
 */
public class Player implements Serializable{
	
	/** Nick gracza. */
	private String nick;
	
	/** Identyfikator gracza. */
	private int playerID;
	
	/** Pionki gracza. */
	private List<Pawn> playerPawns;
	
	/** Dodatkowe przedmioty gracza. */
	private Map<String, Item> playerItems;
	
	/** Obiekt sluzacy do komunikacji z graczem w przypadku gdy jest jego tura. */
	private RemoteTurnHandlable currentTurnHandler;
	
	/** Obiekt sluzacy do komunikacji z graczem w przypadku gdy jest tura innego gracza. */
	private RemoteTurnHandlable otherTurnHandler;
	
	/**
	 * Instancjonuje nowego gracza.
	 *
	 * @param id ID gracza
	 */
	public Player( int id ) {
		this.setPlayerID(id);
		nick = null;
		playerPawns = new LinkedList<Pawn>();
		playerItems = new HashMap<String, Item>();
		currentTurnHandler = null;
		otherTurnHandler = null;
	}
	
	/**
	 * Instancjonuje nowego gracza.
	 *
	 * @param id ID gracza
	 * @param nick nick gracza
	 */
	public Player( int id, String nick) {
		this.setNick(nick);
		this.setPlayerID(id);
		playerPawns = new LinkedList<Pawn>();
		playerItems = new HashMap<String, Item>();
		currentTurnHandler = null;
		otherTurnHandler = null;
	}
	
	/**
	 * Instancjonuje nowego gracza.
	 *
	 * @param id identyfikator gracza
	 * @param currentTurnHandler obiekt do obslugi gracza w przypadku gdy jest jego tura
	 * @param otherTurnHandler obiekt do obslugi gracza w przypadku tury innego gracza
	 */
	public Player(int id, RemoteTurnHandlable currentTurnHandler, RemoteTurnHandlable otherTurnHandler) {
		this(id);
		nick = null;
		this.currentTurnHandler = currentTurnHandler;
		this.otherTurnHandler = otherTurnHandler;
	}
	
	/**
	 * Ustawia obiekt sluzacy do obslugi gracza w przypadku gdy jest jego tura.
	 *
	 * @param currentTurnHandlable the new current turn handler
	 */
	public void setCurrentTurnHandler(RemoteTurnHandlable currentTurnHandlable) {
		currentTurnHandler = currentTurnHandlable;
	}
	
	/**
	 * Ustawia obiekt sluzacy do obslugi gracza w przypadku tury innego gracza.
	 *
	 * @param otherTurnHandlable the new other turn handler
	 */
	public void setOtherTurnHandler(RemoteTurnHandlable otherTurnHandlable) {
		otherTurnHandler = otherTurnHandlable;
	}
	
	/**
	 * Zwraca obiekt sluzacy do obslugi gracza w przypadku gdy jest jego tura.
	 *
	 * @return obiekt sluzacy do obslugi gracza w przypadku gdy jest jego tura
	 */
	public RemoteTurnHandlable getCurrentTurnHandler() {
		if ( (currentTurnHandler == null ) || ( otherTurnHandler == null) )
			throw new UninitializedTurnHandlerException();
		return currentTurnHandler;
	}
	
	/**
	 * Zwraca obiekt sluzacy do obslugi gracza w przypadku tury innego gracza.
	 *
	 * @return obiekt sluzacy do obslugi gracza w przypadku tury innego hracza.
	 */
	public RemoteTurnHandlable getOtherTurnHandler() {
		if ( (currentTurnHandler == null ) || ( otherTurnHandler == null) )
			throw new UninitializedTurnHandlerException();
		return otherTurnHandler;
	}
	
	/**
	 * Zwraca rezultat tury zalezny od podanego kontekstu, w przypadku gdy jest tura gracza.
	 *
	 * @param context kontekst dla ktorego ma zostac wykonana tura
	 * @return rezultat tury gracza
	 * @throws PlayerNotRespondException jezeli gracz nie odpowiada
	 */
	public TurnResult makeCurrentTurn(Context context) throws PlayerNotRespondException {
		if ( (currentTurnHandler == null ) || ( otherTurnHandler == null) )
			throw new UninitializedTurnHandlerException();
		try {
			return currentTurnHandler.makeTurn(context);
		} catch (RemoteException e) {
			throw new PlayerNotRespondException();
		}
	}
	
	/**
	 * Zwraca rezultat tury zalezny od podanego kontekstu, w przypadku gdy jest tura innego gracza.
	 *
	 * @param context kontekst dla ktorego ma zostac wykonana tura
	 * @return rezultat tury gracza
	 * @throws PlayerNotRespondException jezeli gracz nie odpowiada
	 */
	public TurnResult makeOtherTurn(Context context) throws PlayerNotRespondException {
		if ( (currentTurnHandler == null ) || ( otherTurnHandler == null) )
			throw new UninitializedTurnHandlerException();
		try {
			return otherTurnHandler.makeTurn(context);
		} catch (RemoteException e) {
			throw new PlayerNotRespondException();
		}
	}
	
	/**
	 * Zwraca pionki gracza.
	 *
	 * @return pionki gracza
	 */
	public List<Pawn> getPlayerPawns() {
		return playerPawns;
	}
	
	/**
	 * Zwraca przedmioty dodatkowe gracza.
	 *
	 * @return przedmioty dodatkowe gracza
	 */
	public Map<String, Item> getPlayerItems() {
		return playerItems;
	}
	
	/**
	 * Zwraca pierwszy pionek z listy pionkow gracza.
	 *
	 * @return pierwszy pionek gracza
	 * @throws PlayerNotHasPawnsException jezeli gracz nie posiada zadnych pionkow
	 */
	public Pawn getPlayerFirstPawn() throws PlayerNotHasPawnsException {
		if( playerPawns.isEmpty())
			throw new PlayerNotHasPawnsException();
		return playerPawns.get(0);
	}
	
	/**
	 * Zwraca ostatni pionek z listy pionkow gracza.
	 *
	 * @return ostatni pionek z listy pionkow gracza
	 * @throws PlayerNotHasPawnsException jezeli gracz nie posiada zadnych pionkow 
	 */
	public Pawn getPlayerLastPawn() throws PlayerNotHasPawnsException {
		if ( playerPawns.isEmpty())
			throw new PlayerNotHasPawnsException();
		
		return playerPawns.get( playerPawns.size() - 1 ) ;
	}
	
	/**
	 * Dodaje nowy przedmiot dodatkowy gracza.
	 *
	 * @param name nazwa przedmiotu dodatkowego
	 * @param item przedmiot dodatkowy
	 */
	public void setItem(String name, Item item) {
		playerItems.put( name, item );
	}
	
	/**
	 * Usuwa przedmiot dodatkowy z list przedmiotow dodatkowych gracza.
	 *
	 * @param name nazwa przedmiotu dodatkowego
	 */
	public void removeItem(String name) {
		playerItems.remove( name );
	}
	
	/**
	 * Zwraca przedmiot dodatkowy gracza o podanej nazwie.
	 *
	 * @param name nazwa przedmiotu dodatkowego ktory chcemy uzyskac
	 * @return przedmiot dodatkowy o nazwie podanej w parametrze name
	 * @throws NotItemFoundException jezeli przedmiot dodatkowy o podanej nazwie nie znajduje sie na liscie przedmiotow gracza
	 */
	public Item getItem(String name) throws NotItemFoundException{
		if(!playerItems.containsKey(name))
			throw new NotItemFoundException();
		return playerItems.get(name);
	}
	
	/**
	 * Przypisuje graczowi jego pionki.
	 *
	 * @param pawns nowe pionki gracza
	 */
	public void setPlayerPawns(List<Pawn> pawns) {
		this.playerPawns = pawns;
	}
	
	/**
	 * Przypisuje graczowi jego przedmioty dodatkowe.
	 *
	 * @param items przedmioty dodatkowe gracza
	 */
	public void setPlayerItems(Map<String, Item> items) {
		this.playerItems = items;
	}
	
	/**
	 * Dodaje pionek do listy pionkow gracza.
	 *
	 * @param pawn pionek ktory ma zostac dodany.
	 */
	public void addPawn(Pawn pawn) {
		playerPawns.add(pawn);
	}

	/**
	 * Zwraca identyfikator gracza.
	 *
	 * @return the ID gracza
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Ustawia identyfikator gracza.
	 *
	 * @param playerID nowy identyfikator gracza
	 */
	private void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * Zwraca nick gracza.
	 *
	 * @return nick gracza
	 */
	public String getNick() {
		if(nick == null)
			return null;
		return nick;
	}

	/**
	 * Ustawia nowy nick gracza podany w parametrze.
	 *
	 * @param nick nowy nick gracza
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String playerState = new String();
		playerState += "Player" + "\n" + Integer.toString(playerID) + "\n" + nick + "\n";
		playerState += "\tPawns\n";
		for(Pawn p : playerPawns) {
			playerState += p.toString("\t") + "\n";
		}
		playerState += "EndPawns\nItems\n";
		for(String i : playerItems.keySet()) {
			playerState += i + " " + playerItems.get(i).toString("\t");
		}
		playerState += "EndItems\nEndPlayer\n";
		return playerState;
	}
	
}
