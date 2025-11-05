
package internal.pharmacy.system.project;

import java.util.ArrayList;
import java.util.List;


public class Employee {
    private String employeeID, name , username, password , role;
    private double totalSales;
    private List<Sale> salesHistory = new ArrayList<>();
    
//---------------------------------- Construcator ------------------------------

    public Employee(String employeeID, String name, String username, String password, String role) {
        if (employeeID == null || name == null || username == null || password == null || role == null) {
            throw new IllegalArgumentException("Employee fields cannot be null.");
        }
        setEmployeeID(employeeID);
        setName(name);
        setUsername(username);
        setPassword(password);
        setRole(role);
        this.totalSales = 0;
    }
//--------------------- setter and getter --------------------------------------

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    public List<Sale> getSalesHistory() { return salesHistory; }
    
//-------------------------------- methods -------------------------------------
    //==> Function Register
    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful! Welcome " + username);
            return true;
        } else {
            System.out.println("Login failed! Invalid username or password.");
            return false;
        }
    }
    
    public void processSale(Sale sale) {
        if (sale == null) {
            System.out.println("Error: Sale object cannot be null.");
            return;
        }
        if (sale.getTotalAmount() <= 0) {
            System.out.println("Error: Invalid sale amount.");
            return;
        }
        double saleTotal = sale.getTotalAmount();
        totalSales += saleTotal;
        salesHistory.add(sale);
        System.out.println("Sale processed successfully. Total Amount: " + saleTotal);
    }

    public void viewInventory(Inventory inventory, String drugName) {
        if (inventory == null || drugName == null) {
            System.out.println("Error: Invalid inventory or drug name.");
            return;
        }

        boolean found = false;
        for (Product p : inventory.getProductList()) {
            if (p.getName().equalsIgnoreCase(drugName)) {
                System.out.println("drugName " + p.getName());
                System.out.println("StockQuantity " + p.getStockQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("The medicine is unavailable.");
        }
    }
    
    public double calculateBonus(double bonusPercentage) {
        if (bonusPercentage < 0) {
            System.out.println("Error: Bonus percentage cannot be negative.");
            return 0;
        }
        
        double bonus = totalSales * (bonusPercentage / 100);
        System.out.println("Bonus for " + name + ": " + bonus);
        return bonus;
    }

    public void printSalesHistory() {
        if (salesHistory.isEmpty()) {
            System.out.println("No sales made yet.");
            return;
        }
        System.out.println("=== Sales History for " + name + " ===");
        for (Sale s : salesHistory) {
            System.out.println(s.printReceipt());
        }

    }
}
