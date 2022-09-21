import javax.swing.JOptionPane;

public class pvaInfo {
	
	private int year;
	private int month;
	private String VATref;
	private double goodsAmount;
	
	public pvaInfo( int Year, int Month, String VATno , float GoodsAmount)
	{
		year = Year;
		month = Month;
		VATref = VATno;
		goodsAmount = GoodsAmount;
	}
	
	// set year 
	public void setYear( int Year)
	{
		if (Year >= 2020 && Year<= 2050)
		{
			year = Year;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please Select a year between 2020 - 2050", "Invalid Data", JOptionPane.ERROR_MESSAGE);
		}
	}
	// return year
	public int getYear()
	{
		return year;
	} 
	
	//set month
	public void setMonth( int Month)
	{
		if (Month >= 1 && Month <= 12)
		{
			month = Month;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please Select a month from 1 - 12", "Invalid Data", JOptionPane.ERROR_MESSAGE);
		}
	}
	// return month
	public int getMonth()
	{
		return month;
	} 
	
	
	// set VAT ref 
	public void setVAT(String VATno)
	{
		VATref = VATno;

	}
	
	// get VAT ref
	public String getVAT()
	{
		return VATref;
	}
	
	// set goods Amount
	public void setAmount( double GoodsAmount )
	{
		goodsAmount = GoodsAmount;
	}
	
	// get goods Amount
	public double getAmount()
	{
		return goodsAmount;
	}		
}
