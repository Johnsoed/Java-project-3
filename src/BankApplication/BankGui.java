package BankApplication;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class BankGui extends JFrame {

	/**
	 * gui for the application
	 */
	private static final long serialVersionUID = 1L;

	// all the items needed for the menu tabs

	private JPanel window;

	private JMenuBar menuBar;

	private JMenu file, Sort, Add;

	private JMenuItem Savings, Checkings, Delete;

	private JMenuItem sort_Account, sort_Owner, sort_Date;

	private JMenuItem file_load_bin, file_save_bin;

	private JMenuItem file_load_Text, file_save_Text;

	private JMenuItem file_load_XML, file_save_XML;

	private JMenuItem Quit;

	public JTable list;

	private JTextField[] tInput;

	private BankModel ld;

	public BankGui() {
		String names[] = { "Account Number", "Date Opened",
				"Account Owner", "Account Balance", "Information" };
		ld = new BankModel(names);
		list = new JTable(ld);
		list.getTableHeader().setReorderingAllowed(false);
		list.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		setJMenuBar(setupMenu());
		list.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.getClickCount() > 1) {
					int row = list.rowAtPoint(e.getPoint());
					int col = list.columnAtPoint(e.getPoint());
					//if information column is selected, finds if it is
					//savings or checking, and updates accordingly
					// gives prompt for minimum balance and interest if its
					// a savings, and monthly fee if its a checking
					if (col == 4) {
						if (ld.isSavings(row) == true) {
							String updateString = JOptionPane.showInputDialog(
									null, "enter new value: min Balance ");
							System.out.print(
									row + " " + col + " " + updateString);
							if (updateString != null) {
								ld.update(row, 6, updateString);
							}
							String updateString2 = JOptionPane
									.showInputDialog(null,
											"enter new value: Interest Rate ");
							System.out.print(
									row + " " + col + " " + updateString2);
							if (updateString2 != null) {
								ld.update(row, 5, updateString2);
							}
						} else if (ld.isSavings(row) == false) {
							String updateString4 = JOptionPane
									.showInputDialog(null, "enter new value");
							if (updateString4 != null) {
								ld.update(row, 4, updateString4);
							}
						}
					} else if (col < 4) {
						String updateString3 = JOptionPane
								.showInputDialog(null, "enter new value");
						System.out
								.print(row + " " + col + " " + updateString3);

						if (updateString3 != null) {
							ld.update(row, col, updateString3);
						}

					}
				}

			}

		});

	}

	public static void main(String[] args) {
		BankGui bank = new BankGui();
		bank.pack();
		bank.setVisible(true);
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setSize(900, 400);
		bank.setResizable(false);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sort_Owner) {
				ld.sortName();
				// System.out.print("test");
			}
			if (e.getSource() == sort_Account) {
				ld.sortAccount();
				// System.out.print("test");
			}
			if (e.getSource() == sort_Date) {
				ld.sortDate();
				// System.out.print("test");
			}
			if (e.getSource() == Checkings) {
				try {
					bankDialogBox(true);
				} catch (ParseException e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == Savings) {
				try {
					bankDialogBox(false);
				} catch (ParseException e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}

			// change the delete to (int i) instead of (account other)
			if (e.getSource() == Delete) {
				 ld.delete(list.getSelectedRow());
			}
			if (e.getSource() == Quit) {
				System.exit(EXIT_ON_CLOSE);
			}
			
			if (e.getSource() == file_save_Text){
				try {
					ld.saveTable();
				} catch (Exception e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == file_load_Text){
				try {
					ld.loadTable();
				} catch (Exception e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == file_save_XML){
				try {
					ld.saveXML();
				} catch (Exception e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == file_load_XML){
				try {
					ld.loadXML();
				} catch (Exception e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == file_save_bin){
				ld.saveBinary();
			}
			
			if(e.getSource() == file_load_bin){
				ld.loadBinary();
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

		Add = new JMenu("Add");
		Add.setMnemonic(KeyEvent.VK_A);
		menuBar.add(Add);

		Savings = new JMenuItem("Savings");
		Add.add(Savings);
		Savings.addActionListener(listener);

		Checkings = new JMenuItem("Checkings");
		Add.add(Checkings);
		Checkings.addActionListener(listener);

		Delete = new JMenuItem("Delete");
		Add.add(Delete);
		Delete.addActionListener(listener);

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

	public void bankDialogBox(boolean check) throws ParseException {
		JTextField AccNumber = new JTextField(15);
		JTextField AccOwner = new JTextField(15);
		JDateChooser DateOpened = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) DateOpened
				.getDateEditor();
		editor.setEditable(false);
		JTextField AccBal = new JTextField(15);
		JTextField IntRate = new JTextField(15);
		JTextField MiniBal = new JTextField(15);
		JTextField MonFee = new JTextField(15);

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(6, 2)); // a spacer
		myPanel.add(new JLabel("Account Number: "));
		myPanel.add(AccNumber);
		myPanel.add(new JLabel("Account Owner: "));
		myPanel.add(AccOwner);
		myPanel.add(new JLabel("Date Opened: "));
		myPanel.add(DateOpened);
		myPanel.add(new JLabel("Account Balance: "));
		myPanel.add(AccBal);

		if (check == false) {
			myPanel.add(new JLabel("Interest Rate: "));
			myPanel.add(IntRate);
			myPanel.add(new JLabel("Minimum Balance: "));
			myPanel.add(MiniBal);

			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Saving Acount", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
				int num = Integer.parseInt(AccNumber.getText());
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(DateOpened.getDate());
				double bal = Double.parseDouble(AccBal.getText());
				double rate = Double.parseDouble(IntRate.getText());
				double mbal = Double.parseDouble(MiniBal.getText());
				SavingsAccount s = new SavingsAccount(num,
						AccOwner.getText(), cal, bal, mbal, rate);
				ld.add(s);
				}
				
				catch ( Exception e) {
					JOptionPane.showMessageDialog(null,""
							+ "Fields left empty, or not entered correctly");
				}
			} else if (result == JOptionPane.CANCEL_OPTION) {
			}
		} else if (check == true) {
			myPanel.add(new JLabel("Monthly Fee: "));
			myPanel.add(MonFee);

			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Checking Acount", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
				int num = Integer.parseInt(AccNumber.getText());
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(DateOpened.getDate());
				double bal = Double.parseDouble(AccBal.getText());
				double fee = Double.parseDouble(MonFee.getText());
				CheckingAccount s = new CheckingAccount(num,
						AccOwner.getText(), cal, bal, fee);
				ld.add(s);
				}
				catch ( Exception e) {
					JOptionPane.showMessageDialog(null,""
							+ "Fields left empty, or not entered correctly");
				}
				
			} else if (result == JOptionPane.CANCEL_OPTION) {
			}
		}
	}

}


//ogn['ernt]