
package internal.pharmacy.system.project;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Sale {
   private String saleID,employeeId;
   private LocalDate saleDate;
   private HashMap<Product, Integer> soldItems;
   private double totalAmount;
   
//--------------- constructor -------------

    public Sale(String saleID, String employeeId, LocalDate saleDate, HashMap<Product, Integer> soldItems, double totalAmount) {
        setSaleID(saleID);
        setEmployeeId(employeeId);
        setSaleDate(saleDate);
        setSoldItems(soldItems);
        setTotalAmount(totalAmount);
    }

//----------------- setter ----------------

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setSoldItems(HashMap<Product, Integer> soldItems) {
        this.soldItems = soldItems;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
//----------------- getters ----------------

    public String getSaleID() {
        return saleID;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public HashMap<Product, Integer> getSoldItems() {
        return soldItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
//----------------- methods ----------------
    
 
    public void addItem(Product p, int quantity) {
        if (p == null || quantity <= 0) 
            throw new IllegalArgumentException("Invalid product or quantity");
        if (p.getStockQuantity() < quantity) 
            throw new IllegalArgumentException("Insufficient stock for " + p.getName());
        soldItems.merge(p, quantity, Integer::sum); 
        p.setStockQuantity(p.getStockQuantity() - quantity);
        calculateTotal();
    }
      
    public double applyDiscount(int totalBeforeDiscount) {
        double rate = 0.0;
        if (totalBeforeDiscount >= 1000) rate = 0.10;
        else if (totalBeforeDiscount >= 500) rate = 0.05;
        return  totalBeforeDiscount -(totalBeforeDiscount * rate);
    }

    
    public void calculateTotal() {
    double totalBeforeDiscount = 0.0;
    for (Map.Entry<Product, Integer> e : soldItems.entrySet()) {
        totalBeforeDiscount += e.getKey().getPrice() * e.getValue();
    }
    totalAmount = applyDiscount(totalBeforeDiscount);
}
    
    public double getTotalBeforeDiscount() {
    double total = 0.0;
    for (Map.Entry<Product, Integer> e : soldItems.entrySet()) {
        total += e.getKey().getPrice() * e.getValue();
    }
    return total;
}


   
           public void printReceipt() {
        System.out.println("========== INVOICE ==========");
        System.out.println("Sale ID: " + saleID);
        System.out.println("Date: " + this.getSaleDate());
        System.out.println("Employee ID: " + this.getEmployeeId());
        System.out.println("-----------------------------");
        for (Map.Entry<Product, Integer> entry : soldItems.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            System.out.println(p.getName() + " x " + qty + " = " + (p.getPrice() * qty));
        }
        System.out.println("-----------------------------");
        System.out.println("Total (before discount): " + getTotalBeforeDiscount());
        System.out.println("Total (after discount): " + totalAmount);
        System.out.println("=============================");
    }
           
    
}




//    public void calculateTotal() {
//        totalAmount = 0.0;
//        for (Map.Entry<Product, Integer> entry : itemsSold.entrySet()) {
//            Product p = entry.getKey();
//            int quantity = entry.getValue();
//            totalAmount += p.getPrice() * quantity;
//        }
//        totalAmount = Discount(totalAmount); // Apply discount automatically
//    }


//   public String printReceipt() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("===== Invoice =====\n");
//        sb.append("Sale ID: ").append(saleID).append("\n");
//        sb.append("Date: ").append(date).append("\n");
//        sb.append("Employee: ").append(employeeID).append("\n");
//        sb.append("-------------------\n");
//        for (Map.Entry<Product, Integer> e : itemsSold.entrySet()) {
//            sb.append(e.getKey().getName())
//              .append(" x").append(e.getValue())
//              .append(" = ").append(e.getKey().getPrice() * e.getValue())
//              .append("\n");
//        }
//        sb.append("-------------------\n");
//        sb.append("Total: ").append(totalAmount).append("\n");
//        sb.append("===================\n");
//        return sb.toString();
//    }
