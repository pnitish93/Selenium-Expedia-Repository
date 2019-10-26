package flights;

import org.testng.annotations.DataProvider;

public class DataProviderClassOneWay {
	@DataProvider(name = "searchInputs")
	public Object[][] sendInputs() {
		Object[][] objectList = new Object[][] {{ "Bengaluru", "Kolkata", "21/11/2019",  "21/11/2019", "24/11/2019" }, { "Mumbai", "Delhi", "10/12/2019", "11/12/2019", "14/12/2019"}, { "Hyderabad", "London", "29/11/2019", "30/11/2019", "02/12/2019" }};
		return objectList;
	}
	@DataProvider(name = "searchInputsOnlyFlights")
	public Object[][] sendInputsOnlyFlights() {
		Object[][] objectList = new Object[][] {{ "Bengaluru", "Kolkata", "21/11/2019"}, { "Mumbai", "Delhi", "10/12/2019"}, { "Hyderabad", "London", "29/11/2019"}};
		return objectList;
	}
}
