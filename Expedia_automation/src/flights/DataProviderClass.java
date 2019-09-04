package flights;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name = "searchInputs")
	public Object[][] sendInputs() {
		Object[][] objectList = new Object[][] { { "Bengaluru", "Kolkata", "21/09/2019" },
				{ "Mumbai", "Delhi", "06/10/2019" }};
		return objectList;
	}
}
