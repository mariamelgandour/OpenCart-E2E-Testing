<div align="center">

# 🧪 OpenCart Manual Testing

<br/>

[![Status](https://img.shields.io/badge/Status-Ready_for_Review-2496ED?style=for-the-badge&logo=checkmarx&logoColor=white)](#)
[![Pass Rate](https://img.shields.io/badge/Pass_Rate-82.12%25-43A047?style=for-the-badge&logo=testcafe&logoColor=white)](#-execution-summary)
[![Test Cases](https://img.shields.io/badge/Test_Cases-358-0052CC?style=for-the-badge&logo=jira&logoColor=white)](#-coverage-by-module)
[![Defects](https://img.shields.io/badge/Open_Defects-59-D22128?style=for-the-badge&logo=bugsnag&logoColor=white)](#-defect-summary)
[![RTM Coverage](https://img.shields.io/badge/RTM_Coverage-100%25-6A0DAD?style=for-the-badge&logo=confluence&logoColor=white)](#-traceability-matrix)
[![ISTQB](https://img.shields.io/badge/Aligned-ISTQB-FF6B35?style=for-the-badge&logo=bookstack&logoColor=white)](#-testing-approach)

<br/>

> **Risk-based manual QA suite for the OpenCart e-commerce platform.**  
> Covering Functional · Security (SQLi / XSS) · Validation · Boundary · UI · Compatibility testing  
> across 8 customer-facing modules — executed end-to-end before any automation layer.

<br/>

| 📌 Project | 🌐 Environment | 🔗 Base URL | 👤 QA Lead |
|:---:|:---:|:---:|:---:|
| OpenCart Platform | Staging | `https://awesomeqa.com/ui/index.php` | Mohamed Ahmed |

</div>

---

## 📑 Table of Contents

- [📁 Folder Structure](#-folder-structure)
- [🔍 Overview](#-overview)
- [🎯 Scope](#-scope)
- [🚦 Priority Model](#-priority-model)
- [✅ Execution Summary](#-execution-summary)
- [🗂 Coverage by Module](#-coverage-by-module)
- [🐞 Defect Summary](#-defect-summary)
- [🔗 Traceability Matrix](#-traceability-matrix)
- [📄 Test Documents](#-test-documents)
- [🎬 Bug Evidence](#-bug-evidence)
- [🧭 Testing Approach](#-testing-approach)
- [🚪 Entry & Exit Criteria](#-entry--exit-criteria)
- [🛠 How to Use This Folder](#-how-to-use-this-folder)

---

## 📁 Folder Structure

```
Manual/
│
├── 📄 OC-TEST PLAN.pdf                    ← Strategy · Scope · Environment · KPIs · Risks
├── 📊 OC-TEST CAUSE.xlsx                  ← Master workbook: Test Cases · RTM · Bug Log · Dashboard
├── 📊 OC-TEST SUMMARY.xlsx                ← Standalone execution dashboard export
├── 📊 OC-BUG REPORT.xlsx                  ← Standalone bug log export
├── 📊 Traceability Matrix.xlsx            ← Standalone RTM export (User Stories ↔ TCs)
│
├── 📂 Bug_Evidences/                      ← 23 evidence files (MP4 + PNG)
│   ├── OC_AUTH_BUG_001.mp4  ─┐
│   ├── OC_AUTH_BUG_002.mp4   │  Screen recordings (4)
│   ├── OC_AUTH_BUG_003.mp4   │
│   ├── OC_AUTH_BUG_004.mp4  ─┘
│   └── OC_AUTH_BUG_005.png → OC_AUTH_BUG_023.png   ← Screenshots (19)
│
├── 📂 OC-Test Scenario/
│   ├── OC-LOG-Test Scenario.pdf           ← High-level scenarios — Login module
│   └── OC-REG-Test Scenario.pdf           ← High-level scenarios — Registration module
│
└── 📂 OC-USER STORY/
    ├── OC-REG-User-Stories.pdf            ← Registration (with acceptance criteria)
    ├── OC-LOG-User-Stories.pdf            ← Login
    ├── OC-Search-User-Stories.pdf         ← Search
    ├── OC-Compare-User-Stories.pdf        ← Product Compare
    ├── OC-Shopping Cart-User-Stories.pdf  ← Shopping Cart
    └── OC-Wishlist-User-Stories.pdf       ← Wishlist
```

> 💡 **Single Source of Truth:** `OC-TEST CAUSE.xlsx` contains everything in one workbook (Test Cases · RTM · Bug Log · Dashboard). The other `.xlsx` files are standalone exports for stakeholder sharing.

---

## 🔍 Overview

This module is the **foundational QA layer** of the OpenCart E2E Testing project. It was executed first — before API or database testing — to validate all customer-facing behavior at the functional and UI level.

The approach is **risk-based**, driven by a four-tier priority model (P0–P3), and aligned with **ISTQB** standards across four test levels: Component → Integration → System → Acceptance.

| Metric | Value |
|---|:---:|
| Total Test Cases | **358** |
| Modules Covered | **8** |
| User Stories Traced | **25** |
| RTM Coverage | **100%** |
| Defects Logged | **59 Open** |
| Test Execution Rate | **99.72%** |
| Pass Rate | **82.12%** |

---

## 🎯 Scope

| Category | Details |
|---|---|
| ✅ **In Scope** | User management (registration · login · password recovery · session control) · Catalog & navigation (homepage · categories · PDP · search / sort / filter / pagination) · Shopping features (wishlist · cart) · Checkout & order processing · Security (SQL Injection · XSS) · Cross-browser & responsive behavior |
| ❌ **Out of Scope** | Admin / back-office functionality · Third-party payment gateway internals · Deep penetration testing · Native mobile apps |

---

## 🚦 Priority Model

| Priority | Label | TCs | Definition |
|:---:|---|:---:|---|
| **P0** | 🔴 Blocker | 7 | Prevents core functionality or compromises security / data integrity |
| **P1** | 🟠 High Risk | 181 | Critical validation rules ensuring correct and secure behavior |
| **P2** | 🟡 Medium Risk | 121 | Boundary conditions, formatting, non-critical functional behavior |
| **P3** | 🟢 Low Risk | 49 | UI, usability, accessibility, and visual behavior |

---

## ✅ Execution Summary

| Status | Count | % of Total |
|---|:---:|:---:|
| ✅ Pass | 294 | 82.12% |
| ❌ Fail | 59 | 16.48% |
| 🚫 Blocked | 4 | 1.12% |
| ⏳ Pending | 1 | 0.28% |
| **Total** | **358** | **100%** |

**Execution Rate:** `99.72%` — only 1 case not yet executed.

> Every `Fail` status maps 1:1 to a Bug ID in the Bug Log. No orphaned failures.

---

## 🗂 Coverage by Module

| Module | TCs | % of Suite |
|---|:---:|:---:|
| 🔐 Authentication (Registration + Login) | 122 | 34.1% |
| 🛒 Shopping Cart | 45 | 12.6% |
| 🔍 Search | 42 | 11.7% |
| 📂 Category Navigation | 30 | 8.4% |
| ⚖️ Product Compare | 28 | 7.8% |
| ❤️ Wishlist | 27 | 7.5% |
| 💱 Currency Selector | 20 | 5.6% |
| 🗂 Subcategory Navigation | 16 | 4.5% |
| 🍞 Breadcrumb Navigation | 11 | 3.1% |
| 🖱 Header Navigation | 8 | 2.2% |
| 🎨 UI – Category | 3 | 0.8% |
| 📱 Cross-cutting (responsive · compatibility · smoke) | 6 | 1.7% |

---

## 🐞 Defect Summary

All **59 defects** are currently **Open**. Concentrated in 5 modules:

| Module | Bugs | Visual |
|---|:---:|---|
| 🔐 Authentication | 23 | `████████████████████████` |
| ❤️ Wishlist | 14 | `███████████████` |
| 🛒 Shopping Cart | 13 | `██████████████` |
| 🔍 Search | 5 | `█████` |
| 📂 Category Navigation | 5 | `█████` |

### By Severity & Priority

| Severity | Count | | Priority | Count |
|---|:---:|---|---|:---:|
| 🔴 Critical | 5 | | P0 | 1 |
| 🟠 Major | 39 | | P1 | 40 |
| 🟡 Minor | 10 | | P2 | 14 |
| 🟢 Low | 3 | | P3 | 4 |
| ⚪ Medium | 2 | | | |

### 🔴 Critical & Blocker Bugs (Must Fix Before Release)

| Bug ID | Module | Title | Severity | Priority |
|---|---|---|:---:|:---:|
| `OC_AUTH_BUG_001` | Auth | SQL Injection payload in First Name accepted — stored in profile unescaped | 🔴 Critical | P0 |
| `OC_AUTH_BUG_017` | Auth | Password reset email not sent for valid registered address — blocks full forgot-password flow | 🔴 Critical | P1 |
| `OC_AUTH_BUG_018` | Auth | Session not invalidated after logout — account pages accessible via browser Back button | 🔴 Critical | P1 |

### Full Bug Log (All 59)

| Bug ID | TC ID | Module | Title | Severity | Priority |
|---|---|---|---|:---:|:---:|
| `OC_AUTH_BUG_001` | TC_002 | Auth | SQL Injection in First Name accepted — stored unescaped | 🔴 Critical | P0 |
| `OC_AUTH_BUG_002` | TC_010 | Auth | Duplicate telephone number accepted on registration | 🟠 Major | P1 |
| `OC_AUTH_BUG_003` | TC_021 | Auth | Numeric characters in First Name accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_004` | TC_022 | Auth | Special characters in First Name accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_005` | TC_024 | Auth | Numeric characters in Last Name accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_006` | TC_025 | Auth | Special characters in Last Name accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_007` | TC_032 | Auth | Illegal characters in Email accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_008` | TC_033 | Auth | Alphabetic characters in Telephone accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_009` | TC_034 | Auth | Special characters in Telephone accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_010` | TC_035 | Auth | Whitespace-only Telephone accepted | 🟠 Major | P1 |
| `OC_AUTH_BUG_011` | TC_036 | Auth | Whitespace-only Password accepted — security risk | 🟠 Major | P1 |
| `OC_AUTH_BUG_012` | TC_037 | Auth | Leading/trailing spaces in First Name not trimmed | 🟠 Major | P1 |
| `OC_AUTH_BUG_013` | TC_038 | Auth | Leading/trailing spaces in Last Name not trimmed | 🟠 Major | P1 |
| `OC_AUTH_BUG_014` | TC_040 | Auth | Leading spaces in Telephone not trimmed | 🟡 Minor | P1 |
| `OC_AUTH_BUG_015` | TC_041 | Auth | Leading spaces in Password not trimmed | 🟡 Minor | P1 |
| `OC_AUTH_BUG_016` | TC_052 | Auth | Email with leading spaces not trimmed — login fails | 🟠 Major | P1 |
| `OC_AUTH_BUG_017` | TC_057 | Auth | Password reset email not delivered | 🔴 Critical | P1 |
| `OC_AUTH_BUG_018` | TC_060 | Auth | Session not invalidated after logout | 🔴 Critical | P1 |
| `OC_AUTH_BUG_019` | TC_064 | Auth | Email below min length (5 chars) accepted | 🟠 Major | P2 |
| `OC_AUTH_BUG_020` | TC_069 | Auth | Password exceeding 20 chars (max+1) accepted | 🟠 Major | P2 |
| `OC_AUTH_BUG_021` | TC_099 | Auth | Password reset email not delivered on resend attempt | 🟡 Minor | P2 |
| `OC_AUTH_BUG_022` | TC_109 | Auth | Browser-native password toggle shown instead of custom one (Chrome) | 🟢 Low | P3 |
| `OC_AUTH_BUG_023` | TC_116 | Auth | Password visibility toggle missing in Chrome (works in Edge only) | 🟢 Low | P3 |
| `OC_SEAR_BUG_001` | TC_136 | Search | Cart count not updated in header after add-to-cart from search | 🟡 Minor | P2 |
| `OC_SEAR_BUG_002` | TC_137 | Search | No autocomplete suggestions appear in keyword field | 🟡 Minor | P2 |
| `OC_SEAR_BUG_003` | TC_140 | Search | 256+ character input accepted without truncation or error | 🟡 Minor | P2 |
| `OC_SEAR_BUG_004` | TC_146 | Search | Multi-word search returns zero results | 🟡 Minor | P2 |
| `OC_SEAR_BUG_005` | TC_147 | Search | Whitespace-only search returns all products instead of empty | 🟡 Minor | P2 |
| `OC_CAT_BUG_001` | TC_166 | Category | Desktops category lists wrong products | 🟠 Major | P1 |
| `OC_CAT_BUG_002` | TC_168 | Category | Components category page loads with empty product list | 🟠 Major | P1 |
| `OC_CAT_BUG_003` | TC_177 | Category | Hero banner MacBook image — click does nothing | 🟠 Major | P1 |
| `OC_CAT_BUG_004` | TC_178 | Category | Hero banner iPhone image — redirects to wrong product page | 🟠 Major | P1 |
| `OC_CAT_BUG_005` | TC_191 | Category | Keyboard navigation skips categories with subcategories | 🟢 Low | P3 |
| `OC_WISHLIST_BUG_001` | TC_268 | Wishlist | Logout from Wishlist sidebar keeps user on Wishlist page | 🟠 Major | P1 |
| `OC_WISHLIST_BUG_002` | TC_270 | Wishlist | Adding duplicate item shows misleading success notification | 🟡 Minor | P3 |
| `OC_WISHLIST_BUG_003` | TC_272 | Wishlist | Continue button redirects to Login instead of Home/Products | 🟡 Minor | P2 |
| `OC_WISHLIST_BUG_004` | TC_273 | Wishlist | Breadcrumb always shows Home > Account > Wishlist regardless of path | 🟠 Major | P2 |
| `OC_WISHLIST_BUG_005` | TC_274 | Wishlist | Shop button redirects to PDP instead of adding product to cart | 🟠 Major | P1 |

> Bugs OC_WISHLIST_BUG_006 → OC_WISHLIST_BUG_014 and all OC_CART_BUG_001 → OC_CART_BUG_013 are fully documented in `OC-BUG REPORT.xlsx` with reproduction steps, expected vs. actual results, and evidence references.

> ⚠️ **Sign-off blocker:** P0 + P1 open defects = **41 of 59 (69.5%)**. Per exit criteria, all must reach zero before release sign-off.

---

## 🔗 Traceability Matrix

`Traceability Matrix.xlsx` maps **25 User Stories → Test Scenarios → Test Case IDs** with 100% coverage on every traced story.

| Metric | Value |
|---|:---:|
| User Stories Traced | 25 |
| RTM Coverage | ✅ 100% |
| Test Cases Mapped | 122 (Authentication module) |

### User Stories by Category

| Category | User Stories |
|---|:---:|
| Validation | 10 |
| Functional | 6 |
| Performance | 2 |
| Security | 2 |
| Usability | 2 |
| Compatibility | 2 |
| Validation / Security | 1 |

### Sample Traceability Entries

| User Story ID | Title | TCs Mapped | Coverage |
|---|---|:---:|:---:|
| `OC-REG-US-001` | Successful Account Registration | 2 | ✅ 100% |
| `OC-REG-US-002` | First Name & Last Name Validation | 20 | ✅ 100% |
| `OC-REG-US-003` | E-Mail Validation | 16 | ✅ 100% |
| `OC-REG-US-004` | Telephone Validation | 12 | ✅ 100% |
| `OC-REG-US-005` | Password Validation | 14 | ✅ 100% |
| `OC-LOG-US-002` | Login with valid credentials | 4 | ✅ 100% |
| `OC-LOG-US-004` | Forgotten Password flow | 6 | ✅ 100% |
| `OC-LOG-US-005` | Session & Logout behavior | 4 | ✅ 100% |

> Extending the same User Story → Scenario → Test Case mapping to Cart, Search, Wishlist, and Compare is the next milestone for full-project traceability.

---

## 📄 Test Documents

| Document | Description |
|---|---|
| `OC-TEST PLAN.pdf` | Full strategy: scope, entry/exit criteria, environment matrix, test types, test levels, deliverables, team roles, risk register, KPIs |
| `OC-Test Scenario/OC-LOG-Test Scenario.pdf` | High-level test scenarios — Login module |
| `OC-Test Scenario/OC-REG-Test Scenario.pdf` | High-level test scenarios — Registration module |
| `OC-USER STORY/OC-REG-User-Stories.pdf` | Registration user stories with acceptance criteria |
| `OC-USER STORY/OC-LOG-User-Stories.pdf` | Login user stories with acceptance criteria |
| `OC-USER STORY/OC-Search-User-Stories.pdf` | Search user stories |
| `OC-USER STORY/OC-Compare-User-Stories.pdf` | Product Compare user stories |
| `OC-USER STORY/OC-Shopping Cart-User-Stories.pdf` | Shopping Cart user stories |
| `OC-USER STORY/OC-Wishlist-User-Stories.pdf` | Wishlist user stories |

---

## 🎬 Bug Evidence

`Bug_Evidences/` holds the supporting media referenced from the **Attachments** column of the Bug Log — **23 files total**.

| Type | Count | Range |
|---|:---:|---|
| 🎥 Screen Recordings (`.mp4`) | 4 | `OC_AUTH_BUG_001` → `OC_AUTH_BUG_004` |
| 🖼 Screenshots (`.png`) | 19 | `OC_AUTH_BUG_005` → `OC_AUTH_BUG_023` |

Each filename matches its **Bug ID** directly in the bug log — no separate index needed.

---

## 🧭 Testing Approach

| Type | Focus |
|---|---|
| **Static** | Requirement & user-story review · UI/UX design consistency · early ambiguity detection |
| **Functional** | Happy paths · Negative paths · Edge cases across all 8 modules |
| **Validation** | Field-level rules · Boundary Value Analysis (BVA) · Equivalence Partitioning (EP) |
| **Security** | SQL Injection · XSS payload testing on all free-text input fields |
| **Non-Functional** | Cross-browser · Responsive (Desktop 1920×1080 · Tablet 768px · Mobile 375px) · Usability · Accessibility |
| **Regression** | Smoke / Sanity / Full-regression cycles |

### Browser & Device Matrix

| Browser | Version | OS |
|---|:---:|---|
| Chrome | 120+ | Windows 11 · macOS · Android · iOS |
| Firefox | 120+ | Windows 11 · macOS |
| Edge | 120+ | Windows 11 |
| Safari | 17+ | macOS · iOS |

---

## 🚪 Entry & Exit Criteria

### Entry Criteria *(Before Testing Begins)*

- [ ] Requirements / user stories baselined and approved
- [ ] Test environment stable and accessible at base URL
- [ ] Test data prepared and seeded
- [ ] Test cases reviewed and signed off
- [ ] Build deployed to staging
- [ ] Test management tools and browsers configured

### Exit Criteria *(Before Release Sign-Off)*

- [ ] ≥ 95% of planned test cases executed
- [ ] All P0 and P1 cases passed
- [ ] Zero open Critical / High-severity defects
- [ ] Remaining defects documented with agreed mitigation
- [ ] Full regression cycle completed
- [ ] Test summary report reviewed and approved

> **Current status:** 82.12% pass rate · 41 open P0/P1 defects → **exit criteria not yet met**.  
> This is expected for a project in `Ready for Review` status — the manual layer is designed to surface exactly these issues before automation and regression sign-off.

---

## 🛠 How to Use This Folder

```
Step 1 → Read OC-TEST PLAN.pdf
         Understand the scope, environment setup, risk model, and test approach.

Step 2 → Open OC-TEST CAUSE.xlsx → 🧪 Test Cases sheet
         Full 358-case repository with steps, expected/actual results, priority, and status.

Step 3 → Cross-reference failures → 🐞 Bug Log sheet
         Every Fail links to a Bug ID with reproduction steps and evidence filename.

Step 4 → Open Bug_Evidences/ folder
         Match filename to Bug ID for screenshots and screen recordings.

Step 5 → Check Traceability Matrix.xlsx
         Confirm requirement coverage before sign-off review.

Step 6 → Open OC-TEST SUMMARY.xlsx → 📈 Dashboard
         Live snapshot of pass rate, defect aging, and sign-off readiness.
```

---

<div align="center">

**OpenCart Manual Testing** &nbsp;·&nbsp; Risk-Based QA &nbsp;·&nbsp; ISTQB-Aligned

*Part of the [OpenCart E2E Testing Project](../README.md)*

<br/>

![Prepared by](https://img.shields.io/badge/Prepared_by-Mohamed_Ahmed-0052CC?style=flat-square)
![Last Updated](https://img.shields.io/badge/Last_Updated-June_2026-43A047?style=flat-square)

</div>
