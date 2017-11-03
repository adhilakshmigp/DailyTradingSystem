package TradingSystemPack;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


import org.junit.Test;

public class RankEntityMethodTest {

	@Test
	public void RankEntityTest_withNullListAsParameter() {
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity();
		List<TradingSystemEntity> lstTradingSystemEntity = new ArrayList<>();;
		try
		{
			tradingSystemEntity.rankEntity(lstTradingSystemEntity);
			fail("TradingSystemException has to be thrown");

		}catch(TradingSystemException ex)
		{
			System.out.println("Exception caught in RankEntityTest_withNullListAsParameter:"+ex.toString());
		}
	}
	
	@Test
	public void RankEntityTest_withValues() {
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity();
		try
		{
			List<TradingSystemEntity> lstTradingSystemEntity = RetrieveTradingDetails.retrieveDataFromCsvFile();		
			tradingSystemEntity.rankEntity(lstTradingSystemEntity);			

		}catch(TradingSystemException ex)
		{
			System.out.println("TradingSystemException caught in RankEntityTest_withValues:"+ex.toString());
		}catch(IOException ex)
		{
			System.out.println("IOException caught in RankEntityTest_withValues:"+ex.toString());
		}
	}

}
