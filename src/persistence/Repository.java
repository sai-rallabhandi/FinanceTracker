package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

// This class is used to save and load the data using the Serializable classes
public class Repository {
	
	private String fileName = "./transactions.dat";
	
	
	public void save(List<Transaction> transactions) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(transactions);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	// this method is used to load data from transaction.dat file using object streams
	public List<Transaction> load(){
		List<Transaction> transactions = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
			transactions = (List<Transaction>) ois.readObject();
		}
		catch(FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("No data to load ... Starting fresh ");
		}
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

}
