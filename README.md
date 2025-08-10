# 🧪 Swag Lab Web Automation Testing 

## 📌 Project Overview
The **Swag Lab Automation Testing Framework** is a comprehensive, maintainable, and scalable automated testing solution designed to validate the **Swag Labs** e-commerce web application.

This framework ensures that every release of the application meets **functional, regression, and smoke testing requirements** before deployment.  
It is built using **modern automation practices** with:
- **Java** for programming.
- **Selenium WebDriver** for browser automation.
- **TestNG** for test management and execution control.
- **Page Object Model (POM)** for clean and reusable code.
- **Maven** for dependency and build management.
- **Allure** for advanced reporting.
- **Log4j2** for structured and trackable logging.
- **Jenkins** for CI/CD.
- **Jira** for test management and defect tracking.

---

## ✅ Key Features
- Full **Page Object Model** implementation for maintainability.
- **Data-driven testing** using JSON files.
- **Parallel test execution** for faster test cycles.
- **Allure reports** with detailed screenshots and logs.
- **Multiple TestNG XML suite files** for flexible execution.
- **Jenkins pipeline integration** for automated execution.
- **Jira integration** for defect tracking and linking failed cases.

---

## 🛠 Tech Stack
| Technology / Tool      | Purpose                                           |
| ---------------------- | ------------------------------------------------- |
| **Java**               | Main programming language                         |
| **Selenium WebDriver** | Web UI automation                                 |
| **TestNG**             | Test execution and management                     |
| **Maven**              | Build automation and dependency management        |
| **Allure Reports**     | Rich and interactive reporting                    |
| **Log4j2**             | Logging framework                                 |
| **Git**                | Version control and code collaboration            |
| **Jenkins**            | Continuous Integration and Delivery               |
| **Jira**               | Defect tracking, sprint planning, test management |
| **JSON**               | Data-driven testing                               |

---

## 📂 Project Structure
```plaintext
Swag_Lab_Project/
│
├── allure-report/                      # Generated Allure HTML reports
├── allure_result_screens/              # Test execution screenshots organized by scenario type
│   ├── E2E_ResultScreen/
│   ├── Paraller_ResultScreen/
│   ├── Smoke_ResultScreen/
│   └── Testing_ResultScreen/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── driver_factory/          # WebDriver setup & browser configuration
│   │   │   ├── pages/                   # Page classes (POM)
│   │   │   └── utilities/               # Utility classes
│   │   └── resources/                   # Allure & Log4j config
│
│   ├── test/
│   │   ├── java/
│   │   │   ├── e2e_scenarios/
│   │   │   ├── listeners/
│   │   │   ├── paraller_execution/
│   │   │   ├── smoke_scenarios/
│   │   │   ├── tests/
│   │   │   └── utilitie/
│   │   └── resources/
│   │       └── TestData/                 # JSON data files
│
├── target/                               # Maven build output
├── pom.xml                               # Maven dependencies
├── testng.xml                            # Main TestNG suite
├── e2e.xml                               # E2E test suite for valid and invalid scenarios
├── e2e_valid.xml                         # Valid E2E scenarios
├── e2e_invalid.xml                       # Invalid E2E scenarios
├── parallerExecuution.xml                # Parallel execution suite
├── smoke.xml                             # Smoke tests
└── README.md                             # Documentation

```
---


