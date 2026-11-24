
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
| 5 | [Enter the actual result after clicking Love it!] | Pass |
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
- Step 4.7, the line of the admin list that shows your pizza:
- Step 4.8, run 1, result, duration and pizza name:
- Step 4.8, run 2, result, duration and pizza name:
- Step 4.9 (optional), browser and result:

## Part B: Reflection

### 1. Max Calories per Slice locator

The Max Calories per Slice field is located through its label because the page uses the same ID, `floating_filled`, for multiple number input fields. Using that ID could make Selenium select the wrong input. The test uses the label text to identify the correct field more clearly. The page developer could give this input a unique ID, such as `max-calories`. This would make the locator shorter and more reliable for testers.

### 2. Test oracle in Step 7

Step 7 uses a consistency oracle because it compares the tool in the recommendation with the tool stored in the admin list. Both values should be the same for the same pizza. For example, if the recommendation shows `Scissors` but the admin list shows `Knife`, the test will detect the mismatch. This can catch a fault where QuickPizza saves or displays the wrong tool in the admin list.

### 3. Checking whether the pizza is vegetarian

The test could check that the vegetarian option is selected before requesting a pizza. However, this only confirms that the option was selected, not that the recommended pizza is actually vegetarian. A stronger check would verify the ingredients of the recommended pizza and confirm that none of them contain meat. The test would need the ingredient information in the recommendation or access to another reliable source, such as the application's recipe data.

### 6. Part 2 – Selenium Page Object Model

If QuickPizza changes the locator of the calories box, I have to update the locator in `QP01Test` because it directly finds the element using XPath. In `QP01PageObjectTest`, I do not need to change the test itself because the locator is inside the `HomePage` class. I only need to update the `MAX_CALORIES` locator in `HomePage.java`. This makes the repair smaller and easier because the page object keeps the page locators in one place.