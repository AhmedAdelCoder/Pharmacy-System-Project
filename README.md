# 💊 Pharmacy System Project

An **Internal Pharmacy Management System** built in **Java** as a Desktop application using **Swing** for the GUI, connected to a database to manage products, sales, employees, and distributing companies.

---

## 📖 Overview

This project is a complete internal pharmacy management system that allows you to:
- Log in with different user roles (Admin / Manager / Employee).
- Manage inventory and products (Products / Drugs).
- Perform sales operations and generate invoices.
- Manage employee and customer data.
- Manage distributing companies.
- View a dashboard with sales and inventory insights.
- Keep a historical record of all transactions (History / Sale Records).

---

## 🖼️ Screenshots

### Login Screen
![Login Screen](image/Screenshot%202026-07-06%20143120.png)

### Sales Screen
![Sales](image/Screenshot%202026-07-06%20143835.png)

### Invoice
![Invoice](image/Screenshot%202026-07-06%20143855.png)

### Dashboard
![Dashboard](image/Screenshot%202026-07-06%20143909.png)

### History
![History](image/Screenshot%202026-07-06%20143921.png)

---

## 🧩 UML Diagram

The system design is documented with a UML diagram illustrating the relationships between the core classes (Employee, Manager, Product, Drug, Sale, Customer, etc.).

![UML Diagram](image/pharmacy-uml.jpeg)

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Java** | Core programming language |
| **Swing** | Building the graphical user interface (GUI) |
| **JDBC** | Database connectivity |
| **MySQL** (or any compatible RDBMS) | Data storage |

---

## 📂 Project Structure

```
Pharmacy-System-Project/
├── image/
│   ├── Pharmacy project.jpeg              # UML Diagram
│   ├── Screenshot 2026-07-06 143120.png   # Login
│   ├── Screenshot 2026-07-06 143835.png   # Sales
│   ├── Screenshot 2026-07-06 143855.png   # Invoice
│   ├── Screenshot 2026-07-06 143909.png   # Dashboard
│   └── Screenshot 2026-07-06 143921.png   # History
│
└── src/internal/pharmacy/system/project/
    │
    ├── GUI/                       # User interface (Views)
    │   ├── CompanyPanel.java          # Distributing companies management view
    │   ├── DashboardPanel.java        # Main dashboard
    │   ├── EmployeeFrame.java         # Employee window
    │   ├── EmployeesPanel.java        # Employees management view
    │   ├── InventoryPanel.java        # Inventory management view
    │   ├── LoginFrame.java            # Login window
    │   ├── MainFrame.java             # Main application window
    │   ├── ManagerFrame.java          # Manager window
    │   ├── ProductSearchPanel.java    # Product search view
    │   ├── SalesPanel.java            # Sales operations view
    │   └── UserManager.java           # User and permissions management
    │
    ├── Customer.java              # Customer class
    ├── CustomerRecords.java       # Customer records
    ├── DatabaseConnection.java   # Database connection handler
    ├── DistributingCompany.java  # Distributing company class
    ├── Drug.java                  # Drug class
    ├── Employee.java              # Employee class (base/parent class)
    ├── InternalPharmacySystemProject.java  # Application entry point (main)
    ├── Inventory.java             # Inventory management
    ├── Manager.java               # Manager class (inherits from Employee)
    ├── PharmacyDBHandler.java     # Database query handler
    ├── Product.java               # Product class
    └── Sale.java                  # Sale class
```

---

## 🏗️ Architecture

The project follows a design similar to a simplified **MVC pattern**:
- **GUI package**: contains all the Frames and Panels for the user interface (Login, Dashboard, Sales, Inventory, etc.).
- **Model classes** (outside the GUI package): represent the core entities such as `Employee`, `Manager`, `Product`, `Drug`, `Sale`, `Customer`, and `DistributingCompany`.
- **PharmacyDBHandler + DatabaseConnection**: the data access layer, responsible for all CRUD operations.
- **InternalPharmacySystemProject.java**: the entry point that launches the `LoginFrame` and starts the user's journey through the system.

`Manager` and `Employee` are connected through inheritance, and every sale is recorded in `Sale`, linked to a `Product`/`Drug`, a `Customer`, and the `Employee` who processed it — which is reflected in the Invoice and History screens.

---

## ✨ Key Features

- 🔐 **Secure login** with different roles (Admin / Manager / Employee).
- 📊 **Dashboard** with a quick overview of sales and inventory.
- 📦 **Inventory management** (add, edit, and search for products and drugs).
- 🧾 **Real-time sales and invoicing system**.
- 👥 **Employee management** with permission levels.
- 🏢 **Distributing companies management**.
- 🕘 **Transaction history** for all operations performed in the system.

---

## ⚙️ Getting Started

### Prerequisites
- **JDK 8+** installed on your machine.
- A database (MySQL or equivalent) with connection details configured in `DatabaseConnection.java`.
- An IDE that supports Swing (e.g., NetBeans, IntelliJ IDEA, or Eclipse).

### Running the Project

```bash
# 1. Clone the repository
git clone https://github.com/AhmedAdelCoder/Pharmacy-System-Project.git

# 2. Open the project in your preferred IDE

# 3. Set up the database and update the connection details in:
#    src/internal/pharmacy/system/project/DatabaseConnection.java

# 4. Run the main class:
#    InternalPharmacySystemProject.java
```

---

## 🗄️ Database

The system relies on `PharmacyDBHandler` and `DatabaseConnection` to interact with the database, covering the core tables for:
- Employees / Managers
- Products / Drugs
- Inventory
- Customers
- Sales
- Distributing Companies

> Make sure to update the connection details (Host, Username, Password, Database Name) in `DatabaseConnection.java` before running the project.

---

## 🤝 Contributing

Contributions are welcome! If you have a suggestion or improvement:
1. Fork the project.
2. Create a new branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

---

## 👤 Author

**Ahmed Adel**
GitHub: [@AhmedAdelCoder](https://github.com/AhmedAdelCoder)

---

## 📄 License

This project is available for use and modification for educational and personal purposes. Feel free to add a license (e.g., MIT License) as needed.
