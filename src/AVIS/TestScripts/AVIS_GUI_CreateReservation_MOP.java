
package AVIS.TestScripts;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//import java.util.concurrent.TimeUnit;

import org.apache.poi.EmptyFileException;
import org.apache.xmlbeans.impl.piccolo.io.IllegalCharException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.gui.report.Extentmanager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.gui.report.Extentmanager;
import AVIS.CommonFunctions.GUIFunctions;
import AVIS.CommonFunctions.ReadWriteExcel;

/**
 * '#############################################################################################################################
 * SCRIPT NAME     : AVIS_GUI_CreateReservation_MOP
 * BRAND           : AVIS
 * DESCRIPTION     : Create Reservation with different Method of Payments.
 * FUNCTIONAL AREA : Reservation Rates Screen.
 * PRECONDITION    : All the required Test Data should be available in Test Data Sheet.
 * OUTPUT          : Reservation should be created successfully.
 * '#############################################################################################################################
 **/

public class AVIS_GUI_CreateReservation_MOP
{
	public void clickRateshopSearchBtn(ChromeDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String clickSearchJS = "document.getElementById('searchCommandLinkResRateCode').click()";
		jse.executeScript(clickSearchJS);
	}

	ExtentReports extent;
	ExtentTest test;
//
	@BeforeTest
	public void startReport()
	{
		extent = Extentmanager.GetExtent();
		//test = extent.createTest("GUI");
	}

