
# UI Lab Results

Tool parts that I chose: Part 2 and Part 4

## Part 0

- Step 0.1, Chrome version: 158.0.8127.89 (Official Build) (64-bit)
- Step 0.3, ChromeDriver version: 158.0.8127.88, C:\drivers\chromedriver-win64\chromedriver.exe
- Step 0.4, locator of Pizza, Please!: xpath=//button[normalize-space()='Pizza, Please!']
- Step 0.4, locator of Excluded tools: css=select
- Step 0.4, locator of Must be vegetarian: ss=input#default-checkbox
- Step 0.4, locator of Custom Pizza Name: css=input#pizza-name
- Step 0.5, pizza name, tool and calories of the run by hand:
  - Pizza name: Lab Pizza 20261124-2222
  - Tool: Scissors
  - Calories: 300 per slice

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | Opened QuickPizza and checked the page heading. | Pass |
| 2 | Opened Advanced and checked the four advanced fields. | Pass |
| 3 | Entered 600 calories, excluded Knife, selected vegetarian, and entered Lab Pizza 20261124-2222. | Pass |
| 4 | Received Lab Pizza 20261124-2222 with Scissors and 300 calories per slice. | Pass |
| 5 | Clicked Love it! without logging in. The page displayed "Please log in first." | Pass |
| 6 | Opened the admin page, logged in with admin/admin, and found "Latest pizza recommendations". | Pass |
| 7 | Refreshed the admin page and searched again, but Lab Pizza 20261124-2222 was not found. | Fail |

## Part 2: Selenium WebDriver in Java

- Step 2.4, the failure line for the wrong title: FirstTest.webFormOpens:24 expected: <Web Form> but was: <Web form>
- Step 2.15, QP01Test run 1: 1 test, 0 failures, 0 errors; BUILD SUCCESS; 7.763 s.
- Step 2.15, QP01Test run 2: 1 test, 0 failures, 0 errors; BUILD SUCCESS; 7.546 s.
- Step 2.20, full run 1: 5 tests, 0 failures, 0 errors, 0 skipped; BUILD SUCCESS; 21.367 s; finished 24 Nov 2026 23:43:48 (+07:00).
- Step 2.20, full run 2: 5 tests, 0 failures, 0 errors, 0 skipped; BUILD SUCCESS; 22.302 s; finished 24 Nov 2026 23:44:33 (+07:00).

## Part 4: Cypress
- node --version: v22.15.0
- npm --version: 10.9.2
- npx cypress version: 16.2.1
- Step 4.3, failure message for the wrong title: Timed out retrying after 10000ms; expected 'Web Form' but actual was 'Web form'.
- Step 4.3, corrected test: 1 passing, 0 failing; duration 2 seconds.
- Step 4.6, tool and calories in the answer of POST /api/pizza: Knife, 250 calories per slice; vegetarian: true.
- Step 4.7, the line of the admin list that shows your pizza: Lab Pizza 20261125-003932 (tool=Scissors, ingredients_number=5).
- Step 4.8, run 1, result, duration and pizza name: 1 passing, 0 failing; 8 seconds; Lab Pizza 20261125-004448.
- Step 4.8, run 2, result, duration and pizza name: 1 passing, 0 failing; 8 seconds; Lab Pizza 20261125-005029.
- Step 4.9 (optional), browser and result:

## Part B: Reflection

### 1. Max Calories per Slice locator

The Max Calories per Slice field is located through its label because the page uses the same ID, `floating_filled`, for multiple number input fields. If the test uses that ID, Selenium might select the wrong input. The page developer could give this field a unique ID, such as `max-calories`. This would make the locator shorter and more reliable because the tester could identify the correct field directly.

### 2. Test oracle in Step 7

Step 7 uses a consistency oracle because it compares the tool in the pizza recommendation with the tool stored in the admin list. Both values should be the same for the same pizza. For example, if the recommendation shows `Scissors` but the admin list shows `Knife`, the test will detect the mismatch. This could catch a fault where QuickPizza saves or displays the wrong tool in the admin list.

### 3. Checking whether the pizza is vegetarian

The test could check the vegetarian option and verify that the API response says `vegetarian: true`. It could also check that every ingredient in the response has `vegetarian: true`. In my API response, the pizza was marked as vegetarian, and all five ingredients had the vegetarian value set to true. The test would need the vegetarian information from the API response or another reliable source, such as the application's recipe data.

### 4. Comparing Selenium and Cypress

In my Selenium runs, five tests passed in 21.367 and 22.302 seconds, while the Cypress QP-01 test passed in 8 seconds.
Selenium uses explicit waits with `WebDriverWait`, while Cypress automatically retries commands and assertions until they pass or reach the 10-second timeout. Cypress also allowed me to wait for the pizza API request directly using `cy.intercept()` and `cy.wait()`. I found Cypress quicker to write for QP-01 because most actions and checks could be written in one test file, while Selenium required page object classes and separate Java methods. Cypress gave a clear report, such as 1 passing and 0 failing in 8 seconds, while Selenium provided Maven test reports with the number of tests and failures. Both tools were useful, but Cypress was easier to read and work with for this scenario.

### 5. Choosing a tool for Project 2

I would choose Cypress for the automated UI tests of Project 2. First, it was quicker to create and run the QP-01 scenario, with the test completing in about 8 seconds. Second, `cy.intercept()` made it easy to check the API request and response together with the page content. These features would help me write and maintain UI tests more easily.

### 6. Part 2 – Selenium Page Object Model

If QuickPizza changes the locator of the calories box, I have to update the locator in `QP01Test` because it directly finds the element using XPath. In `QP01PageObjectTest`, I do not need to change the test itself because the locator is inside the `HomePage` class. I only need to update the `MAX_CALORIES` locator in `HomePage.java`. This makes the repair smaller and easier because the page object keeps the page locators in one place.

### 7. Part 4 – Cypress and a single test

QP-01 has to be a single test because Cypress clears cookies before each test. The cookie is important because QuickPizza uses it to identify the server that stores the pizza recommendations. If Steps 6 and 7 were placed in a second test, Cypress could start with a different cookie and the admin page might show a different server's pizza list. As a result, the test might not find the pizza created in Steps 1 to 5. Keeping all seven steps in one test helps the scenario use the same session and verify the pizza correctly.
