package pageRepo;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.JavaUtils;

public class SearchPage extends BaseClass {
	
 
	
	public SearchPage(WebDriver localDriver) {
		super(localDriver);
		this.driver = localDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath="//input[@class='desktop-searchBar']")
	WebElement searchBar;
	
	protected HashMap<String, String> sp;
	
	public void search(String value) throws InterruptedException {
		sp = JavaUtils.readExcelData("Search", value);
		Thread.sleep(2000);
		searchBar.sendKeys(sp.get("PRODUCT"),Keys.ENTER);
	}
	
	

}
