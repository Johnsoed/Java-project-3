package BankApplication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class BankModel extends AbstractTableModel implements
java.io.Serializable {
	/** names of the columns*/
	protected String[] columnNames;
	/** vector that stores table information*/
    protected Vector dataVector;

	/*****************************************************************
	constructor for Bank Model, initiates the column names and vector
	*****************************************************************/
    public BankModel(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector();
    }
    
	/*****************************************************************
	 * gets column names
	 * @returns column names in string format
	*****************************************************************/
    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

	/*****************************************************************
	 * gets number of columns
	 * @returns number of columns in int form
	*****************************************************************/
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	/*****************************************************************
	 * gets number of rows
	 * @returns number of rows in int form
	*****************************************************************/
	@Override
	public int getRowCount() {
		 return dataVector.size();
	}

	/*****************************************************************
	 * gets value at selected location
	 * @param int row, row number on table
	 * @param int columns, column number on table
	 * @returns returns different thing based on the column, the case
	 * returns number for case 0, account number column
	 * returns date for case 1, the date column
	 * returns owner name for owner column
	 * returns balance in currency for for balance column
	 * depending on whether the account is a savings for checking,
	 * returns either monthly fee at or interest and minimum balance
	*****************************************************************/
	@Override
	public Object getValueAt(int row, int column) {
		Account yourAccount = (Account)dataVector.get(row);
		switch (column) {
		case 0:
			return yourAccount.getNumber();
		case 1:

			return DateFormat.getDateInstance(DateFormat.SHORT)
					.format(yourAccount.getDateOpened().getTime());
		case 2: 
			return yourAccount.getOwner();
		case 3: 
			return NumberFormat.getCurrencyInstance().format(yourAccount.getBalance());
		case 4:
			return yourAccount.outPut();
			//outPut differs depending on account type
		
		default: 
			return new Object(); 
		}
				}
	
	/*****************************************************************
	 * adds new account
	 * @param Account object to add to table
	*****************************************************************/
	public void add(Account Other) {
		dataVector.add(Other);
		fireTableRowsInserted(dataVector.size() - 1
				,dataVector.size() - 1);
	}
	
	/*****************************************************************
	 * deletes selected account at row
	 * @param row to delete 
	*****************************************************************/
	public void delete(int row) {
		try {
		dataVector.remove(row);
		fireTableRowsDeleted(dataVector.size() - 1
				,dataVector.size() - 1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null,"Account not selected");
			//alerts user if delete is selected without an account
			// being selected
		}
	}

	/*****************************************************************
	 * disables cells from being manually editable
	 * @param row and column
	*****************************************************************/
    public boolean isCellEditable(int row, int col){
    	return false; 
    	}
    
	/*****************************************************************
	 * updates value at selected row and column
	 * @param row
	 * @param column
	 * @param update stuff is what is put into the cell , received in
	 * string form and converted depending on the case
	*****************************************************************/
	public void update(int row, int column, String updateStuff) {
		Account yourAccount = (Account)dataVector.get(row);
		try {
		switch (column) {
		case 0:
		
			int aNumber;
			aNumber = Integer.parseInt(updateStuff);
			yourAccount.setNumber(aNumber);
			fireTableDataChanged();
			break;
		
		case 1:
		
	
			JOptionPane.showMessageDialog(null,""
					+ "Date opened can not be changed");
			break;
			
		
		case 2:
			if (updateStuff.length() > 1) {
			yourAccount.setOwner(updateStuff);
			fireTableDataChanged();
			break;
			//doesn't change if string is empty
			}
		
		case 3:
		{
			double bNumber;
			bNumber = Double.parseDouble(updateStuff);
			yourAccount.setBalance(bNumber);
			fireTableDataChanged();
			break;
		}
			
		case 4: 

		{
			if (yourAccount.hasMonthlyFee() == true) {
				double fNumber;
				fNumber = Double.parseDouble(updateStuff);
				((CheckingAccount) yourAccount).setMonthlyFee(fNumber);
				fireTableDataChanged();
			}
			else {
				JOptionPane.showMessageDialog(null,""
						+ "No monthly fee for savings account");	
				
				}
		
		break;
		}
		case 5: 
			double fNumber;
			fNumber = Double.parseDouble(updateStuff);
			((SavingsAccount) yourAccount).setInterestRate(fNumber);
			fireTableDataChanged();
			break;
		
		case 6:
			fNumber = Double.parseDouble(updateStuff);
			((SavingsAccount) yourAccount).setMinBalance(fNumber);
			fireTableDataChanged();
			break;

		}
		}
		catch (NumberFormatException e) {
			//catches any incorrect inputs for ints or doubles
			JOptionPane.showMessageDialog(null,""
					+ "not a valid value");
			
		}
		
		
	}
	
	/*****************************************************************
	sorts table in descending alphabetical order
	@param object a   account object a for comparator
	@param object b   second account object for comparator 
	*****************************************************************/
	@SuppressWarnings("unchecked")
	public void sortName() {
		Collections.sort(dataVector, new Comparator() {
				@Override
			  public int compare(Object a, Object b) {
				  return ((Account) a).getOwner().compareTo
						  (((Account) b).getOwner());
			  }
		});
		fireTableDataChanged();
		}
	
	/*****************************************************************
	sorts table using account number, from small to large
	@param object a   account object a for comparator
	@param object b   second account object for comparator 
	*****************************************************************/
	@SuppressWarnings("unchecked")
	public void sortAccount() {
		Collections.sort(dataVector, new Comparator() {
				@Override
			  public int compare(Object a, Object b) {
					int o = ((Account) a).getNumber();
					int i = ((Account) b).getNumber(); 
					if (o>i)
						return 1;
					if (i>0)
						return -1;
					else 
						return 0;
			  }
		});
		fireTableDataChanged();
		}
	
	/*****************************************************************
	sorts table using date, from earliest to latest
	@param object a   account object a for comparator
	@param object b   second account object for comparator 
	*****************************************************************/
	@SuppressWarnings("unchecked")
	public void sortDate() {
		Collections.sort(dataVector, new Comparator() {
			  @Override
			  public int compare(Object a, Object b) {
					Calendar date1 = ((Account) a).getDateOpened();
//					System.out.print(date1);
					Calendar date2 = ((Account) b).getDateOpened();
//					System.out.print(date2);
					if (date1.before(date2)) 
							return -1;					
					if (date1.after(date2)) 
							return 1;
					else {
						return 0;
					}


			  }
		});
		fireTableDataChanged();
		}
	
	
	/*****************************************************************
	Tells if an account at row is a savings account or not
	@param row row of account 
	@returns returns true if account at row is savings, false if it
	isn't 
	*****************************************************************/
	public boolean isSavings( int row) {
		Account yourAccount = (Account)dataVector.get(row);
		if (yourAccount.hasMonthlyFee() == false) 
			return true; 
		else 
			return false; 
	
	}
	
	/*****************************************************************
	Saves the dataVector vector object to a binary file
	*****************************************************************/
	public void saveBinary() {
		try {
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new 
			FileOutputStream("myobject.data");

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new
			ObjectOutputStream (f_out);

		// Write object out to disk
		obj_out.writeObject ( dataVector );
		}
		catch(IOException e) {
			
		}
	}

	/*****************************************************************
	loads the dataVector vector object from a binary file, and then
	sets the table using the information from it.
	*****************************************************************/
	public void loadBinary() {
		Object obj = null;
		try {
		// Read from disk using FileInputStream
		FileInputStream f_in = new 
			FileInputStream("myobject.data");

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = 
			new ObjectInputStream (f_in);

		// Read an object
		 obj = obj_in.readObject();
		}
		catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (obj instanceof Vector)
		{
			// Cast object to a Vector
			dataVector = (Vector) obj;
			fireTableChanged(null);
			// Do something with vector....
		}
	
	
	}
	
	
	public void saveTable()throws Exception
	{
	   BufferedWriter bfw = new BufferedWriter(new FileWriter("Data.txt"));
	   for(int i = 0 ; i < dataVector.size() ; i++)
	   {
	      //gregoriancalandar have to be split so that they can be
	      //added later on easily
	      Account yourAccount = (Account)dataVector.get(i);
	      bfw.write(yourAccount.getNumber() + "|");
	      GregorianCalendar date = yourAccount.getDateOpened();
	      bfw.write((date.get(Calendar.MONTH) + 1) + "|");
	      bfw.write(date.get(Calendar.DATE) + "|");
	      bfw.write(date.get(Calendar.YEAR) + "|");
	      bfw.write(yourAccount.getOwner() + "|");
	      bfw.write(yourAccount.getBalance() + "|");
	      if (yourAccount.hasMonthlyFee() == true){
	         bfw.write(((CheckingAccount) yourAccount).getMonthlyFee() + "");
	         bfw.newLine();
	      }else{
	         bfw.write(((SavingsAccount) yourAccount).getMinBalance() + "|");
	         bfw.write(((SavingsAccount) yourAccount).getInterestRate() + "");
	         bfw.newLine();
	      }
	   }

	   bfw.close();
	}

	public void loadTable() throws Exception{
	   dataVector.clear();
	   Scanner fileReader = new Scanner(new File("Data.txt"));
	   while(fileReader.hasNextLine()) {           //reads each line and see if there is next()
	      String line = fileReader.nextLine();      //reads each line in the text file
	      String[] tokens = line.split("\\|");      // splits that string line by |
	      int n = Integer.parseInt(tokens[0]);      //changes to int from first object in string array
	      int m = Integer.parseInt(tokens[1]);      //adds mm/dd/yyyy to int and into gregoriancalandar
	      int d = Integer.parseInt(tokens[2]);
	      int y = Integer.parseInt(tokens[3]);
	      GregorianCalendar date = new GregorianCalendar(y, m - 1, d);
	      String name = tokens[4];               //adds the owner name
	      Double balance = Double.parseDouble(tokens[5]);
	      if (tokens.length == 7) {
	         Double monthly = Double.parseDouble(tokens[6]);
	         CheckingAccount check = new CheckingAccount(n, name, date, balance, monthly);
	         dataVector.add(check);
	      } else {
	         Double minBal = Double.parseDouble(tokens[6]);
	         Double r = Double.parseDouble(tokens[7]);
	         SavingsAccount save = new SavingsAccount(n, name, date, balance, minBal, r);
	         dataVector.add(save);
	      }
	   }
	   fireTableDataChanged();
	   //reading whats inside the text file may help what is going on.
	}

	public void saveXML(){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Account");
			doc.appendChild(rootElement);

			for (int i = 0; i < dataVector.size(); i++){
				Account yourAccount = (Account)dataVector.get(i);
				Element account = doc.createElement("AccountNumber");
				rootElement.appendChild(account);
				account.setAttribute("id", String.valueOf(yourAccount.getNumber()));
				if (yourAccount.hasMonthlyFee() == true){
					account.setAttribute("Checking", (i + 1) + "");
				}else {
					account.setAttribute("Savings", (i + 1) + "");
				}

				//Gregorian calendar
				GregorianCalendar date = yourAccount.getDateOpened();
				Element month = doc.createElement("Month");
				month.appendChild(doc.createTextNode(String.valueOf(date.get(Calendar.MONTH) + 1)));
				account.appendChild(month);

				Element dateOpen = doc.createElement("Date");
				dateOpen.appendChild(doc.createTextNode(String.valueOf(date.get(Calendar.DATE))));
				account.appendChild(dateOpen);

				Element year = doc.createElement("Year");
				year.appendChild(doc.createTextNode(String.valueOf(date.get(Calendar.YEAR))));
				account.appendChild(year);

				//account Owner element
				Element accOwner = doc.createElement("Owner");
				accOwner.appendChild(doc.createTextNode(yourAccount.getOwner()));
				account.appendChild(accOwner);

				//Account Number element
				Element accBal = doc.createElement("Balance");
				accBal.appendChild(doc.createTextNode(String.valueOf(yourAccount.getBalance())));
				account.appendChild(accBal);

				if(yourAccount.hasMonthlyFee() == true) {
					Element accFee = doc.createElement("Fee");
					accFee.appendChild(doc.createTextNode(String.valueOf(((CheckingAccount) yourAccount).getMonthlyFee())));
					account.appendChild(accFee);
				}else{
					Element accMin = doc.createElement("MinimumBalance");
					accMin.appendChild(doc.createTextNode(String.valueOf(((SavingsAccount) yourAccount).getMinBalance())));
					account.appendChild(accMin);

					Element accRate = doc.createElement("InterestRate");
					accRate.appendChild(doc.createTextNode(String.valueOf(((SavingsAccount) yourAccount).getInterestRate())));
					account.appendChild(accRate);
				}
			}

			//write contents to xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer trans = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("data.xml"));

			trans.transform(source, result);
			System.out.println("File Saved!");

		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (TransformerException t){
			t.printStackTrace();
		}
	}

	public void loadXML(){
		try {
			dataVector.clear();
			File file = new File("data.xml");
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = fac.newDocumentBuilder();
			Document doc = build.parse(file);

			NodeList sList = doc.getElementsByTagName("AccountNumber");

			for (int i = 0; i < sList.getLength(); i++){
				Node nNode = sList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;
					System.out.println(e.getNodeValue());
					int number = Integer.parseInt(e.getAttribute("id"));
					int m = Integer.parseInt(e.getElementsByTagName("Month").item(0).getTextContent());
					int d = Integer.parseInt(e.getElementsByTagName("Date").item(0).getTextContent());
					int y = Integer.parseInt(e.getElementsByTagName("Year").item(0).getTextContent());
					GregorianCalendar date = new GregorianCalendar(y, m - 1, d);
					String o = e.getElementsByTagName("Owner").item(0).getTextContent();
					Double b = Double.parseDouble(e.getElementsByTagName("Balance").item(0).getTextContent());
					if (e.hasAttribute("Checking")) {
						Double f = Double.parseDouble(e.getElementsByTagName("Fee").item(0).getTextContent());
						CheckingAccount check = new CheckingAccount(number, o, date, b, f);
						dataVector.add(check);
					} else {
						Double mb = Double.parseDouble(e.getElementsByTagName("MinimumBalance").item(0).getTextContent());
						Double r = Double.parseDouble(e.getElementsByTagName("InterestRate").item(0).getTextContent());
						SavingsAccount save = new SavingsAccount(number, o, date, b, mb, r);
						dataVector.add(save);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fireTableDataChanged();
		}
	}
}







