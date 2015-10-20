package BankApplication;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.GregorianCalendar;

public abstract class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private int number;
	private String owner;
	private GregorianCalendar dateOpened;
	private double balance;

	public Account(int num, String own, GregorianCalendar d, double bal){
		number = num;
		owner = own;
		dateOpened = d;
		balance = bal;
	}

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

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public GregorianCalendar getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(GregorianCalendar dateOpened) {
		this.dateOpened = dateOpened;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
