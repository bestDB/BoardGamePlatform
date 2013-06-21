
import monopolyConsolePrototype.test.BeginningFirstPlay;
import monopolyConsolePrototype.test.InitializationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import boardGamePlatform.tests.BoardBuilderTest;
import boardGamePlatform.tests.BoardHelperTest;
import boardGamePlatform.tests.BoardTest;
import boardGamePlatform.tests.FieldTest;
import boardGamePlatform.tests.GameTest;
import boardGamePlatform.tests.ItemTest;
import boardGamePlatform.tests.PawnTest;
import boardGamePlatform.tests.StateHelperTest;
import boardGamePlatform.tests.TurnLogicTest;


@RunWith(Suite.class)
@SuiteClasses({BoardBuilderTest.class, BoardHelperTest.class, BoardTest.class, FieldTest.class , 
	StateHelperTest.class, TurnLogicTest.class, GameTest.class, PawnTest.class, ItemTest.class ,
	BeginningFirstPlay.class , InitializationTest.class })
public class AllTests {

}
