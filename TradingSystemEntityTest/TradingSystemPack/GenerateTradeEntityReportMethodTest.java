package TradingSystemPack;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class GenerateTradeEntityReportMethodTest {

	@Test
	public void generateTradeEntityReportTest_withValues() {
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity();	
		try
		{
			List<TradingSystemEntity> tradingSystemEntityList = RetrieveTradingDetails.retrieveDataFromCsvFile();
			for(int i=0; i< tradingSystemEntityList.size();i++)
			{				
				TradingSystemEntity tradingSystemEntity1 = tradingSystemEntityList.get(i) ;
				tradingSystemEntity1.calculateUSDAmt();	
			    String currency = tradingSystemEntityList.get(i).getCurrencyType();
				Date settlementDt = tradingSystemEntityList.get(i).getSettlementDt();						
				Date validSettlementDt = tradingSystemEntityList.get(i).getSettlementDt();
				validSettlementDt = tradingSystemEntity1.validateSettlementDt(settlementDt, currency );
				if (validSettlementDt.compareTo(settlementDt) >0)
					tradingSystemEntity1.setSettlementDt(validSettlementDt);				
				
			}	
			tradingSystemEntity.rankEntity(tradingSystemEntityList);		
			tradingSystemEntity.generateTradeEntityReport(tradingSystemEntityList);

		}catch(TradingSystemException ex)
		{
			System.out.println("TradingSystemException caught in RankEntityTest_withValues:"+ex.toString());
		}catch(IOException ex)
		{
			System.out.println("IOException caught in RankEntityTest_withValues:"+ex.toString());
		}
	}
	
	@Test
	public void generateTradeEntityReportTest_ifNoDetailsfoundInList() {
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity();	
		List<TradingSystemEntity> lstTradingSystemEntity = new ArrayList<>();
		tradingSystemEntity.generateTradeEntityReport(lstTradingSystemEntity);
	}

}
