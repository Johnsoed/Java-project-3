package BankApplication;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.GregorianCalendar;

/*************************************************************************
 * Parent account class that identifies personal accounts. This class
 * also implements Serializable.
 * 
 * @author Hirano Ryuta, Edward Johnson, Lanndon Rose
 * @version November 2015
 ************************************************************************/
public abstract class Account implements Serializable{

	/** implements the serializable */
	private static final long serialVersionUID = 1L;
	
	/** integer for account number*/
	private int number;
	
	/** for account owner*/
	private String owner;
	
	/** for the date opened*/
	private GregorianCalendar dateOpened;
	
	/** for the account balance*/
	private double balance;
	
	/*********************************************************************
	 * account constructor that does nothing.
	 ********************************************************************/
	public Account(){
	}

	/*********************************************************************
	 * Account constructor that stores the information onto the instance
	 * variables.
	 * 
	 * @param num - for account number
	 * @param own - for string account owner
	 * @param d - date opened using gregoriancalendar java class
	 * @param bal - account balance that is in double.
	 ********************************************************************/
	public Account(int num, String own, GregorianCalendar d, double bal){
		super();
		number = num;
		owner = own;
		dateOpened = d;
		balance = bal;
	}

	/*********************************************************************
	 * Equals method to check and see if the variables equals the account
	 * class.
	 * 
	 * @param other - calling an other account class for equal boolean
	 * @return - true if the value does equal, otherwise returns false.
	 ********************************************************************/
	public boolean equals(Object other){
		if (other instanceof Account){
			Account ant = (Account) other;
			if ((this.number == ant.number) && (this.owner == ant.owner)){
				if(this.dateOpened == ant.dateOpened)
					if(this.balance == ant.balance)
						return true;
			}
		}
		return false;
	}

	/*********************************************************************
	 * String method that prints out the account class when called.
	 * 
	 * @return - the string followed by all the information on the account.
	 ********************************************************************/
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String result = "Number: " + number + "\n" + "Date Opened: ";
		result += (dateOpened.get(dateOpened.MONTH) + 1) + "/";
		result += dateOpened.get(dateOpened.DATE) + "/";
		result += dateOpened.get(dateOpened.YEAR);
		result += "\n" + "Owner: " + owner + "\n";
		result += "Balance: " + formatter.format(balance);
		return result;
	}

	/*********************************************************************
	 * Gets the account number.
	 * @return - current account number.
	 ********************************************************************/
	public int getNumber() {
		return number;
	}
	
	/*********************************************************************
	 * Sets the account number with the following parameter.
	 * @param number - account number to set.
	 ********************************************************************/
	public void setNumber(int number) {
		this.number = number;
	}
	
	/*********************************************************************
	 * Gets the string owner.
	 * @return - the current account owner.
	 ********************************************************************/
	public String getOwner() {
		return owner;
	}
	
	/*********************************************************************
	 * Sets the account string with the following parameter
	 * @param owner - string owner to set.
	 ********************************************************************/
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/*********************************************************************
	 * Gets the current Gregorian Calendar dates.
	 * @return - the current date.
	 ********************************************************************/
	public GregorianCalendar getDateOpened() {
		return dateOpened;
	}
	
	/*********************************************************************
	 * Sets the date opened with the following parameter.
	 * @param dateOpened - the date of the account opened
	 ********************************************************************/
	public void setDateOpened(GregorianCalendar dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	/*********************************************************************
	 * Gets the account balance.
	 * @return - the current account balance
	 ********************************************************************/
	public double getBalance() {
		return balance;
	}
	
	/*********************************************************************
	 * Sets the account balance with the following parameter.
	 * @param balance - the account balance.
	 ********************************************************************/
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/*********************************************************************
	 * To get the implemented serializable version from the class.
	 * @return - the current serial version UID.
	 ********************************************************************/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*********************************************************************
	 * Boolean abstract class to see if an account has an monthly fee
	 * or not.
	 * @return - true if it has monthlyfee, otherwise returns false.
	 */
	public abstract boolean hasMonthlyFee();
	
	/*********************************************************************
	 * String abstract class to return the Checkings/Savings instance
	 * variables that account base class doesn't have.
	 * @return - the account class depending on whether if it is a
	 * checking or savings.
	 ********************************************************************/
	public abstract String outPut();
	
}
