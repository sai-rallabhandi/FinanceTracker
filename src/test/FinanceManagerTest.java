package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.FinanceManager;
import model.Category;
import model.Transaction;
import model.TransactionType;

class FinanceManagerTest {

	private FinanceManager manager;
	
	// used to clear transactions before every test
	@BeforeEach
	void setup() {
		manager = new FinanceManager();
		manager.getTransactions().clear();
	}
	
	// tests adding a transaction
	@Test
	void testAddTransaction() {
		Transaction t = new Transaction(1,100,Category.FOOD,TransactionType.EXPENSE);
		manager.addTransaction(t);
		assertEquals(1,manager.getTransactions().size());
	}
	
	// tests balance when transaction type is income
	@Test
	void testBalanceIncome() {
		manager.addTransaction(new Transaction(1,2000,Category.OTHER,TransactionType.INCOME));
		assertEquals(2000,manager.getBalance());
	}
	
	// tests balance when transaction type is expense
	@Test
	void testBalanceExpense() {
		manager.addTransaction(new Transaction(1,200,Category.FOOD,TransactionType.EXPENSE));
		manager.addTransaction(new Transaction(2,50,Category.TRANSPORT,TransactionType.EXPENSE));
		assertEquals(-250,manager.getBalance());
	}
	
	// tests the functionality of deleting a transaction
	@Test
	void testDeleteTransaction() {
		manager.addTransaction(new Transaction(1,200,Category.FOOD,TransactionType.EXPENSE));
		manager.deleteTransaction(1);
		assertEquals(0,manager.getTransactions().size());
	}
	
	// tests whether the multiple transactions are added correctly or not
	@Test
	void multipleTransactions() {
		manager.addTransaction(new Transaction(1,200,Category.FOOD,TransactionType.EXPENSE));
		manager.addTransaction(new Transaction(2,300,Category.RENT,TransactionType.EXPENSE));
		manager.addTransaction(new Transaction(3,300,Category.OTHER,TransactionType.INCOME));
		assertEquals(3,manager.getTransactions().size());
	}
}
