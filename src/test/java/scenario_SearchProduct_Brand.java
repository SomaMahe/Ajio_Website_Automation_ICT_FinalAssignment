import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class scenario_SearchProduct_Brand extends BasePage{

    private WebDriver driver;

    public scenario_SearchProduct_Brand(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


    private By searchTextBox =By.name("searchVal");
    private By menLinkText = By.xpath("//a[@title='MEN']");
    private By womenLinkText = By.xpath( "//a[@title='WOMEN']");
    private By kidsLinkText = By.xpath( "//a[@title='KIDS']");
    private By customerCareLinkText = By.xpath("//*[@id='appContainer']/div[1]/div/header/div[1]/ul/li[2]/a");


    //    Scenarios : Search  Available Product and Unavailable Product
    //    Fetching data from DataProvider

    public void searchProduct(String searchString) {

        WebElement searchArea = driver.findElement(searchTextBox);
        searchArea.sendKeys(searchString);
        searchArea.sendKeys(Keys.ENTER);
        Reports.extentTest.log(Status.INFO, "Searched Product   " + searchString);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void verifySearchProduct(String searchString) throws Exception {


        String unAvailableMsg = "Sorry !! No Results found for ";
        String invalidProductMsg= "Sorry! We couldn't find any matching items for";

        if(driver.getPageSource().contains(invalidProductMsg)){
            Assert.fail("Invalid Product Search");
            Reports.extentTest.log(Status.FAIL, "Invalid Product  " + searchString, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            System.out.println("Invalid Product search "+ searchString);
        }
        else if(driver.getPageSource().contains(unAvailableMsg)) {
            System.out.println("Product unavailable " +searchString);
            Reports.extentTest.log(Status.PASS, "Searched Product Unavailable  " + searchString, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        else {
            Reports.extentTest.log(Status.PASS, "Searched Product Available  " + searchString, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            System.out.println("Product Available " +searchString);
        }



    }

    // Scenario : Search Brand -----> PUMA

    public void verifySearchForBrandPuma(String searchBrand) throws Exception{

        searchProduct(searchBrand);
        verifySearchProduct(searchBrand);

    }

    //Scenario : Verify FAQ in Customer Care

    public void verifyFAQCustomerCare()throws Exception{

        click(customerCareLinkText);

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,400)");

        if (locateElement(By.xpath("//label[contains(text(),'Shipping FAQs')]")).isDisplayed()& locateElement((By.xpath("//label[contains(text(),'Cancellation FAQs')]"))).isDisplayed() ){

            Reports.extentTest.log(Status.PASS, "Verified FAQ - contains Shipping and Cancellation FAQs", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }else{
            Reports.extentTest.log(Status.FAIL, "Verified FAQ does not contains Shipping and Cancellation FAQs", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }


    }


}
