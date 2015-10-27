package BankApplication;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
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
	
	public void add(Account Other) {
		dataVector.add(Other);
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
			break;
		
		case 1:
		
	
			JOptionPane.showMessageDialog(null,""
					+ "Date opened can not be changed");
			break;
			
		
		case 2:
		
		
			yourAccount.setOwner(updateStuff);
			fireTableDataChanged();
			break;
			
		
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
				break;
				}
		
		
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
		fireTableDataChanged();
		}
	
	
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
	
	@SuppressWarnings("unchecked")
	public void sortDate() {
		Collections.sort(dataVector, new Comparator() {
			  @Override
			  public int compare(Object a, Object b) {
					Calendar date1 = ((Account) a).getDateOpened();
					System.out.print(date1);
					Calendar date2 = ((Account) b).getDateOpened();
					System.out.print(date2);
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
	
	
	
	}







