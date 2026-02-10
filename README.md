# UserVault – Profile Manager

UserVault is a secure **Profile Management Web Application** built using **Spring boot and Thymeleaf**, providing complete user authentication, session handling, and profile management features with a modern UI.

---

## Features

* User can Signup & Login.
* Secure Password Hashing using **BCrypt** (Spring security).
* Session-based Authentication.
* View, Edit and Delete User Profile.
* Forgot Password & Reset Password Flow using session token.
* Account Deletion with Confirmation.
* Input Validation & Error Handling.
* MVC Architecture with Clean Separation of Layers like - Entity, Config, Controller, Service, Repository.

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring boot, Spring Data JPA etc.
* **Frontend:** Thymeleaf, HTML, CSS, JavaScript
* **Database:** PostgreSQL
* **Security:** BCrypt Password Hashing, HttpSession
* **Server:** Apache Tomcat 10.1
* **Build Tool:** Maven

---

## 📂 Project Structure

```
src/main/java
│
├── com.user_vault
│   ├── entity
│   │   ├── User.java
│   │
│   ├── repository
│   │   └── UserRepository.java
│   │
│   ├── service
│   │   ├── UserService.java #interface
|   |   └── impl
|   |         └── UserServiceImpl.java
│   │
│   ├── dto
│   │   └── UserDTO.java
│   │
│   ├── controller
│   │   ├── UserController.java
│   │   ├── AuthController.java
│   │   └── HomeController.java
│   │
│   └── config
│       ├── WebConfig.java
│       └── SecurityConfig.java
│
├── resources
│   ├── static
│   │   ├── css
|   |   |   ├── auth.css
│   │   |   ├── edit.css
│   │   |   ├── home.css
│   │   |   ├── index.css
│   │   |   └── style.css
│   │   └── imgs #images/screenshots 
|   |
│   ├── templates
│   |   ├── login.jsp
│   │   ├── signup.jsp
│   │   ├── home.jsp
│   │   ├── edit.jsp
│   │   ├── forgot.jsp
│   │   ├── reset-password.jsp
│   │   └── index.jsp
│   └── application.properties #all the configurations
│
├── pom.xml
└── README.md
```

---

## 🔐 Security Highlights

* Passwords are **never stored in plain text**.
* Implemented one way hasing password can not retrived in normal form.
* BCrypt hashing ensures strong password protection
* Cache-control headers block back-button access after logout
* Reset password flow protected via session-based validation and Token.

---

## 🔄 Application Flow

1. **Signup** → User registers with hashed password
2. **Login** → Credentials validated using BCrypt
3. **Dashboard** → Protected via session
4. **Profile Management** → View / Edit profile details
5. **Forgot Password** → Identity verification
6. **Reset Password** → Secure update
7. **Logout / Delete Account** → Session invalidated

---

## ⚙️ Setup & Run Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/Chetanpatil45/user-vault.git
   ```

2. Configure database in:

   ```
   application.properties
   ```

3. Update database credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/<your_db>
   spring.datasource.username=<user_name>
   spring.datasource.password=<password>

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. Build the project:

   ```bash
   mvn clean install
   ```

5. Deploy on **Apache Tomcat** and access:

   ```
   http://localhost:8080/uservault
   ```

---

## 📌 Key Learning Outcomes

* Implemented **Spring boot MVC architecture**
* Hands-on experience with **Spring Data JPA**
* Designed secure authentication flows
* Built reusable UI components using Thymeleaf & CSS
* Applied real-world **session management & security**
* Learn about hashing and **BCrypt**.

---

## 🧩 Application Flow Diagram - UserVault

The diagram below illustrates the complete user journey and system flow,
including authentication, profile management, and password recovery.

<img width="11526" height="6693" alt="UserVault-design" src="https://github.com/user-attachments/assets/92f6eff9-e0cb-4aa2-9229-744248fd3e8b" />

---

## 👤 Author

**Chetan Bachchhav**</br>
Java Explorer | Tech Enthusiast
