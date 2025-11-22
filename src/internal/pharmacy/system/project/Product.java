
package internal.pharmacy.system.project;
import java.time.LocalDate;

public class Product {
    private int productID;
    private int stockQuantity;
    private double price;
    private String name;
    private LocalDate expiryDate;
    private boolean isPrescriptionRequired;
    private String category;
    
//------------------------------ Constructor  ----------------------------------
    public Product(){
        this.name="";
        this.expiryDate = null;
        this.price=0.0;
        this.productID=0;
        this.stockQuantity=0;
        this.isPrescriptionRequired=false;
        this.category="";
    }
    public Product(int productID, int stockQuantity, double price, String name, LocalDate expiryDate, boolean isPrescriptionRequired,String category) {
        setProductID(productID);
        setStockQuantity(stockQuantity);
        setPrice(price);
        setName(name);
        setExpiryDate(expiryDate);
        setIsPrescriptionRequired(isPrescriptionRequired);
        setCategory(category);
    }   
    
//--------------------------------- setters ------------------------------------
    
    public void setProductID(int productID) {
        if (productID <= 0) {
        throw new IllegalArgumentException("Product ID must be a positive number.");
        }
        this.productID = productID;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }
    
    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name.trim();
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past.");
        }
        this.expiryDate = expiryDate;
    }

    public void setIsPrescriptionRequired(boolean isPrescriptionRequired) {
        this.isPrescriptionRequired = isPrescriptionRequired;
    }
    
    public void setCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank.");
        }   
        this.category = category;
    }
    
//--------------------------------- Getters ------------------------------------
    public int getProductID() {
        return productID;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public LocalDate  getExpiryDate() {
        return expiryDate;
    }

    public boolean isIsPrescriptionRequired() {
        return isPrescriptionRequired;
    }
    
    public String getCategory() {
        return category;
    }
    
//--------------------------------- method -------------------------------------
    
    public void updateStock(int quantity) {
        if (stockQuantity + quantity < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        stockQuantity += quantity;
        System.out.println("Stock updated successfully. New quantity: " + stockQuantity);
    }

    public void updatePrice(double newPrice){
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        price = newPrice;
        System.out.println("Price updated to: " + price);
    }
    
    public boolean checkExpiry() {
        if (expiryDate == null) {
             System.out.println("No expiry date set for this product.");
            return false;
        }   
        LocalDate today = LocalDate.now();
        if (expiryDate.isBefore(today)) {
            System.out.println("This product has expired!");
            return true;   
        } else {
            System.out.println(" Product is still valid.");
            return false; 
        }
    }
 
     public void display() {
        System.out.println("Product ID: " + productID);
        System.out.println("category: " + category);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Prescription Required: " + isPrescriptionRequired);
    }
   
}