
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

- node --version:
- npm --version:
- npx cypress version:
- Step 4.3, failure message for the wrong title:
- Step 4.6, tool and calories in the answer of POST /api/pizza:
- Step 4.7, the line of the admin list that shows your pizza:
- Step 4.8, run 1, result, duration and pizza name:
- Step 4.8, run 2, result, duration and pizza name:
- Step 4.9 (optional), browser and result:

## Part B

-
-
-
-
-
-