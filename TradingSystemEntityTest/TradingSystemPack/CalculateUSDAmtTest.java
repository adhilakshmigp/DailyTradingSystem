package TradingSystemPack;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Test;

public class CalculateUSDAmtTest {

	@Test
	public void calculateUSDAmtTest_withProperValues() {
		BigDecimal agreedFx = new BigDecimal("0.26");
		BigDecimal pricePerUnit = new BigDecimal("100.24");
		int noOfUnit = 25;
		BigDecimal expectedUSDAmt =  new BigDecimal("651.56");
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			assertEquals(expectedUSDAmt.toString(),tradingSystemEntity.getUsdAmt().toString() );
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught : "+ex.toString());
		}		
		
	}
		
	@Test
	public void calculateUSDAmtTest_withAllNullValues() {
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(null, 0, null);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withAllNullValues method: "+ex.toString());
		}		
		
	}
	
	@Test
	public void calculateUSDAmtTest_withAgreedFxAsNullValue() {
		BigDecimal agreedFx = null;
		BigDecimal pricePerUnit = new BigDecimal("100.24");
		int noOfUnit = 25;
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withAgreedFxAsNullValue method: "+ex.toString());
		}		
		
	}
	
	@Test
	public void calculateUSDAmtTest_withPricePerUnitAsNullValue() {
		BigDecimal agreedFx = new BigDecimal(".50");;
		BigDecimal pricePerUnit = null;
		int noOfUnit = 25;
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withPricePerUnitAsNullValue method: "+ex.toString());
		}		
		
	}
	
	@Test
	public void calculateUSDAmtTest_withPricePerUnitAsZeroValue() {
		BigDecimal agreedFx = new BigDecimal(".50");
		BigDecimal pricePerUnit = new BigDecimal("0.00");
		int noOfUnit = 25;
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withPricePerUnitAsZeroValue method: "+ex.toString());
		}		
		
	}

	@Test
	public void calculateUSDAmtTest_withAgreedFxAsZeroValue() {
		BigDecimal agreedFx = new BigDecimal("0.00");
		BigDecimal pricePerUnit = new BigDecimal("100.27");
		int noOfUnit = 25;
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withAgreedFxAsZeroValue method: "+ex.toString());
		}		
		
	}
	
	@Test
	public void calculateUSDAmtTest_withNoOfUnitAsZeroValue() {
		BigDecimal agreedFx = new BigDecimal("0.10");
		BigDecimal pricePerUnit = new BigDecimal("100.27");
		int noOfUnit = 0;
		
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity(agreedFx, noOfUnit, pricePerUnit);
		try
		{
			tradingSystemEntity.calculateUSDAmt();
			fail("TradingSysteException should be Thrown");
		}catch(TradingSystemException ex)
		{
			System.out.println("Exception Caught in calculateUSDAmtTest_withNoOfUnitAsZeroValue method: "+ex.toString());
		}		
		
	}
}
