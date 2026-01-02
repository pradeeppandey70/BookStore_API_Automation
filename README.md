📘 BookStore API Automation Framework
🚀 Overview

This project is an end-to-end API Automation Framework built using Java + Rest Assured + TestNG, integrated with Docker and Jenkins CI pipeline.
It automates critical workflows of the BookStore API including user management, authentication, book operations, schema validation, and CI execution.

The framework is designed following enterprise standards with reusability, scalability, and CI/CD readiness in mind.

🧰 Tech Stack:

- Language: Java (JDK 17)
- API Automation: Rest Assured
- Test Framework: TestNG
- Build Tool: Maven
- CI/CD: Jenkins
- Containerization: Docker
- Reporting: TestNG Reports (Allure-ready)
- Version Control: Git & GitHub
- Schema Validation: JSON Schema Validator

📂 Project Structure

<img width="817" height="595" alt="image" src="https://github.com/user-attachments/assets/c8739955-8614-46b7-bea6-75ea41fe1319" />


🔐 Automated Scenarios

✅ User Management
- Create user
- Generate authentication token
- Fetch user details (authorized)
- Schema validation for responses


✅ Book Store Operations
- Fetch available books
- Add book to user
- Delete book
- Authorization validation

✅ Validations
- HTTP status codes
- Response body assertions
- JSON schema validation
- Authorization checks

▶️ How to Run Tests
- Run Locally
mvn clean test

- Run Using TestNG Suite
mvn clean test -DsuiteXmlFile=testng.xml

- Run Using Docker
docker build -t bookstore-api-tests .
docker run bookstore-api-tests

🔄 CI/CD with Jenkins
- Jenkins pipeline pulls code from GitHub
- Builds Docker image
- Runs API tests inside Docker container
- Publishes test results
- Pipeline fails on test failure (CI enforcement)
- ✔️ Pipeline Status: SUCCESS ✅

🌟 Key Highlights
- Enterprise-grade framework design
- Fully Dockerized execution
- Jenkins CI pipeline integration
- Token-based authentication handling
- JSON schema validation
- Reusable request specifications
- Clean separation of concerns

📌 Future Enhancements

- Parallel execution using TestNG
- Allure reporting integration
- Environment-based execution
- Database validation layer
- API + UI end-to-end integration

👨‍💻 Author

Pradeep Pandey
Automation Test Engineer | API | CI/CD | Java | Selenium

🔗 GitHub: https://github.com/pradeeppandey70
