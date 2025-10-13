
package internal.pharmacy.system.project;
import java.time.LocalDate;

public class Product {
    
    // Data member   
    private int productID;
    private int stockQuantity;
    private double price;
    private String name;
    private LocalDate expiryDate;
    private boolean isPrescriptionRequired;
    
// Constructor
    public Product(){
        this.name="";
        this.expiryDate = null;
        this.price=0.0;
        this.productID=0;
        this.stockQuantity=0;
        this.isPrescriptionRequired=false;
    }
    public Product(int productID, int stockQuantity, double price, String name, LocalDate expiryDate, boolean isPrescriptionRequired) {
        setProductID(productID);
        setStockQuatity(stockQuantity);
        setPrice(price);
        setName(name);
        setExpiryDate(expiryDate);
        setIsPrescriptionRequired(isPrescriptionRequired);
    }   
    
//setters
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setIsPrescriptionRequired(boolean isPrescriptionRequired) {
        this.isPrescriptionRequired = isPrescriptionRequired;
    }
    
// Getters
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
    
//updateStock
    public void updateStock(int quatity){
       stockQuantity += quatity;
       System.out.println("Stock updated successfully. New quantity: " +stockQuantity);
    }
    
// updatePrice
    public void updatePrice(double newPrice){
        price = newPrice;
        System.out.println("Price updated to: " + price);
    }
    
// checkExpiry 
    public boolean checkExpiry() {
        if (expiryDate == null) {
             System.out.println("No expiry date set for this product.");
            return false;
       }   
       LocalDate today = LocalDate.now();
        if (expiryDate.isBefore(today)) {
            System.out.println(" This product has expired!");
            return true;   
        } else {
            System.out.println(" Product is still valid.");
            return false; 
        }
    }
 
//Display
     public void display() {
        System.out.println("Product ID: " + productID);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Prescription Required: " + isPrescriptionRequired);
    }
   
}
