package locator;

import exception.SelectorNotFoundException;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class HepsiBuradaLocator {
    private final Map<String, By> locator;


    public HepsiBuradaLocator() {
        locator = new HashMap<>();
        init();
    }

    public By get(String key) {
        By selector = locator.get(key);
        if( selector != null){
            return selector;
        }else{
            throw new SelectorNotFoundException("The following selector value was not found: " + key);
        }


    }

    private void init() {
        locator.put("siparislerim", By.xpath("(//*[@title='Siparişlerim'])[1]"));
        locator.put("HESABIM", By.id("myAccount"));
        locator.put("GIRIS YAP", By.id("login"));
        locator.put("EMAIL INPUT", By.id("txtUserName"));
        locator.put("LOGIN BUTTON", By.id("btnLogin"));
        locator.put("PASSWORD INPUT", By.id("txtPassword"));
        locator.put("LOGIN EMAIL SELECT", By.id("btnEmailSelect"));
        locator.put("HESABIM TEXT", By.xpath("//span[.='Hesabım']"));
        locator.put("SEARCH INPUT", By.cssSelector("input.desktopOldAutosuggestTheme-input"));
        locator.put("SEARCH BUTTON", By.cssSelector("div.SearchBoxOld-buttonContainer"));
        locator.put("PRODUCT LIST", By.xpath("//li[@class='productListContent-item']"));
        locator.put("ADD TO CART", By.id("addToCart"));
        locator.put("CLOSE POPUP", By.cssSelector("a.checkoutui-Modal-2iZXl"));
        locator.put("ADD ANOTHER SALE TO CART", By.xpath("(//span[.='Sepete Ekle'])[2]"));
        locator.put("SHOPPING CART", By.id("shoppingCart"));
        locator.put("BASKET ITEM COUNT", By.id("basket-item-count"));

    }
}
