

package internal.pharmacy.system.project;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory {
    private ArrayList <Product> products ;
    private String lastUpdateDate;
    
//------------------------------ Constructor  ----------------------------------
    public Inventory(){
        products = new ArrayList<>();
        lastUpdateDate = LocalDate.now().toString();
    }
    
//--------------------------------- setters ------------------------------------
    public void setProduct(Product p ) {
        products.add(p);
        lastUpdateDate = LocalDate.now().toString();
    }
    
//--------------------------------- Getters ------------------------------------
    public ArrayList<Product> getProductList() {
        return products;
    }
    
///------------------------------- method ---------------------------------------
    public Product SearchProduct(String name){
        for(Product p : products ){
            if(p.getName().equalsIgnoreCase(name)){
                System.out.println("Product found:");
                p.display();
                return p;
            }
        }
        System.out.println("* Product Not found: *");
        return null;
    }
    
    public void updateStock(Product prod , int quantity){
        for(Product p : products ){
            if(p.getProductID() == prod.getProductID() ){
                p.updateStock(quantity);
                lastUpdateDate = LocalDate.now().toString();
                return;
            }
        }
            System.out.println("Product not found in inventory.");
    }
    
    public void getLowStock(int threshold) {
        System.out.println("Low stock products (below " + threshold + "):");
        for (Product p : products ) {
            if (p.getStockQuantity() < threshold) {
                p.display();
                System.out.println("-------------------");
            }
        }
    }

    public Product searchById(int productID) {
        for (Product p : products) {
            if (p.getProductID() == productID) {
                return p;
            }
        }
        return null;
    }
    
    public void addProduct(Product p) {
        if (p == null) {
            System.out.println("Cannot add null product to inventory.");
            return;
        }
        products.add(p);
        lastUpdateDate = LocalDate.now().toString();
    }

    
    public void removeProduct(int productID) {
        products.removeIf(p -> p.getProductID() == productID);
        lastUpdateDate = LocalDate.now().toString();
}
 
    public void displayAll() {
        System.out.println("=== INVENTORY LIST ===");
        for (Product p : products ) {
            p.display();
            System.out.println("-------------------");
        }
        System.out.println("Last updated: " + lastUpdateDate);
    }          
}

