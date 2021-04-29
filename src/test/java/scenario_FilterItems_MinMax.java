import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class scenario_FilterItems_MinMax extends BasePage {

    public scenario_FilterItems_MinMax(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //---------Scenario :  Filter By Price-Min and Max-------

    public void FilterMinMax(String searchProduct) throws Exception {

        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0,400)");

            locateElement(By.cssSelector(".rilrtl-list-item:nth-child(3) .facet-left-pane-label")).click();
            click(By.id("minPrice"));
            locateElement(By.id("minPrice")).sendKeys("2000");
            click(By.id("maxPrice"));
            locateElement(By.id("maxPrice")).sendKeys("5000");
            Thread.sleep(1000);
            click(By.cssSelector(".rilrtl-button:nth-child(4)"));
            Thread.sleep(5000);

            List<WebElement> price = driver.findElements(By.xpath("//span[@class='price  ']"));


            String priceListString;
            List<String> priceArrayList = new ArrayList<String>();

            for (int i = 0; i < price.size(); i = i + 1) {
                priceListString = price.get(i).getText();
                String trimPriceListString = priceListString.replaceAll("Rs. ", "");
                String commaRemovedPriceList = trimPriceListString.replaceAll(",", "");
                priceArrayList.add(commaRemovedPriceList);

            }
            ArrayList<Float> priceList = new ArrayList<Float>();
            for (String stringValue : priceArrayList) {
                priceList.add(Float.parseFloat(stringValue));
            }

            Collections.sort(priceList);

            float minProductPrice = priceList.get(0);
            float maxProductPrice = priceList.get(priceList.size() - 1);
            javascriptExecutor.executeScript("window.scrollBy(0,400)");


            if (minProductPrice >= 2000 & maxProductPrice <= 5000) {
                Assert.assertTrue(minProductPrice >= 2000, "Minimum Priced Product    " + minProductPrice);
                Assert.assertTrue(maxProductPrice <= 5000, "Maximum Priced Product    " + maxProductPrice);
                Reports.extentTest.log(Status.PASS, "Verified filtering by Price (Min-Max)  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            } else {
                Reports.extentTest.log(Status.FAIL, "Verified Price not filtered (Min-Max)  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            }

        } catch (Exception e) {
            Assert.fail("Filtering Price Min-Max exception" + e.getStackTrace());
        }
    }


    //---------Scenario :  Filter By Gender  ------------

    public void FilterByGender() throws Exception {

        try{

        String initialResults = locateElement(By.className("length")).getText();
        String regXRemovedResults= initialResults.replaceAll("[^0-9]","");
        int initialResultsCount = Integer.parseInt(regXRemovedResults);

        takeScreenshot();

        click(By.xpath("//label[@for='Men']"));
        Thread.sleep(5000);

        String filteredResults = driver.findElement(By.className("length")).getText();
        String  regRemovedFilteredResults = filteredResults.replaceAll("[^0-9]","");
        int filteredResultsCount = Integer.parseInt(regRemovedFilteredResults);

        if((initialResultsCount - filteredResultsCount) > 0)
        {
            Reports.extentTest.log(Status.PASS, "Verified filtering by Gender  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        else
        {
            Reports.extentTest.log(Status.FAIL, "Verified not filtering by Gender  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        } catch (Exception e){

            Assert.fail("Filtering By Gender exception "+e.getStackTrace());
        }







    }
}
