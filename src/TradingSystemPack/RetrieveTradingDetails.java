package TradingSystemPack;
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Date;
	import java.util.Scanner;
	import java.math.BigDecimal;
	import java.math.BigInteger;
	import java.text.*;
	
	/**
	 * This class is used to retrieve the Trading details and generate the trading report
	 * @author Adhilakshmi
	 * @version 1.0
	 *
	 */
	public class RetrieveTradingDetails 
	{
	 
		public static BigDecimal calEntityTotalUSDAmt(List<TradingSystemEntity> tradeEntityList, String pmEntity, char pmEntityType, Date pmSettlementDt)
		{
			BigDecimal entTotalUSDAmt = new BigDecimal(BigInteger.ZERO, 2);
			
			for(int i=0; i< tradeEntityList.size();i++)
			{
				TradingSystemEntity tradingSystemEntity = tradeEntityList.get(i);
				if (tradingSystemEntity.getEntity().equalsIgnoreCase(pmEntity) && tradingSystemEntity.getEntityType() == pmEntityType)
				{
					entTotalUSDAmt = entTotalUSDAmt.add(tradingSystemEntity.getUsdAmt());
				}
			}
			return entTotalUSDAmt;
			
		} 
			 
		/**
		 * This method retrieve Trading details from the CSV file and prepare the list of 
		 * TradingSystemEntity objects with the details from CSV file and return that list 
		 * @return This contains the list of TradingSystemEntity objects
		 * @throws IOException This exception throws IOException
		 */
		public static List<TradingSystemEntity> retrieveDataFromCsvFile() throws IOException 
		{
			Scanner scanner = new Scanner(new File("TradingDetails.csv"));
			Scanner dataScanner = null;
		    
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			int index = 0;
			List<TradingSystemEntity> tradingSystemEntityList = new ArrayList<>();
			try
			{
			while (scanner.hasNextLine()) {
				dataScanner = new Scanner(scanner.nextLine());
				dataScanner.useDelimiter(";");
				TradingSystemEntity tradingSystemEntity = new TradingSystemEntity() ;

				while (dataScanner.hasNext()) {
		
					String data = dataScanner.next();
					if (index == 0)				
						tradingSystemEntity.setEntity(data);
					else if (index == 1)
						tradingSystemEntity.setEntityType(data.charAt(0));
					else if (index == 2)
						tradingSystemEntity.setAgreedFx(new BigDecimal(data));
					else if (index == 3)
						tradingSystemEntity.setCurrencyType(data);
					else if (index == 4)
						tradingSystemEntity.setInstructionDt(sdf.parse(data));
					else if (index == 5)
						tradingSystemEntity.setSettlementDt(sdf.parse(data));
					else if (index == 6)
						tradingSystemEntity.setNoOfUnit(Integer.parseInt(data));
					else if (index == 7)
						tradingSystemEntity.setPricePerUnit(new BigDecimal(data));
					else
						System.out.println("invalid data::" + data);
							index++;
				}
				index = 0;				
				tradingSystemEntityList.add(tradingSystemEntity);
				
			}
			}catch(Exception ex)
			{				
			   System.out.println("Exception : "+ ex.getMessage());
			}finally
			{
				scanner.close();
			}
			
			return tradingSystemEntityList;
		}
		
			/**
			 * This main method retrieve the Trading Entity details from the CSV file 
			 * and Validate the Settlement date and Calculate the USD amount and 
			 * generate the Trading report
			 * @param args[] This holds string array 
			 */
			public static void main(String[] args) {
		  
			try
			{
			List<TradingSystemEntity> tradingSystemEntityList =retrieveDataFromCsvFile();					
			for(int i=0; i< tradingSystemEntityList.size();i++)
			{				
					TradingSystemEntity tradingSystemEntity = tradingSystemEntityList.get(i) ;
				    tradingSystemEntity.calculateUSDAmt();	
				    String currency = tradingSystemEntityList.get(i).getCurrencyType();
					Date settlementDt = tradingSystemEntityList.get(i).getSettlementDt();						
					Date validSettlementDt = tradingSystemEntityList.get(i).getSettlementDt();
					validSettlementDt = tradingSystemEntity.validateSettlementDt(settlementDt, currency );
					if (validSettlementDt.compareTo(settlementDt) >0)
						tradingSystemEntity.setSettlementDt(validSettlementDt);				
				
			}			
			
		    TradingSystemEntity obj = new TradingSystemEntity();
			obj.rankEntity(tradingSystemEntityList);
			obj.generateTradeEntityReport(tradingSystemEntityList);
			}catch(TradingSystemException ex)
			{
				System.out.println("TradingSystemException caught :"+ex.toString());
			}catch(Exception ex)
			{
				System.out.println("Exception Caught :"+ex.getMessage());
			}
		
		} //End of main()

	

}
