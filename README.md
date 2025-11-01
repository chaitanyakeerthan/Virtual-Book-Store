# 📚 Virtual Book Store

A **Full-Stack Book Store Web Application** built with **React** (Frontend) and **Spring Boot** (Backend).  
This project demonstrates a complete **e-commerce workflow** including user authentication, browsing, cart management, and order placement — integrated seamlessly with REST APIs.

---

## 🚀 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Frontend** | React.js, Axios, CSS |
| **Backend** | Spring Boot, Spring Data JPA |
| **Database** | MySQL |
| **Tools** | IntelliJ IDEA, VS Code, Postman, Git, GitHub |

---

## ✨ Features

- 👤 **User Authentication:** Register and login securely with BCrypt password encryption  
- 📖 **Book Management:** Browse, search, and view detailed information for books  
- 🛒 **Cart System:** Add, remove, and update items in the shopping cart  
- 💳 **Order Placement:** Checkout and store order details in the database  
- 🔒 **Security:** Integrated with **Spring Security + JWT** (if added) for secure operations  
- 🔗 **REST API Integration:** Smooth communication between frontend and backend  
- 📱 **Responsive UI:** Clean and user-friendly design built using React

---

## ⚙️ Setup Instructions

### 🖥️ Backend Setup
bash
cd backend
Configure MySQL in src/main/resources/application.properties
Example:

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Run the backend server:

mvn spring-boot:run

### 💻 Frontend Setup
cd frontend


Install dependencies:

npm install


Start the development server:

npm start

🧠 Key Highlights

🏗️ Scalable architecture with clean separation of frontend and backend layers

🔁 Seamless REST API communication between React and Spring Boot

🗄️ Persistent storage using MySQL and JPA

🧩 State management and routing in React for efficient navigation

🔐 Spring Security configurations for secure user authentication

⚡ Modular code structure and reusable components
