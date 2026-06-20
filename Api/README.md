# 🌐 OpenCart API Testing

<div align="center">

![Type](https://img.shields.io/badge/Type-REST%20API%20Testing-blue?style=for-the-badge)
![Tool](https://img.shields.io/badge/Tool-Postman%20%2F%20Newman-orange?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-OpenCart%20v3.x-red?style=for-the-badge)
![TCs](https://img.shields.io/badge/Test%20Cases-21-informational?style=for-the-badge)
![Pass Rate](https://img.shields.io/badge/Pass%20Rate-76.2%25-brightgreen?style=for-the-badge)

**Functional REST API test suite for the OpenCart e-commerce platform.**  
Covers the full order lifecycle — Authentication → Cart → Customer → Order → Token Validation → Shipping.

</div>

---

## 📁 Folder Contents

```
Api/
├── OpenCart API.postman_collection.json              # Main Postman collection (21 TCs)
├── OpenCart Local Environment.postman_environment.json  # Environment variables
├── OC-API-TEST_CAUSE.xlsx                            # Detailed test cases
├── OC-API-TEST_SUMMARY.xlsx                          # Execution dashboard
└── OC-API-Bug-Report.xlsx                            # Bug report
```

---

## ⚙️ Environment Setup

Import `OpenCart Local Environment.postman_environment.json` into Postman and configure:

| Variable | Default | Type | Description |
|---|---|---|---|
| `base_url` | `http://localhost/opencart` | default | OpenCart installation URL |
| `api_username` | `Default` | default | API account (Admin → System → API) |
| `api_key` | `YOUR_API_KEY_HERE` | 🔒 secret | Never commit to source control |
| `api_token` | *(auto-captured)* | default | Set automatically after login |
| `product_id` | `48` | default | Default test product |
| `cart_id` | *(auto-captured)* | default | Set after cart operations |
| `order_id` | *(auto-captured)* | default | Set after order placement |

> ⚠️ **Security:** `api_key` is marked as `secret` in Postman. Use **Postman Vault** or CI/CD secrets — never hardcode it in the collection file.

---

## 🧪 Test Modules & Coverage

The collection is organized into 6 modules that mirror the full checkout flow:

| # | Module | TCs | ✅ Pass | ❌ Fail | Pass Rate |
|---|---|---|---|---|---|
| 1 | Authentication | 4 | 3 | 1 | 75% |
| 2 | Cart | 8 | 5 | 3 | 63% |
| 3 | Customer | 2 | 2 | 0 | 100% |
| 4 | Order | 2 | 1 | 1 | 50% |
| 5 | Token Validation | 4 | 4 | 0 | 100% |
| 6 | Shipping | 1 | 1 | 0 | 100% |
| | **Total** | **21** | **16** | **5** | **76.2%** |

### Priority Breakdown

| Priority | Count | Meaning |
|---|---|---|
| P0 – Blocker | 2 | Release-blocking issues |
| P1 – High Risk | 8 | Critical business flows |
| P2 – Medium Risk | 8 | Important edge cases |
| P3 – Low Risk | 3 | Minor / informational |

---

## 🐞 Bug Report

5 bugs were discovered during test execution. All are currently **Open**.

| Bug ID | TC | Module | Title | Severity | Priority |
|---|---|---|---|---|---|
| `OC_API_BUG_001` | TC-002 | Authentication | Login with invalid key returns `[]` instead of error object | 🔴 Critical | P0 |
| `OC_API_BUG_002` | TC-006 | Cart | Add product without `quantity` returns success instead of error | 🟠 Major | P1 |
| `OC_API_BUG_003` | TC-008 | Cart | Retrieve "empty" cart returns stale products from previous session | 🟠 Major | P1 |
| `OC_API_BUG_004` | TC-010 | Cart | Edit cart without `key` returns raw **HTML PHP warning** — exposes server path | 🔴 Critical | P1 |
| `OC_API_BUG_005` | TC-014 | Order | Place order with all details returns error instead of `order_id` | 🔴 Critical | P0 |

> Full reproduction steps and expected vs. actual results are documented in `OC-API-Bug-Report.xlsx`.

---

## 🚀 How to Run

### Postman UI
1. Import `OpenCart API.postman_collection.json`
2. Import `OpenCart Local Environment.postman_environment.json`
3. Set `api_key` in the environment
4. Select the environment → click **Run Collection**

### Newman (CLI)
```bash
npm install -g newman

newman run "OpenCart API.postman_collection.json" \
  --environment "OpenCart Local Environment.postman_environment.json" \
  --env-var "api_key=YOUR_KEY_HERE" \
  --reporters cli,junit \
  --reporter-junit-export results/api-results.xml
```

### GitHub Actions
```yaml
jobs:
  api-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install Newman
        run: npm install -g newman

      - name: Run API Tests
        run: |
          newman run "Api/OpenCart API.postman_collection.json" \
            --environment "Api/OpenCart Local Environment.postman_environment.json" \
            --env-var "api_key=${{ secrets.OC_API_KEY }}" \
            --reporters cli,junit \
            --reporter-junit-export results/api-results.xml

      - name: Upload Results
        uses: actions/upload-artifact@v4
        with:
          name: api-test-results
          path: results/api-results.xml
```

---

## 📋 Covered API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/index.php?route=api/login` | Authenticate and receive `api_token` |
| `POST` | `/index.php?route=api/cart/add` | Add a product to cart |
| `GET` | `/index.php?route=api/cart/products` | Retrieve cart contents |
| `POST` | `/index.php?route=api/cart/edit` | Edit product quantity in cart |
| `POST` | `/index.php?route=api/cart/remove` | Remove product from cart |
| `POST` | `/index.php?route=api/customer` | Set customer details |
| `POST` | `/index.php?route=api/order/add` | Place an order |
| `POST` | `/index.php?route=api/currency` | Change active currency |
| `POST` | `/index.php?route=api/shipping/address` | Set shipping address |

---

## 🛠️ Prerequisites

| Tool | Version | Purpose |
|---|---|---|
| [XAMPP](https://www.apachefriends.org/) | Any | Local OpenCart server |
| OpenCart | v3.x | Application under test |
| [Postman](https://www.postman.com/) | ≥ v10 | Collection runner |
| [Newman](https://github.com/postmanlabs/newman) | ≥ v6 | CLI / CI runner |

---

## 👤 Author

**Mohamed Ahmed** — QA Engineer

---

<div align="center">
<sub>Part of the OpenCart E2E Testing project · Last updated: June 2026</sub>
</div>
