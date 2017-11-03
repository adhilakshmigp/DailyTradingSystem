

package TradingSystemPack;


import java.util.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

/**
 * @author Adhilakshmi
 * @version 1.0
 *
 */
public class TradingSystemEntity {

	
	private String entity;
	private char entityType;
	private BigDecimal agreedFx ;
	private String currencyType;
	private Date instructionDt;
	private Date settlementDt;
	private int noOfUnit;
	private BigDecimal pricePerUnit;	
	private BigDecimal usdAmt = new BigDecimal(BigInteger.ZERO, 2);
	private int rank;
	protected static final String DATEFORMAT = "dd-MMM-yyyy";
	
	
	
	class CompareByUsdAmt implements Comparator<TradingSystemEntity> {

	    public int compare(TradingSystemEntity o1, TradingSystemEntity o2) {
	       return o2.getUsdAmt().compareTo(o1.getUsdAmt());
	    }
	}
	
	class CompareByEntityType implements Comparator<TradingSystemEntity> {

	    public int compare(TradingSystemEntity o1, TradingSystemEntity o2) {
	        return String.valueOf(o1.getEntityType()).compareTo(String.valueOf(o2.getEntityType()));
	    }
	}
	
	class CompareBySettlementDt implements Comparator<TradingSystemEntity> {

	    public int compare(TradingSystemEntity o1, TradingSystemEntity o2) {
	        return o1.getSettlementDt().compareTo(o2.getSettlementDt());
	    }
	}
	
	class CompareByEntity implements Comparator<TradingSystemEntity> {

	    public int compare(TradingSystemEntity o1, TradingSystemEntity o2) {
	        return o1.getEntity().compareTo(o2.getEntity());
	    }
	}
	
	/**
	 * This class will sort the TradingSystemEntity objects based on the list of Comparators
	 * @author Adhilakshmi
	 * @version 1.0
	 *
	 */
	class MultipleComparatorsCheck implements Comparator<TradingSystemEntity> {
		 
	    private List<Comparator<TradingSystemEntity>> listComparators;
	 
	    public MultipleComparatorsCheck(Comparator<TradingSystemEntity>... comparators) {
	        this.listComparators = Arrays.asList(comparators);
	    }	 
	    
