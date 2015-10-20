package BankApplication;

import java.io.*;
import java.util.*;

import javax.swing.*;

public class ExampleModel extends AbstractListModel {
	private ArrayList<Account> acts;

	public ExampleModel(){
		acts = new ArrayList<Account>();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return acts.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return acts.get(index);
	}

	public void addElement(Account other){
		acts.add(other);
		fireIntervalAdded(acts, acts.size() - 1,acts.size() - 1);
	}

	public void delete(Account other){
		if(other instanceof CheckingAccount){
			acts.remove(other);
			fireIntervalRemoved(acts, 0, acts.size());
		}
		if(other instanceof SavingsAccount){
			acts.remove(other);
			fireIntervalRemoved(acts, 0, acts.size());
		}
	}

	public void update(Account other){

	}

	public void sort(){
		//Collections.sort(acts);
		fireContentsChanged(this, 0, acts.size());
	}

	public void saveText(String name, Account other){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(name + ".txt")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		out.println(other);
		out.close(); 
	}

	public void loadText(String name){
		try{
			// open the data file
			Scanner fileReader = new Scanner(new File(name + ".txt")); 
			
		}

			// could not find file
		catch(FileNotFoundException error) {
			System.out.println("File not found ");
		}

			// problem reading the file
		catch(IOException error){
			System.out.println("Oops!  Something went wrong.");
		}

	}
}