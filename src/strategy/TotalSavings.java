package strategy;

import java.util.List;

import model.Transaction;
import model.TransactionType;

public class TotalSavings implements ReportStrategy{
	// undefined method generateReport() in the ReportStrategy interface to generate report on total savings
	public String generateReport(List<Transaction> transactions) {
		
		double income = 0, expense = 0;
		
		for(Transaction t : transactions) {
			if(t.GetTransactionType() == TransactionType.INCOME) {
				income += t.getAmount();
			}
			else if(t.GetTransactionType() == TransactionType.EXPENSE) {
				expense += t.getAmount();
			}
		}
		return "Total Savings : " + (income - expense);
	}

}
