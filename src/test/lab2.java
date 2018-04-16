package test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import org.junit.runners.Parameterized;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;  
  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
@RunWith(Parameterized.class)
public class lab2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String num;
  private String password;
  private String github;
  public lab2(String num, String password, String github)
  {
	  this.num = num ;
	  this.password = password;
	  this.github = github;
  }
  
  @Parameters
  public static Collection data()
  {
	 String [][] content = new String [97][3];
	 try {  
	     InputStream is = new FileInputStream(new File("c:\\Users\\dell\\Desktop\\input.xlsx"));  
	     XSSFWorkbook wb = new XSSFWorkbook(is);  
	     XSSFCell cell = null;  
//	     for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {  
	         XSSFSheet st = wb.getSheetAt(0);  
	         for (int rowIndex = 0; rowIndex <= st.getLastRowNum(); rowIndex++) {  
	             XSSFRow row = st.getRow(rowIndex);  
	             cell = row.getCell(0);   
	             content[rowIndex][0] = row.getCell(0).toString();
	             content[rowIndex][1] = row.getCell(0).toString().substring(4);
	             content[rowIndex][2] = row.getCell(1).toString();
	         }  
//	     }  
	          
	 }catch (Exception e) {  
	     e.printStackTrace(); 
	 }
	 return Arrays.asList(content);
  }
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://psych.liebes.top/st";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void lab2() throws Exception {
    driver.get("https://psych.liebes.top/st");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    System.out.println(num);
    driver.findElement(By.id("username")).sendKeys(num);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.name("loginForm")).submit();
//    driver.findElement(By.xpath("//p")).click();
    System.out.print(driver.findElement(By.xpath("//p")).getText());
    assertEquals(github,driver.findElement(By.xpath("//p")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
