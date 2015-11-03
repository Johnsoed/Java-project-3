package BankApplication;

import java.text.NumberFormat;
import java.util.GregorianCalendar;

/*************************************************************************
 * Subclass of account that stores checking account if the account
 * has monthly fee.
 * 
 * @author Hirano Ryuta, Edward Johnson, Lanndon Rose
 * @version November 2015
 ************************************************************************/
public class CheckingAccount extends Account {

	/** implements the serializable */
	private static final long serialVersionUID = 1L;

	/** for monthly fee in double format*/
	private double monthlyFee;
	
	/*********************************************************************
	 * checking constructor that does nothing.
	 ********************************************************************/
	public CheckingAccount(){
	}

	/*********************************************************************
	 * Checking Account constructor that stores information onto the 
	 * instance variables.
	 * 
	 * @param num - account number for the super class.
	 * @param own - account owner for the super class.
	 * @param d - date opened for the super class.
	 * @param bal - account balance for the super class.
	 * @param fee - account monthly fee only for checking account.
	 ********************************************************************/
	public CheckingAccount(int num, String own, GregorianCalendar d, 
			double bal, double fee) {
		super(num, own, d, bal);
		// TODO Auto-generated constructor stub
		monthlyFee = fee;
	}

	/*********************************************************************
	 * String method that prints out the checking class when called.
	 * 
	 * @return - the string followed by all the information on the account.
	 ********************************************************************/
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String result = super.toString();
		result += "\nMonthly Fee: " + formatter.format(monthlyFee);
		return result;
	}

	/*********************************************************************
	 * Equals method to check and see if the variables equals the checking
	 * class.
	 * 
	 * @param other - calling other account class for equal boolean
	 * @return - true if the value does equal, otherwise returns false.
	 ********************************************************************/
	public boolean equals(Object other){
		if (other instanceof Account){
			CheckingAccount ant = (CheckingAccount) other;
			if(super.equals(other) == true){
				if (this.monthlyFee == ant.monthlyFee)
					return true;
			}
		}
		return false;
	}

	/*********************************************************************
	 * Gets the current account monthly fee.
	 * @return - the monthly fee value in double.
	 ********************************************************************/
	public double getMonthlyFee() {
		return monthlyFee;
	}

	/*********************************************************************
	 * Sets the monthly fee value.
	 * @param monthlyFee - the monthly fee value.
	 ********************************************************************/
	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	/*********************************************************************
	 * To get the implemented serializable version from the class.
	 * @return - the current serial version UID.
	 ********************************************************************/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*********************************************************************
	 * Overrides the account abstract monthly fee method to return true,
	 * since checking has a monthlyfee value.
	 * @return - true for having monthly fee value.
	 ********************************************************************/
	@Override
	public boolean hasMonthlyFee() {
		return true;
	}
	
	/*********************************************************************
	 * Overrides the account string output to return the monthly fee value
	 * in string. This method is mainly for the bankModel.
	 * @return - string of monthly fee in currency value.
	 ********************************************************************/
	@Override
	public String outPut() {
		String s = "Monthly Fee: "; 
		s += NumberFormat.getCurrencyInstance().format(monthlyFee);
		return s;
	}
	
}
//hello fellow partners