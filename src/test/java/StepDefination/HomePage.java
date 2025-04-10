package StepDefination;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import StepDefination.BaseClass;

public class HomePage extends BaseClass{
	
	public HomePage()
	{
		super.setUp();
	}
	
	@Given("I launch the URL {string}")
	public void i_launch_the_url(String url) {
		driver.get(url);
	}

	@Then("I should see the title of the page as {string}")
	public void i_should_see_the_title_of_the_page_as(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
	}

	@When("I click on the {string} link")
	public void i_click_on_the_link(String linkText) throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='A/B Testing']")).click();
	}

	@Then("I should see the text {string} on the page")
	public void i_should_see_the_text_on_the_page(String expectedString) {
		String mainwindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				String actualTitle=driver.findElement(By.xpath("//*[contains(text(),'A/B Test Variation 1')]")).getText();
				assertEquals(actualTitle, expectedString);
				driver.close();
				System.out.println("Child window closed");
			}
		}    
	}

	@When("Navigate back to the home page and click on the {string} link")
	public void navigate_back_to_the_home_page_and_click_on_the_link(String string) {
		driver.navigate().back();
		driver.findElement(By.xpath("//a[normalize-space()='Dropdown']")).click();
	}

	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String string) {
		String mainwindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.close();
				System.out.println("Child window closed");
			}
		}   
	}

	@When("I select {string} from the dropdown")
	public void i_select_from_the_dropdown(String option) {
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select objSelect = new Select(dropdown);

		objSelect.selectByVisibleText("Option 1");
		WebElement selectedOption = objSelect.getFirstSelectedOption();
		System.out.println("selectedOption Dropdown: -"+selectedOption.getText());
		assertEquals(selectedOption.getText(), option);
	}

	@Then("Navigate back to Home Page and Click on Frames")
	public void navigate_back_to_home_page_and_click_on_frames() throws InterruptedException {

		driver.navigate().back();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[normalize-space()='Frames']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Nested Frames']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='iFrame']")).isDisplayed());
		
		
		/*String originalWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[normalize-space()='Frames']")).click();
       
		Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();

        while (iterator.hasNext()) {
            String windowHandle = iterator.next();
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);

				try {
					// Find all the anchor tags (hyperlinks) on the page
					List<WebElement> links = driver.findElements(By.tagName("a"));

					int numberOfLinks = links.size();
					System.out.println("Number of hyperlinks on the page: " + numberOfLinks);

					// If there are hyperlinks, print the 'href' attribute of each
					if (numberOfLinks > 0) {
						for (WebElement link : links) {
							System.out.println(link.getAttribute("href"));
						}

					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					// Close the browser
					driver.quit();
				}
			}
		}    */

	}
}