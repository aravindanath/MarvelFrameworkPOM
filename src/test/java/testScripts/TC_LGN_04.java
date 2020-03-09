package testScripts;

import org.testng.annotations.Test;

import pageRepo.LoginPage;
import pageRepo.SearchPage;

public class TC_LGN_04 extends BaseTest {
	/**
	 * @author aravindanathdm
	 * 
	 * @throws InterruptedException
	 */
	 
	
	@Test
	public void loginCase() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.gotoProfile();
		lp.login("LG_01");
		SearchPage sp = new SearchPage(driver);
		sp.search("SC_01");
		
	}

}
