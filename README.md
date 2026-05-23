# 🛒 OpenCart E2E Testing Project

> **Comprehensive Quality Assurance Suite** — Manual Testing · UI Automation · API Testing

[![Java](https://img.shields.io/badge/Java-23-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.20.0-43B02A?style=flat&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-FF6C37?style=flat)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=flat&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-blueviolet?style=flat)](https://www.extentreports.com/)

---

## ☁️ Project Drive

📁 **[View All Project Artifacts on Google Drive](https://drive.google.com/drive/folders/1ug7ITtV8Dn9s4WlnjtPvT_0yCogAPTXW?usp=sharing)**

> Contains manual test documents, bug evidence screenshots, user stories, API documentation, and execution reports.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Test Strategy](#-test-strategy)
- [Project Structure](#-project-structure)
- [Tech Stack](#-tech-stack)
- [Architecture & Design Patterns](#-architecture--design-patterns)
- [Test Coverage](#-test-coverage)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#-setup--installation)
- [Running the Tests](#-running-the-tests)
- [Test Reports](#-test-reports)
- [Manual Testing Artifacts](#-manual-testing-artifacts)
- [API Testing Artifacts](#-api-testing-artifacts)
- [Team](#-team)

---

## 🔍 Project Overview

This repository is a **full-spectrum QA project** targeting the OpenCart e-commerce platform. It covers the complete testing lifecycle — from test planning and manual test case execution to automated UI regression and REST API validation.

| Layer          | Scope | Tooling |
|----------------|---|---|
| **Manual**     | Test Plan · Test Cases · Bug Reports · User Stories | Excel / PDF |
| **Automation** | Component · Integration · End-to-End | Java · Selenium · TestNG |
| **API**        | REST endpoint validation · Postman collections | Postman · Excel reports |

**Target Application:** `https://awesomeqa.com/ui/index.php?route=common/home`

---

## 🎯 Test Strategy

The automation pyramid implemented in this project follows a three-layer approach:

```
         ┌──────────────────┐
         │   E2E Tests (1)  │   ← Full business journey (13 steps)
         └──────────────────┘
       ┌──────────────────────┐
       │  Integration (6)     │   ← Cross-module user flows
       └──────────────────────┘
    ┌──────────────────────────────┐
    │   Component Tests (11)       │   ← Isolated feature verification
    └──────────────────────────────┘
```

- **Component tests** validate individual UI modules in isolation (login, register, search, cart, etc.)
- **Integration tests** verify that multiple modules work correctly together across user flows
- **E2E test** simulates a complete real-world shopping journey from currency selection through order confirmation

---

## 📁 Project Structure

```
OpenCart-E2E-Testing/
│
├── 📂 Manual/                               ← Manual QA artifacts
│   ├── OC-TEST PLAN.pdf                     ← Test strategy & scope document
│   ├── OC-TEST CAUSE.xlsx                   ← Full test case repository
│   ├── OC-TEST SUMMARY.xlsx                 ← Execution summary & metrics
│   ├── OC-BUG REPORT.xlsx                   ← Defect log with evidence
│   └── Bug_Evidences/                       ← Screenshots & screen recordings
│
├── 📂 Api/                                  ← API testing artifacts
│   ├── OC-API-Documentation.pdf             ← API reference documentation
│   ├── OC-API-TEST_CAUSE.xlsx               ← API test cases
│   ├── OC-API-TEST_SUMMARY.xlsx             ← API execution summary
│   ├── OC-API-Bug-Report.xlsx               ← API defect log
│   ├── OpenCart API.postman_collection.json ← Ready-to-import Postman collection
│   └── OpenCart Local Environment.postman_environment.json
│
├── 📂 Automation/                           ← Selenium automation framework
│   ├── pom.xml                              ← Maven dependencies & build config
│   ├── testng.xml                           ← Test suite orchestration
│   ├── Reports/
│   │   └── ExtentReport.html               ← Generated HTML test report
│   │
│   └── src/
│       ├── main/java/
│       │   ├── pages/                       ← Page Object Model (POM)
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
│       │       └── BaseTest.java            ← Central test lifecycle manager
│       │
│       └── test/java/
│           ├── component/                   ← Feature-level unit tests
│           │   ├── account/
│           │   ├── cart/
│           │   ├── catalog/
│           │   ├── checkout/
│           │   ├── header/
│           │   └── wishlist/
│           │
│           ├── integration/                 ← Cross-feature flow tests
│           │
│           └── e2e/                         ← Full-journey scenario
│               └── ComprehensiveE2ETest.java
│
└── 📂 Reports/                              ← Top-level report output
    └── ExtentReport.html
```

---

## 🛠 Tech Stack

| Component | Technology | Version |
|---|---|---|
| Language | Java | 23 |
| Browser Automation | Selenium WebDriver | 4.20.0 |
| Test Framework | TestNG | 7.10.2 |
| Reporting | ExtentReports (Spark) | 5.1.1 |
| Build Tool | Apache Maven | 3.x |
| API Testing | Postman | Latest |
| Browser | Google Chrome | Latest |
| Driver Management | Selenium Manager (built-in) | 4.20+ |

---

## 🏗 Architecture & Design Patterns

### Page Object Model (POM)

Every UI screen is encapsulated in a dedicated Page class with:

- **Locators** declared as `private final By` fields at the top of the class
- **Action methods** that perform user interactions
- **Assertion helpers** that return `boolean` or `String` — never `Assert` inside page classes

```java
// Example: LoginPage encapsulation
public class LoginPage {
    private final By emailField    = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By loginButton   = By.cssSelector("input[value='Login']");

    public void loginWith(String email, String password) { ... }
    public boolean isErrorDisplayed()                    { ... }
    public boolean isMyAccountHeadingDisplayed()         { ... }
}
```

### BaseTest — Centralized Lifecycle Management

All test classes extend `BaseTest`, which handles:

| Hook | Responsibility |
|---|---|
| `@BeforeSuite` | Initialises ExtentReports + system metadata |
| `@BeforeMethod` | Launches ChromeDriver, maximises window, navigates to base URL, creates test entry in report |
| `@AfterMethod` | Logs PASS/FAIL/SKIP to report, quits browser |
| `@AfterSuite` | Flushes report to disk |

```
TestClass
   └── extends BaseTest
           ├── @BeforeSuite  → setupReport()
           ├── @BeforeMethod → setUp(Method)   ← new browser per test
           └── @AfterMethod  → tearDown(result)
```

### Explicit Waits Strategy

- `WebDriverWait` with a 10-second timeout is used consistently across all page objects
- `ExpectedConditions` is used for visibility, clickability, URL conditions, and custom Lambda predicates for dynamic dropdowns (e.g., region loading after country selection)
- `Thread.sleep` is used only where JavaScript animations or network delays cannot be handled by condition-based waits, and is minimised throughout

### Test Data Approach

- Dynamic email generation with `UUID` prevents test interference in registration flows
- Credential constants are defined at the test class level for readability
- No external data files are required — the suite is self-contained

---

## ✅ Test Coverage

### Component Tests (11 test classes)

| Module | Test Class | Scenarios Covered |
|---|---|---|
| Account – Register | `RegisterTest` | Valid registration, missing required fields |
| Account – Login | `LoginTest` | Valid credentials, invalid credentials |
| Account – Forgot Password | `ForgotPasswordTest` | Known email submission |
| Header – Currency | `CurrencyTest` | Switch to EUR/GBP/USD, price reflection |
| Header – Navigation Hover | `NavigationHoverTest` | Mega-menu hover & subcategory navigation |
| Header – Search | `SearchTest` | Keyword search, results display |
| Catalog – Navigation | `NavigationTest` | Category page navigation |
| Catalog – Comparison | `ComparisonTest` | Add to compare, compare page, remove |
| Cart | `CartTest` | Add product with all option types, remove from cart |
| Wishlist | `WishlistTest` | Add to wishlist, redirect-to-login guard |
| Checkout | `CheckoutTest` | Guest checkout, terms validation, order confirmation |

### Integration Tests (6 test classes)

| Flow | Test Class |
|---|---|
| Register → Login | `RegisterThenLoginTest` |
| Login → Search → Add to Cart | `LoginThenSearchThenAddToCartTest` |
| Login → Add to Cart → Checkout | `LoginThenAddToCartThenCheckoutTest` |
| Login → Wishlist → View Product | `LoginThenWishlistThenViewProductTest` |
| Search → Compare Products | `SearchThenCompareProductsTest` |
| Currency Switch → Verify Product Price | `CurrencySwitchThenVerifyProductPriceTest` |

### E2E Test (1 comprehensive scenario)

`ComprehensiveE2ETest` — **13-step full shopping journey**:

```
STEP 1  Currency switch EUR → USD, price verification
STEP 2  Top-nav hover, navigate to Tablets category
STEP 3  Add product to comparison list, verify success alert
STEP 4  Navigate to Comparison page, remove product
STEP 5  Register new account with dynamic email
STEP 6  Logout
STEP 7  Forgot Password flow with known email
STEP 8  Login with newly registered account
STEP 9  Search for product (HTC Touch HD)
STEP 10 Add search result to cart, verify success alert
STEP 11 Wishlist button click — verify correct redirect
STEP 12 Guest checkout — billing details, shipping, payment
STEP 13 Verify order confirmation page header & body text
```

---

## 💻 Prerequisites

Before running the automation suite, ensure the following are installed:

- **Java JDK 23** or later
  ```bash
  java -version
  ```
- **Apache Maven 3.x**
  ```bash
  mvn -version
  ```
- **Google Chrome** (latest stable)
- Internet access to reach `https://awesomeqa.com`

> **Note:** ChromeDriver is managed automatically by Selenium Manager (bundled with Selenium 4.20+). No manual driver download is required.

---

## ⚙️ Setup & Installation

```bash
# 1. Clone the repository
git clone https://github.com/your-org/OpenCart-E2E-Testing.git
cd OpenCart-E2E-Testing

# 2. Navigate to the automation module
cd Automation

# 3. Download dependencies
mvn dependency:resolve
```

---

## ▶️ Running the Tests

### Run Full Suite (all 18 test classes via testng.xml)

```bash
cd Automation
mvn test
```

### Run a Specific Test Class

```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=ComprehensiveE2ETest
```

### Run a Specific Test Group

```bash
# Run only component tests
mvn test -Dgroups=component

# Run only integration tests
mvn test -Dgroups=integration
```

### Run with a Specific Browser (future extension)

```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Suite Execution Order (as defined in testng.xml)

```
1.  Component – Account – Registration
2.  Component – Account – Login
3.  Component – Account – Forgot Password
4.  Component – Header – Currency
5.  Component – Header – Navigation Hover
6.  Component – Header – Search
7.  Component – Catalog – Navigation
8.  Component – Catalog – Product Comparison
9.  Component – Cart – Cart Operations
10. Component – Wishlist – Wishlist Operations
11. Component – Checkout – Order Placement
12. Integration – Register Then Login
13. Integration – Login Then Search Then Add To Cart
14. Integration – Search Then Compare Products
15. Integration – Login Then Add To Cart Then Checkout
16. Integration – Currency Switch Then Verify Product Price
17. Integration – Login Then Wishlist Then View Product
18. E2E – Full Shopping Journey
```

---

## 📊 Test Reports

After execution, an interactive HTML report is generated automatically at:

```
Automation/Reports/ExtentReport.html
```

The report includes:

- **Pass / Fail / Skip** status per test method
- **Execution timestamps** and duration
- **Step-level logs** with inline status markers
- **System information** (tester names, environment)
- **Failure stack traces** captured automatically via `@AfterMethod`

To open the report:

```bash
# macOS / Linux
open Automation/Reports/ExtentReport.html

# Windows
start Automation/Reports/ExtentReport.html
```

> The report is powered by ExtentReports **SparkReporter** and is fully self-contained — no server required.

---

## 📄 Manual Testing Artifacts

All manual testing documents are located in the `Manual/` directory:

| Artifact | Description |
|---|---|
| `OC-TEST PLAN.pdf` | Test scope, objectives, entry/exit criteria, schedule, risk assessment |
| `OC-TEST CAUSE.xlsx` | Detailed test cases with steps, expected results, and actual results |
| `OC-TEST SUMMARY.xlsx` | Execution metrics: total / passed / failed / blocked counts |
| `OC-BUG REPORT.xlsx` | Defect register with severity, priority, status, and reproduction steps |
| `Bug_Evidences/` | Supporting screenshots and video recordings for each raised defect |
| `USER STORY/` | User story documents defining acceptance criteria per feature |

---

## 🔌 API Testing Artifacts

All API testing documents are located in the `Api/` directory:

| Artifact | Description |
|---|---|
| `OC-API-Documentation.pdf` | Complete REST API reference for the OpenCart application |
| `OC-API-TEST_CAUSE.xlsx` | API test cases covering all endpoints (GET / POST / PUT / PATCH / DELETE) |
| `OC-API-TEST_SUMMARY.xlsx` | API execution summary with pass/fail metrics |
| `OC-API-Bug-Report.xlsx` | API-specific defect log |
| `OpenCart API.postman_collection.json` | Ready-to-import Postman collection |
| `OpenCart Local Environment.postman_environment.json` | Environment variables for local execution |

### Importing the Postman Collection

1. Open **Postman**
2. Click **Import** → select `OpenCart API.postman_collection.json`
3. Import the environment file `OpenCart Local Environment.postman_environment.json`
4. Select the environment from the top-right dropdown
5. Run individual requests or use the **Collection Runner** for batch execution

---

## 👥 Team

| Name | Role |
|---|---|
| **Mohamed Ahmed** | QA Automation Engineer |
| **Mariam Elgandour** | QA Engineer |

---

## 📌 Notes & Known Limitations

- Tests run against a **shared QA environment** (`awesomeqa.com`). Data created during tests (registered users, placed orders) persists on the server and may affect subsequent runs.
- The `ComprehensiveE2ETest` uses `UUID`-based dynamic emails to ensure registration uniqueness across runs.
- `Thread.sleep` calls in the E2E test are intentional pauses for browser animation synchronisation and should be replaced with `WebDriverWait` polling conditions in future refactoring iterations.
- The framework currently targets **Chrome only**. Cross-browser support can be added by parameterising the driver initialisation in `BaseTest`.

---

<div align="center">

**OpenCart E2E Testing Project** · Built with ❤️ using Java, Selenium & TestNG

</div>
