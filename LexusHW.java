package hw3;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LexusHW {

	public static void main(String[] args) throws InterruptedException {
		
System.setProperty("webdriver.chrome.driver", "C:\\Selenium Files\\Browser Drivers\\chromedriver.exe");
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1. Go to Lexus.com
		
		driver.get("https://www.lexus.com/");
		
		
		
		

		//2.Verify the title of the page contains “Experience Amazing”
		
		
		
		if (driver.getTitle().contains("Experience Amazing")) {
			System.out.println("Step 2- PASS. The title of the page contains “Experience Amazing”");
		} else System.out.println("Step 2 - FAIL");
		
		//3.Click on Do not Sell My Personal information at the bottom of the page
		
		driver.findElement(By.linkText("DO NOT SELL MY PERSONAL INFORMATION")).click();
		
		//4.Verify the page title contains “Privacy Hub”
		
		Thread.sleep(2000);
		
		
		String parentWindowHandle=driver.getWindowHandle();
		
		Set<String> allHandles = driver.getWindowHandles();
		
		
		for (String handle : allHandles) {
			if(!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				
			}
			
		}
		
		System.out.println(driver.getTitle());
		
		if (driver.getTitle().contains("Privacy Hub")) {
			System.out.println("step 4 - PASS");
		} else System.out.println("FAIL");
		
		
		//5.
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//div//a[.='Do Not Sell My Personal Information']")).click();
		driver.findElement(By.linkText("Do Not Sell My Personal Information")).click();
		
		//6. Verify that URL url is “https://privacy.toyota.com/privacy-hub/donotsell.html”
		
		String url = driver.getCurrentUrl();
		
		String expectedURL = "https://privacy.toyota.com/privacy-hub/donotsell.html";
		
		if (url.contentEquals(expectedURL)) {
			System.out.println("Step 6 - PASS");
		} else System.out.println("FAIL");
		
		//7.Go back to the main window and click on Build your Lexus
		
		String currentWindowHandle=driver.getWindowHandle();
		for (String handle : allHandles) {
			if(!handle.equals(currentWindowHandle)) {
				driver.switchTo().window(handle);}
			}
		
		//8.
		
		Thread.sleep(2000);
					
				((JavascriptExecutor) driver).executeScript("scroll(0,700)");
				
		Thread.sleep(2000);
				
				driver.findElement(By.linkText("BUILD YOUR LEXUS")).click();
				
				
		 
				driver.findElement(By.cssSelector("input[value='Enter Zip'")).sendKeys("22182");
				
				driver.findElement(By.xpath("//button[.='Enter']")).click();
		
		//9. Click on model GS
				
				driver.findElement(By.xpath("//span[.='GS']")).click();
				
		//10. Choose 2020 GS 350 F Sport AWD. Before clicking, get the price of the vehicle and save it into an int variable		
				
				
				String price = driver.findElement(By.xpath("//span[contains(text(),'54,505')]")).getText().replace("$", "").replace(",", "").replace("*", "");
				
				int lexusGS = Integer.parseInt(price);
				
				
				((JavascriptExecutor) driver).executeScript("scroll(0,2000)");
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//span[contains(text(),'54,505')]")).click();
				
				Thread.sleep(2000);
				
		//11. On the next page, click on the price menu on top and retrieve and store the base price, 
				//dp&h fee and msrp into separate int variables. 
				
				driver.findElement(By.xpath("//span[@id='total-price']")).click();
				
				//driver.findElement(By.id("total-price")).click();
				
				Thread.sleep(2000);
				String sportPrice0 = driver.findElement(By.xpath("//span[contains(text(),'54,505')]")).getText().replace("$", "").replace(",", "");
          
				
				int sportPrice = Integer.parseInt(sportPrice0); 
				System.out.println(sportPrice);
				
                String dph0 = driver.findElement(By.xpath("//span[contains(text(),'1,025')]")).getText().replace("$", "").replace(",", "");
				
				int dph = Integer.parseInt(dph0);
				System.out.println(dph);
				
                String msrp0 = driver.findElement(By.xpath("//span[contains(text(),'55,530')]")).getText().replace("$", "").replace(",", "");
				
				int msrp = Integer.parseInt(msrp0);
				System.out.println(msrp);
				
				
				//Verify that the base price is the same as the price shown at Step 10
				
				if (lexusGS == sportPrice) {
					System.out.println("step 11 - PASS");
					} else System.out.println("FAIL");
				
				//Verify that msrp equals to base price + dp&h fee
				
				if (msrp == (sportPrice + dph)) {
					System.out.println("step 11 - PASS");
				} else System.out.println("FAIL");
				
				//12. Close the price menu and click on blue color
				
				driver.findElement(By.xpath("//span[@id='total-price']")).click();
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//img[@src='/config/pub/static/images/4f6a4fd50607a5684488cf2dc8df3ae65782f918"
						+ "_2015-Lexus-RC-Exterior-swatch-ultrasonic-blue-mica-2.0-60x60.png']")).click();
				
				
				//13. Click on the price menu on top again and retrieve the price for color and store into int variable. Retrieve msrp one more time and verify that msrp  now equals to base price + dp&h fee + color

				driver.findElement(By.xpath("//span[@id='total-price']")).click();
				
                String msrp2 = driver.findElement(By.xpath("//span[contains(text(),'56,125')]")).getText().replace("$", "").replace(",", "");
				
				int msrp3 = Integer.parseInt(msrp2);
				System.out.println(msrp3);
				
				String color = driver.findElement(By.xpath("//span[contains(text(),'595')]")).getText().replace("$", "").replace(",", "");
                int color1 = Integer.parseInt(color);
                System.out.println(color);

				if (msrp3 == (sportPrice + dph + color1)) {
					System.out.println("step 13 - PASS");
				} else System.out.println("FAIL");
				
				//14. 
				
				driver.findElement(By.xpath("//a[.='Next: Interior']")).click();
				
				//15. Choose “Rioja Red leather with Naguri Aluminum trim” from the options .Click on Next:packages 
				
				driver.findElement(By.xpath("//span[@id='total-price']")).click();
				driver.findElement(By.xpath("//img[@data-code='LB36']")).click();
				driver.findElement(By.xpath("//a[.='Next: Packages']")).click();
				
				//16. In the next menu, click on add button for Mark Levinson sound system
				//Thread.sleep(2000);
				driver.findElement(By.xpath("(//div//a[.='Add'])[2]")).click();
				
				//17. Click on price menu again and retrieve and store the price for Sound system into int variable.
				
				driver.findElement(By.xpath("//span[.='57,505']")).click();
				
				String soundPrice0 = driver.findElement(By.xpath("//span[contains(text(),'1,380')]")).getText().
						replace("$", "").replace(",", "").replace("*", "");
				
				int soundPrice = Integer.parseInt(soundPrice0);
				System.out.println(soundPrice);
				
				//Retrieve msrp once again and verify that msrp now equals to to base price + dp&h fee + color+sound system.
				
				
				JavascriptExecutor js=(JavascriptExecutor)driver;
       	        js.executeScript("window.scrollBy(0,300)");
       	        
				String msrp4 = driver.findElement(By.xpath("(//span[.='57,505'])[2]")).getText().
						replace("$", "").replace(",", "").replace("*", "");
				
				int msrp5 = Integer.parseInt(msrp4);
				
				if (msrp5 == (lexusGS + soundPrice + dph + color1)) {
					System.out.println("Test 17 - PASS");
				} else System.out.println("Test 17 - FAIL");
       	        
				
				//18.Click on Next:Accessories , on the next menu don’t add anything and click on Next:summary
				

		        
		        driver.findElement(By.xpath("//a[.='Next: Accessories']")).click();
		        
		        driver.findElement(By.xpath("//a[.='Next: Summary']")).click();
		        
		        //19. On the next page, retrieve msrp and verify that it is equal to the 
		        //final msrp from step 17. Then click on Send to Dealer.
		        
		        //int finalMsrp = driver.findElement(By.xpath());
		        
		        driver.findElement(By.xpath("//a[.='Send to Dealer']")).click();
		        
		        //20. Next, first verify that the page contains “Send us Your Dream Car” text.
		        //Then enter the below information to the form fields, choose Pohanka as preferred dealer and click on submit.
		        
		        
		        
		        
		        String mostCurrent = driver.getWindowHandle();
				for (String handle : driver.getWindowHandles()) {
					if (!(mostCurrent.equals(handle)) && !(mostCurrent.equals(currentWindowHandle))) {
						driver.switchTo().window(handle);
						
					}
					
				}
		        Thread.sleep(2000);
		        
		       
				System.out.println(driver.getTitle());
				
				if((driver.getPageSource()).contains("Send us Your Dream Car")) {
					System.out.println("PASS - the page contains “Send us Your Dream Car” text");
				}
		        
		       Thread.sleep(2000);
//		      
	            driver.findElement(By.id("first-name")).sendKeys("John");
				driver.findElement(By.id("last-name")).sendKeys("Doe");
				driver.findElement(By.id("email")).sendKeys("anyemail@gmail.com");
				driver.findElement(By.id("phone")).sendKeys("5712975417");
				
		       ((JavascriptExecutor) driver).executeScript("scroll(0,300)");
				
				
				//driver.findElement(By.xpath("(//label[@class='in-replace'])[1]")).click();
				
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(//label[@class='in-replace'])[1]")));
				
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				
				//21. In the last page, verify that the page contains “We'll Be In Touch Shortly” text
				

		        Thread.sleep(3000);
		        
				if(driver.getPageSource().contains("We'll Be In Touch Shortly")) {
					System.out.println("PASS - the page contains “We'll Be In Touch Shortly” text");
				}
		}
		
	}

