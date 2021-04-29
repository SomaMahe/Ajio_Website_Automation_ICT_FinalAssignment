import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

@Listeners(TestListener.class)

public class AjioTestCases {


    private WebDriver driver;

    @Parameters({"chromebrowser","url"})
    @BeforeMethod
    public void initSetup(String browser,String url) throws Exception {
        driver=Browser.openBrowser(browser, url);
    }

    @Test(dataProviderClass = DataProviderClass.class,dataProvider = "data-provider")
    public void verifyAvailableProductSearch(String availableProduct) throws Exception{

        Reports.createReport("Verify Available Product");
        scenario_SearchProduct_Brand searchProduct = new scenario_SearchProduct_Brand(driver);
        searchProduct.searchProduct(availableProduct);
        searchProduct.verifySearchProduct(availableProduct);

    }

    @Test(priority = 1,dataProviderClass = DataProviderClass.class,dataProvider = "data-provider")
    public void verifyUnavailableProductSearch(String unavailableProduct) throws Exception{

        Reports.createReport("Verify Unavailable Product");
        scenario_SearchProduct_Brand searchProduct = new scenario_SearchProduct_Brand(driver);
        searchProduct.searchProduct(unavailableProduct);
        searchProduct.verifySearchProduct(unavailableProduct);

    }

    @Test(priority = 2)
    public void verifyMenuBar() throws Exception{

        Reports.createReport("Verify Menu bar");
        scenario_VerifyMenu_URLchanges menubarObject = new scenario_VerifyMenu_URLchanges(driver);
        menubarObject.verifyMenuBarContains();

    }

    @Test(priority = 3)
    public void verifyFooter() throws Exception{

        Reports.createReport("Verify Footer ");
        scenario_VerifyFooter verifyFooter = new scenario_VerifyFooter(driver);
        verifyFooter.verifyFooterContains();

    }

    @Test(priority = 4)
    public void verifyUrlChangeForMenuClick() throws Exception{

        Reports.createReport("Verify URL change for Menu clicks ");
        scenario_VerifyMenu_URLchanges verifyMenuUrLchanges = new scenario_VerifyMenu_URLchanges(driver);
        verifyMenuUrLchanges.verifyUrlChangeForMenClick();

        driver.navigate().back();
        verifyMenuUrLchanges.verifyUrlChangeForWomenClick();

        driver.navigate().back();
        verifyMenuUrLchanges.verifyUrlChangeForKidsClick();
    }

    @Parameters({"searchAvailableText"})
    @Test(priority = 5)
    public void lowToHigh(String searchString) throws Exception {

        Reports.createReport("Verify sorting low to High ");
        scenario_SortByPrice_lowest_Highest lowest_highest = new scenario_SortByPrice_lowest_Highest(driver);
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);

        searchProductBrand.searchProduct(searchString);
       // lowest_highest.selectDropDown();
        lowest_highest.sortPriceAscending();


    }

    @Parameters({"searchAvailableText"})
    @Test(priority = 6)
    public void highToLow(String searchString) throws Exception {

        Reports.createReport("Verify sorting High to Low ");
        scenario_SortByPrice_lowest_Highest highest_lowest = new scenario_SortByPrice_lowest_Highest(driver);
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);

        searchProductBrand.searchProduct(searchString);
        //highest_lowest.selectDropDown();
        highest_lowest.sortPriceDescending();

    }

    @Parameters({"searchBrand"})
    @Test(priority = 7)
    public  void searchForBrandPuma(String searchBrand) throws Exception{

        Reports.createReport("Verify search brand - PUMA ");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        searchProductBrand.verifySearchForBrandPuma(searchBrand);

    }


    @Test(priority = 8)
    public void MenuItemsForMen() throws Exception{

        Reports.createReport("Verify Menu Items for MEN ");
        scenario_VerifyMenu_URLchanges verifyMenuUrLchanges = new scenario_VerifyMenu_URLchanges(driver);
        verifyMenuUrLchanges.verifyMenuItemsForMen();

    }

    @Parameters({"searchAvailableText"})
    @Test(priority = 9)
    public void ReturnPolicyCheck(String searchString) throws Exception{

        Reports.createReport("Verify Return Policy ");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        scenario_VerifyReturnPolicy returnPolicy = new scenario_VerifyReturnPolicy(driver);

        searchProductBrand.searchProduct(searchString);
        returnPolicy.verifyReturnPolicy(searchString);
    }

    @Parameters({"searchAvailableText"})
    @Test(priority = 9)
    public void addToBag(String searchString) throws Exception{

        Reports.createReport("Verify Add to Bag ");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        searchProductBrand.searchProduct(searchString);

        scenario_AddToBag addToBag = new scenario_AddToBag(driver);
        addToBag.verifyAddToBag();



    }

    @Test(priority = 10)
    public void verifyFAQ() throws Exception{

        Reports.createReport("Verify FAQ ");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        searchProductBrand.verifyFAQCustomerCare();


    }

    @Test(priority = 11,dataProviderClass = DataProviderClass.class,dataProvider = "data-provider")
    public void verifyFilterMinMax(String searchProduct) throws Exception{

        Reports.createReport("Verify Filter Min-Max");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        scenario_FilterItems_MinMax minMax = new scenario_FilterItems_MinMax(driver);

        searchProductBrand.searchProduct(searchProduct);
        minMax.FilterMinMax(searchProduct);

    }

    @Parameters({"searchAvailableText"})
    @Test(priority = 12)
    public void verifyFilterByGender(String searchProduct) throws Exception{


        Reports.createReport("Verify Filter By Gender");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        searchProductBrand.searchProduct(searchProduct);

        scenario_FilterItems_MinMax filterObject = new scenario_FilterItems_MinMax(driver);
        filterObject.FilterByGender();


    }
    @Parameters({"searchAvailableText"})
    @Test(priority = 13)
    public void verifyProceedToBag(String searchProduct) throws Exception{

        Reports.createReport("Verify Proceed To Bag");
        scenario_SearchProduct_Brand searchProductBrand = new scenario_SearchProduct_Brand(driver);
        searchProductBrand.searchProduct(searchProduct);

        scenario_ProceedToBag proceedToBag = new scenario_ProceedToBag(driver);
        proceedToBag.verifyProceedToBag();

    }




        @AfterMethod
    public void tearDownSetup(){

        Browser.closeBrowser(driver);
    }



    }