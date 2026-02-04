# 🧪 Java Programming Exercise: Refactoring, Security, and New Features

This exercise aims to evaluate your skills in Java development with Spring Boot, focusing on:

- 🔁 Refactoring and code quality
- 🔐 Security and vulnerability management
- ⚙️ Implementation of new features
- 🧪 Writing tests and documentation

---

## 🎯 Goal

The repository contains a simple **Spring Boot** application. Your task is to improve the existing code and add new features, demonstrating proficiency in:

- 🔐 **Security**: Identifying and fixing vulnerabilities.
- ✨ **Code Quality**: Refactoring, exception handling, and documentation.
- 🚀 **Functional Development**: Extending the application with business logic.
- ✅ **Testing**: Validating features through automated tests.

---

## ⚙️ Setup and Submission

### 📦 Cloning the Repository
Fork the repository to your personal GitHub account.

### 💾 Exercise Submission
Complete all the required tasks, ensuring all changes are committed and pushed to your fork.

### 📬 Work Submission
Send the repository link via email to: **f.mori@sara.it**

---

## 📌 Tasks to Complete

1. **🧾 Data Validation**
    - In the user creation service, implement strict validation for the `email` and `phoneNumber` fields.
    - The `phoneNumber` must comply with the **Italian standard** (correct prefix and length).

2. **🛡️ SQL Injection Prevention**
    - The `PUT` endpoint is vulnerable to **SQL Injection**.
    - Identify and fix the vulnerable logic by properly validating inputs.

3. **📊 Pagination, Sorting, and Search**
    - Complete the user search method:
        - Return a paginated list after input validation.
        - Apply sorting based on the `OrderType` enum.
        - Implement a case-insensitive filter on a search string (e.g., name or email using `contains`).

4. **🚨 Exception Handling**
    - Introduce centralized exception handling consistent with the existing `StatusDTO` standard.
    - All responses, including errors, must return HTTP status code **200**.

5. **🔐 JWT Security**
    - Integrate authentication using **JSON Web Token (JWT)**.
    - The token must be validated against:
        - **Policy**: Applicable authorization rules
        - **Issuer**: Token issuing authority
        - **Expiration**: Token validity period

6. **🐞 Bug Fixing**
    - Identify and fix any bugs in the base code.

7. **🧪 Unit Testing**
    - Add unit tests for new and modified features.
    - Ensure adequate coverage.

8. **📝 Javadoc Documentation**
    - Add **Javadoc comments** for relevant classes, methods, and attributes.
    - This improves code **readability** and **maintainability**.

---

## 📎 Important Notes

- 📚 **External Libraries**: Use of third-party libraries is allowed if they are **up-to-date and free from known vulnerabilities**.
- 🧱 **Architecture**: Do **not** modify the existing layered architecture of the application.

---

## ❓ Follow-up Question

> **🧠 For larger and more complex projects, why is it discouraged to use web-exposed objects (e.g., `? extends GenericRequest`, `? extends GenericResponse`) within the Service layer?**

Please include a brief answer to this question in your submission email.
