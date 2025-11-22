package internal.pharmacy.system.project;

import java.sql.*;
import java.time.LocalDate;

public class PharmacyDBHandler {

    // ================== INSERT PRODUCT ==================
    public static void insertProduct(Product p) {
        String sql = "INSERT INTO products (productID, name, category, price, stockQuantity, expiryDate, isPrescriptionRequired) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getProductID());
            ps.setString(2, p.getName());
            ps.setString(3, p.getCategory());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getStockQuantity());
            ps.setDate(6, Date.valueOf(p.getExpiryDate()));
            ps.setBoolean(7, p.isIsPrescriptionRequired());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================== INSERT EMPLOYEE ==================
    public static void insertEmployee(Employee e) {
        String sql = "INSERT INTO employees (employeeID, name, username, password, role, totalSales) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getEmployeeID());
            ps.setString(2, e.getName());
            ps.setString(3, e.getUsername());
            ps.setString(4, e.getPassword());
            ps.setString(5, e.getRole());
            ps.setDouble(6, e.getTotalSales());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ================== INSERT COMPANY ==================
    public static void insertCompany(DistributingCompany c) {
        String sql = "INSERT INTO companies (companyID, name, email, location) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCompanyID());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getLocation());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ================== SELECT ALL PRODUCTS ==================
    public static void printAllProducts() {
        String sql = "SELECT * FROM products";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("productID") +
                                   ", Name: " + rs.getString("name") +
                                   ", Category: " + rs.getString("category") +
                                   ", Price: " + rs.getDouble("price") +
                                   ", Stock: " + rs.getInt("stockQuantity") +
                                   ", Expiry: " + rs.getDate("expiryDate") +
                                   ", Prescription: " + rs.getBoolean("isPrescriptionRequired"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

