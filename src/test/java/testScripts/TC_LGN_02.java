package testScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import pageRepo.LoginPage;

public class TC_LGN_02 extends BaseTest {
	/**
	 * @author aravindanathdm
	 * 
	 * @throws InterruptedException
	 */	
	 
	
	@Test
	public void loginCase() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.gotoProfile();
		lp.passwordRecovery();
		
	}

}
