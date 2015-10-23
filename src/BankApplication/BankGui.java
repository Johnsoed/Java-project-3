package BankApplication;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

public class BankGui extends JFrame {

	/**
	 * gui for the application
	 */
	private static final long serialVersionUID = 1L;

	// all the items needed for the menu tabs

	private JPanel window;

	private JMenuBar menuBar;

	private JMenu file, Sort;

	private JMenuItem sort_Account, sort_Owner, sort_Date;

	private JMenuItem file_load_bin, file_save_bin;

	private JMenuItem file_load_Text, file_save_Text;

	private JMenuItem file_load_XML, file_save_XML;

	private JMenuItem Quit;

	private JTable list;

	private JList<Account> accounts;

	private JTextField[] tInput;

	private BankModel ld;

	public BankGui() {
		String names[] = { "Account Number", "Date Opened",
				"Account Owner", "Account Balance" };
		ld = new BankModel(names);
		list = new JTable(ld);
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		setJMenuBar(setupMenu());

	}

	public static void main(String[] args) {
		BankGui bank = new BankGui();
		bank.pack();
		bank.setVisible(true);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setResizable(false);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Sort) {

			}
		}

	}

	public JMenuBar setupMenu() {
		ButtonListener listener = new ButtonListener();
		menuBar = new JMenuBar();

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);

		Sort = new JMenu("Sort");
		Sort.setMnemonic(KeyEvent.VK_S);
		menuBar.add(Sort);

		Quit = new JMenuItem("Close");
		Quit.addActionListener(listener);

		file_load_bin = new JMenuItem("Load From Binary...");
		file_save_bin = new JMenuItem("Save As Binary...");

		file_load_bin.addActionListener(listener);
		file_save_bin.addActionListener(listener);

		file_load_Text = new JMenuItem("Load From Text...");
		file_save_Text = new JMenuItem("Save As Text...");

		file_load_Text.addActionListener(listener);
		file_save_Text.addActionListener(listener);

		file_load_XML = new JMenuItem("Load From XML...");
		file_save_XML = new JMenuItem("Save As XML...");

		file_load_XML.addActionListener(listener);
		file_save_XML.addActionListener(listener);

		file.add(file_load_bin);
		file.add(file_save_bin);
		file.addSeparator();
		file.add(file_load_Text);
		file.add(file_save_Text);
		file.addSeparator();
		file.add(file_load_XML);
		file.add(file_save_XML);
		file.addSeparator();
		file.add(Quit);

		sort_Account = new JMenuItem("Sort By Account");
		sort_Owner = new JMenuItem("Sort By Owner");
		sort_Date = new JMenuItem("Sort By Date");

		sort_Account.addActionListener(listener);
		sort_Owner.addActionListener(listener);
		sort_Date.addActionListener(listener);

		Sort.add(sort_Account);
		Sort.add(sort_Owner);
		Sort.add(sort_Date);

		return menuBar;
	}
}