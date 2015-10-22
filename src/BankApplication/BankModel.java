package BankApplication;

import java.util.ArrayList;
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
			return yourAccount.getDateOpened();
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
		}

		
	}
	

}



