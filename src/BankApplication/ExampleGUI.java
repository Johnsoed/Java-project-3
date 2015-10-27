
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

public class ExampleGUI extends JFrame {

	private JButton addBTN, delete, update, clear;

	private JPanel btnPNL, radioPNL, inputPNL;

	private JTable list;

	private JList<Account> accounts;

	private JRadioButton checking, savings;

	/** JMenu Item that quits when selected */
	private JMenuItem quitItem, loadBin, saveBin, loadText, saveText;

	private JMenuItem loadXML, saveXML;
	private JMenuItem accountN, accountO, dateO;
	private JMenu fileMenu;
	private JMenu sortMenu;
	private JMenuBar menu;

	private JLabel[] label;

	private JTextField[] tInput;

	private BankModel ld;

	public ExampleGUI() {
		setLayout(new BorderLayout());
		btnPNL = new JPanel(new GridLayout(4, 1));
		radioPNL = new JPanel(new GridBagLayout());
		inputPNL = new JPanel();
		ButtonListener listener = new ButtonListener();

		addBTN = new JButton("Add");
		addBTN.addActionListener(listener);
		btnPNL.add(addBTN);

		delete = new JButton("Delete");
		delete.addActionListener(listener);
		btnPNL.add(delete);

		update = new JButton("Update");
		update.addActionListener(listener);
		btnPNL.add(update);

		clear = new JButton("Clear");
		clear.addActionListener(listener);
		btnPNL.add(clear);

		checking = new JRadioButton("Checkings");
		checking.addActionListener(listener);
		radioPNL.add(checking);

		savings = new JRadioButton("Savings");
		savings.addActionListener(listener);
		radioPNL.add(savings);

		ButtonGroup group = new ButtonGroup();
		group.add(checking);
		group.add(savings);

		// String data[][] = {{"123", "9/10/15", "Sam", "$1,000.00"}};
		// String column[] = {"Number", "Date Opened", "Account Owner",
		// "Balance"};

		ld = new BankModel();
		accounts = new JList<>(ld);

		// list = new JTable(data, column);
		// list.setBounds(30,40,200,300);
		// JScrollPane sp = new JScrollPane(list);
		// JPanel p = new JPanel();
		// p.add(Box.createRigidArea(new Dimension()));
		// p.add(sp);
		// p.add(Box.createRigidArea(new Dimension(10,10)));

		inputPNL.add(Box.createRigidArea(new Dimension(10, 0)));
		inputPNL.add(labs());

		JPanel sets = new JPanel(new BorderLayout());
		sets.add(inputPNL, BorderLayout.CENTER);
		sets.add(btnPNL, BorderLayout.EAST);

		setJMenuBar(setupMenu());
		add(new JScrollPane(accounts), BorderLayout.NORTH);
		add(radioPNL, BorderLayout.CENTER);
		add(sets, BorderLayout.SOUTH);
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checking.isSelected()) {
				tInput[4].setEnabled(true);
				tInput[5].setEnabled(false);
				tInput[6].setEnabled(false);
			} else if (savings.isSelected()) {
				tInput[4].setEnabled(false);
				tInput[5].setEnabled(true);
				tInput[6].setEnabled(true);
			}
			if (e.getSource() == addBTN) {
				if ((!tInput[0].getText().trim().equals(""))
						&& (!tInput[1].getText().trim().equals(""))
						&& (!tInput[3].getText().trim().equals(""))
						&& (tInput[0].getText().trim()
								.replaceAll("[a-zA-Z]", "")
								.equals(tInput[0].getText()))
						&& (tInput[1].getText().trim()
								.replaceAll("[0-9]", "")
								.equals(tInput[1].getText()))
						&& (tInput[3].getText().trim()
								.replaceAll("[a-zA-Z]", "")
								.equals(tInput[3].getText()))) {
					accounts.repaint();
					int number = Integer
							.parseInt(tInput[0].getText().trim());
					String own = tInput[1].getText().trim();
					double bal = Double
							.parseDouble(tInput[3].getText().trim());
					if (checking.isSelected()) {
						String str = tInput[4].getText().trim();
						if ((!str.equals(""))
								&& (str.replaceAll("[a-zA-Z]", "")
										.equals(str))) {
							double fee = Double.parseDouble(
									tInput[4].getText().trim());
							String dInput = tInput[2].getText().trim();
							Date date;
							try {
								SimpleDateFormat format = new SimpleDateFormat(
										"MM/dd/yyyy");
								date = format.parse(dInput);
								GregorianCalendar cal = new GregorianCalendar();
								cal.setTime(date);
								CheckingAccount c = new CheckingAccount(
										number, own, cal, bal, fee);
								ld.addElement(c);
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(null,
										"Date format must match MM/dd/yyyy");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Opps! Something went wrong.");
						}
					} else if (savings.isSelected()) {
						String s = tInput[5].getText().trim();
						String d = tInput[6].getText().trim();
						if ((!s.equals("")) && (!d.equals(""))
								&& (s.replaceAll("[a-zA-Z]", "")
										.equals(s))
								&& (d.replaceAll("[a-zA-Z]", "")
										.equals(d))) {

							double mBal = Double.parseDouble(
									tInput[5].getText().trim());
							double r = Double.parseDouble(
									tInput[6].getText().trim());
							String dInput = tInput[2].getText().trim();
							SimpleDateFormat format = new SimpleDateFormat(
									"MM/dd/yyyy");
							Date date;
							try {
								date = format.parse(dInput);
								GregorianCalendar cal = new GregorianCalendar();
								cal.setTime(date);
								SavingsAccount c = new SavingsAccount(
										number, own, cal, bal, mBal, r);
								ld.addElement(c);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Opps! Something went wrong.");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Please select one of the check boxes");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Opps! Something went wrong.");
				}
			}
			if (e.getSource() == delete) {
				accounts.repaint();
				ld.delete(accounts.getSelectedValue());
			}
			if (e.getSource() == accountN) {
				ld.sort();
			} else if (e.getSource() == accountO) {

			} else if (e.getSource() == dateO) {

			}
			if (e.getSource() == quitItem) {
				System.exit(0);
			}
			if (e.getSource() == saveText) {
				if (accounts.getSelectedIndex() != -1) {
					String play = JOptionPane.showInputDialog(null,
							"Name of File");
					ld.saveText(play, accounts.getSelectedValue());
				}
			}
		}
	}

	private JPanel labs() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.EAST;

		GridBagConstraints b = new GridBagConstraints();
		b.gridx = 1;
		b.gridy = GridBagConstraints.RELATIVE;
		b.weightx = 1.0;
		b.fill = GridBagConstraints.HORIZONTAL;
		b.anchor = GridBagConstraints.CENTER;

		label = new JLabel[7];
		label[0] = new JLabel("Account Number: ");
		label[1] = new JLabel("Account Owner: ");
		label[2] = new JLabel("Date Opened: ");
		label[3] = new JLabel("Account Balance: ");
		label[4] = new JLabel("Monthly Fee: ");
		label[5] = new JLabel("Minimum Balance: ");
		label[6] = new JLabel("Interest Rate: ");

		tInput = new JTextField[7];
		for (int i = 0; i < label.length; i++) {
			tInput[i] = new JTextField(20);

			panel.add(label[i], c);
			label[i].setLabelFor(tInput[i]);
			panel.add(tInput[i], b);
		}

		return panel;
	}

