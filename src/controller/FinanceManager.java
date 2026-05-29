package controller;

import java.util.ArrayList;

import java.util.List;

import model.Transaction;

import observer.Subscriber;

import persistence.Repository;

// FinanceManager acts a the controller of the application it manages all the business logic
public class FinanceManager {
	
	private List<Transaction> transactions;
	private List<Subscriber> subscribers = new ArrayList<>();
	private Repository repo = new Repository();
	
	public FinanceManager() {
		transactions = repo.load();
		notifySubscribers();
	}
	
	public void addSubscriber(Subscriber s) {
		subscribers.add(s);
	}
	
	// notifies all subscribers when data changes
	public void notifySubscribers() {
		for(Subscriber s: subscribers) {
			s.update(transactions);
		}
	}
	// adds a transaction to the system
	public void addTransaction(Transaction t) {
		transactions.add(t);
		repo.save(transactions);
		notifySubscribers();
	}
	
	// deletes transaction based on Id
	public void deleteTransaction(int transactionId) {
		transactions.removeIf(t -> t.getId() == transactionId);
		repo.save(transactions);
		notifySubscribers();
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	// calculates the balance income type gets added and expense will get subtracted
	public double getBalance() {
		double bal = 0;
		for(Transaction t : transactions) {
			if(t.GetTransactionType() == model.TransactionType.INCOME) {
				bal += t.getAmount();
			}
			else {
				bal -= t.getAmount();
			}
		}
		return bal;
	}
	
}
