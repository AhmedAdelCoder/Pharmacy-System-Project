
package internal.pharmacy.system.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InternalPharmacySystemProject {

    
    public static void main(String[] args) {
 Product p1 = new Product(1, 50, 20.0, "Panadol", LocalDate.of(2025, 12, 31), false);
        Product p2 = new Product(2, 30, 25.0, "Vitamin C", LocalDate.of(2026, 5, 15), false);

          
        Sale s = new Sale("S001", "EMP05", LocalDate.now(), new HashMap<>(), 0.0, "Credit Card");

        
        s.addItem(p1, 2);
        s.addItem(p2, 1);

         
        System.out.println(s.printReceipt());
        
         List<String> orders = new ArrayList<>();
        orders.add("Panadol");
        orders.add("Vitamin C");
        orders.add("Cough Syrup");

        
        CustomerRecords customer = new CustomerRecords(
                101,                        
                "Ahmed Adel",               
                "01012345678",              
                "Cairo, Egypt",             
                orders,                     
                350.75,                     
                "2025-10-20"                
        );

        
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Phone: " + customer.getPhone());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Orders: " + customer.getOrder());
        System.out.println("Amount Due: " + customer.getAmountDue());
        System.out.println("Last Payment Date: " + customer.getLastPaymentDate());
        System.out.println("------------------------------------------");

        customer.payDueAmount(100);
        customer.payDueAmount(300);     
        customer.payDueAmount(-50);    

        System.out.println("------------------------------------------");
        System.out.println("Remaining Balance: " + customer.getRemainingBalance());
    }
    
}
