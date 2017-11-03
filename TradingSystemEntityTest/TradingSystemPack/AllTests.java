package TradingSystemPack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculateUSDAmtTest.class, GenerateTradeEntityReportMethodTest.class, RankEntityMethodTest.class,
		SettelmentDtTestWithParameter.class })
public class AllTests {

}
