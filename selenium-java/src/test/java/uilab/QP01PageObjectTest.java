
package uilab;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import uilab.pages.AdminPage;
import uilab.pages.HomePage;

class QP01PageObjectTest extends BaseTest {

    @Test
    void customPizzaReachesTheAdminList() {
        String stamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String pizzaName = "Lab Pizza " + stamp;

        HomePage home = new HomePage(driver, wait);
        AdminPage admin = new AdminPage(driver, wait);

        // Step 1: open QuickPizza
        home.open();

        // Step 2: turn on Advanced
        home.turnOnAdvanced();

        // Step 3: enter the restrictions
        home.setRestrictions("600", "Knife", pizzaName);        shot("step-3");

        // Step 4: ask for a pizza
        String recommendation = home.askForPizza();
        shot("step-4");

        assertTrue(recommendation.contains("Name: " + pizzaName));

        String tool = recommendation.lines()
                .filter(line -> line.startsWith("Tool: "))
                .map(line -> line.substring("Tool: ".length()).trim())
                .findFirst()
                .orElseThrow();

        String caloriesText = recommendation.lines()
                .filter(line -> line.startsWith("Calories per slice: "))
                .map(line -> line.substring(
                        "Calories per slice: ".length()).trim())
                .findFirst()
                .orElseThrow();

        int calories = Integer.parseInt(caloriesText);

        assertNotEquals("Knife", tool);
        assertTrue(calories <= 600);

        // Step 5: rate without logging in
        home.rateWithoutLogin();
        shot("step-5");

        assertTrue(home.rateMessage().contains("Please log in first."));

        // Step 6: log in as admin
        admin.open();
        admin.logIn("admin", "admin");

        // Step 7: find the pizza in the admin list
        String entry = admin.lineFor(pizzaName);
        shot("step-7");

        assertTrue(entry.contains("(tool=" + tool + ","));
    }
}
