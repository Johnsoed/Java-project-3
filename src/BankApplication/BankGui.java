package BankApplication;

import javax.swing.*;

public class BankGui extends JFrame {

	/**
	 * gui for the application
	 */
	private static final long serialVersionUID = 1L;
	
	//all the items needed for the menu tabs
	
	private JPanel window;

	private JMenuBar menuBar;

	private JMenu file , Sort;

	private JMenuItem sort_Account, sort_Owner,Sort_Date;
	
	private JMenuItem file_load_Bin,file_save_bin;

	private JMenuItem file_load_Text, file_save_Text;

	private JMenuItem file_load_XML, file_save_XML;

	private JMenuItem file_Quit;
	
	private JTable list;

	private JList<Account> accounts;
	
	private JTextField[] tInput;
	
	private BankModel ld;
	
	public BankGui(){
		String names[] = {"Number", "Date Opened", "Owner", "Balance"};
		ld = new BankModel(names);
		list = new JTable(ld);
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
	}
	
	public static void main(String[] args){
		BankGui bank = new BankGui();
		bank.pack();
		bank.setVisible(true);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setResizable(false);
	}
}
