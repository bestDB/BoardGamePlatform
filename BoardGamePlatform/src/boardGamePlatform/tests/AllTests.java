/*
 * 
 */
package boardGamePlatform.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class AllTests.
 */
@RunWith(Suite.class)
@SuiteClasses({ BoardBuilderTest.class, BoardHelperTest.class, BoardTest.class, FieldTest.class , 
			StateHelperTest.class, TurnLogicTest.class, GameTest.class, PawnTest.class, ItemTest.class })
public class AllTests {

}
