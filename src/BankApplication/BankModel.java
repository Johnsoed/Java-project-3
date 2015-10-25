package BankApplication;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class BankModel extends AbstractTableModel {
	protected String[] columnNames;
    protected Vector dataVector;
    private ArrayList<Account> acts;
	

    public BankModel(String[] columnNames) {
        this.columnNames = columnNames;
        acts = new ArrayList<Account>();
        dataVector = new Vector();
    }
    
    
    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		 return dataVector.size();
	}

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
			return yourAccount.getBalance();
		default: 
			return new Object(); 
		}
				}
	
	public void add(Account other) {
		dataVector.add(other);
		fireTableRowsInserted(dataVector.size() - 1
				,dataVector.size() - 1);
	}
	
	public void delete(Account other) {
		dataVector.remove(other);
		fireTableRowsDeleted(dataVector.size() - 1
				,dataVector.size() - 1);
	}

    public boolean isCellEditable(int row, int col){
    	return true; 
    	}
    
	public void update(int row, int column, String updateStuff) {
		Account yourAccount = (Account)dataVector.get(row);
		switch (column) {
		case 0:
			int aNumber;
			aNumber = Integer.parseInt(updateStuff);
			yourAccount.setNumber(aNumber);
			fireTableDataChanged();
		case 1:
			JOptionPane.showMessageDialog(null,""
					+ "Date opened can not be changed");
		case 2:
			yourAccount.setOwner(updateStuff);
			fireTableDataChanged();
		case 3:
			double bNumber;
			bNumber = Double.parseDouble(updateStuff);
			yourAccount.setBalance(bNumber);
			fireTableDataChanged();
			
		case 4:
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
		
		}

		
	}
	@SuppressWarnings("unchecked")
	public void sortName() {
		Collections.sort(dataVector, new Comparator() {
				@Override
			  public int compare(Object a, Object b) {
				  return ((Account) a).getOwner().compareTo
						  (((Account) b).getOwner());
			  }
		});
					
		}
	}







