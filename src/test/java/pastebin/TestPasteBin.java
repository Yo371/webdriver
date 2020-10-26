package pastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

public class TestPasteBin {

    private WebDriver driver;
    private CreatedPastPage createdPastPage;
    private final String gitString = "git config --global user.name \"New Sheriff in Town\"" +
            "\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
            "\ngit push origin master --force";
    private final String title = "How to gain dominance among developers";

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        createdPastPage = fillTheFormAndCreatePaste();
    }

    @Test
    public void checkMatchingTitleWithPasteName() {

        Assert.assertTrue(driver.getTitle().contains(title));
    }

    @Test
    public void checkHighlightedGitText() {

        int amountOfHighlighted = createdPastPage.getListOfFontColorsOfGitText()
                .stream().filter(e -> e.equals("rgba(194, 12, 185, 1)"))
                .collect(Collectors.toList()).size();

        Assert.assertEquals(amountOfHighlighted,
                createdPastPage.getListOfFontColorsOfGitText().size());
    }

    @Test
    public void checkEqualsCode() {

        Assert.assertEquals(gitString, createdPastPage.getTextFromRawPasteData());
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }


    private CreatedPastPage fillTheFormAndCreatePaste() {
        PasteBinHomePage pasteBinHomePage = new PasteBinHomePage(driver);
        pasteBinHomePage.open();
        return pasteBinHomePage.inputToTextArea(gitString)
                .chooseBashSyntaxHighlight()
                .chooseExpiration10Min()
                .inputToTitleArea(title)
                .createPaste();
    }
}
