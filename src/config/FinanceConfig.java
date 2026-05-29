package config;

// Using the FinanceConfig class i implemented singleton pattern to maintain the configuration through out the entire application
public class FinanceConfig {
	
	private static FinanceConfig fcg;
	
	private String currencySymbol="£";
	
	private FinanceConfig() {
		
	}
	
	public static FinanceConfig getInstance() {
		if(fcg == null) {
			return new FinanceConfig();
		}
		
		return fcg;
	}
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}

}