	 //public static void main(String[] args) throws IllegalCharException, EmptyFileException;
	// FileNotFoundException {
	//@Test
	public void test() throws Exception {
		try {

			// Read input from excel
			for (int k = 1; k <= 30; k++) {
				AVIS_GUI_CreateReservation_MOP avis = new AVIS_GUI_CreateReservation_MOP();
				ReadWriteExcel rwe = new ReadWriteExcel(
						"C:\\Selenium\\TestData\\AVIS_GUITestData_CreateReservation_MOP.xlsx");
				String Execute = rwe.getCellData("Avis_GUI", k, 2);

				if (Execute.equals("Y")) {
					System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
					ChromeDriver driver = new ChromeDriver();
					GUIFunctions functions = new GUIFunctions(driver);
					int a = 26;
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					System.out.println(" iteration " + k);
					String testCaseName     = rwe.getCellData("Avis_GUI", k, 4);
					String clientURL        = rwe.getCellData("Avis_GUI", k, 6);
					String outSTA           = rwe.getCellData("Avis_GUI", k, 7);
					String thinClient       = clientURL + outSTA;
					String uName            = rwe.getCellData("Avis_GUI", k, 8);
					String pswd             = rwe.getCellData("Avis_GUI", k, 9);
					String lstname          = rwe.getCellData("Avis_GUI", k, 10);
					String fstname          = rwe.getCellData("Avis_GUI", k, 11);
					String codte            = rwe.getCellData("Avis_GUI", k, 12);
					String cotme            = rwe.getCellData("Avis_GUI", k, 13);
					String insta            = rwe.getCellData("Avis_GUI", k, 14);
					String cidte            = rwe.getCellData("Avis_GUI", k, 15);
					String citme            = rwe.getCellData("Avis_GUI", k, 16);
					String carGrp           = rwe.getCellData("Avis_GUI", k, 17);
					String otherMOP         = rwe.getCellData("Avis_GUI", k, 18);
					String cardname         = rwe.getCellData("Avis_GUI", k, 19);
					String cardNumber       = rwe.getCellData("Avis_GUI", k, 20);
					String expireMonth      = rwe.getCellData("Avis_GUI", k, 21);
					String expireYear       = rwe.getCellData("Avis_GUI", k, 22);
					String reason           = rwe.getCellData("Avis_GUI", k, 23);

					/* Open GUI URL's */
					functions.openURL(thinClient);

					/* Login */
					functions.login(uName, pswd);
					functions.navigateToTab("ReservationRates");
					Thread.sleep(500);

					/* Enter Customer Informations */
					functions.enterCustomerInformation(lstname, fstname, codte, cotme, insta, cidte, citme);

					/* Select car group */
					functions.selectCarGroupByVT(carGrp);
					Thread.sleep(2000);

					/* Enter AWD */
					avis.clickRateshopSearchBtn(driver);
					ArrayList<WebElement> radio = (ArrayList<WebElement>) driver
							.findElements(By.xpath("//input[@name='radioRate' and @type='radio']"));
					try {
						for (int i = 0; i < radio.size(); i++) {
							if (radio.get(i).isDisplayed()) {
								if (radio.get(i).isEnabled()) {
									radio.get(i).click();
									if (radio.get(i).isSelected()) {
										break;
									}
								}
							}
						}
					} catch (Exception e) {
						driver.findElement(By.id("cancelButton")).click();
						Thread.sleep(2000);
						functions.selectCarGroupByIndex(0);
						avis.clickRateshopSearchBtn(driver);
						for (int l = 0; l < radio.size(); l++) {
							if (radio.get(l).isDisplayed()) {
								if (radio.get(l).isEnabled()) {
									radio.get(l).click();
									if (radio.get(l).isSelected()) {
										break;
									}
								}
							}
						}
					}

					Thread.sleep(4000); //
					functions.clickSelectRateBtn();
					Thread.sleep(5000);

					/* Enter MOP details */
					functions.expandPaymentInfoSection();
					if (otherMOP.equalsIgnoreCase("Credit Card/Debit Card")) {
						functions.enterPaymentInformations(cardname, cardNumber, expireMonth, expireYear, reason);
					} else {
						functions.enterOtherMOP(otherMOP);

					}

					/* Create reservation */
					functions.clickCreateReservationBtn();
					Thread.sleep(1000);
					functions.ScreenCapture("C:\\Selenium\\GUI_Results\\Avis_CR\\", testCaseName);
					String Resmsg = driver
							.findElement(By.xpath("//form[@id='templateInfoForm']//div[@class='modal-body']"))
							.getText();
					rwe.setCellData("Avis_GUI", k, 39, Resmsg); // write
																// respopup in
																// excel
					Thread.sleep(1000);
					driver.findElement(By
							.xpath("//div[@class='modal-footer']//div[@class='row']//div[@class='col-md-12 text-left']//button[@value='OK']"))
							.click();

					/* to print QV data in Excel */

					WebElement res = driver.findElement(By.cssSelector(
							"#quickViewPanel > div.panel-body > table > tbody > tr:nth-child(8) > td > div:nth-child(2) > div > table > tbody > tr > td:nth-child(2) > a > span"));

					try {
						if (res.getText().isEmpty()) {
							WebDriverWait wait1 = new WebDriverWait(driver, 20);
							wait1.until(ExpectedConditions.visibilityOf(res));
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					WebElement table = driver.findElement(By.id("resQvForm"));
					ArrayList<WebElement> rows = (ArrayList<WebElement>) table.findElements(By.tagName("tr"));
					for (int i = 1; i < rows.size(); i++) {
						ArrayList<WebElement> cells = (ArrayList<WebElement>) rows.get(i)
								.findElements(By.tagName("td"));
						for (int j = 0; j < cells.size(); j++) {
							String val = cells.get(j).getText();
							if (val.isEmpty()) {
								break;
							} else {
								if (j == 2) {
									val = val.replaceAll("[*]", ""); // Remover
																		// '*'
																		// before
																		// rates
									rwe.setCellData("Avis_GUI", k, a, val);
									a++;
								}
							}
						}
					}

					/* Log out and close tabs */

					functions.logout();
					Thread.sleep(1000);
					functions.closeWindows();
					
					test = extent.createTest(testCaseName);

					if (rwe.getCellData("Avis_GUI", k, 26).isEmpty()) {
						rwe.setCellData("Avis_GUI", k, 40, "FAIL");
						test.log(Status.FAIL, "Fail");
					} else {
						test.log(Status.PASS, "Pass");
						rwe.setCellData("Avis_GUI", k, 40, "PASS");
					}
				} else {
					System.out.println("Execution status is N for iteration " + k + "...");
				}

			}
		} finally {
			// TODO: handle finally clause
			extent.flush();
		}

	}

}
