# 🧪 Swag Labs Web Automation Project

A complete automation test framework for [Swag Labs](https://www.saucedemo.com/) using **Java**, **Selenium WebDriver**, **TestNG**, **Allure Reports**, and **Page Object Model (POM)**.

## 📌 Overview

This framework automates key user flows in the Swag Labs web application, including:

* User authentication (valid/invalid login)
* Product listing & product details
* Add to Cart / Remove from Cart
* Checkout flow (valid & invalid)
* Complete E2E scenarios
* Logging, screenshots on failure, and test reports

## 🛠️ Tech Stack

| Tool / Library     | Purpose                            |
| ------------------ | ---------------------------------- |
| Java               | Core programming language          |
| Selenium WebDriver | Web automation engine              |
| TestNG             | Test framework                     |
| Maven              | Dependency management & build tool |
| Allure Reports     | Test reporting                     |
| Log4j2             | Logging utility                    |
| JSON               | Data-driven testing                |
| Git & GitHub       | Version control                    |

---

## 📁 Project Structure

```
Swag_Lab_Project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── DriverFactory/
│   │   │   │   └── DriverFactory.java
│   │   │   ├── Pages/
│   │   │   │   ├── Login_Page.java
│   │   │   │   ├── Home_Page.java
│   │   │   │   ├── AddToCart_Page.java
│   │   │   │   ├── Checkout_Page.java
│   │   │   │   ├── OverView_Page.java
│   │   │   │   ├── Finish_Page.java
│   │   │   │   └── ProductDetails_Page.java
│   │   │   └── Utilities/
│   │   │       ├── AllureUtils.java
│   │   │       ├── DataUtilitie.java
│   │   │       ├── LogsUtility.java
│   │   │       └── Utilitie.java
│   │   └── resources/
│   │       ├── allure.properties
│   │       └── log4j2.properties
│   ├── test/
│   │   ├── java/
│   │   │   ├── Listeners/
│   │   │   │   ├── ITestResultListener.java
│   │   │   │   └── TestMethodLoggerListener.java
│   │   │   └── Tests/
│   │   │       ├── Login_TC.java
│   │   │       ├── Home_TC.java
│   │   │       ├── Add_Cart_TC.java
│   │   │       ├── Product_Details_TC.java
│   │   │       ├── Checkout_TC.java
│   │   │       ├── OverView_TC.java
│   │   │       └── Finish_TC.java
│   │   └── resources/
│   │       └── TestData/
│   │           ├── ValidLogin.json
│   │           ├── InValidLogin.json
│   │           ├── Valid_Checkout_data.json
│   │           ├── InValid_Checkout_data.json
│   │           └── environments.properties
│
├── output/
│   ├── allure-report/
│   │   └── index.html
│   ├── allure-results/
│   ├── screenshots/
│   └── logs/
│
├── testng.xml
├── pom.xml
└── README.md
```

---

## 🧪 How to Run Tests

### Using TestNG in IDE

> Right-click `testng.xml` > Run

### Using Maven

```bash
mvn clean test
```

---

## 📈 Allure Report & Upload

**To generate and view the Allure report locally:**

```bash
allure serve Test_out/allure-results
```

> The report is generated from the results stored under `Test_out/allure-results`.
> It includes test case statuses, screenshots on failure, execution time, and detailed steps.
> You can take a screenshot of the report and include it in documentation or presentations.

📁 **Allure results location:**

```
Test_out/allure-results
```

📈 **Example screenshot of the report interface:**

![Allure Report Screenshot](https://github.com/nour-allah-khaled/Swag_Lab-Web-Automation-Project/blob/main/assets/allure-report.png)

---

## 🚀 How to Run Locally

1. Clone the repo:

```bash
git clone https://github.com/your-username/Swag_Lab_Project.git
```

2. Navigate to the project directory:

```bash
cd Swag_Lab_Project
```

3. Run the tests:

```bash
mvn clean test
```

4. Generate and open the Allure report:

```bash
allure serve Test_out/allure-results
```

---

## 👤 Author

**Nour Allah Khaled**  
Manual and Automation Tester  
🔗 [LinkedIn](www.linkedin.com/in/nour-allah-khaled)  
📧 [nourallahk7@gmail.com](mailto:nourallahk7@gmail.com)

