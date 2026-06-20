# 🗄️ OpenCart Database Testing

<div align="center">

![Type](https://img.shields.io/badge/Type-Database%20Testing-blue?style=for-the-badge)
![Engine](https://img.shields.io/badge/Engine-SQLite%20v3-lightgrey?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-OpenCart%20v3.x-red?style=for-the-badge)
![TCs](https://img.shields.io/badge/Test%20Cases-30-informational?style=for-the-badge)
![Pass Rate](https://img.shields.io/badge/Pass%20Rate-73.3%25-yellow?style=for-the-badge)

**SQL-level integrity, security, and performance testing for the OpenCart database.**  
Validates data quality, referential integrity, business rules, schema structure, and query performance.

</div>

---

## 📁 Folder Contents

```
Database/
├── opencart.db                      # SQLite test database (144 tables)
├── OpenCart - DB Schema.pdf         # Full ER diagram and table definitions
├── OC_DB_TEST_CAUSE.xlsx            # Detailed test cases (30 TCs)
├── OC-DB-TEST_SUMMARY.xlsx          # Execution dashboard
└── OC-DB-Bug-Report.xlsx            # Bug report (8 bugs)
```

---

## 📊 Execution Summary

| Metric | Value |
|---|---|
| Total Test Cases | 30 |
| ✅ Pass | 22 |
| ❌ Fail | 8 |
| 🚫 Blocked | 0 |
| **Pass Rate** | **73.3%** |

### Priority Breakdown

| Priority | Count | Meaning |
|---|---|---|
| P0 – Blocker | 9 | Must-fix before any release |
| P1 – High Risk | 15 | Core data integrity issues |
| P2 – Medium Risk | 5 | Business rule violations |
| P3 – Low Risk | 1 | Performance / informational |

---

## 🧪 Test Modules & Coverage

| Module | TCs | ✅ Pass | ❌ Fail |
|---|---|---|---|
| Customer Registration | 5 | 2 | 3 |
| Order Processing | 8 | 6 | 2 |
| Shopping Cart | 2 | 0 | 2 |
| Schema / Database Structure | 3 | 3 | 0 |
| Product Catalog | 5 | 5 | 0 |
| Coupon Management | 2 | 1 | 1 |
| Customer Login | 3 | 3 | 0 |
| Performance | 2 | 2 | 0 |

### Test Types Covered

| Type | Description |
|---|---|
| **Data Quality** | NULL checks, missing required values |
| **Security** | Password hashing, token uniqueness, disabled accounts |
| **Referential Integrity** | FK consistency across related tables |
| **Business Rule** | Order totals, coupon validity, stock subtraction |
| **Structural** | Table count, primary keys, composite PKs |
| **Performance** | Index existence, query execution time |

---

## 🐞 Bug Report

8 bugs were found. All are currently **Open**.

| Bug ID | TC | Module | Description | Severity | Priority |
|---|---|---|---|---|---|
| `OC_DB_BUG_001` | TC_001 | Customer Registration | `customer_id=102` has `password = NULL` — account created without a password | 🔴 Critical | P1 |
| `OC_DB_BUG_002` | TC_002 | Customer Registration | Same account has no bcrypt hash — `password NOT LIKE '$2y$10%'` — credential exposure risk | 🔴 Critical | P1 |
| `OC_DB_BUG_003` | TC_003 | Order Processing | `order_product_id=101` has `price = NULL` and `total = NULL` — invoice generation broken | 🔴 Critical | P1 |
| `OC_DB_BUG_004` | TC_004 | Shopping Cart | `cart_id=102` references `product_id=9999` which does not exist in `oc_product` — orphan cart item | 🔴 Critical | P1 |
| `OC_DB_BUG_005` | TC_007 | Order Processing | 5 orders have mismatched totals between `oc_order.total` and `SUM(oc_order_total.value)` — customers billed incorrectly | 🔴 Critical | P1 |
| `OC_DB_BUG_006` | TC_010 | Customer Registration | Email `ahmed.ali@gmail.com` appears on `customer_id=1` and `103` — duplicate login identity | 🟠 Major | P2 |
| `OC_DB_BUG_007` | TC_011 | Shopping Cart | `cart_id=101` has `quantity = -5` — negative quantity produces negative subtotal | 🟠 Major | P2 |
| `OC_DB_BUG_008` | TC_012 | Coupon Management | `coupon BROKEN99`: `date_start=2024-12-31` is after `date_end=2024-01-01` — coupon permanently invalid | 🟠 Major | P2 |

> Full SQL queries, expected vs. actual results, and comments are documented in `OC-DB-Bug-Report.xlsx`.

---

## 🗂️ Database Schema Overview

The database contains **144 application tables**. See `OpenCart - DB Schema.pdf` for the full ER diagram.

| Group | Key Tables |
|---|---|
| **Customer** | `oc_customer`, `oc_customer_group`, `oc_address`, `oc_customer_login` |
| **Product Catalog** | `oc_product`, `oc_product_description`, `oc_product_image`, `oc_category` |
| **Cart & Session** | `oc_cart`, `oc_session`, `oc_api`, `oc_api_session` |
| **Order** | `oc_order`, `oc_order_product`, `oc_order_total`, `oc_order_history` |
| **Coupons & Vouchers** | `oc_coupon`, `oc_coupon_history`, `oc_voucher`, `oc_voucher_history` |
| **Configuration** | `oc_setting`, `oc_store`, `oc_currency`, `oc_language` |

---

## 🔍 Running the Tests

### SQLite CLI
```bash
sqlite3 Database/opencart.db

-- TC_001: NULL passwords
SELECT customer_id FROM oc_customer
WHERE password IS NULL OR date_added IS NULL;

-- TC_002: Unhashed passwords
SELECT customer_id, password FROM oc_customer
WHERE password IS NULL OR password NOT LIKE '$2y$10%';

-- TC_004: Orphan cart items
SELECT cart_id, product_id FROM oc_cart
WHERE product_id NOT IN (SELECT product_id FROM oc_product);

-- TC_007: Mismatched order totals
SELECT o.order_id, o.total, SUM(ot.value) AS ot_sum
FROM oc_order o
LEFT JOIN oc_order_total ot ON o.order_id = ot.order_id
GROUP BY o.order_id
HAVING ABS(o.total - IFNULL(SUM(ot.value), 0)) > 0.01;

-- TC_010: Duplicate emails
SELECT email, COUNT(*) AS cnt
FROM oc_customer
GROUP BY email
HAVING cnt > 1;

-- TC_011: Negative cart quantities
SELECT cart_id, quantity FROM oc_cart WHERE quantity <= 0;

-- TC_012: Invalid coupon date ranges
SELECT coupon_id, code, date_start, date_end
FROM oc_coupon WHERE date_end < date_start;
```

### Python
```python
import sqlite3

conn = sqlite3.connect("Database/opencart.db")
cursor = conn.cursor()

# TC_001 — No NULL passwords
cursor.execute("""
    SELECT customer_id FROM oc_customer
    WHERE password IS NULL OR date_added IS NULL
""")
assert cursor.fetchall() == [], "FAIL TC_001: NULL password found"

# TC_004 — No orphan cart items
cursor.execute("""
    SELECT cart_id, product_id FROM oc_cart
    WHERE product_id NOT IN (SELECT product_id FROM oc_product)
""")
assert cursor.fetchall() == [], "FAIL TC_004: Orphan cart item found"

conn.close()
print("All assertions passed.")
```

### GitHub Actions
```yaml
jobs:
  db-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up Python
        uses: actions/setup-python@v5
        with:
          python-version: "3.11"

      - name: Run DB Tests
        run: python tests/run_db_tests.py
```

---

## 🛠️ Prerequisites

| Tool | Version | Purpose |
|---|---|---|
| SQLite | ≥ v3.35 | Direct SQL execution |
| Python | ≥ v3.9 | Scripted assertions (optional) |
| DB Browser for SQLite | Any | Visual schema inspection |

---

## 👤 Author

**Mohamed Ahmed** — QA Engineer

---

<div align="center">
<sub>Part of the OpenCart E2E Testing project · Last updated: June 2026</sub>
</div>
