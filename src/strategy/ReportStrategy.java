package strategy;

import java.util.List;

import model.Transaction;
// this interface is used to implement Strategy design pattern
public interface ReportStrategy {
	
	String generateReport(List<Transaction> transactions);

}
