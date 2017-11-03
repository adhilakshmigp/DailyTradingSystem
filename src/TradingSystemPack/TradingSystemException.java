package TradingSystemPack;

/**
 * @author Adhilakshmi
 * @version 1.0
 *
 */
public class TradingSystemException extends Exception{
	private int errCode;
	private String errMsg ;
	
	/**
	 * This constructor will set the values of errCode as 0 and errMsg memmber variable of this class
	 * @param errMsg This parameter contains the error message
	 */
	public TradingSystemException( String errMsg)
	{
		this.errCode = 0;
		this.errMsg = errMsg;		
	}


	/**
	 * This constructor will set the errCode and errMsg member variables of this class
	 * @param errCode This parameter contains error code value as integer
	 * @param errMsg This parameter contains the error message
	 */
	public TradingSystemException(int errCode, String errMsg)
	{
		this.errCode = errCode;
		this.errMsg = errMsg;		
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString()
	{
		return "TradingSystemException :ErrorCode="+this.errCode +" ErrorMessage="+errMsg;
	}
	
		
}
