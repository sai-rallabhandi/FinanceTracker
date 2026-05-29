package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import command.AddTransactionCommand;
import command.Command;
import command.DeleteTransaction;
import config.FinanceConfig;
import controller.FinanceManager;
import model.Category;
import model.Transaction;
import model.TransactionType;
import observer.Subscriber;

import strategy.*;

public class MainFrame extends JFrame implements Subscriber{
	
	private FinanceManager manager = new FinanceManager();
	private JTextField amount;
	private JComboBox<Category> category;
	private JComboBox<TransactionType> typeOfTransaction;
	private JLabel bal;
	private int id = 1;
	private DefaultTableModel tableModel;
	private JTable table;
	public MainFrame() {
		id = manager.getTransactions().size()+1;
		setTitle("Student Finance Tracker");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		manager.addSubscriber(this);
		userInterface();
		update(manager.getTransactions());
		setVisible(true);
		
	}
	
	/*public void update(List<Transaction> transactions) {
		String symbol = FinanceConfig.getInstance().getCurrencySymbol();	
		bal.setText("Balance : "+ symbol + manager.getBalance());
	}*/
	
	public void update(List<Transaction> transactions) {
		String symbol = FinanceConfig.getInstance().getCurrencySymbol();
		bal.setText("Balance : "+ symbol + String.format("%.2f", manager.getBalance()));
		
		tableModel.setRowCount(0);
		
		for (Transaction t: transactions) {
			tableModel.addRow(new Object[]{
				t.getId(),
				t.getAmount(),
				t.getCat(),
				t.getType()
			});
			
		}
	}
	
	// This method will hold the code for the user interface
	private void userInterface() {
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new GridLayout(2, 4, 5, 5));
		
		amount = new JTextField();
		category = new JComboBox<>(Category.values());
		typeOfTransaction = new JComboBox<>(TransactionType.values());
		
		JButton add = new JButton("Add Transaction");
		JButton delete = new JButton("Delete Selected");
		
		topPanel.add(new JLabel("Amount"));
		topPanel.add(amount);
		
		topPanel.add(new JLabel("Category"));
		topPanel.add(category);
		
		topPanel.add(new JLabel("Transaction Type"));
		topPanel.add(typeOfTransaction);
		
		topPanel.add(add);
		topPanel.add(delete);
		
		add(topPanel, BorderLayout.NORTH);
		
		tableModel = new DefaultTableModel(new String[]{"ID","Amount","Category","Type"},0);
		
		table = new JTable(tableModel);
		
		add(new JScrollPane(table),BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
		bal = new JLabel("Balance : 0 ");
		bal.setHorizontalAlignment(SwingConstants.CENTER);
		
		add.addActionListener(e -> addTransaction());
		delete.addActionListener(e -> deleteTransaction());
		
		JPanel reportPanel = new JPanel();
		JComboBox<String> reportBox = new JComboBox<>(new String[] {"Total Spending","Total Savings"});
		JButton reportButton = new JButton("Generate Report");
		
		reportPanel.add(reportBox);
		reportPanel.add(reportButton);
		
		bottomPanel.add(bal);
		bottomPanel.add(reportPanel);
		
		add(bottomPanel,BorderLayout.SOUTH);
		
		reportButton.addActionListener(e -> {
			
			ReportStrategy report = null;
			
			if(reportBox.getSelectedItem().equals("Total Spending")) {
				report = new TotalSpending();
			}
			else if(reportBox.getSelectedItem().equals("Total Savings")) {
				report = new TotalSavings();
			}
			String result = report.generateReport(manager.getTransactions());
			
			JOptionPane.showMessageDialog(this,result);
		});
		
	}
	
	// this method calls add transaction method in controller
	private void addTransaction() {
		
		try {
			double amt = Double.parseDouble(amount.getText());
			Category cat = (Category) category.getSelectedItem();
			TransactionType type = (TransactionType) typeOfTransaction.getSelectedItem();
			Transaction t = new Transaction(id++,amt,cat,type);
//			manager.addTransaction(t);
			Command command = new AddTransactionCommand(manager,t);
			command.execute();
			
//			bal.setText("Balance : "+ manager.getBalance());
			amount.setText("");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Enter a valid amount");
			
		}
	}
	// this method calls the delete transaction method in controller
	private void deleteTransaction() {
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Select a transaction to delete");
			return;
		}
		
		int id = (int) tableModel.getValueAt(selectedRow, 0);
		Command command = new DeleteTransaction(manager,id);
		command.execute();
	}
}
