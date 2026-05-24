<div align="center">

# 🛒 OpenCart E2E Testing Project

### Full-Spectrum Quality Assurance Suite

*Manual Testing · Database Testing · UI Automation · API Testing*

---

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.20.0-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-FF6C37?style=for-the-badge)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Postman](https://img.shields.io/badge/Postman-API_Tests-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-6A0DAD?style=for-the-badge)](https://www.extentreports.com/)

---

**Target Application:** [`https://awesomeqa.com/ui/index.php?route=common/home`](https://awesomeqa.com/ui/index.php?route=common/home)

📁 **[All Project Artifacts on Google Drive](https://drive.google.com/drive/folders/1ug7ITtV8Dn9s4WlnjtPvT_0yCogAPTXW?usp=sharing)**

</div>

---

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Testing Layers at a Glance](#-testing-layers-at-a-glance)
- [Test Strategy & Pyramid](#-test-strategy--pyramid)
- [Project Structure](#-project-structure)
- [Tech Stack](#-tech-stack)
- [Architecture & Design Patterns](#-architecture--design-patterns)
- [Test Coverage](#-test-coverage)
- [Database Testing](#-database-testing)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#-setup--installation)
- [Running the Tests](#-running-the-tests)
- [Test Reports](#-test-reports)
- [Manual Testing Artifacts](#-manual-testing-artifacts)
- [API Testing Artifacts](#-api-testing-artifacts)
- [Team](#-team)

---

## 🔍 Project Overview

This repository is a **full-spectrum QA project** targeting the OpenCart e-commerce platform. It covers the complete software testing lifecycle — from requirements definition and test planning, through manual test execution and database validation, to automated UI regression and REST API testing.

The project demonstrates industry-standard QA practices across **four independent testing layers**, each with its own documentation, test cases, bug reports, and execution summaries.

| Layer | Scope | Tooling |
|---|---|---|
| **Manual** | Test Plan · User Stories · Test Cases · Bug Reports | Excel · PDF |
| **Database** | Schema validation · Data integrity · SQL queries | SQLite · Excel |
| **Automation** | Component · Integration · End-to-End UI | Java · Selenium · TestNG |
| **API** | REST endpoint validation · Postman collections | Postman · Excel |

---

## 🧪 Testing Layers at a Glance

| Layer | Test Cases | Bugs Found | Status |
|---|:---:|:---:|:---:|
| Manual | See `OC-TEST CAUSE.xlsx` | See `OC-BUG REPORT.xlsx` | ✅ Executed |
| Database | See `OC_DB_TEST_CAUSE.xlsx` | See `OC-DB-Bug-Report.xlsx` | ✅ Executed |
| Automation | 18 test classes (Component + Integration + E2E) | N/A | ✅ Implemented |
| API | See `OC-API-TEST_CAUSE.xlsx` | See `OC-API-Bug-Report.xlsx` | ✅ Executed |

---

## 🎯 Test Strategy & Pyramid

The automation framework implements the classic **testing pyramid**:

```
                   ┌─────────────────┐
                   │   E2E Tests (1) │   ← Full 13-step business journey
                   └─────────────────┘
                ┌────────────────────────┐
                │   Integration (6)      │   ← Cross-module user flows
                └────────────────────────┘
         ┌────────────────────────────────────┐
         │      Component Tests (11)          │   ← Isolated feature verification
         └────────────────────────────────────┘
```

- **Component tests** validate individual UI modules in isolation (login, register, search, cart, etc.)
- **Integration tests** verify that multiple modules interact correctly across realistic user flows
- **E2E test** simulates a complete real-world shopping journey from currency selection to order confirmation
- **Database tests** validate data integrity, schema correctness, and business rule enforcement at the data layer
- **API tests** verify REST endpoints independently of the UI, covering all HTTP methods

---

## 📁 Project Structure

```
OpenCart-E2E-Testing/
│
├── 📂 Manual/                                   ← Manual QA artifacts
│   ├── OC-TEST PLAN.pdf                         ← Test strategy, scope & schedule
│   ├── OC-TEST CAUSE.xlsx                       ← Full test case repository with steps
│   ├── OC-TEST SUMMARY.xlsx                     ← Execution metrics & pass/fail summary
│   ├── OC-BUG REPORT.xlsx                       ← Defect log with severity & priority
│   ├── Traceability Matrix.xlsx                 ← Requirements ↔ test cases mapping
│   ├── Bug_Evidences/                           ← Screenshots & screen recordings (23 files)
│   │   ├── OC_AUTH_BUG_001.mp4 – 004.mp4       ← Video recordings
│   │   └── OC_AUTH_BUG_005.png – 023.png       ← Screenshots
│   ├── OC-Test Scenario/                        ← High-level test scenarios
│   │   ├── OC-LOG-Test Scenario.pdf             ← Login module scenarios
│   │   └── OC-REG-Test Scenario.pdf             ← Register module scenarios
│   └── OC-USER STORY/                           ← User stories per feature
│       ├── OC-REG-User-Stories.pdf              ← Registration
│       ├── OC-LOG-User-Stories.pdf              ← Login
│       ├── OC-Compare-User-Stories.pdf          ← Product comparison
│       ├── OC-Search-User-Stories.pdf           ← Search
│       ├── OC-Shopping Cart-User-Stories.pdf    ← Cart
│       └── OC-Wishlist-User-Stories.pdf         ← Wishlist
│
├── 📂 DB/                                       ← Database testing artifacts
│   ├── OpenCart - DB Schema.pdf                 ← Full database schema reference
│   ├── OC_DB_TEST_CAUSE.xlsx                    ← DB test cases with SQL queries
│   ├── OC-DB-TEST_SUMMARY.xlsx                  ← DB execution summary & metrics
│   ├── OC-DB-Bug-Report.xlsx                    ← Database-level defect log
│   └── opencart.db                              ← SQLite database snapshot
│
├── 📂 Api/                                      ← API testing artifacts
│   ├── OC-API-Documentation.pdf                 ← Complete REST API reference
│   ├── OC-API-TEST_CAUSE.xlsx                   ← API test cases (all HTTP methods)
│   ├── OC-API-TEST_SUMMARY.xlsx                 ← API execution summary
│   ├── OC-API-Bug-Report.xlsx                   ← API-specific defect log
│   ├── OpenCart API.postman_collection.json     ← Ready-to-import Postman collection
│   └── OpenCart Local Environment.postman_environment.json
│
├── 📂 Automation/                               ← Selenium automation framework
│   ├── pom.xml                                  ← Maven dependencies & build config
│   ├── testng.xml                               ← Test suite orchestration (18 classes)
│   ├── Reports/
│   │   └── ExtentReport.html                   ← Generated interactive HTML report
│   │
│   └── src/
│       ├── main/java/
│       │   ├── pages/                           ← Page Object Model (POM)
│       │   │   ├── account/
│       │   │   │   ├── LoginPage.java
│       │   │   │   ├── RegisterPage.java
│       │   │   │   └── ForgotPasswordPage.java
│       │   │   ├── cart/
│       │   │   │   └── CartPage.java
│       │   │   ├── catalog/
│       │   │   │   ├── NavigationPage.java
│       │   │   │   ├── ProductPage.java
│       │   │   │   ├── SearchPage.java
│       │   │   │   └── ComparisonPage.java
│       │   │   ├── checkout/
│       │   │   │   └── CheckoutPage.java
│       │   │   ├── home/
│       │   │   │   ├── HomePage.java
│       │   │   │   └── CurrencyDropdown.java
│       │   │   └── wishlist/
│       │   │       └── WishlistPage.java
│       │   │
│       │   └── utils/
│       │       └── BaseTest.java               ← Central lifecycle manager
│       │
│       └── test/java/
│           ├── component/                       ← Feature-level isolated tests
│           │   ├── account/
│           │   │   ├── LoginTest.java
│           │   │   ├── RegisterTest.java
│           │   │   └── ForgotPasswordTest.java
│           │   ├── cart/
│           │   │   └── CartTest.java
│           │   ├── catalog/
│           │   │   ├── NavigationTest.java
│           │   │   └── ComparisonTest.java
│           │   ├── checkout/
│           │   │   └── CheckoutTest.java
│           │   ├── header/
│           │   │   ├── CurrencyTest.java
│           │   │   ├── NavigationHoverTest.java
│           │   │   └── SearchTest.java
│           │   └── wishlist/
│           │       └── WishlistTest.java
│           ├── integration/                     ← Cross-feature flow tests
│           │   ├── RegisterThenLoginTest.java
│           │   ├── LoginThenSearchThenAddToCartTest.java
│           │   ├── LoginThenAddToCartThenCheckoutTest.java
│           │   ├── LoginThenWishlistThenViewProductTest.java
│           │   ├── SearchThenCompareProductsTest.java
│           │   └── CurrencySwitchThenVerifyProductPriceTest.java
│           └── e2e/
│               └── E2ETest.java                ← Full 13-step shopping journey
│
├── 📂 Reports/                                  ← Top-level report output
│   └── ExtentReport.html
│
└── 📄 Project Presentation.pptx                ← Project summary presentation
```

---

## 🛠 Tech Stack

| Component | Technology | Version |
|---|---|---|
| Language | Java | 21 |
| Browser Automation | Selenium WebDriver | 4.20.0 |
| Test Framework | TestNG | 7.10.2 |
| Reporting | ExtentReports (SparkReporter) | 5.1.1 |
| Driver Management | WebDriverManager | 5.8.0 |
| Build Tool | Apache Maven | 3.x |
| API Testing | Postman | Latest |
| Database | SQLite | — |
| Browser | Google Chrome | Latest |
| IDE | IntelliJ IDEA | — |

---

## 🏗 Architecture & Design Patterns

### Page Object Model (POM)

Every UI screen is encapsulated in a dedicated Page class following strict separation of concerns:

- **Locators** declared as `private final By` fields — never hardcoded inline
- **Action methods** that perform user interactions (e.g., `loginWith(email, password)`)
- **Assertion helpers** that return `boolean` or `String` — `Assert` is never called inside page classes

```java
// LoginPage — clean POM example
public class LoginPage {
    private final By emailField    = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By loginButton   = By.cssSelector("input[value='Login']");

    public void loginWith(String email, String password) { ... }
    public boolean isErrorDisplayed()                    { ... }
    public boolean isMyAccountHeadingDisplayed()         { ... }
}
```

---

### BaseTest — Centralised Lifecycle Management

All test classes extend `BaseTest`, which manages the full test lifecycle and reporting:

| Hook | Responsibility |
|---|---|
| `@BeforeSuite` | Initialises ExtentReports + system metadata (testers, environment) |
| `@BeforeMethod` | Launches ChromeDriver, maximises window, navigates to base URL, creates test entry in report |
| `@AfterMethod` | Logs PASS / FAIL / SKIP status to report, captures throwable on failure, quits browser |
| `@AfterSuite` | Flushes report to disk |

```
TestClass
   └── extends BaseTest
           ├── @BeforeSuite  → setupReport()
           ├── @BeforeMethod → setUp(Method)    ← fresh browser per test method
           ├── @AfterMethod  → tearDown(result) ← logs result + quits driver
           └── @AfterSuite   → flushReport()
```

---

### Explicit Waits Strategy

- `WebDriverWait` with a **10-second timeout** is used consistently across all page objects
- `ExpectedConditions` handles visibility, clickability, URL transitions, and text presence
- **Lambda predicates** handle dynamic UI elements (e.g., region dropdown population after country selection)
- `Thread.sleep` is reserved only for browser animation synchronisation where condition-based waits are insufficient

---

### Test Data Strategy

- **Dynamic email generation** via `UUID` prevents registration conflicts across parallel or repeated runs
- Credential constants are defined at the test class level for readability and easy maintenance
- The suite is **self-contained** — no external data files, no database seeding required

---

## ✅ Test Coverage

### Component Tests — 11 Classes

| Module | Test Class | Scenarios Covered |
|---|---|---|
| Account – Register | `RegisterTest` | Valid registration, missing required fields |
| Account – Login | `LoginTest` | Valid credentials, invalid credentials |
| Account – Forgot Password | `ForgotPasswordTest` | Known email submission, confirmation message |
| Header – Currency | `CurrencyTest` | Switch to EUR / GBP / USD, verify price reflection |
| Header – Navigation Hover | `NavigationHoverTest` | Mega-menu hover, subcategory navigation |
| Header – Search | `SearchTest` | Keyword search, results display validation |
| Catalog – Navigation | `NavigationTest` | Category page navigation and breadcrumb |
| Catalog – Comparison | `ComparisonTest` | Add to compare, compare page, remove product |
| Cart | `CartTest` | Add product with all option types, quantity change, remove |
| Wishlist | `WishlistTest` | Add to wishlist, unauthenticated redirect guard |
| Checkout | `CheckoutTest` | Guest checkout, T&C validation, order confirmation |

---

### Integration Tests — 6 Classes

| User Flow | Test Class |
|---|---|
| Register → Login | `RegisterThenLoginTest` |
| Login → Search → Add to Cart | `LoginThenSearchThenAddToCartTest` |
| Login → Add to Cart → Checkout | `LoginThenAddToCartThenCheckoutTest` |
| Login → Wishlist → View Product | `LoginThenWishlistThenViewProductTest` |
| Search → Compare Products | `SearchThenCompareProductsTest` |
| Currency Switch → Verify Product Price | `CurrencySwitchThenVerifyProductPriceTest` |

---

### E2E Test — 1 Comprehensive Scenario

`E2ETest.java` — **13-step full shopping journey:**

```
STEP  1  Currency switch EUR → USD, price verification
STEP  2  Top-nav hover, navigate to Tablets category
STEP  3  Add product to comparison list, verify success alert
STEP  4  Navigate to Comparison page, remove product
STEP  5  Register new account with UUID-generated dynamic email
STEP  6  Logout
STEP  7  Forgot Password flow with known email
STEP  8  Login with newly registered account credentials
STEP  9  Search for product (HTC Touch HD)
STEP 10  Add search result to cart, verify success alert
STEP 11  Wishlist button — verify correct authentication redirect
STEP 12  Guest checkout — billing, shipping, payment method selection
STEP 13  Verify order confirmation page header and body text
```

---

## 🗄 Database Testing

The `DB/` folder contains a complete database-level testing artefact set built on the OpenCart SQLite schema.

### What Was Tested

- **Schema validation** — verifying table structures, column types, constraints, and foreign keys against the documented schema (`OpenCart - DB Schema.pdf`)
- **Data integrity checks** — ensuring referential integrity between related tables (orders ↔ customers, products ↔ categories)
- **Business rule enforcement** — validating that DB-level constraints reflect expected business logic (e.g., price > 0, required fields NOT NULL)
- **SQL query verification** — executing test queries to confirm correct data retrieval for key application workflows

### DB Artefacts

| Artefact | Description |
|---|---|
| `OpenCart - DB Schema.pdf` | Full entity-relationship schema and table reference |
| `OC_DB_TEST_CAUSE.xlsx` | Test cases with SQL queries, expected vs actual results |
| `OC-DB-TEST_SUMMARY.xlsx` | Execution metrics: total / passed / failed counts |
| `OC-DB-Bug-Report.xlsx` | Database-level defect log with reproduction steps |
| `opencart.db` | SQLite database snapshot used during testing |

---

## 💻 Prerequisites

| Requirement | Version | Verify |
|---|---|---|
| Java JDK | 21+ | `java -version` |
| Apache Maven | 3.x | `mvn -version` |
| Google Chrome | Latest stable | — |
| Internet access | — | Reach `awesomeqa.com` |

> **Note:** ChromeDriver is managed automatically by **WebDriverManager** (bundled dependency). No manual driver download is required.

---

## ⚙️ Setup & Installation

```bash
# 1. Clone the repository
git clone https://github.com/MohamedAhmed/OpenCart-E2E-Testing.git
cd OpenCart-E2E-Testing

# 2. Navigate to the automation module
cd Automation

# 3. Resolve all Maven dependencies
mvn dependency:resolve
```

---

## ▶️ Running the Tests

### Run the Full Suite (18 test classes via testng.xml)

```bash
cd Automation
mvn test
```

### Run a Specific Test Class

```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=E2ETest
mvn test -Dtest=RegisterThenLoginTest
```

### Suite Execution Order (defined in testng.xml)

```
 1.  Component – Account – Registration
 2.  Component – Account – Login
 3.  Component – Account – Forgot Password
 4.  Component – Header – Currency
 5.  Component – Header – Navigation Hover
 6.  Component – Header – Search
 7.  Component – Catalog – Navigation
 8.  Component – Catalog – Product Comparison
 9.  Component – Cart – Operations
10.  Component – Wishlist – Operations
11.  Component – Checkout – Order Placement
12.  Integration – Register Then Login
13.  Integration – Login Then Search Then Add To Cart
14.  Integration – Search Then Compare Products
15.  Integration – Login Then Add To Cart Then Checkout
16.  Integration – Currency Switch Then Verify Product Price
17.  Integration – Login Then Wishlist Then View Product
18.  E2E – Full Shopping Journey
```

---

## 📊 Test Reports

After execution, an interactive HTML report is auto-generated at:

```
Automation/Reports/ExtentReport.html
```

**Report features:**
- Pass / Fail / Skip status per test method
- Execution timestamps and total duration
- Step-level logs with inline status markers
- System information (testers, environment)
- Full failure stack traces captured automatically via `@AfterMethod`

**Opening the report:**

```bash
# macOS / Linux
open Automation/Reports/ExtentReport.html

# Windows
start Automation/Reports/ExtentReport.html
```

> The report is powered by **ExtentReports SparkReporter** and is fully self-contained — no server or internet connection required to view it.

---

## 📄 Manual Testing Artifacts

All manual testing documents are in the `Manual/` directory:

| Artefact | Description |
|---|---|
| `OC-TEST PLAN.pdf` | Test scope, objectives, entry/exit criteria, schedule, risk assessment |
| `OC-TEST CAUSE.xlsx` | Detailed test cases with steps, expected results, and actual results |
| `OC-TEST SUMMARY.xlsx` | Execution metrics: total / passed / failed / blocked counts |
| `OC-BUG REPORT.xlsx` | Defect register with severity, priority, status, and reproduction steps |
| `Traceability Matrix.xlsx` | Requirements-to-test-cases mapping ensuring full coverage traceability |
| `Bug_Evidences/` | 23 bug evidence files — 4 video recordings (MP4) + 19 screenshots (PNG) |
| `OC-Test Scenario/` | High-level test scenarios for Login (`OC-LOG`) and Register (`OC-REG`) modules |
| `OC-USER STORY/` | Per-feature user stories covering Register · Login · Search · Compare · Cart · Wishlist |

---

## 🔌 API Testing Artifacts

All API testing documents are in the `Api/` directory:

| Artefact | Description |
|---|---|
| `OC-API-Documentation.pdf` | Complete REST API reference for the OpenCart application |
| `OC-API-TEST_CAUSE.xlsx` | API test cases covering GET / POST / PUT / PATCH / DELETE |
| `OC-API-TEST_SUMMARY.xlsx` | API execution summary with pass/fail metrics |
| `OC-API-Bug-Report.xlsx` | API-specific defect log with response payloads |
| `OpenCart API.postman_collection.json` | Ready-to-import Postman collection |
| `OpenCart Local Environment.postman_environment.json` | Environment variables for local execution |

### Importing the Postman Collection

1. Open **Postman** → click **Import**
2. Select `Api/OpenCart API.postman_collection.json`
3. Also import `Api/OpenCart Local Environment.postman_environment.json`
4. Select the environment from the top-right dropdown
5. Run individual requests or use the **Collection Runner** for full batch execution

---

## 👥 Team

| Name | Role |
|---|---|
| **Mohamed Ahmed** | QA Automation Engineer |
| **Mariam Elgandour** | QA Engineer |

---

<div align="center">

**OpenCart E2E Testing Project** &nbsp;·&nbsp; Built with ☕ Java · Selenium · TestNG

*Covering the full QA lifecycle: Manual · Database · Automation · API*

</div>
