package BankApplication;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Viewer {
	public static void main(String[] arg){
		BankGUI bank = new BankGUI();
		bank.pack();
		bank.setVisible(true);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setResizable(false);
	}
}
