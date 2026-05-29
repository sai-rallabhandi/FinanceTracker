package strategy;

import java.util.List;

import model.Transaction;
import model.TransactionType;

public class TotalSpending implements ReportStrategy{
	// undefined method generateReport() in the ReportStrategy interface to generate report on total spending
	public String generateReport(List<Transaction> transactions) {
		
		double totalSpending = 0;
		
		for(Transaction t : transactions) {
			if(t.GetTransactionType() == TransactionType.EXPENSE) {
				totalSpending += t.getAmount(); 
			}
		}
		
		
		return "Total Spendings : " + totalSpending;
		
	}

}
