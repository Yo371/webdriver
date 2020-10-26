package pastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();


        //1
        /*new PasteBinHomePage(driver).open().inputToTextArea("Hello from WebDriver")
        .chooseExpiration10Min().inputToTitleArea("helloweb");*/

        String gitString = "git config --global user.name \"New Sheriff in Town\"" +
                "\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
                "\ngit push origin master --force";
        String title = "How to gain dominance among developers";
        //2
         /*CreatedPastPage createdPastPage = new PasteBinHomePage(driver).open().inputToTextArea(gitString)
                .chooseBashSyntaxHighlight().chooseExpiration10Min()
                .inputToTitleArea(title).createPaste();*/


        driver.get("https://pastebin.com/syDxexcF");
        CreatedPastPage createdPastPage = new CreatedPastPage(driver);
        System.out.println(createdPastPage.getTextFromRawPasteData().equals(gitString));
        /*System.out.println(createdPastPage.listOfGitSpan);
        System.out.println(createdPastPage.getListOfFontColorsOfGitText());*/
        System.out.println(createdPastPage.getListOfFontColorsOfGitText().size());
        System.out.println(createdPastPage.getListOfFontColorsOfGitText()
                .stream().filter(e -> e.equals("rgba(194, 12, 185, 1)"))
                .collect(Collectors.toList()).size());







    }
}