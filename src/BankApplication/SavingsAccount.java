package BankApplication;

import java.text.NumberFormat;
import java.util.GregorianCalendar;

/*************************************************************************
 * Subclass of account that stores savings account if the account is
 * savings and has minimum balance and interest rate values.
 * 
 * @author Hirano Ryuta, Edward Johnson, Lanndon Rose
 * @version November 2015
 ************************************************************************/
public class SavingsAccount extends Account {

	/** implements the serializable */
	private static final long serialVersionUID = 1L;

	/** minimum balance in double*/
	private double minBalance;
	
	/** interest rate in double*/
	private double interestRate;
	
	/*********************************************************************
	 * savings account constructor that does nothing.
	 ********************************************************************/
	public SavingsAccount(){
	}

	/*********************************************************************
	 * Savings account constructor that stores information onto the 
	 * instance variables.
	 * @param num - account number for the super class.
	 * @param own - account owner for the super class.
	 * @param d - Gregorian calendar date for the super class.
	 * @param bal - account balance for the super class.
	 * @param minBal - minimum balance only for savings account.
	 * @param rate - interest rate only for savings account.
	 ********************************************************************/
	public SavingsAccount(int num, String own, GregorianCalendar d,
			double bal, double minBal, double rate) {
		super(num, own, d, bal);
		// TODO Auto-generated constructor stub
		minBalance = minBal;
		interestRate = rate;
	}

	/*********************************************************************
	 * String method that prints out the savings class when called.
	 * 
	 * @return - the string followed by all the information on the account.
	 ********************************************************************/
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String result = super.toString();
		result += "\nMinimum Balance: " + formatter.format(minBalance);
		result += "\nInterest Rate: " + formatter.format(interestRate);

		return result;
	}

	/*********************************************************************
	 * Equals method to check and see if the variables equals the savings
	 * class.
	 * 
	 * @param other - calling other account class for equal boolean
	 * @return - true if the value does equal, otherwise returns false.
	 ********************************************************************/
	public boolean equals(Object other){
		if (other instanceof Account){
			SavingsAccount ant = (SavingsAccount) other;
			if(super.equals(other) == true){
				if (this.minBalance == ant.minBalance)
					if(this.interestRate == ant.interestRate)
						return true;
			}
		}
		return false;
	}

	/*********************************************************************
	 * Gets the minimum balance in double format.
	 * @return - the current minimum balance.
	 ********************************************************************/
	public double getMinBalance() {
		return minBalance;
	}

	/*********************************************************************
	 * Sets the minimum balance with the following parameter.
	 * @param minBalance - the minimum balance value.
	 ********************************************************************/
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	/*********************************************************************
	 * Gets the interest rate.
	 * @return - the current interest rate.
	 ********************************************************************/
	public double getInterestRate() {
		return interestRate;
	}

	/*********************************************************************
	 * Sets the interest rate with the following parameter.
	 * @param interestRate - interest rate value.
	 ********************************************************************/
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/*********************************************************************
	 * To get the implemented serializable version from the class.
	 * @return - the current serial version UID.
	 ********************************************************************/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*********************************************************************
	 * Overrides the account abstract monthly fee method to return false,
	 * since saving does not have monthly fee value.
	 * @return - true for having monthly fee value.
	 ********************************************************************/
	@Override
	public boolean hasMonthlyFee() {
		return false;
	}
	
	/*********************************************************************
	 * Overrides the account string output to return the minimum balance
	 * and interest rate variables in string. 
	 * This method is mainly for the bankModel.
	 * @return - string of minimum balance in currency value and interest
	 * rate in percent value.
	 ********************************************************************/
	@Override
	public String outPut() {
		String s = "<html>Minimum Balance: ";
		s += NumberFormat.getCurrencyInstance().format(minBalance);
		s += "<br>Interest Rate: ";
		s += NumberFormat.getPercentInstance().format(interestRate);
		return s;
	}

}
