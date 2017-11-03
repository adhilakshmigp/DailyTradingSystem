package TradingSystemPack;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.Collection;
import java.util.Arrays;
import java.text.SimpleDateFormat;

@RunWith(Parameterized.class)
public class SettelmentDtTestWithParameter {
	
	String currency = new String();
	String inputSettlementDt = new String();
	String expectedSettlementDt = new String();
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	
	@Parameterized.Parameters
	public static Collection<Object[]> data()
	{
		return Arrays.asList(new Object[][] {{null, null, null },
											{null, "27 Feb 2016", null },
											 {"AED","27 Feb 2016","28 Feb 2016"}, 
											 {"AED","28 Feb 2016","28 Feb 2016"},
											 {"AED","29 Feb 2016","29 Feb 2016"},
											 {"AED","01 Mar 2016","01 Mar 2016"},
											 {"AED","02 Mar 2016","02 Mar 2016"},
											 {"AED","03 Mar 2016","03 Mar 2016"},
											 {"AED","04 Mar 2016","06 Mar 2016"},
											 {"AED","05 Mar 2016","06 Mar 2016"},
											 {"SAR","27 Feb 2016","28 Feb 2016"}, 
											 {"SAR","28 Feb 2016","28 Feb 2016"},
											 {"SAR","29 Feb 2016","29 Feb 2016"},
											 {"SAR","01 Mar 2016","01 Mar 2016"},
											 {"SAR","02 Mar 2016","02 Mar 2016"},
											 {"SAR","03 Mar 2016","03 Mar 2016"},
											 {"SAR","04 Mar 2016","06 Mar 2016"},
											 {"SAR","05 Mar 2016","06 Mar 2016"},
											 //other Currencies like INR / USD except AED and SAR
											 {"INR","27 Feb 2016","29 Feb 2016"}, 
											 {"INR","28 Feb 2016","29 Feb 2016"},
											 {"INR","29 Feb 2016","29 Feb 2016"},
											 {"INR","01 Mar 2016","01 Mar 2016"},
											 {"INR","02 Mar 2016","02 Mar 2016"},
											 {"INR","03 Mar 2016","03 Mar 2016"},
											 {"INR","04 Mar 2016","04 Mar 2016"},
											 {"INR","05 Mar 2016","07 Mar 2016"},
											 {"INR","06 Mar 2016","07 Mar 2016"},
											 {"USD","27 Feb 2016","29 Feb 2016"}, 
											 {"USD","28 Feb 2016","29 Feb 2016"},
											 {"USD","29 Feb 2016","29 Feb 2016"},
											 {"USD","01 Mar 2016","01 Mar 2016"},
											 {"USD","02 Mar 2016","02 Mar 2016"},
											 {"USD","03 Mar 2016","03 Mar 2016"},
											 {"USD","04 Mar 2016","04 Mar 2016"},
											 {"USD","05 Mar 2016","07 Mar 2016"},
											 {"USD","06 Mar 2016","07 Mar 2016"}
		});

	}
	
	
	public SettelmentDtTestWithParameter(String currency, String inputSettlementDt, String expectedSettlementDt)
	{
		this.currency = currency;
		this.inputSettlementDt = inputSettlementDt;
		this.expectedSettlementDt = expectedSettlementDt;	
		
	}
	
	

	@Test
	public void validateSettelementDt_multipleParameters() {
		try
		{
		TradingSystemEntity tradingSystemEntity = new TradingSystemEntity();
		Date settlementDt = null;
		if (this.inputSettlementDt != null)
			settlementDt = sdf.parse(this.inputSettlementDt);
		Date returnDt = tradingSystemEntity.validateSettlementDt(settlementDt, this.currency);
		//fail("My method didn't throw when I expected it to");
	    assertEquals(this.expectedSettlementDt, sdf.format(returnDt));
		}catch (TradingSystemException expectedException)
		{
			System.out.println("Exception Caught :"+expectedException.toString());
		}catch (Exception e)
		{
			System.out.println("Inside Exception :"+e.getMessage());
		}
	}
	
	

}
