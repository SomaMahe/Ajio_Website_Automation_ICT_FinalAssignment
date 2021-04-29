import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class scenario_VerifyReturnPolicy extends BasePage{

    private WebDriver driver;

    public scenario_VerifyReturnPolicy(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //----Scenario : Verify Return Policy

    public void verifyReturnPolicy(String searchItem) throws Exception{

        try {

            click(By.cssSelector(".imgHolder"));
            Thread.sleep(1000);

            String parentWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();

            Iterator<String> iter = windowHandles.iterator();

            while (iter.hasNext()) {

                String child_window = iter.next();

                if (!parentWindow.equals(child_window)) {
                    driver.switchTo().window(child_window);

                    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                    javascriptExecutor.executeScript("window.scrollBy(0,450)");

                    WebElement returnDescription = locateElement(By.xpath("//div[@class='return-desc']//span"));

                    if(returnDescription.isDisplayed())
                    {
                        Thread.sleep(3000);
                        Reports.extentTest.log(Status.PASS, "Verified Return Policy ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
                        System.out.println(returnDescription.getText());
                    }

                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
        }


        catch(Exception e){
            Assert.fail(" No return Policy found");
            Reports.extentTest.log(Status.FAIL, "Verified No Return Policy ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }


}
