<div align="center">

# 🛒 OpenCart E2E Testing Project

### Full-Spectrum Quality Assurance Suite

*Manual Testing · Database Testing · UI Automation · API Testing · Performance Testing*

---

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.20.0-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-FF6C37?style=for-the-badge)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Postman](https://img.shields.io/badge/Postman-API_Tests-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![JMeter](https://img.shields.io/badge/Apache_JMeter-5.6.3-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white)](https://jmeter.apache.org/)
[![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-6A0DAD?style=for-the-badge)](https://www.extentreports.com/)

---

**Application Under Test:** [`awesomeqa.com`](https://awesomeqa.com/ui/index.php?route=common/home) *(staging instance of OpenCart, UI layer)* · local OpenCart v3.x install *(API & Performance layers)*

📁 **[All Project Artifacts on Google Drive](https://drive.google.com/drive/folders/1ug7ITtV8Dn9s4WlnjtPvT_0yCogAPTXW?usp=sharing)**

</div>

---

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Project Infographic](#-project-infographic)
- [Testing Layers at a Glance](#-testing-layers-at-a-glance)
- [Test Strategy & Pyramid](#-test-strategy--pyramid)
- [Project Structure](#-project-structure)
- [Tech Stack](#-tech-stack)
- [Architecture & Design Patterns](#-architecture--design-patterns)
- [Test Coverage — Automation](#-test-coverage--automation)
- [Manual Testing](#-manual-testing)
- [API Testing](#-api-testing)
- [Database Testing](#-database-testing)
- [Performance Testing](#-performance-testing)
- [Prerequisites](#-prerequisites)
- [Setup & Installation](#️-setup--installation)
- [Running the Tests](#️-running-the-tests)
- [Test Reports](#-test-reports)
- [Team](#-team)

---

## 🔍 Project Overview

This repository is a **full-spectrum QA project** built against the OpenCart e-commerce platform. It covers the complete software testing lifecycle — from requirements analysis and test planning, through manual functional/security testing and database validation, to automated UI regression, REST API testing, and load/stress performance testing.

The project demonstrates industry-standard QA practices across **five independent testing layers**, each with its own dedicated documentation, test artifacts, and execution evidence:

| Layer | Scope | Tooling |
|---|---|---|
| **Manual** | Test Plan · User Stories · Test Cases · Bug Reports · Traceability Matrix | Excel · PDF |
| **Database** | Schema validation · Data integrity · Business rule enforcement | SQLite · Excel |
| **Automation** | Component · Integration · End-to-End UI regression | Java · Selenium · TestNG |
| **API** | REST endpoint validation across the full checkout lifecycle | Postman · Newman |
| **Performance** | Load & stress testing of the REST API checkout flow | Apache JMeter |

---
## 📊 Project Infographic

Before diving into the project details, the infographic below provides a concise visual overview of the complete QA solution, including the testing layers, architecture, technology stack, workflow, deliverables, and implementation roadmap.

<p align="center">
  <img src="Documentation/OpenCart_E2E_Testing_Infographic.png"
       alt="OpenCart E2E Testing Project Infographic"
       width="100%">
</p>

> 💡 **Quick Overview:** A presentation-style roadmap summarizing the project's objectives, testing strategy, architecture, technology stack, development workflow, and key deliverables in a single visual.
---

## 🧪 Testing Layers at a Glance

| Layer | Test Cases | Pass Rate | Bugs Logged | Status |
|---|:---:|:---:|:---:|:---:|
| Manual | 358 | 82.12% | 59 | ✅ Executed |
| Database | 30 | 73.3% | 8 | ✅ Executed |
| API | 21 | 76.2% | 5 | ✅ Executed |
| Performance | 10,200 samples | — | 3 bottlenecks identified | ✅ Executed |
| Automation | 18 test classes (Component + Integration + E2E) | — | — | ✅ Implemented |

> Full breakdowns, bug logs, and execution dashboards for each layer live in their own folder README — linked throughout this document.

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
- **Manual tests** cover functional, validation, security (SQLi/XSS), and compatibility testing across 8 customer-facing modules
- **Database tests** validate data integrity, schema correctness, and business rule enforcement at the data layer
- **API tests** verify REST endpoints independently of the UI, covering the full order lifecycle
- **Performance tests** simulate 50–200 concurrent shoppers to measure response time, throughput, and error rate under load

---

## 📁 Project Structure

```
OpenCart-E2E-Testing/
│
├── 📂 Manual/                                   ← Manual QA artifacts (see Manual/README.md)
│   ├── OC-TEST PLAN.pdf                         ← Test strategy, scope & schedule
│   ├── OC-TEST CAUSE.xlsx                       ← Master workbook: test cases · RTM · bug log · dashboard
│   ├── OC-TEST SUMMARY.xlsx                     ← Execution dashboard export
│   ├── OC-BUG REPORT.xlsx                       ← Defect register export
│   ├── Traceability Matrix.xlsx                 ← Requirements ↔ test cases mapping
│   ├── Bug_Evidences/                           ← 23 evidence files (4 MP4 + 19 PNG)
│   ├── OC-Test Scenario/                        ← High-level scenarios — Login & Register
│   └── OC-USER STORY/                           ← User stories — 6 feature areas
│
├── 📂 Database/                                 ← Database testing artifacts (see Database/README.md)
│   ├── OpenCart - DB Schema.pdf                 ← Full database schema reference
│   ├── OC_DB_TEST_CAUSE.xlsx                    ← DB test cases with SQL queries
│   ├── OC-DB-TEST_SUMMARY.xlsx                  ← DB execution summary & metrics
│   ├── OC-DB-Bug-Report.xlsx                    ← Database-level defect log
│   └── opencart.db                              ← SQLite database snapshot
│
├── 📂 Api/                                      ← API testing artifacts (see Api/README.md)
│   ├── OC-API-Documentation.pdf                 ← Complete REST API reference
│   ├── OC-API-TEST_CAUSE.xlsx                   ← API test cases (Auth · Cart · Customer · Order · Shipping)
│   ├── OC-API-TEST_SUMMARY.xlsx                 ← API execution summary
│   ├── OC-API-Bug-Report.xlsx                   ← API-specific defect log
│   ├── OpenCart API.postman_collection.json     ← Ready-to-import Postman collection (21 requests)
│   └── OpenCart Local Environment.postman_environment.json
│
├── 📂 performance/                              ← Load/stress testing artifacts (see performance/README.md)
│   ├── OC-PERF-PLAN.jmx                         ← JMeter test plan — Load (50u) & Stress (200u) thread groups
│   ├── results.jtl                              ← Raw sample results (10,200 samples)
│   ├── HTML-Report/                             ← Generated interactive JMeter dashboard
│   └── *.png                                    ← Summary Report, Results Tree, Aggregate Graph screenshots
│
├── 📂 Automation/                               ← Selenium automation framework
│   ├── pom.xml                                  ← Maven dependencies & build config
│   ├── testng.xml                               ← Test suite orchestration (18 classes)
│   ├── Reports/
│   │   └── ExtentReport.html                    ← Generated interactive HTML report
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
│       │       └── BaseTest.java                ← Central lifecycle manager (driver + report)
│       │
│       └── test/java/
│           ├── component/                       ← Feature-level isolated tests (11 classes)
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
│           ├── integration/                     ← Cross-feature flow tests (6 classes)
│           │   ├── RegisterThenLoginTest.java
│           │   ├── LoginThenSearchThenAddToCartTest.java
│           │   ├── LoginThenAddToCartThenCheckoutTest.java
│           │   ├── LoginThenWishlistThenViewProductTest.java
│           │   ├── SearchThenCompareProductsTest.java
│           │   └── CurrencySwitchThenVerifyProductPriceTest.java
│           └── e2e/
│               └── E2ETest.java                 ← Full 13-step shopping journey
│
└── 📂 Reports/                                  ← Top-level report output
    └── ExtentReport.html
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
| API Testing | Postman / Newman | Latest |
| Performance Testing | Apache JMeter | 5.6.3 |
| Database | SQLite | 3.x |
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
    public boolean isMyAccountHeadingDisplayed()          { ... }
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

## ✅ Test Coverage — Automation

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

### Integration Tests — 6 Classes

| User Flow | Test Class |
|---|---|
| Register → Login | `RegisterThenLoginTest` |
| Login → Search → Add to Cart | `LoginThenSearchThenAddToCartTest` |
| Login → Add to Cart → Checkout | `LoginThenAddToCartThenCheckoutTest` |
| Login → Wishlist → View Product | `LoginThenWishlistThenViewProductTest` |
| Search → Compare Products | `SearchThenCompareProductsTest` |
| Currency Switch → Verify Product Price | `CurrencySwitchThenVerifyProductPriceTest` |

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

## 📄 Manual Testing

The `Manual/` folder contains a complete risk-based manual QA suite covering Functional, Security (SQLi/XSS), Validation, Boundary, UI, and Compatibility testing across 8 customer-facing modules, aligned with **ISTQB** test levels and a four-tier **P0–P3** priority model.

| Metric | Value |
|---|:---:|
| Total Test Cases | **358** |
| Modules Covered | **8** |
| User Stories Traced | **25** (100% RTM coverage) |
| Pass Rate | **82.12%** (294 pass · 59 fail · 4 blocked · 1 pending) |
| Defects Logged | **59** (all open — 41 are P0/P1) |

➡️ **Full breakdown, defect log, traceability matrix, and entry/exit criteria: [`Manual/README.md`](Manual/README.md)**

---

## 🔌 API Testing

The `Api/` folder contains a Postman collection covering the full order lifecycle against a local OpenCart REST API — Authentication → Cart → Customer → Order → Token Validation → Shipping.

| Metric | Value |
|---|:---:|
| Total Test Cases | **21** |
| Pass Rate | **76.2%** (16 pass · 5 fail) |
| Defects Logged | **5** (2 Critical / P0) |
| Modules | Authentication · Cart · Customer · Order · Token Validation · Shipping |

### Importing the Collection

1. Open **Postman** → click **Import**
2. Select `Api/OpenCart API.postman_collection.json`
3. Also import `Api/OpenCart Local Environment.postman_environment.json`
4. Set the `api_key` variable, then select the environment from the top-right dropdown
5. Run individual requests or use the **Collection Runner** / **Newman** for full batch execution

➡️ **Full endpoint reference, bug log, and Newman/CI commands: [`Api/README.md`](Api/README.md)**

---

## 🗄 Database Testing

The `Database/` folder contains SQL-level integrity, security, and business-rule testing against a 144-table OpenCart SQLite schema.

| Metric | Value |
|---|:---:|
| Total Test Cases | **30** |
| Pass Rate | **73.3%** (22 pass · 8 fail) |
| Defects Logged | **8** (5 Critical) |
| Coverage | Customer Registration · Order Processing · Shopping Cart · Schema · Product Catalog · Coupons · Login · Performance |

➡️ **Full SQL test queries, bug log, and schema overview: [`Database/README.md`](Database/README.md)**

---

## ⚡ Performance Testing

The `performance/` folder contains an Apache JMeter load & stress test plan (`OC-PERF-PLAN.jmx`) simulating **50–200 concurrent shoppers** through the complete purchase journey: Login → Cart → Customer → Shipping → Payment → Order Placement, executed directly against the REST API.

| Thread Group | Users | Ramp-Up | Loops | Total Requests | Profile |
|---|:---:|:---:|:---:|:---:|---|
| Load Test | 50 | 10 s | 5 | 4,500 | Realistic day-to-day traffic |
| Stress Test | 200 | 60 s | 3 | 10,800 | Peak / promotional spike |

| Controller | Samples | Error % | Mean Response Time |
|---|:---:|:---:|:---:|
| Login | 850 | 9.18% | 5,486 ms |
| Cart | 850 | 9.88% | 8,234 ms |
| Checkout | 850 | 13.29% | 21,120 ms |

> 🔴 **Key finding:** the Checkout transaction chain averages 21.1 s with a 13.29% error rate under stress load — far outside a typical <3 s / <1% SLA — pointing to sequential DB locking or cumulative cart-recalculation cost. Full findings and remediation recommendations are documented in the dedicated README.

### Quick Run

```bash
cd performance
jmeter -n -t OC-PERF-PLAN.jmx -l results.jtl -e -o HTML-Report
```

➡️ **Full test plan architecture, results breakdown, bottleneck analysis, and recommendations: [`performance/README.md`](performance/README.md)**

---

## 💻 Prerequisites

| Requirement | Version | Verify |
|---|---|---|
| Java JDK | 21+ | `java -version` |
| Apache Maven | 3.x | `mvn -version` |
| Google Chrome | Latest stable | — |
| Apache JMeter *(performance layer only)* | 5.6.3+ | `jmeter --version` |
| Postman / Newman *(API layer only)* | Latest | — |
| Internet access | — | Reach `awesomeqa.com` |

> **Note:** ChromeDriver is managed automatically by **WebDriverManager** (bundled dependency). No manual driver download is required for the automation suite.

---

## ⚙️ Setup & Installation

```bash
# 1. Clone the repository
git clone https://github.com/mariamelgandour/OpenCart-E2E-Testing.git
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
16.  Integration – Currency Switch Then Verify Price
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

## 👥 Team

| Name | Role |
|---|---|
| **Mariam Elgandour** | QA Engineer |
| **Mohamed Ahmed** | QA Automation Engineer |

---

<div align="center">

**OpenCart E2E Testing Project** &nbsp;·&nbsp; Built with ☕ Java · Selenium · TestNG · JMeter

*Covering the full QA lifecycle: Manual · Database · Automation · API · Performance*

</div>
