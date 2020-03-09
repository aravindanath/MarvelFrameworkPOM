package pageRepo;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.JavaUtils;

public class LoginPage  extends BaseClass{

 
	
	public LoginPage(WebDriver localDriver) {
		super(localDriver);
		this.driver = localDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	@FindBy(xpath="//span[text()='Profile']")
	WebElement profile;
	
	@FindBy(xpath="//a[text()='log in']")
	WebElement loginLink;
	
	@FindBy(css=".login-user-input-email.login-user-input")
	WebElement emailText;
	
	@FindBy(css=".login-user-input-password.login-user-input")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[text()='Recover password']")
	WebElement recPassword;
	
	@FindBy(css=".forgot-password-button")
	WebElement forgotBtn;
	
	protected HashMap<String, String> lp;
	
	public void gotoProfile() throws InterruptedException {
		BaseClass.assertPageTitle("Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra",driver);
		Thread.sleep(1000);
		BaseClass.mouseHover(profile, driver);
		Thread.sleep(1000);
		BaseClass.clickAction(loginLink,driver);
	}
	
	public void login(String uniqeValue) throws InterruptedException {
		lp = JavaUtils.readExcelData("Login",uniqeValue);
		Thread.sleep(1000);
		emailText.clear();
		emailText.sendKeys(lp.get("USERNAME"));
		password.clear();
		password.sendKeys(lp.get("PASSWORD"));
		loginBtn.click();
		
	}
	
	public void passwordRecovery() throws InterruptedException {
		Thread.sleep(1000);
		 recPassword.click();
		 Thread.sleep(1000);
		 forgotBtn.click();
		
	}
	
	
	
	
	
	
	

}
