
package uilab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String URL = "https://quickpizza.grafana.com/";
    private static final By HEADING = By.tagName("h1");
    private static final By ADVANCED =
            By.xpath("//label[.//span[normalize-space()='Advanced']]");
    private static final By MAX_CALORIES = By.xpath(
            "//label[normalize-space()='Max Calories per Slice']"
                    + "/preceding-sibling::input");
    private static final By TOOLS = By.tagName("select");
    private static final By VEGETARIAN = By.id("default-checkbox");
    private static final By PIZZA_NAME =
            By.cssSelector("input#pizza-name");
    private static final By PIZZA_PLEASE = By.name("pizza-please");
    private static final By RESULT = By.id("recommendations");
    private static final By LOVE_IT = By.name("rate-5");
    private static final By RATE_RESULT = By.id("rate-result");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                HEADING, "Looking to break out of your pizza routine?"));
    }

    public void turnOnAdvanced() {
        wait.until(ExpectedConditions.elementToBeClickable(ADVANCED))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                MAX_CALORIES));
    }

    public void setRestrictions(String calories, String tool,
                                String pizzaName) {
        WebElement calorieBox = driver.findElement(MAX_CALORIES);
        calorieBox.clear();
        calorieBox.sendKeys(calories);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select/option[normalize-space()='Knife']")));

        new Select(driver.findElement(TOOLS))
                .selectByVisibleText(tool);

        WebElement vegetarian = driver.findElement(VEGETARIAN);
        if (!vegetarian.isSelected()) {
            vegetarian.click();
        }

        WebElement name = driver.findElement(PIZZA_NAME);
        name.clear();
        name.sendKeys(pizzaName);
    }

    public String askForPizza() {
        driver.findElement(PIZZA_PLEASE).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                RESULT, "Name:"));

        return driver.findElement(RESULT).getText();
    }

    public void rateWithoutLogin() {
        driver.findElement(LOVE_IT).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                RATE_RESULT, "Please log in first."));
    }

    public String rateMessage() {
        return driver.findElement(RATE_RESULT).getText();
    }
}
