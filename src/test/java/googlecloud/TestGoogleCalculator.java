package googlecloud;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestGoogleCalculator {
    private WebDriver driver;
    private CalculatorGooglePage calculatorGooglePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        calculatorGooglePage = addOptionsToCalculatorAndEstimateAll();
    }

    @Test
    public void checkVMClass() {
        Assert.assertEquals(calculatorGooglePage.getResultVMclass(), "regular");
    }

    @Test
    public void checkInstanceType() {
        Assert.assertEquals(calculatorGooglePage.getResultInstanceType(), "e2-standard-8");
    }

    @Test
    public void checkRegion() {
        Assert.assertEquals(calculatorGooglePage.getResultRegion(), "Frankfurt");
    }

    @Test
    public void checkCostPerMonth() {
        String[] splitted = calculatorGooglePage.getResultCostMonth().split(" ");
        double cost = 0;
        for (String s : splitted) {
            try {
                cost = Double.parseDouble(s);
                break;
            } catch (NumberFormatException ignored) {
            }
        }

        Assert.assertEquals(cost, 635.29);
    }

    @Test
    public void checkCostFromEmail() {

        String totalEstimate = calculatorGooglePage.getTotalCost();

        calculatorGooglePage.emailEstimate();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open('https://10minutemail.com/');");

        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(handles.get(1));

        EmailPage emailPage = new EmailPage(driver);

        String emailAddress = emailPage.getAddress();
        driver.switchTo().window(handles.get(0));

        calculatorGooglePage.switchFrame().sendToEmail(emailAddress);


        driver.switchTo().window(handles.get(1));

        String totalEstimateFromEmail = emailPage.getEstimateFromMessage();

        Assert.assertEquals(getCostFromStringEstimate(totalEstimate),
                getCostFromStringEstimate(totalEstimateFromEmail));
    }


    private CalculatorGooglePage addOptionsToCalculatorAndEstimateAll() {
        ResultGooglePage resultGooglePage = new HomeGoogleCloudPage(driver).open()
                .searchByText("Google Cloud Platform Pricing Calculator");

        calculatorGooglePage = resultGooglePage.chooseCalculatorLink();

        return calculatorGooglePage.inputInstances(4)
                .selectE2Standart8_MachineType()
                .selectFrankfurtLocation()
                .selectCommittedUsage_1Year()
                .addGPU4NvidiaV100()
                .selectOneNode()
                .addSsd375GB()
                .estimateInstancesField()
                .estimateNodesField();
    }

    private static String getCostFromStringEstimate(String s) {
        String cost = null;
        String[] splitted = s.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals("USD")) {
                cost = splitted[i + 1];
                break;
            }
        }

        return cost;
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
