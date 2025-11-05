
package internal.pharmacy.system.project;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Sale {
   private String saleID,employeeId;
   private LocalDate saleDate;
   private HashMap<Product, Integer> soldItems= new HashMap<>();;
   private double totalAmount;
   private String payment;
   
//------------------------------- constructor ----------------------------------
   
    public Sale() {
        this.saleID = "";
        this.employeeId = "";
        this.saleDate = LocalDate.now();
        this.soldItems = new HashMap<>();
        this.totalAmount = 0.0;
        this.payment="Cash";
    }

    public Sale(String saleID, String employeeId, LocalDate saleDate, HashMap<Product, Integer> soldItems, double totalAmount ,String payment ) {
        setSaleID(saleID);
        setEmployeeId(employeeId);
        setSaleDate(saleDate);
        setSoldItems(soldItems);
        setTotalAmount(totalAmount);
        setPayment(payment);
    }

//------------------------------ setter ----------------------------------------

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
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
//------------------------------ getters ---------------------------------------

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
    
    public String getPayment() {
        return payment;
    }
//------------------------------- methods --------------------------------------
    
 
    public void addItem(Product p, int quantity) {
        if (p == null || quantity <= 0) 
            throw new IllegalArgumentException("Invalid product or quantity");
        if (p.getStockQuantity() < quantity) 
            throw new IllegalArgumentException("Insufficient stock for " + p.getName());
        soldItems.merge(p, quantity, Integer::sum); 
        p.setStockQuantity(p.getStockQuantity() - quantity);
        calculateTotal();
    }
      
    public double applyDiscount(double totalBeforeDiscount) {
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

    public String printReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== INVOICE ==========\n");
        sb.append("Sale ID: ").append(saleID).append("\n");
        sb.append("Date: ").append(this.getSaleDate()).append("\n");
        sb.append("Employee ID: ").append(this.getEmployeeId()).append("\n");
        sb.append("Payment Method: ").append(this.getPayment()).append("\n");
        sb.append("-----------------------------\n");

        if (soldItems == null || soldItems.isEmpty()) {
            sb.append("No items sold.\n");
            sb.append("=============================\n");
            return sb.toString();
        }

        for (Map.Entry<Product, Integer> entry : soldItems.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            sb.append(p.getName())
            .append(" x ")
            .append(qty)
            .append(" = ")
            .append(p.getPrice() * qty)
            .append("\n");
        }

        sb.append("-----------------------------\n");
        sb.append("Total (before discount): ").append(getTotalBeforeDiscount()).append("\n");
        sb.append("Total (after discount): ").append(totalAmount).append("\n");
        sb.append("=============================\n");

        return sb.toString();
    }
}


