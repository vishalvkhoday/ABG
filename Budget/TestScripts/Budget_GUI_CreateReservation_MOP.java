/**
 * 
 */
package methods;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pomClasses.GUIFunctions;
import pomClasses.ReadWriteExcel;

/**
 * '#############################################################################################################################
 * SCRIPT NAME      :  Budget_GUI_CreateReservation_MOP 
 * BRAND            :  Budget
 * DESCRIPTION      :  Create Reservation with different Method of Payments. 
 * FUNCTIONAL AREA  :  Reservation Rates Screen 
 * PRECONDITION     :  All the required Test Data should be available in Test Data Sheet. 
 * OUTPUT           :  Reservation should be created successfully.
 * '#############################################################################################################################
 **/

public class Budget_GUI_CreateReservation_MOP
{
	public void clickRateshopSearchBtn(ChromeDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String clickSearchJS = "document.getElementById('searchCommandLinkResRateCode').click()";
		jse.executeScript(clickSearchJS);
	}

	public static void main(String[] args) throws IOException, Exception, FileNotFoundException {
		// Read input from excel
		for (int k = 1; k <= 30; k++)
		{
			Budget_GUI_CreateReservation_MOP avis = new Budget_GUI_CreateReservation_MOP();
			ReadWriteExcel rwe = new ReadWriteExcel("C:\\Selenium\\TestData\\AVIS_GUITestData_CreateReservation_MOP.xlsx");
			String Execute = rwe.getCellData("Avis_GUI", k, 2);
			
			if (Execute.equals("Y"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
				ChromeDriver driver = new ChromeDriver();
				GUIFunctions functions = new GUIFunctions(driver);
				int a = 26;
				//ChromeDriver driver = new ChromeDriver();
				//GUIWebDriverFunctions wdfunctions = new GUIWebDriverFunctions(driver);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				System.out.println(" iteration " + k);
				//String tokenURL    = rwe.getCellData("Avis_GUI", k, 6);
				String clientURL   = rwe.getCellData("Avis_GUI", k, 6);
				String outSTA      = rwe.getCellData("Avis_GUI", k, 7);
				String thinClient  = clientURL+outSTA;
				String uName       = rwe.getCellData("Avis_GUI", k, 8);
				String pswd        = rwe.getCellData("Avis_GUI", k, 9);
				String lstname     = rwe.getCellData("Avis_GUI", k, 10);
				String fstname     = rwe.getCellData("Avis_GUI", k, 11);
				String codte       = rwe.getCellData("Avis_GUI", k, 12);
				String cotme       = rwe.getCellData("Avis_GUI", k, 13);
				String insta       = rwe.getCellData("Avis_GUI", k, 14);
				String cidte       = rwe.getCellData("Avis_GUI", k, 15);
				String citme       = rwe.getCellData("Avis_GUI", k, 16);
				String carGrp      = rwe.getCellData("Avis_GUI", k, 17);
				String otherMOP    = rwe.getCellData("Avis_GUI", k, 18);
				String cardname    = rwe.getCellData("Avis_GUI", k, 19);
				String cardNumber  = rwe.getCellData("Avis_GUI", k, 20);
				String expireMonth = rwe.getCellData("Avis_GUI", k, 21);
				String expireYear  = rwe.getCellData("Avis_GUI", k, 22);
				String reason      = rwe.getCellData("Avis_GUI", k, 23);

				/* Open GUI URL's */
				//System.out.println(" token URL value : " + tokenURL);
				
				functions.link(thinClient);
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
//				if (rwe.getCellData("Avis_GUI", k, 18).isEmpty()) {
					avis.clickRateshopSearchBtn(driver);
					// Select Radio Button for Base Rate
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
//				} // Select available base rate from Standard rate table
//				else {
////					functions.enterAWD(awd);
//					avis.clickRateshopSearchBtn(driver);
//					WebElement ratetabel = driver.findElement(By.xpath("//*[@id='resRateLookupDlg:rateLookupForm:discountAwdRates']/table"));
//					ArrayList<WebElement> radio1 = (ArrayList<WebElement>) ratetabel.findElements(By.xpath("//input[@name='radioRate' and @type='radio']"));
//					try {
//						for (int i = 0; i < radio1.size(); i++) {
//							if (radio1.get(i).isDisplayed()) {
//								if (radio1.get(i).isEnabled()) {
//									radio1.get(i).click();
//									if (radio1.get(i).isSelected()) {
//										break;
//									}
//								}
//							}
//						}
//					}
//					// Select available base rate from Standard rate table
//					catch (Exception e1) {
//						driver.findElement(By.id("cancelButton")).click();
//						functions.selectCarGroupByIndex(0);
//						avis.clickRateshopSearchBtn(driver);
//						for (int l = 0; l < radio1.size(); l++) {
//							if (radio1.get(l).isDisplayed()) {
//								if (radio1.get(l).isEnabled()) {
//									radio1.get(l).click();
//									if (radio1.get(l).isSelected()) {
//										break;
//									}
//								}
//							}
//						}
//					}
//				}
				Thread.sleep(4000); //
				functions.clickSelectRateBtn();
				Thread.sleep(5000);

				/* Enter MOP details */
				functions.expandPaymentInfoSection();
				if (otherMOP.equalsIgnoreCase("Credit Card/Debit Card"))
  				{
  					functions.enterPaymentInformations(cardname, cardNumber, expireMonth, expireYear, reason);	
  				} 
  				else 
  				{
  					functions.enterOtherMOP(otherMOP);
  					
  				}

				/* Add Insurances */
				Thread.sleep(5000);
//				functions.expandProtectionCoverageSection();
//				if (rwe.getCellData("Avis_GUI", k, 25).isEmpty()) {
//					System.out.print("No Insurance selected");
//				} else {
//					String[] insVal = rwe.getCellData("Avis_GUI", k, 25).split("-");
//					for (String e : insVal) {
//						WebDriverWait wait1 = new WebDriverWait(driver, 10);
//						if (e.equalsIgnoreCase("LDW")) {
//							WebElement insurace1 = driver
//									.findElement(By.id("menulist:rateshopContainer:resForm:coverageLdwYesNo"));
//							Select insLDW = new Select(insurace1);
//							wait1.until(ExpectedConditions.visibilityOf(insurace1));
//							if (insurace1.isDisplayed()) {
//								insLDW.selectByVisibleText("Yes");
//							} else {
//								break;
//							}
//
//						} else if (e.equalsIgnoreCase("PAI")) {
//							WebElement insurace2 = driver
//									.findElement(By.id("menulist:rateshopContainer:resForm:coveragePaiYesNo"));
//							Select insPAI = new Select(insurace2);
//							wait1.until(ExpectedConditions.visibilityOf(insurace2));
//							if (insurace2.isDisplayed()) {
//								insPAI.selectByVisibleText("Yes");
//							} else {
//								break;
//							}
//						} else if (e.equalsIgnoreCase("PEP")) {
//							WebElement insurace3 = driver
//									.findElement(By.id("menulist:rateshopContainer:resForm:coveragePepYesNo"));
//							Select insPEP = new Select(insurace3);
//							wait1.until(ExpectedConditions.visibilityOf(insurace3));
//							if (insurace3.isDisplayed()) {
//								insPEP.selectByVisibleText("Yes");
//							} else {
//								break;
//							}
//
//						} else if (e.equalsIgnoreCase("ALI")) {
//							WebElement insurace4 = driver
//									.findElement(By.id("menulist:rateshopContainer:resForm:coverageAliYesNo"));
//							Select insALI = new Select(insurace4);
//							wait1.until(ExpectedConditions.visibilityOf(insurace4));
//							if (insurace4.isDisplayed()) {
//								insALI.selectByVisibleText("Yes");
//							} else {
//								break;
//							}
//						} else if (e.equalsIgnoreCase("FSO")) {
//							WebElement insurace5 = driver
//									.findElement(By.id("menulist:rateshopContainer:resForm:fuelServiceOption"));
//							Select insFSO = new Select(insurace5);
//							wait1.until(ExpectedConditions.visibilityOf(insurace5));
//							if (insurace5.isDisplayed()) {
//								insFSO.selectByVisibleText("Yes");
//							} else {
//								break;
//							}
//						}
//
//						else {
//							break;
//						}
//					}
//
//				}

				/*
				 * Add CounterProducts
				 */

//				if (rwe.getCellData("Avis_GUI", k, 26).isEmpty()) {
//					System.out.print("No CounterProduct selected");
//					// break;
//				} else {
//					String[] cpVal = rwe.getCellData("Avis_GUI", k, 26).split("-");
//					for (String e : cpVal) {
//						WebDriverWait wait = new WebDriverWait(driver, 10);
//						try {
//							if (e.equalsIgnoreCase("ADR")) {
//								WebElement cp1 = driver.findElement(By.id("productQuantity40"));
//								Select cpADR = new Select(cp1);
//								wait.until(ExpectedConditions.visibilityOf(cp1));
//								if (cp1.isDisplayed()) {
//									cpADR.selectByVisibleText("1");
//								} else {
//									break;
//								}
//
//							} else if (e.equalsIgnoreCase("CBS")) {
//								WebElement cp2 = driver.findElement(By.id("productQuantity32"));
//								Select cpCBS = new Select(cp2);
//								wait.until(ExpectedConditions.visibilityOf(cp2));
//								if (cp2.isDisplayed()) {
//									cpCBS.selectByVisibleText("1");
//								} else {
//									break;
//								}
//							} else if (e.equalsIgnoreCase("CSS")) {
//								WebElement cp3 = driver.findElement(By.id("productQuantity34"));
//								Select cpCSS = new Select(cp3);
//								wait.until(ExpectedConditions.visibilityOf(cp3));
//								if (cp3.isDisplayed()) {
//									cpCSS.selectByVisibleText("1");
//								} else {
//									break;
//								}
//							} else if (e.equalsIgnoreCase("GPS")) {
//								WebElement cp4 = driver.findElement(By.id("productQuantityYesNo6"));
//								Select cpGPS = new Select(cp4);
//								wait.until(ExpectedConditions.visibilityOf(cp4));
//								if (cp4.isDisplayed()) {
//									cpGPS.selectByVisibleText("Y");
//								} else {
//									break;
//								}
//							} else if (e.equalsIgnoreCase("RSN")) {
//								WebElement cp5 = driver.findElement(By.id("productQuantityYesNo11"));
//								Select cpRSN = new Select(cp5);
//								wait.until(ExpectedConditions.visibilityOf(cp5));
//								if (cp5.isDisplayed()) {
//									cpRSN.selectByVisibleText("Y");
//								} else {
//									break;
//								}
//							} else if (e.equalsIgnoreCase("TAB")) {
//								WebElement cp6 = driver.findElement(By.id("productQuantityYesNo12"));
//								Select cpTAB = new Select(cp6);
//								wait.until(ExpectedConditions.visibilityOf(cp6));
//								if (cp6.isDisplayed()) {
//									cpTAB.selectByVisibleText("Y");
//								} else {
//									break;
//								}
//							} else if (e.equalsIgnoreCase("ESP")) {
//								WebElement cp7 = driver.findElement(By.id("productQuantityYesNo6"));
//								Select cpESP = new Select(cp7);
//								wait.until(ExpectedConditions.visibilityOf(cp7));
//								if (cp7.isDisplayed()) {
//									cpESP.selectByVisibleText("Y");
//								} else {
//									break;
//								}
//
//							} else if (e.equalsIgnoreCase("SNB")) {
//								WebElement cp8 = driver.findElement(By.id("productQuantityYesNo11"));
//								Select cpSNB = new Select(cp8);
//								if (cp8.isDisplayed()) {
//									wait.until(ExpectedConditions.visibilityOf(cp8));
//									cpSNB.selectByVisibleText("Y");
//								} else {
//									break;
//								}
//							}
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//
//				}

				/*
				 * Create reservation
				 */
				functions.clickCreateReservationBtn();
				Thread.sleep(1000);
				//String Resmsg = driver.findElement(By.xpath("//*[@id='templateInfoForm:templateInfoMsg']")).getText();
				//String Resmsg = driver.findElement(By.cssSelector("#templateInfoForm\3a templateInfoMsg")).getText();
				//String Resmsg = driver.findElement(By.xpath("//[@id='templateInfoForm:templateInfoMsg']")).getText();
				//System.out.println(driver.findElement(By.xpath("//form[@id='templateInfoForm']//div[@class='modal-body']")).getText());
				String Resmsg = driver.findElement(By.xpath("//form[@id='templateInfoForm']//div[@class='modal-body']")).getText();
				rwe.setCellData("Avis_GUI", k, 39, Resmsg); // write respopup in excel
				Thread.sleep(1000);
				//driver.findElement(By.xpath("//*[@id='templateInfoForm:templateInfoButton']")).click(); // clicks OK
				//driver.findElement(By.cssSelector("#templateInfoForm\3a templateInfoButton")).click();																					// button in
																										// Reservation
																										// pop up
				driver.findElement(By.xpath("//div[@class='modal-footer']//div[@class='row']//div[@class='col-md-12 text-left']//button[@value='OK']")).click(); 

				/*
				 * to print QV data in Excel
				 */

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
					ArrayList<WebElement> cells = (ArrayList<WebElement>) rows.get(i).findElements(By.tagName("td"));
					for (int j = 0; j < cells.size(); j++) {
						String val = cells.get(j).getText();
						if (val.isEmpty()) {
							break;
						} else {
							if (j == 2) {
								val = val.replaceAll("[*]", ""); // Remover '*' before rates
								rwe.setCellData("Avis_GUI", k, a, val);
								a++;
							}
						}
					}
				}
				/*
				 * Log out and close tabs
				 */

				functions.logout();
				Thread.sleep(1000);
				functions.closeWindows();
				if (rwe.getCellData("Avis_GUI", k, 26).isEmpty()) 
				{
					rwe.setCellData("Avis_GUI", k, 40, "FAIL");
				} else 
				{
					rwe.setCellData("Avis_GUI", k, 40, "PASS");
				}
			}
			else
			{
				System.out.println("Execution status is N for iteration " + k + "...");
			}

		}

	}

}
