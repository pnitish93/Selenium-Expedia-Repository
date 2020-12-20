package com.expedia.page_classes;

import org.testng.annotations.DataProvider;

public class DataProviderClassOneWay {
	@DataProvider(name = "searchInputs")
	public Object[][] sendInputs() {
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "FlightsWithHotelsPositive");
		Object[][] objectList = ExcelUtility.getCellData("OneWayHotels");
		return objectList;
	}
	@DataProvider(name = "searchOneWayFlightsPosTravellers")
	public Object[][] searchOneWayFlightsTravellersPos() {
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "OneWayFlightsPosTravellers");
		Object[][] objectList = ExcelUtility.getCellData("OneWayFlightsTravellers");
		return objectList;
	}
	@DataProvider(name = "searchOneWayFlightsPos")
	public Object[][] searchOneWayFlightsPos(){
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "OneWayFlightsPosDefaultDate");
		Object[][] objectList = ExcelUtility.getCellData("OneWayFlightsPos");
		return objectList;
	}
	@DataProvider(name = "searchOneWayFlightsPosDate")
	public Object[][] searchOneWayFlightsPosDate(){
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "OneWayFlightsPosDate");
		Object[][] objectList = ExcelUtility.getCellData("OneWayFlightsPosDate");
		return objectList;
	}
	@DataProvider(name = "searchInputsFlightsAge")
	public Object[][] sendInputsFlightsAge(){
		ExcelUtility.setExcelPath(Constants.path+"\\"+Constants.fileName, "FlightsOnlyPosWAge");
		Object[][] objectList = ExcelUtility.getCellData("OneWayAge");
		return objectList;
	}
}
