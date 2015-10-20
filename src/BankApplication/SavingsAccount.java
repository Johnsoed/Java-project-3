package BankApplication;

import java.text.NumberFormat;
import java.util.GregorianCalendar;

public class SavingsAccount extends Account {

	private static final long serialVersionUID = 1L;

	private double minBalance;
	private double interestRate;

	public SavingsAccount(int num, String own, GregorianCalendar d,
			double bal, double minBal, double rate) {
		super(num, own, d, bal);
		// TODO Auto-generated constructor stub
		minBalance = minBal;
		interestRate = rate;
	}

	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String result = super.toString();
		result += "\nMinimum Balance: " + formatter.format(minBalance);
		result += "\nInterest Rate: " + formatter.format(interestRate);

		return result;
	}

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

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