	public JMenuBar setupMenu() {
		ButtonListener ml = new ButtonListener();
		menu = new JMenuBar();

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menu.add(fileMenu);

		sortMenu = new JMenu("Sort");
		sortMenu.setMnemonic(KeyEvent.VK_F);
		menu.add(sortMenu);

		quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(ml);

		loadBin = new JMenuItem("Load From Binary...");
		saveBin = new JMenuItem("Save As Binary...");

		loadText = new JMenuItem("Load From Text...");
		saveText = new JMenuItem("Save As Text...");

		loadXML = new JMenuItem("Load From XML...");
		saveXML = new JMenuItem("Save As XML...");

		loadBin.addActionListener(ml);
		saveBin.addActionListener(ml);
		loadText.addActionListener(ml);
		saveText.addActionListener(ml);
		loadXML.addActionListener(ml);
		saveXML.addActionListener(ml);

		fileMenu.add(loadBin);
		fileMenu.add(saveBin);
		fileMenu.addSeparator();
		fileMenu.add(loadText);
		fileMenu.add(saveText);
		fileMenu.addSeparator();
		fileMenu.add(loadXML);
		fileMenu.add(saveXML);
		fileMenu.addSeparator();
		fileMenu.add(quitItem);

		accountN = new JMenuItem("By Account Number");
		accountO = new JMenuItem("By Account Owner");
		dateO = new JMenuItem("By Date Opened");

		accountN.addActionListener(ml);
		accountO.addActionListener(ml);
		dateO.addActionListener(ml);

		sortMenu.add(accountN);
		sortMenu.add(accountO);
		sortMenu.add(dateO);

		return menu;
	}
}
