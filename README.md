# 🛒 OpenCart Web Automation — Graduation Project

> **Selenium Java** end-to-end test automation suite for [AwesomeQA OpenCart](https://awesomeqa.com/ui)  
> Developed as part of the **Testing DEPI** training programme.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Team & Task Distribution](#-team--task-distribution)
- [Test Cases Coverage](#-test-cases-coverage)
- [User Stories](#-user-stories)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [How to Run](#-how-to-run)
- [Reports](#-reports)

---

## 🔍 Project Overview

This project is a full **web automation graduation project** built with **Selenium WebDriver + Java + TestNG**, targeting the OpenCart demo store.  
It covers **12 core test cases** (positive & negative scenarios) plus **20 additional** designed test cases, all automated and integrated with **Extent Reports**.

**Target Website:** https://awesomeqa.com/ui

---

## 👥 Team & Task Distribution

| Test Case # | Feature | Assigned To |
|:-----------:|---------|-------------|
| 1 | Registration Functionality (Positive & Negative) | Mohamed Abo Bakar |
| 2 | Login Functionality (Positive & Negative) | Mohamed Abo Bakar |
| 3 | Forget Password Functionality | Mohamed Abo Bakar |
| 4 | Logged-in Users Can Search for Any Product | Mohamed Ashraf |
| 5 | Logged-in Users Can Switch Between Currencies (USD ↔ EUR) | Mariam Elgandour |
| 6 | Select Main and Sub Categories Randomly | Shorouk Mohammed |
| 7 | Hover Main and Sub Categories Randomly | Mariam Elgandour |
| 8 | Logged-in Users Can Select Different Categories | Mohamed Ashraf |
| 9 | Add Items to Wishlist | Mariam Elgandour |
| 10 | Add Items to Compare List | Shorouk Mohammed |
| 11 | Add Items to the Shopping Cart | Mariam Saleh |
| 12 | Create an Order with a Successful Payment | Mariam Saleh |

---

## ✅ Test Cases Coverage

### Core Test Cases (12)

| # | Test Case | Type | Status |
|---|-----------|------|--------|
| TC-01 | Register with valid data | Positive | ✅ Automated |
| TC-02 | Register with existing email / missing fields | Negative | ✅ Automated |
| TC-03 | Login with valid credentials | Positive | ✅ Automated |
| TC-04 | Login with invalid password / empty fields | Negative | ✅ Automated |
| TC-05 | Request password reset with registered email | Positive | ✅ Automated |
| TC-06 | Search for existing product by keyword | Positive | ✅ Automated |
| TC-07 | Switch currency from USD to EUR and verify prices | Positive | ✅ Automated |
| TC-08 | Select random main & sub categories | Positive | ✅ Automated |
| TC-09 | Hover over categories and verify dropdown behaviour | Positive | ✅ Automated |
| TC-10 | Select different categories while logged in | Positive | ✅ Automated |
| TC-11 | Add product to wishlist and verify | Positive | ✅ Automated |
| TC-12 | Add product to compare list and verify alert | Positive | ✅ Automated |
| TC-13 | Add item to shopping cart and verify cart page | Positive | ✅ Automated |
| TC-14 | Complete checkout with successful payment | Positive | ✅ Automated |

---

## 📄 User Stories

The full **User Story Specification** document (covering all modules with Acceptance Criteria in Given/When/Then format) is available on Google Drive.

📁 **Project Documentation & User Stories**  
Access all user stories, test scenarios, acceptance criteria, and supporting documents here:

🔗 [Open Google Drive Folder](https://drive.google.com/drive/folders/1OUAiiYAhOM1T1AYahY47eefAe0IgjNxF?usp=sharing)

### Modules Covered

| Module | User Story IDs | Author |
|--------|---------------|--------|
| Registration | OC-REG-US-001, OC-REG-US-002 | Mohamed Abo Bakar |
| Login | OC-LOG-US-001, OC-LOG-US-002 | Mohamed Abo Bakar |
| Forgot Password | OC-FP-US-001 | Mohamed Abo Bakar |
| Product Search | OC-SRCH-US-001 | Mohamed Ashraf |
| Currency Switch | OC-CUR-US-001 | Mariam Elgandour |
| Select Categories | OC-CAT-US-001 | Shorouk Mohammed |
| Hover Categories | OC-HOV-US-001 | Mariam Elgandour |
| Category Selection (Logged-in) | OC-SCAT-US-001 | Mohamed Ashraf |
| Wishlist | OC-WISH-US-001 | Mariam Elgandour |
| Product Compare | OC-COMP-US-001, 002, 003 | Shorouk Mohammed |
| Shopping Cart | OC-CART-US-001 | Mariam Saleh |
| Order & Payment | OC-ORD-US-001 | Mariam Saleh |

---

## 🛠️ Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17+ | Programming language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test framework & annotations |
| Maven | 3.x | Build & dependency management |
| Extent Reports | 5.x | HTML test execution reports |
| ChromeDriver | Latest | Chrome browser driver |

---

## 📁 Project Structure

```bash
OpenCart-Automation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/               # Page Object Model classes
│   │       │   ├── HomePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── RegisterPage.java
│   │       │   ├── SearchPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── WishlistPage.java
│   │       │   ├── ComparePage.java
│   │       │   └── CheckoutPage.java
│   │       └── utils/               # Utilities & helpers
│   │           ├── DriverFactory.java
│   │           ├── ExtentManager.java
│   │           └── DataProvider.java
│   │
│   └── test/
│       └── java/
│           └── tests/               # Test classes per module
│               ├── RegistrationTest.java      # TC-01, TC-02 — Mohamed Abo Bakar
│               ├── LoginTest.java             # TC-03, TC-04 — Mohamed Abo Bakar
│               ├── ForgotPasswordTest.java    # TC-05       — Mohamed Abo Bakar
│               ├── SearchTest.java            # TC-06       — Mohamed Ashraf
│               ├── CurrencyTest.java          # TC-07       — Mariam Elgandour
│               ├── CategorySelectTest.java    # TC-08       — Shorouk Mohammed
│               ├── CategoryHoverTest.java     # TC-09       — Mariam Elgandour
│               ├── CategoryLoggedInTest.java  # TC-10       — Mohamed Ashraf
│               ├── WishlistTest.java          # TC-11       — Mariam Elgandour
│               ├── CompareTest.java           # TC-12       — Shorouk Mohammed
│               ├── CartTest.java              # TC-13       — Mariam Saleh
│               └── CheckoutTest.java          # TC-14       — Mariam Saleh
│
├── test-output/
│   └── ExtentReport.html            # Generated after execution
│
├── testng.xml                       # TestNG suite configuration
├── pom.xml                          # Maven dependencies
└── README.md
```

---

## ▶️ How to Run

### Prerequisites
- Java JDK 17 or higher installed
- Maven installed
- Google Chrome browser installed
- ChromeDriver matching your Chrome version

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>
```

**2. Install dependencies**
```bash
mvn clean install -DskipTests
```

**3. Run all test cases**
```bash
mvn test
```

**4. Run a specific test class**
```bash
mvn test -Dtest=LoginTest
```

**5. Run via TestNG XML suite**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reports

After execution, the **Extent Report** is generated automatically at:

```bash
test-output/ExtentReport.html
```

Open it in any browser to view:
- ✅ Passed / ❌ Failed / ⏭️ Skipped test breakdown
- Execution time per test
- Screenshots on failure
- Logs and step-by-step details

---

## 📌 Notes

- All tests require a valid registered account on the demo site. Update credentials in `config.properties` before running.
- The site is a shared demo environment — product availability and prices may vary.
- Tests are designed to run on **Chrome** (headless mode supported).

---

<div align="center">

**Testing DEPI Graduation Project — 2026**

</div>
