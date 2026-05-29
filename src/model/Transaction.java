package model;

import java.io.Serializable;

public class Transaction implements Serializable{
	private int id;
	private double amount;
	private Category cat;
	private TransactionType type;
	
	private static final long serialiVersionUID = 1L;
	public Transaction(int id,double amount,Category cat,TransactionType type) {
		this.id = id;
		this.amount=amount;
		this.cat=cat;
		this.type=type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType GetTransactionType() {
		return type;
	}
}
