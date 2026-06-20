<div align="center">

# ⚡ OpenCart Performance Testing

<br/>

[![JMeter](https://img.shields.io/badge/Apache_JMeter-5.x-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white)](https://jmeter.apache.org/)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Protocol](https://img.shields.io/badge/Protocol-HTTP%20/%20REST-2496ED?style=for-the-badge&logo=fastapi&logoColor=white)](#)
[![Samples](https://img.shields.io/badge/Total_Samples-10%2C200-6A0DAD?style=for-the-badge)](#-results-summary)
[![Error Rate](https://img.shields.io/badge/Aggregate_Error_Rate-4.48%25-FF6B35?style=for-the-badge)](#-results-summary)
[![Report](https://img.shields.io/badge/HTML_Dashboard-Available-43A047?style=for-the-badge&logo=googlechrome&logoColor=white)](#-viewing-the-html-dashboard-report)

<br/>

> **Load & stress testing suite for the OpenCart REST API.**  
> Simulates 50–200 concurrent shoppers through the full purchase journey:  
> Login → Cart → Customer Details → Shipping → Payment → Order Placement.

<br/>

| 🛠 Tool | 📌 Target | 🌐 Protocol | 👤 Prepared By |
|:---:|:---:|:---:|:---:|
| Apache JMeter 5.x | OpenCart v3.x | HTTP / REST | Mohamed Ahmed |

</div>

---

## 📑 Table of Contents

- [📁 Folder Structure](#-folder-structure)
- [🔍 Overview](#-overview)
- [🎯 Test Objectives](#-test-objectives)
- [🏗 Test Plan Architecture](#-test-plan-architecture)
- [🛍 Business Flow Under Test](#-business-flow-under-test)
- [⚙️ Configuration & Variables](#-configuration--variables)
- [💻 Prerequisites](#-prerequisites)
- [▶️ Running the Test Plan](#-running-the-test-plan)
- [📊 Results Summary](#-results-summary)
- [🔎 Key Findings & Bottlenecks](#-key-findings--bottlenecks)
- [💡 Recommendations](#-recommendations)
- [🖥 Viewing the HTML Dashboard Report](#-viewing-the-html-dashboard-report)
- [📎 Artifacts Reference](#-artifacts-reference)

---

## 📁 Folder Structure

```
performance/
│
├── ⚙️  OC-PERF-PLAN.jmx                                   ← JMeter test plan (source of truth)
├── 📄  results.jtl                                         ← Raw sample results (10,200 samples)
│
├── 📂  HTML-Report/                                        ← Generated interactive dashboard
│   ├── index.html                                          ← Entry point — open in any browser
│   ├── content/pages/
│   │   ├── OverTime.html                                   ← Response time & throughput over time
│   │   ├── ResponseTimes.html                              ← Percentile distribution charts
│   │   └── Throughput.html                                 ← Requests/sec & bandwidth charts
│   └── sbadmin2-1.0.7/                                     ← Dashboard theme assets
│
├── 🖼️  Test Plan Configuration.png                         ← Thread Group setup screenshot
├── 🖼️  View Results Tree.png                               ← Per-request debug view
├── 🖼️  Summary Report.png                                  ← JMeter Summary Report listener
├── 🖼️  Aggregate Graph — Average Response Times.png        ← Response time visualization
└── 🖼️  Aggregate Graph — Settings & Percentile Data.png    ← Percentile configuration & output
```

---

## 🔍 Overview

This module covers **load and stress testing** of the OpenCart REST API using **Apache JMeter**. It simulates concurrent shoppers performing a complete purchase journey — from authentication through order placement — to measure how the backend performs under increasing concurrency, and to surface response time degradation, throughput limits, and error-rate spikes before they reach production.

Unlike the UI automation suite, this layer communicates **directly with the API** (`route=api/...`), isolating backend and application-server performance from front-end rendering overhead.

The plan executed **10,200 total samples** across two concurrency profiles (50 and 200 users), producing the results captured in `results.jtl` and the interactive `HTML-Report/`.

---

## 🎯 Test Objectives

- Measure **response time** (mean, median, P90 / P95 / P99) for each transaction under realistic concurrent load
- Measure **throughput** (requests/sec) and **error rate** as concurrency increases
- Identify the **weakest link** in the checkout pipeline under stress conditions
- Establish a **performance baseline** to compare against future releases or infrastructure changes
- Validate that the system can sustain load without connection-pool exhaustion or cascading timeout failures

---

## 🏗 Test Plan Architecture

The plan (`OC-API-PERF-PLAN`) is organized into **two Thread Groups** sharing the same 18-request business flow:

| Thread Group | Users | Ramp-Up | Loops | Total Requests | Profile |
|---|:---:|:---:|:---:|:---:|---|
| **01 — Load Test** | 50 | 10 s | 5 | 4,500 | Realistic day-to-day traffic |
| **02 — Stress Test** | 200 | 60 s | 3 | 10,800 | Peak / promotional spike |

> Both Thread Groups share the same **HTTP Header Manager** (`Content-Type: application/x-www-form-urlencoded`), **HTTP Request Defaults** (`connect_timeout = 10 s`, `response_timeout = 20 s`), and a **Uniform Random Timer** to emulate natural think-time between steps.

### Request Hierarchy (Transaction Controllers)

```
Thread Group
 ├── 🔐 Transaction Controller: Login
 │     └── TC-001  POST  api/login                 → captures {api_token}
 │
 ├── 🛒 Transaction Controller: Cart
 │     ├── TC-004  POST  api/cart/add               → adds product_id=48
 │     └── TC-007  GET   api/cart/products           → captures {cart_id}
 │
 └── 💳 Transaction Controller: Checkout
       ├── TC-012  POST  api/customer               → sets customer details
       ├── TC-020  POST  api/shipping/address       → sets shipping address
       │           POST  api/shipping/methods       → selects shipping method
       │           POST  api/payment/address        → sets payment address
       │           POST  api/payment/methods        → selects payment method
       └── TC-014  POST  api/order/add              → places order → captures {order_id}
```

Every request carries a **Response Assertion** (`HTTP Status = 200`), and `api_token`, `cart_id`, and `order_id` are chained via **JSON Extractor Post-Processors** — so each virtual user runs a fully authenticated, stateful, end-to-end purchase, not isolated stub calls.

---

## 🛍 Business Flow Under Test

```
  [1] Login
      ↓
  [2] Add Product to Cart (product_id = 48)
      ↓
  [3] View Cart Contents
      ↓
  [4] Set Customer Details
      ↓
  [5] Set Shipping Address  →  [6] Select Shipping Method
      ↓
  [7] Set Payment Address   →  [8] Select Payment Method
      ↓
  [9] Place Order  ✅
```

This mirrors the UI automation checkout flow, giving the project **two independent lenses** over the same business-critical path: functional correctness (Selenium) and performance behavior (JMeter).

---

## ⚙️ Configuration & Variables

Set under **Test Plan → User Defined Variables**:

| Variable | Default | Purpose |
|---|---|---|
| `PROTOCOL` | `http` | Scheme for all requests |
| `DOMAIN` | `localhost` | Target host — update per environment |
| `PATH_PREFIX` | `/opencart` | Base path to OpenCart installation |
| `API_USERNAME` | `Default` | OpenCart API user (Admin → System → API) |
| `API_KEY` | `YOUR_API_KEY_HERE` | 🔒 Secret key — **never commit a real value** |
| `PRODUCT_ID` | `48` | Product added to cart during the test flow |

> ⚠️ Always override `DOMAIN`, `PATH_PREFIX`, and `API_KEY` before running. Use `-J` CLI flags (see below) — never hardcode secrets in the `.jmx` file.

---

## 💻 Prerequisites

| Requirement | Version | Check |
|---|---|---|
| [Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi) | 5.5+ | `jmeter --version` |
| Java JDK | 11+ (21 recommended) | `java -version` |
| OpenCart instance | v3.x — API module enabled | Admin → System → API |

---

## ▶️ Running the Test Plan

### GUI Mode — Authoring & Debugging Only

```bash
jmeter -t OC-PERF-PLAN.jmx
```

> ⚠️ GUI mode is only for inspecting and editing the plan. **Never generate real load in GUI mode** — JMeter's own documentation warns it skews results significantly.

### Non-GUI Mode — Actual Load/Stress Execution

```bash
jmeter -n \
  -t OC-PERF-PLAN.jmx \
  -l results.jtl \
  -e -o HTML-Report
```

| Flag | Meaning |
|---|---|
| `-n` | Headless (non-GUI) execution |
| `-t` | Path to test plan |
| `-l` | Raw output file (JTL/CSV) |
| `-e -o` | Generate HTML dashboard after run |

### Override Variables at Runtime

```bash
jmeter -n -t OC-PERF-PLAN.jmx \
  -JDOMAIN=staging.opencart.example.com \
  -JPATH_PREFIX="" \
  -JAPI_KEY=$OC_API_KEY \
  -l results.jtl -e -o HTML-Report
```

### Run via GitHub Actions

```yaml
jobs:
  performance-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Install JMeter
        run: |
          wget -q https://downloads.apache.org/jmeter/binaries/apache-jmeter-5.6.3.tgz
          tar -xf apache-jmeter-5.6.3.tgz
          echo "$PWD/apache-jmeter-5.6.3/bin" >> $GITHUB_PATH

      - name: Run Performance Tests
        run: |
          jmeter -n -t performance/OC-PERF-PLAN.jmx \
            -JDOMAIN=${{ vars.OC_DOMAIN }} \
            -JAPI_KEY=${{ secrets.OC_API_KEY }} \
            -l performance/results.jtl \
            -e -o performance/HTML-Report

      - name: Upload HTML Report
        uses: actions/upload-artifact@v4
        with:
          name: jmeter-html-report
          path: performance/HTML-Report/
```

---

## 📊 Results Summary

> Extracted from `results.jtl` — **10,200 total samples** across both Thread Groups.

### Individual Request Metrics

| Transaction | Samples | Error % | Mean | P50 | P90 | P95 | P99 | Max |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| TC-001 Login | 850 | 9.18% | 5,486 ms | 4,070 ms | 19,383 ms | 20,003 ms | 20,011 ms | 20,065 ms |
| TC-004 Cart — Add Product | 850 | 6.71% | 4,457 ms | 2,706 ms | 15,697 ms | 20,002 ms | 20,007 ms | 20,051 ms |
| TC-007 Cart — View Products | 850 | 6.24% | 3,777 ms | 2,487 ms | 12,147 ms | 20,002 ms | 20,005 ms | 20,017 ms |
| TC-012 Customer — Add Details | 850 | 4.47% | 3,545 ms | 2,171 ms | 7,539 ms | 15,172 ms | 20,004 ms | 20,010 ms |
| TC-020 Shipping — Set Address | 850 | 4.59% | 3,734 ms | 3,007 ms | 8,014 ms | 17,313 ms | 20,004 ms | 20,013 ms |
| Shipping — Set Method | 850 | 3.65% | 3,699 ms | 4,050 ms | 7,063 ms | 14,587 ms | 20,005 ms | 20,019 ms |
| Payment — Set Address | 850 | 2.00% | 3,361 ms | 4,064 ms | 4,350 ms | 11,485 ms | 20,004 ms | 20,009 ms |
| Payment — Set Method | 850 | 1.76% | 3,290 ms | 4,072 ms | 4,318 ms | 6,825 ms | 20,003 ms | 20,013 ms |
| TC-014 Order — Place Order | 850 | 1.76% | 3,491 ms | 4,078 ms | 4,374 ms | 6,963 ms | 20,003 ms | 20,010 ms |

### Transaction Controller Rollups

| Controller | Samples | Error % | Mean | P50 | P90 | P95 | P99 | Max |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **Login** | 850 | 9.18% | 5,486 ms | 4,070 ms | 19,383 ms | 20,003 ms | 20,011 ms | 20,065 ms |
| **Cart** | 850 | 9.88% | 8,234 ms | 5,680 ms | 22,106 ms | 32,362 ms | 40,008 ms | 40,054 ms |
| **Checkout** | 850 | 13.29% | 21,120 ms | 24,521 ms | 43,776 ms | 51,509 ms | 65,581 ms | 66,952 ms |

### Error Rate Visual

```
Login       ████████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░  9.18%
Cart        █████████████████████████░░░░░░░░░░░░░░░░░░░░░░  9.88%
Checkout    █████████████████████████████████░░░░░░░░░░░░░░  13.29%
Customer    ███████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  4.47%
Shipping    ████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  4.59%
Payment     █████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  2.00%
Order       █████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  1.76%
```

---

## 🔎 Key Findings & Bottlenecks

### 🔴 Critical — Checkout Controller (13.29% error rate, 21 s mean)

The rolled-up **Checkout** transaction averages **21.1 seconds** with a worst case of **67 seconds** and a **13.29% error rate** — far above any industry-standard SLA for a checkout flow (<3 s, <1% errors). The gap between individual sub-step latency (~3.3–3.7 s each) and the aggregated total points to either **sequential DB locking**, **session-write contention**, or **cumulative cart-recalculation cost** compounding across each checkout step.

### 🟠 High — Login Degradation Under Stress (9.18% error rate)

Login is the **second-highest error contributor**. With a P90 of **19.4 seconds** and a P95 collapsing to the timeout ceiling (20,003 ms), login degrades disproportionately as concurrency climbs toward 200 users — consistent with **session-table locking** or **bcrypt CPU saturation** under parallel load.

### 🟡 Medium — Timeout-Driven Failures, Not Fast Errors

Multiple transactions show P95/P99 values clustering at exactly **20,000–20,065 ms** — the configured `response_timeout`. These are **client-side giveups**, not server-side rejections. The server isn't returning fast errors; it's exhausting the connection pool or blocking on slow queries until the client times out. This pattern is a classic symptom of **thread-pool or DB-connection exhaustion**.

### 🟢 Stable — Payment & Order Placement

`Payment — Set Method` and `TC-014 Order — Place Order` both post **1.76% error rates** and sub-4 s P90 values — the healthiest steps in the flow. These hold up well at 200 concurrent users, suggesting the order-write path itself is not the bottleneck.

---

## 💡 Recommendations

| Priority | Recommendation |
|---|---|
| 🔴 P0 | **Profile the checkout step chain** server-side — enable `slow_query_log` (MySQL) and trace `api/customer`, `api/shipping/*`, `api/payment/*`, `api/order/add`. The ~21 s aggregate vs ~3.5 s per-step means cost is **cumulative**, not per-request. |
| 🔴 P0 | **Fix Login timeout at high concurrency** — investigate bcrypt cost factor (consider reducing from 12 to 10 on non-sensitive environments) and check `oc_session` table locking under parallel writes. |
| 🟠 P1 | **Enable OPcache** on the PHP layer and compare against this baseline as the "before" benchmark — this alone can halve PHP execution time on OpenCart. |
| 🟠 P1 | **Add a DB query cache / connection pool** (e.g. ProxySQL or increase `max_connections`) to prevent connection-pool exhaustion at 200 concurrent users. |
| 🟡 P2 | **Design a breakpoint test** — use a Stepping Thread Group or Concurrency Thread Group to find the exact user count where error rate crosses 1%. The current jump from 50→200 users skips the knee of the curve. |
| 🟡 P2 | **Add a User-Agent header** if running against a shared or cloud-hosted target — WAF/anti-bot rules can inject 403s mid-test and inflate error rates artificially. |
| 🟢 P3 | **Raise response_timeout carefully** only as a diagnostic aid alongside server-side logging — it will mask failures as "slow success" rather than surface them as errors. |

---

## 🖥 Viewing the HTML Dashboard Report

The `HTML-Report/` folder contains a fully static interactive dashboard — no server required.

```bash
# macOS
open HTML-Report/index.html

# Linux
xdg-open HTML-Report/index.html

# Windows
start HTML-Report\index.html
```

The dashboard includes:

- **APDEX table** — satisfaction scores per transaction (target: ≤ 500 ms, tolerated: ≤ 1500 ms)
- **Response Time Over Time** — see exactly when latency spikes during the ramp-up
- **Throughput Over Time** — requests/sec and active threads over the test duration
- **Response Time Percentiles** — P50 / P90 / P95 / P99 distribution per transaction
- **Errors** — error rate by type and transaction

---

## 📎 Artifacts Reference

| Artifact | Description |
|---|---|
| `OC-PERF-PLAN.jmx` | JMeter source plan — open in GUI to inspect Thread Groups, samplers, extractors, and assertions |
| `results.jtl` | Raw per-sample log (10,200 rows) — import into JMeter or any JTL-compatible analysis tool |
| `HTML-Report/index.html` | Interactive dashboard entry point — charts, APDEX, percentiles |
| `Test Plan Configuration.png` | Screenshot of Thread Group / sampler setup |
| `View Results Tree.png` | Per-request request/response debug view |
| `Summary Report.png` | JMeter built-in Summary Report listener output |
| `Aggregate Graph — Average Response Times.png` | Visual comparison of mean response times per transaction |
| `Aggregate Graph — Settings & Percentile Data.png` | Percentile configuration and resulting bar chart |

---

<div align="center">

**OpenCart Performance Testing** &nbsp;·&nbsp; Load & Stress &nbsp;·&nbsp; Apache JMeter

*Part of the [OpenCart E2E Testing Project](../README.md)*

<br/>

![Prepared by](https://img.shields.io/badge/Prepared_by-Mohamed_Ahmed-0052CC?style=flat-square)
![Last Updated](https://img.shields.io/badge/Last_Updated-June_2026-43A047?style=flat-square)
![Samples](https://img.shields.io/badge/Samples_Analyzed-10%2C200-D22128?style=flat-square)

</div>
