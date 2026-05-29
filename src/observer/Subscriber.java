package observer;

import java.util.List;

import model.Transaction;
// this interface is used to implement the observer design pattern
public interface Subscriber {
	void update(List<Transaction> transactions);
}