	    public int compare(TradingSystemEntity obj1, TradingSystemEntity obj2) {
	        for (Comparator<TradingSystemEntity> comparator : listComparators) {
	            int result = comparator.compare(obj1, obj2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        return 0;
	    }
	}
	
	/**
	 * This constructor set the null and default values to the memeber variables of 
	 * this class
	 */
	public TradingSystemEntity()
	{
		entity = null;
		entityType = Character.MIN_VALUE;
		agreedFx = null;
		currencyType = null;
		instructionDt = null;
		settlementDt =  null;
		noOfUnit = 0;
		pricePerUnit = null;
	}
	
	
	/**
	 * This Constructor set the value passed to this method to the corresponding 
	 * member variable and all other variables are set to constant values.
	 * @param ParamAgreedFx  This parameter used to set the value to the member variable agreedFx.
	 * @param paramNoOfUnit This parameter used to set the value to the member variable noOfUnit.
	 * @param paramPricePerUnit This parameter used to set the value to the member variable pricePerUnit.
	 */
	public TradingSystemEntity(BigDecimal ParamAgreedFx, int paramNoOfUnit, BigDecimal paramPricePerUnit)
	{
		entity = "Foo";
		entityType = 'B';
		agreedFx = ParamAgreedFx;
		currencyType = "SAR";
		instructionDt = new Date();
		settlementDt =  new Date();
		noOfUnit = paramNoOfUnit;
		pricePerUnit = paramPricePerUnit;
	}
	
	/**
	 * This constructor set the values to the member variables
	 * @param paramEntity This parameter used to set the member variable paramEntity
	 * @param paramEntityType This parameter used to set the member variable paramEntityType
	 * @param ParamAgreedFx This parameter used to set the member variable ParamAgreedFx
	 * @param paramCurrencyType This parameter used to set the member variable paramCurrencyType
	 * @param paramInstructionDt This parameter used to set the member variable paramInstructionDt
	 * @param paramSettlementDt This parameter used to set the member variable paramSettlementDt
	 * @param paramNoOfUnit This parameter used to set the member variable paramNoOfUnit
	 * @param paramPricePerUnit This parameter used to set the member variable paramPricePerUnit
	 */
	public  TradingSystemEntity(String paramEntity,char paramEntityType,BigDecimal ParamAgreedFx,String paramCurrencyType,Date paramInstructionDt,Date paramSettlementDt,int paramNoOfUnit, BigDecimal paramPricePerUnit)
	{
		entity = paramEntity;
		entityType = paramEntityType;
		agreedFx = ParamAgreedFx;
		currencyType = paramCurrencyType;
		instructionDt = paramInstructionDt;
		settlementDt = paramSettlementDt;
		noOfUnit = paramNoOfUnit;
		pricePerUnit = paramPricePerUnit;
	}	
	

	/**
	 * This method is used to sort the TradingSystemEntity List in descending order of 
	 * USD amount based on the Entity type.
	 * @param tradingSystemEntityList This parameter contains the list of TradingSystemEntity objcts.
	 * @throws TradingSystemException This exception throws when the list is null or there is no 
	 * TradingSystemEntity objects in the list.
	 */
	public void rankEntity(List<TradingSystemEntity> tradingSystemEntityList) throws TradingSystemException
	{
		if (tradingSystemEntityList != null && tradingSystemEntityList.size() > 0)
			Collections.sort(tradingSystemEntityList, new MultipleComparatorsCheck( new CompareByEntityType(), new CompareByUsdAmt()));
		else 
			throw new TradingSystemException(107, "No trading entities are found to be ranked");
	}

	/**
	 * This method is used to validate the settlement date where it is holiday based on the
	 * currency and assign the next work day to the settlement date
	 * @param paramSettlementDt This parameter contains the Settlement date to be validated.
	 * @param paramCurrencyType This parameter contains the Currency for that settlement.
	 * @return It returns the date object of the valid Settlement Date.
	 * @throws TradingSystemException This exception throws when Settlement date or Currency has null values.
	 */
	public Date validateSettlementDt(Date paramSettlementDt, String paramCurrencyType) throws TradingSystemException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		Date nextDate ;
		
		if (paramSettlementDt == null)
			throw new TradingSystemException(100,"Settlement Date can not be null");
		if (paramCurrencyType == null || paramCurrencyType =="")
			throw new TradingSystemException(101, "Currency can not be null");
		
		
		String dayOfWeek = sdf.format(paramSettlementDt);
		
	    if (paramCurrencyType.equalsIgnoreCase("AED") || paramCurrencyType.equalsIgnoreCase("SAR")  ) 
		{
			switch (dayOfWeek)
			{
			case "Fri":
				nextDate = nextWorkingDate(paramSettlementDt, 2);
				break;
			case "Sat":
				nextDate = nextWorkingDate(paramSettlementDt, 1);
				break;
			default:
				nextDate = paramSettlementDt;

			}
		}else
		{
			switch (dayOfWeek)
			{
			case "Sat":
				nextDate = nextWorkingDate(paramSettlementDt, 2);
				break;
			case "Sun":
				nextDate = nextWorkingDate(paramSettlementDt, 1);
				break;
			default:
				nextDate = paramSettlementDt;
			}
		}
	    return nextDate;
	}

	
	/**
	 * This method is used to calculate the USD amount by using the member variables 
	 * pricePerUnit, noOFUnit and agreedFx.
	 * @throws TradingSystemException This exception throws when the member variables 
	 * pricePerUnit, noOfUnit and agreedFx is having null or zero values.
	 */
	public void calculateUSDAmt() throws TradingSystemException
	{
		//System.out.println("calculateUSDAmt");
		if(this.pricePerUnit == null)
			throw new TradingSystemException( 102, "Price per Unit can not be null");
		else
		{
			//System.out.println("23333" +this.pricePerUnit.floatValue());
			if  (this.pricePerUnit.floatValue() == 0)
				throw new TradingSystemException(103,  "Price per Unit can not Zero");
		}
	//	System.out.println("33333333");
		if(noOfUnit == 0)
			throw new TradingSystemException(104,"NoOfUnit can not be Zero");
				
		if(this.agreedFx == null)
			throw new TradingSystemException(105, "agreedFx can not be null");
		else
			if  (this.agreedFx.floatValue() == 0)
				throw new TradingSystemException(106, "agreedFx can not Zero");
		
		this.usdAmt = pricePerUnit.multiply(new BigDecimal(this.noOfUnit)).multiply( this.agreedFx);
		this.usdAmt = this.usdAmt.setScale(2, BigDecimal.ROUND_UP );	
	}
	
    /**
     * This method returns the date in which the given date is added with the number of days.
     * @param givenDate This parameter contains the date object
     * @param noOfDays This parameter contains the number of days to be added.
     * @return
     */
    private Date nextWorkingDate(Date givenDate, int noOfDays)  {

         Date nextDate ;       
        Calendar now = Calendar.getInstance();
        now.setTime(givenDate); 
        now.add(Calendar.DAY_OF_MONTH, noOfDays);// noOfDays to be added to the given date
        nextDate = now.getTime();        
        return nextDate;
   }
    
	//Setter methods 
	 void setEntity(String paramEntity)
	{
		entity = paramEntity;
	}	
	void setEntityType(char paramEntityType)
	{
		entityType = paramEntityType;
	}
	void setAgreedFx(BigDecimal ParamAgreedFx)
	{
		agreedFx = ParamAgreedFx;
	}
	void setCurrencyType(String paramCurrencyType)
	{
		currencyType = paramCurrencyType;
	}
	void setInstructionDt(Date paramInstructionDt)
	{
		instructionDt = paramInstructionDt;
	}
	void setSettlementDt(Date paramSettlementDt)
	{
		settlementDt = paramSettlementDt;
	}
	void setNoOfUnit(int paramNoOfUnit)
	{
		noOfUnit = paramNoOfUnit;
	}
	
