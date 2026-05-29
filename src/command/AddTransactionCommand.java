package command;

import controller.FinanceManager;
import model.Transaction;

public class AddTransactionCommand implements Command{

	private FinanceManager manager;
	private Transaction transaction;
	
	public AddTransactionCommand(FinanceManager manager, Transaction transaction) {	
		this.manager=manager;
		this.transaction=transaction;
	}
	
	// implementing the undefined execute method in command interface to add a transaction
	public void execute() {
		manager.addTransaction(transaction);
	}
	
}
