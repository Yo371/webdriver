package googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pastebin.CustomCondition;

import java.util.List;

public class CalculatorGooglePage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframeCloud;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement iframeInsideCloud;

    @FindBy(xpath = "//*[@id=\"input_61\"]")
    private WebElement instancesInput;

    @FindBy(css = "#select_87")
    private WebElement selectMachineType;

    @FindBy(css = "#select_option_234 > div.md-text.ng-binding")
    private WebElement optionMachineType_e2St8;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.soleTenant.addGPUs']")
    private WebElement checkboxGPU;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.gpuCount']")
    private WebElement selectGpuCount;

    @FindBy(xpath = "//md-option[@id=\"select_option_359\"]")
    private WebElement optionGpu4;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.gpuType']")
    private WebElement selectGpuType;

    @FindBy(xpath = "//*[@id=\"select_option_362\"]")
    private WebElement optionTeslaV100;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.ssd']")
    private WebElement selectSSD;

    @FindBy(xpath = "//*[@id=\"select_option_115\"]")
    private WebElement optionSSD375GB;

    @FindBy(xpath = "//*[@id=\"select_value_label_59\"]")
    private WebElement selectLocation;

    @FindBy(xpath = "//*[@id=\"select_option_203\"]")
    private WebElement optionFrankfurt;

    @FindBy(xpath = "//*[@id=\"select_value_label_60\"]")
    private WebElement selectCommittedUsage;

    @FindBy(xpath = "//*[@id=\"select_option_94\"]")
    private WebElement option1year;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.soleTenant.nodesCount']")
    private WebElement selectNodesCount;

    @FindBy(xpath = "//button[@aria-label=\"Add to Estimate\"]")
    private List<WebElement> buttonsEstimateList;

    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement resultVM;

    @FindBy(xpath = "//div[contains(text(), 'Instance type')]")
    private WebElement resultInstanceType;

    @FindBy(xpath = "//div[contains(text(), 'Region')]")
    private WebElement resultRegion;

    @FindBy(xpath = "//b[contains(text(), 'Estimated Component Cost:')]")
    private WebElement resultCostMonth;

    @FindBy(xpath = "//b[contains(text(), \"Total Estimated Cost\")]")
    private WebElement resultTotalCost;

    @FindBy(xpath = "//button[@aria-label=\"Email Estimate\"]")
    private WebElement emailButton;

    @FindBy(xpath = "//*[@id=\"input_419\"]")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[@aria-label=\"Send Email\"]")
    private WebElement sendEmailButton;


    public CalculatorGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        CustomCondition.waitElementPresence(driver, By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe"), 15);
        switchFrame();
    }

    public CalculatorGooglePage switchFrame() {
        driver.switchTo().frame(iframeCloud).switchTo().frame(iframeInsideCloud);
        return this;
    }

    public CalculatorGooglePage inputInstances(int instances) {
        CustomCondition.waitElementPresence(driver, By.id("input_61"), 5);
        instancesInput.sendKeys("" + instances);
        return this;
    }

    public CalculatorGooglePage selectE2Standart8_MachineType() {
        selectMachineType.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(optionMachineType_e2St8));
        optionMachineType_e2St8.click();
        return this;
    }

    public CalculatorGooglePage addGPU4NvidiaV100() {
        checkboxGPU.click();

        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(selectGpuCount));

        selectGpuCount.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionGpu4));
        optionGpu4.click();

        selectGpuType.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionTeslaV100));
        optionTeslaV100.click();
        return this;
    }

    public CalculatorGooglePage addSsd375GB() {
        selectSSD.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionSSD375GB));
        optionSSD375GB.click();
        return this;
    }

    public CalculatorGooglePage selectFrankfurtLocation() {
        selectLocation.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionFrankfurt));
        optionFrankfurt.click();
        return this;
    }

    public CalculatorGooglePage selectCommittedUsage_1Year() {
        selectCommittedUsage.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(option1year));
        option1year.click();
        return this;
    }

    public CalculatorGooglePage selectOneNode() {
        selectNodesCount.click();
        selectNodesCount.sendKeys("1");
        return this;
    }

    public CalculatorGooglePage estimateInstancesField() {

        /*JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", buttonsEstimateList.get(0));*/

        //buttonsEstimateList.get(0).click();

        buttonsEstimateList.get(0).sendKeys(Keys.ENTER);
        return this;
    }

    public CalculatorGooglePage estimateNodesField() {
        /*new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(buttonsEstimateList.get(1)));
         buttonsEstimateList.get(1).click();
        ElementClickInterceptedException: element click intercepted*/

        /*JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", buttonsEstimateList.get(1));*/


        buttonsEstimateList.get(1).sendKeys(Keys.ENTER);

        return this;
    }

    public CalculatorGooglePage emailEstimate() {
        emailButton.click();
        return this;
    }

    public CalculatorGooglePage sendToEmail(String email) {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id=\"input_419\"]")));

        inputEmail.sendKeys(email);

        sendEmailButton.click();


        return this;
    }

    public String getResultVMclass() {
        return resultVM.getText().split(":")[1].trim();
    }

    public String getResultInstanceType() {
        return resultInstanceType.getText().split(":")[1].trim();
    }

    public String getResultRegion() {
        return resultRegion.getText().split(":")[1].trim();
    }

    public String getResultCostMonth() {
        return resultCostMonth.getText();
    }

    public String getTotalCost() {
        return resultTotalCost.getText();
    }


}