	void setPricePerUnit(BigDecimal paramPricePerUnit)
	{
		pricePerUnit = paramPricePerUnit;
	}
	void setUsdAmt(BigDecimal paramUsdAmt)
	{
		usdAmt = paramUsdAmt;
	}
	void setRank(int paramRank)
	{
		rank = paramRank;
	}
	
	//Getter Methods 	
	String getEntity()
	{
		return entity;
	}	
	char getEntityType()
	{
		return entityType;
	}
	BigDecimal getAgreedFx()
	{
		return agreedFx ;
	}
	String getCurrencyType()
	{
		return currencyType;
	}
	Date getInstructionDt()
	{
		return instructionDt;
	}
	Date getSettlementDt()
	{
		return settlementDt;
	}
	int getNoOfUnit()
	{
		return noOfUnit;
	}
	BigDecimal getPricePerUnit()
	{
		return pricePerUnit ;
	}
	
	BigDecimal getUsdAmt()
	{
		return usdAmt ;
	}
	int getRank()
	{
		return rank;
	}
	
	/**
	 * This method pring the trading entity details.
	 */
	public void printTradeEntity()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		System.out.print(entity+"\t"+entityType+"\t"+currencyType+"\t"+this.agreedFx+"\t");
		System.out.print(sdf.format(instructionDt)+"\t"+sdf.format(settlementDt)+"\t"+noOfUnit+"\t"+pricePerUnit+"\t"+usdAmt);
		System.out.println();
	}
	
	/**
	 * This method generate the Trading entity report
	 * @param tradingSystemEntityList This parameter contains the list of trading system entity
	 */
	public void generateTradeEntityReport(List<TradingSystemEntity> tradingSystemEntityList)
	{
	
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    int rankForBuyingAmt = 0;
	    int rankForSellingAmt = 0;
	    DecimalFormat decimalFormat = new DecimalFormat("#########0.00");
	    System.out.println("***************************************************************************************************************************************************");
		System.out.println("Entity\tType\tCurrency\tAgreedFx\tInstruction Date\tSettlement Date\t No of Units\tPrice per Unit\t      Rank\tUSD Amount");
		System.out.println("***************************************************************************************************************************************************");
		if (tradingSystemEntityList != null && tradingSystemEntityList.size() > 0)
		{
			for(int i = 0; i<tradingSystemEntityList.size(); i++)
			{
				String entity = tradingSystemEntityList.get(i).getEntity();
				String entityType = String.valueOf(tradingSystemEntityList.get(i).getEntityType());
				String agreedFx = decimalFormat.format((tradingSystemEntityList.get(i).getAgreedFx().doubleValue()));
				String currencyType = tradingSystemEntityList.get(i).getCurrencyType();
				String instructionDt = sdf.format(tradingSystemEntityList.get(i).getInstructionDt());						
				String settlementDt = sdf.format(tradingSystemEntityList.get(i).getSettlementDt());
				String noOfUnit =Integer.toString(tradingSystemEntityList.get(i).getNoOfUnit());
				String pricePerUnit = decimalFormat.format(tradingSystemEntityList.get(i).getPricePerUnit().doubleValue()).trim();
				String usdAmt =  decimalFormat.format(tradingSystemEntityList.get(i).getUsdAmt().doubleValue()).trim();
				String entityTypeDesc = new String();
				if (String.valueOf(tradingSystemEntityList.get(i).getEntityType()).equalsIgnoreCase("B"))
					 entityTypeDesc = "Buy";
				else if (String.valueOf(tradingSystemEntityList.get(i).getEntityType()).equalsIgnoreCase("S"))
					entityTypeDesc = "Sell";			
			   		
			System.out.print(entity+"\t"+entityTypeDesc+"\t"+currencyType+"\t\t"+agreedFx+"\t\t");
			System.out.print(instructionDt+"\t\t"+settlementDt+"\t\t"+noOfUnit+"\t\t"+pricePerUnit+"\t\t");
	
	        if(String.valueOf(entityType).equalsIgnoreCase("B"))
	        {
	        	rankForBuyingAmt ++;
	        	tradingSystemEntityList.get(i).setRank(rankForBuyingAmt);
	            System.out.print( Integer.toString(tradingSystemEntityList.get(i).getRank()));
	        }else if (String.valueOf(entityType).equalsIgnoreCase("S"))
	        {
	        	rankForSellingAmt ++;
	        	tradingSystemEntityList.get(i).setRank(rankForSellingAmt);
	            System.out.print(Integer.toString(tradingSystemEntityList.get(i).getRank()));
	        }
				System.out.print("\t"+usdAmt+"\t");
			
				System.out.println();
			}
			System.out.println("***************************************************************************************************************************************************");
	
		}
		else
		{
			System.out.println();
			System.out.println("***************************************************************************************************************************************************");
			System.out.println("No Trading Entity Details Found");
		}
		
	}
	


}//End of TradingSystemEntity Class
