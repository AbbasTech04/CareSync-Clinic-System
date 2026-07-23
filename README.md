# 🏥 CareSync - Clinic Appointment Management System

**CareSync** is a lightweight, full-stack medical clinic appointment application built with **Java 21**, **Spring Boot 3**, and **Thymeleaf**. Designed as a clean-architecture foundation for developers and students.

---

## ✨ Key Features

* ** smart Booking Engine:** Automatically blocks weekend bookings (Saturdays & Sundays) and prevents double-booking time slots for doctors.
* **📄 Digital Receipts:** Seamless client-side PDF ticket generation using `html2pdf.js`.
* **📊 Receptionist Dashboard:** Interactive administration panel powered by `DataTables.js` for instant searching and sorting.
* **⚙️ Clean Architecture:** Follows strictly the MVC (Model-View-Controller) design pattern.

---

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Hibernate.
* **Frontend:** HTML5, CSS3, JavaScript, Thymeleaf, Bootstrap 4 (SB Admin 2).
* **Database:** MySQL 8.0.

---

## 📄 Project Documentation

A comprehensive **50+ page technical manual** and developer guide is available in the repository. It covers full architecture diagrams, error troubleshooting, and database design.

👉 **[Download Full Documentation PDF](./docs/CareSync-Documentation.pdf]**
---

## 🔒 Security Roadmap (Contribution Challenge)

> ⚠️ **Security Notice:** Currently, the `/admin` route is kept accessible without authentication to allow quick frontend testing and review. 

If you are a developer looking to contribute, feel free to implement **Spring Security** to lock down the admin route! 

---

## 📜 License

This project is licensed under the [MIT License](LICENSE) - feel free to use, modify, and distribute it.
