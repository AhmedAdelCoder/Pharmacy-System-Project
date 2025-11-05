
package internal.pharmacy.system.project;

import java.util.List;

public class CustomerRecords extends Customer{
    private double amountDue;
    private String lastPaymentDate;

//------------------------------- Constructors ---------------------------------
    public CustomerRecords() {
        super();
        this.amountDue = 0.0;
        this.lastPaymentDate = "";
    }

    public CustomerRecords(int customerID, String name, String phone, String address,List<String> order, double amountDue, String lastPaymentDate) {
        super(customerID, name, phone, address, order);
        setAmountDue(amountDue);
        setLastPaymentDate(lastPaymentDate); 
    }

//-------------------------------Setter ----------------------------------------
    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

//--------------------------- Getters------------------------ ------------------
    
    public double getAmountDue() {
        return amountDue;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

//------------------------------- Methods --------------------------------------
    
    public void payDueAmount(double amount) {
        if (amount > 0 && amount <= amountDue) {
            amountDue -= amount;
            System.out.println("Payment of " + amount + " accepted. Remaining balance: " + amountDue);
        } else if (amount > amountDue) {
            System.out.println("Payment exceeds due amount. Please pay only: " + amountDue);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    public double getRemainingBalance() {
        return amountDue;
    }
  }

