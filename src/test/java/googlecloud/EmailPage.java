package googlecloud;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pastebin.CustomCondition;

public class EmailPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='mail_address']")
    private WebElement emailAdress;

    @FindBy(xpath = "//*[@id=\"mail_messages_content\"]")
    private WebElement message;

    @FindBy(xpath = "//*[@id=\"inbox_count_number\"]")
    private WebElement inboxCountNumber;

    @FindBy(xpath = "//td/h3[contains(text(), \"USD\")]")
    private WebElement resultEstimate;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAdress() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBeNotEmpty(emailAdress, "value"));
        return emailAdress.getAttribute("value");
    }

    public String getEstimateFromMessage() {

        try {
            new WebDriverWait(driver, 20)
                    .until(CustomCondition.waitForEmail(inboxCountNumber));
        } catch (TimeoutException e) {
            driver.navigate().refresh();
            new WebDriverWait(driver, 20)
                    .until(CustomCondition.waitForEmail(inboxCountNumber));
        }

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(message));

        message.click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(resultEstimate));

        return resultEstimate.getText();
    }
}
