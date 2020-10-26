package googlecloud;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        ResultGooglePage resultGooglePage = new HomeGoogleCloudPage(driver).open()
        .searchByText("Google Cloud Platform Pricing Calculator");

        CalculatorGooglePage calculatorGooglePage = resultGooglePage.chooseCalculatorLink();

        /*driver.get("https://cloud.google.com/products/calculator");*/
        //https://cloudpricingcalculator.appspot.com/
        calculatorGooglePage.inputInstances(4).selectE2Standart8_MachineType()
        .selectFrankfurtLocation().selectCommittedUsage_1Year().addGPU4NvidiaV100()
                .selectOneNode().addSsd375GB().estimateInstancesField().estimateNodesField();

        String totalEstimate = calculatorGooglePage.getTotalCost();

        calculatorGooglePage.emailEstimate();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open('https://10minutemail.com/');");

        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(handles.get(1));

        EmailPage emailPage = new EmailPage(driver);

        String email = emailPage.getAdress();
        System.out.println(email);
        driver.switchTo().window(handles.get(0));

        calculatorGooglePage.switchFrame().sendToEmail(email);

        driver.switchTo().window(handles.get(1));


        String totalEstimateFromEmail = emailPage.getEstimateFromMessage();
        System.out.println(totalEstimate);
        System.out.println(totalEstimateFromEmail);






       /* ResultGooglePage resultGooglePage = new HomeGoogleCloudPage(driver).open()
                .searchByText("Google Cloud Platform Pricing Calculator");

        CalculatorGooglePage calculatorGooglePage = resultGooglePage.chooseCalculatorLink();

        calculatorGooglePage.inputInstances(4).selectE2Standart8_MachineType()
                .selectFrankfurtLocation().selectCommittedUsage_1Year().addGPU4NvidiaV100()
                .selectOneNode().addSsd375GB().estimateInstancesField().estimateNodesField();*/

        //9,616.33




    }

    public static String getCostFromStringEstimate(String s){
        String cost = null;
        String[] splitted = s.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            if(splitted[i].equals("USD")){
                cost = splitted[i + 1];
                break;
            }
        }

        return cost;
    }


}
