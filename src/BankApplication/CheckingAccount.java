package BankApplication;

import java.text.NumberFormat;
import java.util.GregorianCalendar;

public class CheckingAccount extends Account {

	private static final long serialVersionUID = 1L;

	private double monthlyFee;

	public CheckingAccount(int num, String own, GregorianCalendar d, 
			double bal, double fee) {
		super(num, own, d, bal);
		// TODO Auto-generated constructor stub
		monthlyFee = fee;
	}

	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String result = super.toString();
		result += "\nMonthly Fee: " + formatter.format(monthlyFee);
		return result;
	}

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

	public double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
