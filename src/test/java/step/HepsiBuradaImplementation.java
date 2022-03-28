package step;

import com.thoughtworks.gauge.Step;
import locator.HepsiBuradaLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.TestException;
import java.util.List;



public class HepsiBuradaImplementation extends BaseImplementation {


    public HepsiBuradaLocator locator = new HepsiBuradaLocator();

    @Step("<key> elementine tiklanir")
    public void clickOnElement(String key) {
        By selector = locator.get(key);
        clickElement(selector);
        logger.info("'{}' elementine tiklandi ", key);

    }

    @Step("<key> elementi uzerine gelinir")
    public void hoverOverElement(String key) {
        By selector = locator.get(key);
        hoverToThenClick(selector);
        logger.info("'{}' elementi uzerine gelindi ", key);
    }

    @Step("<key> input alanina <value> girilir")
    public void sendKeys(String key, String value) {
        By selector = locator.get(key);

        if (System.getenv(value) != null) {
            value = System.getenv(value);
        }
        sendKeys(selector, value);
        logger.info("'{}' input alanina '{}' yazildi", key, value);
    }


    @Step("<key> elementinin <text> icerdigini kontrol et")
    public void verifyValues(String key, String expectedText) {
        By selector = locator.get(key);
        String actualTitle = findElement(selector).getText();
        logger.info(key + " elementinin " + actualTitle + " icerdigi dogrulanmasi");
        if (!expectedText.equals(actualTitle)) {
            throw new TestException(key + " elementinin degeri ['" + actualTitle + "']. Beklenen deger ['" + expectedText + "'].");
        }
    }

    @Step("<key> elementlerinden birine random tiklanir")
    public void clickRandomElement(String key) {
        By selector = locator.get(key);
        List<WebElement> elements = findElements(selector);
        int elementNum = generateRandomNumber(elements.size());
        elements.get(elementNum).click();

    }

    private int generateRandomNumber(int num) {
        return (int) (Math.random() * num);
    }

    @Step("Yeni tabda devam et")
    public void focusNewTab(){
        String currentTab = _driver.getWindowHandle();
        for (String tab : _driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                _driver.switchTo().window(tab);
            }
        }

    }

    @Step("Kullanici <title> sitesini ziyaret eder")
    public void checkWebSite(String title){
        String actualTitle = _driver.getTitle();
        logger.info(title + " basligi " + actualTitle + " icerdigi dogrulanmasi");
        if (!actualTitle.contains(title)) {
            throw new TestException(title + " basliginin degeri ['" + actualTitle + "']. Beklenen deger ['" + title + "'].");
        }


    }
}
