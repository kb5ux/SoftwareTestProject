/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Student
 *
 */
class mortgageCalcTest {
	
	private WebDriver driver;
	private String url = "http://127.0.0.1:8000/";
	private WebElement price, down, loan, rate, term, calc, reset, monthlyPayment;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\Downloads\\chromedriver.exe"); 	   
	    driver = new ChromeDriver();
	    driver.get(url);
	    driver.navigate().to("http://127.0.0.1:8000/Solve.html");
	    
	    price = driver.findElement(By.id("totalPrice"));
		price.clear();
		
		down = driver.findElement(By.id("downPayment"));
		down.clear();
		
		loan = driver.findElement(By.id("loan"));
		loan.clear();
		
		rate = driver.findElement(By.id("rate"));
		rate.clear();
		
		term = driver.findElement(By.id("term"));
		term.clear();
		
		calc = driver.findElement(By.id("calcbtn"));
		reset = driver.findElement(By.id("resetbtn"));
		
		monthlyPayment = driver.findElement(By.id("monthlypayment"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception 
	{
		driver.quit();
	}

	// Base TR: {number, pos, Calculate, None, None}
	@Test
	public void bccTR1()
	{
	   price.sendKeys("100");
	   
	   down.sendKeys("50");
	   
	   loan.clear();
	   loan.sendKeys("50");
	
	   rate.sendKeys("1");
	   
	   term.sendKeys("12");
	  
	   calc.click();
	  
	   assertEquals(monthlyPayment.getText(), "$4.19");
	}
	
	// TR 2: {not number, pos, Calculate, None, None}
	@Test
	public void bccTR2()
	{
		price.sendKeys("a");
					   
		down.sendKeys("bc");
					   
		loan.clear();
		loan.sendKeys("def");
					
		rate.sendKeys("hj");
					   
		term.sendKeys("akl");
					  
		calc.click();
					   
		assertEquals(monthlyPayment.getText(), "$0.00");
					   
	}
	
	// TR 3: {number, 0, Calculate, None, None}	
	@Test
	public void bccTR3()
	{
	   price.sendKeys("0");
	   
	   down.sendKeys("0");
	   
	   loan.clear();
	   loan.sendKeys("0");
	
	   rate.sendKeys("0");
	   
	   term.sendKeys("0");
	  
	   calc.click();
	  
	   assertEquals(monthlyPayment.getText(), "$0.00");
	}
	
	// TR 4: {number, neg, Calculate, None, None}
	@Test
	public void bccTR4()
	{
	   price.sendKeys("-10");
	   
	   down.sendKeys("-5");
	   
	   loan.clear();
	   loan.sendKeys("-5");
	
	   rate.sendKeys("-1");
	   
	   term.sendKeys("-12");
	  
	   calc.click();
	  
	   assertEquals(monthlyPayment.getText(), "$0.42");
	}
	
	// TR 5: {number, pos, Reset, None, None}
	@Test
	public void bccTR5()
	{
	   price.sendKeys("100");
	   
	   down.sendKeys("25");
	   
	   loan.clear();
	   loan.sendKeys("75");
	
	   rate.sendKeys("2.7");
	   
	   term.sendKeys("10");
	  
	   reset.click();
	   
	   assertEquals(price.getAttribute("value"), "$125,000");
	   assertEquals(down.getAttribute("value"), "$25,000");
	   assertEquals(loan.getAttribute("value"), "$100,000");
	   assertEquals(rate.getAttribute("value"), "3.92");
	   assertEquals(term.getAttribute("value"), "360");
	   assertEquals(monthlyPayment.getText(), "$0.00");
	   
	}
	
	// TR 6: {number, pos, Calculate, Back, None}
	@Test
	public void bccTR6()
	{
		price.sendKeys("1000");
		   
	    down.sendKeys("90");
		   
	    loan.clear();
		loan.sendKeys("910");
		
		rate.sendKeys("2.1");
		   
		term.sendKeys("15");
		  
		calc.click();
		   
		driver.navigate().back();
		   
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/");
		   
	}
		
	// TR 7: {number, pos, Calculate, Reload, None}
	@Test
	public void bccTR7()
	{
		price.sendKeys("160");
						   
		down.sendKeys("20");
						   
		loan.clear();
		loan.sendKeys("140");
						
		rate.sendKeys("7.3");
						   
		term.sendKeys("14");
						  
		calc.click();
					
		driver.navigate().refresh();
					
		price = driver.findElement(By.id("totalPrice"));
					
		down = driver.findElement(By.id("downPayment"));
					
		loan = driver.findElement(By.id("loan"));
						
		rate = driver.findElement(By.id("rate"));
						
		term = driver.findElement(By.id("term"));
				
		monthlyPayment = driver.findElement(By.id("monthlypayment"));
					
		assertEquals(price.getAttribute("value"), "$125,000");
		assertEquals(down.getAttribute("value"), "$25,000");
		assertEquals(loan.getAttribute("value"), "$100,000");
		assertEquals(rate.getAttribute("value"), "3.92");
		assertEquals(term.getAttribute("value"), "360");
		assertEquals(monthlyPayment.getText(), "$0.00");
						   		   
	}
		
	// TR 8: {number, pos, Calculate, Forward, None}
	@Test
	public void bccTR8()
	{
		price.sendKeys("1000");
				   
		down.sendKeys("250");
				   
		loan.clear();
		loan.sendKeys("750");
				
		rate.sendKeys("1.1");
				   
		term.sendKeys("17");
				  
		calc.click();
			
		driver.navigate().forward();
				   
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Solve.html");
				   
	}
		
	// TR 9: {number, pos, Calculate, None, Code}
	@Test
	public void bccTR9()
	{
		price.sendKeys("100");
			   
		down.sendKeys("25");
			   
		loan.clear();
		loan.sendKeys("75");
			
		rate.sendKeys("2.7");
				   
		term.sendKeys("10");
				  
		calc.click();
				   
		WebElement codeNav = driver.findElement(By.xpath("//a[@href='Code.html']"));
			
		codeNav.click();
			
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Code.html");
				   
	}
		
	// TR 10: {number, pos, Calculate, None, Github}
	@Test
	public void bccTR10()
	{
		price.sendKeys("100");
				   
		down.sendKeys("25");
				   
		loan.clear();
		loan.sendKeys("75");
				
		rate.sendKeys("2.7");
				   
		term.sendKeys("10");
				  
		calc.click();
				   
		WebElement gitNav = driver.findElement(By.xpath("//a[@href='https://github.com/mackenzieweaver/MortgageCalculator']"));
		gitNav.click();
			
			
		String current = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			
		String gitTitle = "GitHub - mackenzieweaver/MortgageCalculator: Welcome to our mortgage calculator app. Enter in the "
					+ "loan amount, the interest rate, and a term (in months) and click "
					+ "calculate to build a table to track each payment.";
			
		driver.switchTo().window(tabs.get(1));
		
		assertEquals(driver.getTitle(), gitTitle);
							   
	}
	
	// TR1: {Not Number, Neg, Calc, None, None}
	@Test
	public void pwcTR1()
	{
		price.sendKeys("-10a0");
		   
		down.sendKeys("-+10");
				   
		loan.clear();
		loan.sendKeys("-^9&*0");
				
		rate.sendKeys("-&&2&");
				   
		term.sendKeys("-_1)4");
				  
		calc.click();
		
		price = driver.findElement(By.id("totalPrice"));
		
		down = driver.findElement(By.id("downPayment"));
					
		loan = driver.findElement(By.id("loan"));
						
		rate = driver.findElement(By.id("rate"));
						
		term = driver.findElement(By.id("term"));
		
		assertEquals(price.getAttribute("value"), "$100");
		assertEquals(down.getAttribute("value"), "$010");
		assertEquals(loan.getAttribute("value"), "$90");
		assertEquals(rate.getAttribute("value"), "2");
		assertEquals(term.getAttribute("value"), "14");
		assertEquals(monthlyPayment.getText(), "$6.51");
	}
	
	// TR3: {Not Number, Pos, Calc, Reload, None}
	@Test
	public void pwcTR3()
	{
		price.sendKeys("a");
		   
		down.sendKeys("+{}");
				   
		loan.clear();
		loan.sendKeys("^&*");
				
		rate.sendKeys("&u&");
				   
		term.sendKeys("}aru");
				  
		calc.click();
		
		driver.navigate().refresh();
		
		price = driver.findElement(By.id("totalPrice"));
					
		down = driver.findElement(By.id("downPayment"));
					
		loan = driver.findElement(By.id("loan"));
						
		rate = driver.findElement(By.id("rate"));
						
		term = driver.findElement(By.id("term"));
				
		monthlyPayment = driver.findElement(By.id("monthlypayment"));
		
		assertEquals(price.getAttribute("value"), "$125,000");
		assertEquals(down.getAttribute("value"), "$25,000");
		assertEquals(loan.getAttribute("value"), "$100,000");
		assertEquals(rate.getAttribute("value"), "3.92");
		assertEquals(term.getAttribute("value"), "360");
		assertEquals(monthlyPayment.getText(), "$0.00");
	}
	
	// TR4: {Number, Neg, Reset, Forward, None}
	@Test
	public void pwcTR4()
	{
		price.sendKeys("-102");
		   
		down.sendKeys("-30");
				   
		loan.clear();
		loan.sendKeys("-72");
				
		rate.sendKeys("-1.3");
				   
		term.sendKeys("-12");
				  
		reset.click();
		
		driver.navigate().forward();
		
		assertEquals(price.getAttribute("value"), "$125,000");
		assertEquals(down.getAttribute("value"), "$25,000");
		assertEquals(loan.getAttribute("value"), "$100,000");
		assertEquals(rate.getAttribute("value"), "3.92");
		assertEquals(term.getAttribute("value"), "360");
		assertEquals(monthlyPayment.getText(), "$0.00");
	}
	
	// TR6: {Not Number, Pos, Calc, Back, Home}
	@Test
	public void pwcTR6()
	{
		price.sendKeys("a1234vd");
		   
		down.sendKeys("wd20q0");
				   
		loan.clear();
		loan.sendKeys("e1f34w");
				
		rate.sendKeys("w32f");
				   
		term.sendKeys("z344x");
				  
		calc.click();
		
		driver.navigate().back();
		
		WebElement homeNav = driver.findElement(By.xpath("//a[@href='Index.html']"));
		
		homeNav.click();
		
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Index.html");
	}
	
	// TR7: {Number, Neg, Calculate, Reload, Home}
	@Test
	public void pwcTR7()
	{
		price.sendKeys("-1000");
		   
		down.sendKeys("-120");
				  
		loan.clear();
		loan.sendKeys("-880");
				
		rate.sendKeys("-2.1");
				   
		term.sendKeys("-190");
				  
		calc.click();
		
		driver.navigate().refresh();
		
		WebElement homeNav = driver.findElement(By.xpath("//a[@href='Index.html']"));
		
		homeNav.click();
		
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Index.html");
	}
	
	// TR10: {Not Number, Neg, Reset, Back, Solution}
	@Test
	public void pwcTR10()
	{
		price.sendKeys("al-100s");
		   
		down.sendKeys("-csc23em");
				  
		loan.clear();
		loan.sendKeys("-cs77oiv");
				
		rate.sendKeys("-cw2i");
				   
		term.sendKeys("-wfi1w3");
				  
		reset.click();
		
		driver.navigate().back();
		
		WebElement solNav = driver.findElement(By.xpath("//a[@href='Solve.html']"));
		
		solNav.click();
		
		price = driver.findElement(By.id("totalPrice"));
		
		down = driver.findElement(By.id("downPayment"));
					
		loan = driver.findElement(By.id("loan"));
						
		rate = driver.findElement(By.id("rate"));
						
		term = driver.findElement(By.id("term"));
				
		monthlyPayment = driver.findElement(By.id("monthlypayment"));
		
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Solve.html");
		assertEquals(price.getAttribute("value"), "$125,000");
		assertEquals(down.getAttribute("value"), "$25,000");
		assertEquals(loan.getAttribute("value"), "$100,000");
		assertEquals(rate.getAttribute("value"), "3.92");
		assertEquals(term.getAttribute("value"), "360");
		assertEquals(monthlyPayment.getText(), "$0.00");
		
	}
	
	// TR14: {Not Number, 0, Reset, Back, Code}
	@Test
	public void pwcTR14()
	{
		price.sendKeys("ado123a");
		   
		down.sendKeys("jkh2nb3");
				  
		loan.clear();
		loan.sendKeys("affe100ka");
				
		rate.sendKeys("hj8dkl");
				   
		term.sendKeys("kl12lk");
				  
		reset.click();
		
		driver.navigate().back();
		
		WebElement codeNav = driver.findElement(By.xpath("//a[@href='Code.html']"));
		
		codeNav.click();
		
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Code.html");
	} 
	
	// 	TR15: {Number, Pos, Calculate, Reload, Code}
	@Test
	public void pwcTR15()
	{
		price.sendKeys("1200");
		   
		down.sendKeys("200");
				  
		loan.clear();
		loan.sendKeys("1000");
				
		rate.sendKeys("2.0");
				   
		term.sendKeys("12");
				  
		calc.click();
		
		driver.navigate().refresh();
		
		WebElement codeNav = driver.findElement(By.xpath("//a[@href='Code.html']"));
		
		codeNav.click();
		
		assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/Code.html");
	}
	
	// TR18: {Not Number, Pos, Reset, Back, Github}
	@Test
	public void pwcTR18()
	{
		price.sendKeys("ad100fa");
		   
		down.sendKeys("ads20f");
				  
		loan.clear();
		loan.sendKeys("vi80od");
				
		rate.sendKeys("okf2saf");
				   
		term.sendKeys("fjis1f0j");
				  
		reset.click();
		
		driver.navigate().back();
		
		WebElement gitNav = driver.findElement(By.xpath("//a[@href='https://github.com/mackenzieweaver/MortgageCalculator']"));
		gitNav.click();
			
			
		String current = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			
		String gitTitle = "GitHub - mackenzieweaver/MortgageCalculator: Welcome to our mortgage calculator app. Enter in the "
					+ "loan amount, the interest rate, and a term (in months) and click "
					+ "calculate to build a table to track each payment.";
			
		driver.switchTo().window(tabs.get(1));
		
		assertEquals(driver.getTitle(), gitTitle);
	} 
	
	// TR19: {Number, Neg, Reset, Reload, Github}
	@Test
	public void pwcTR19()
	{
		price.sendKeys("-1567");
		   
		down.sendKeys("-67");
				  
		loan.clear();
		loan.sendKeys("-1500");
				
		rate.sendKeys("-2.1");
				   
		term.sendKeys("-300");
				  
		reset.click();
		
		driver.navigate().refresh();
		
		WebElement gitNav = driver.findElement(By.xpath("//a[@href='https://github.com/mackenzieweaver/MortgageCalculator']"));
		gitNav.click();
			
			
		String current = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			
		String gitTitle = "GitHub - mackenzieweaver/MortgageCalculator: Welcome to our mortgage calculator app. Enter in the "
					+ "loan amount, the interest rate, and a term (in months) and click "
					+ "calculate to build a table to track each payment.";
			
		driver.switchTo().window(tabs.get(1));
		
		assertEquals(driver.getTitle(), gitTitle);
	}
	
	
						
}