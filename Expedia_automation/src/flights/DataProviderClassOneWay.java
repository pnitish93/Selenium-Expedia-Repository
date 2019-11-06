package flights;

import org.testng.annotations.DataProvider;

public class DataProviderClassOneWay {
	@DataProvider(name = "searchInputs")
	public Object[][] sendInputs() {
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "FlightsWithHotelsPositive");
		Object[][] objectList = ExcelUtility.getCellData("OneWayHotels");
		return objectList;
	}
	@DataProvider(name = "searchInputsOnlyFlights")
	public Object[][] sendInputsOnlyFlights() {
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "FlightsOnlyPositive");
		Object[][] objectList = ExcelUtility.getCellData("One-way");
		return objectList;
	}
}
