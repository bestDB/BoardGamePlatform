/*
 * 
 */
package boardGamePlatform.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import boardGamePlatform.game.Player;
import boardGamePlatform.utilities.StateHelper;


// TODO: Auto-generated Javadoc
/**
 * The Class StateHelperTest.
 */
public class StateHelperTest {

	/**
	 * Test get next player.
	 */
	@Test
	public void testGetNextPlayer() {
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		Player currentPlayer = player2;
		List<Player> allPlayers = new LinkedList<Player>();
		for (Player player : new Player[] { player1, player2, player3 , player4 } )
			allPlayers.add( player );
		
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		assertEquals(player3, currentPlayer);
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		assertEquals(player4, currentPlayer);
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		assertEquals(player1, currentPlayer);
		currentPlayer = StateHelper.getNextPlayer(currentPlayer, allPlayers);
		assertEquals(player2, currentPlayer);
	}

}
