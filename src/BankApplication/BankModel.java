package BankApplication;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class BankModel extends AbstractTableModel {
	protected String[] columnNames;
    protected Vector dataVector;
	

    public BankModel(String[] columnNames) {
        this.columnNames = columnNames;
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

    public boolean isCellEditable(int row, int col){
    	return true; 
    	}
}



