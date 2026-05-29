package command;

import controller.FinanceManager;

public class DeleteTransaction implements Command{
	
	private FinanceManager manager;
	private int transactionId;
	
	public DeleteTransaction(FinanceManager manager, int transactionId) {
		this.manager=manager;
		this.transactionId = transactionId;
	}
	
	// implementing the undefined execute method in command interface to delete a transaction
	public void execute() {
		manager.deleteTransaction(transactionId);
	}
}
