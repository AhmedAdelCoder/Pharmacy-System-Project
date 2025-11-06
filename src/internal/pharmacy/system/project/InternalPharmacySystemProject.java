
package internal.pharmacy.system.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InternalPharmacySystemProject {

    
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        // ===== 2. إنشاء منتجات =====
        Product panadol = new Product(1, 100, 300.0, "Panadol", LocalDate.now().plusMonths(12), false);
        Product augmentin = new Product(2, 50, 80.0, "Augmentin", LocalDate.now().plusMonths(6), true);
        OtherItem shampoo = new OtherItem(3, 20, 45.5, "Head&Shoulders", LocalDate.now().plusYears(1), false, "Hair Care");

        inventory.addDrugs(panadol);
        inventory.addDrugs(augmentin);
        inventory.addDrugs(shampoo);

        System.out.println("\n=== INVENTORY ===");
        inventory.displayAll();

       
        DistributingCompany company1 = new DistributingCompany("C001", "MedLife Pharma", "info@medlife.com", "Cairo");
        company1.addProduct(panadol);
        company1.addProduct(augmentin);

            
        company1.supplyStock(inventory, 1, 50);      

        Employee emp1 = new Employee("E001", "Ahmed", "ahmedUser", "1234", "Pharmacist");
        Employee emp2 = new Employee("E002", "Omar", "omarUser", "5678", "Cashier");
        Manager manager = new Manager("M001", "Sara", "saraAdmin", "admin123", "Manager");

        manager.addEmployee(emp1);
        manager.addEmployee(emp2);

        manager.addCompany(company1);

        System.out.println("\n=== LOGIN TEST ===");
        emp1.login("ahmedUser", "1234");

        HashMap<Product, Integer> soldItems = new HashMap<>();
        soldItems.put(panadol, 3);
        soldItems.put(shampoo, 1);

        Sale sale1 = new Sale("S001", emp1.getEmployeeID(), LocalDate.now(), soldItems, 0.0, "Cash");
 
        sale1.calculateTotal();     
        emp1.processSale(sale1);     

        System.out.println("\n=== RECEIPT ===");
        System.out.println(sale1.printReceipt());
        

        System.out.println("\n=== MANAGER REPORTS ===");
        manager.generateReport("employees");
        manager.generateReport("companies");
        manager.generateReport("sales");

        List<String> orderList = List.of("Panadol", "Augmentin");
        CustomerRecords customer1 = new CustomerRecords(101, "Ali", "01012345678", "Cairo", orderList, 2000.0, "2025-10-15");

        System.out.println("\n=== CUSTOMER ===");
        System.out.println("Customer Name: " + customer1.getName());
        System.out.println("Amount Due: " + customer1.getAmountDue());
        customer1.payDueAmount(100);
        

    }
    
}












//        Product p1 = new Product(1, 50, 20.0, "Panadol", LocalDate.of(2025, 12, 31), false);
//        Product p2 = new Product(2, 30, 25.0, "Vitamin C", LocalDate.of(2026, 5, 15), false);
//
//          
//        Sale s = new Sale("S001", "EMP05", LocalDate.now(), new HashMap<>(), 0.0, "Credit Card");
//
//        
//        s.addItem(p1, 2);
//        s.addItem(p2, 1);
//
//         
//        System.out.println(s.printReceipt());
//        
//         List<String> orders = new ArrayList<>();
//        orders.add("Panadol");
//        orders.add("Vitamin C");
//        orders.add("Cough Syrup");
//
//        
//        CustomerRecords customer = new CustomerRecords(
//                101,                        
//                "Ahmed Adel",               
//                "01012345678",              
//                "Cairo, Egypt",             
//                orders,                     
//                350.75,                     
//                "2025-10-20"                
//        );
//
//        
//        System.out.println("Customer Name: " + customer.getName());
//        System.out.println("Customer Phone: " + customer.getPhone());
//        System.out.println("Address: " + customer.getAddress());
//        System.out.println("Orders: " + customer.getOrder());
//        System.out.println("Amount Due: " + customer.getAmountDue());
//        System.out.println("Last Payment Date: " + customer.getLastPaymentDate());
//        System.out.println("------------------------------------------");
//
//        customer.payDueAmount(100);
//        customer.payDueAmount(300);     
//        customer.payDueAmount(-50);    
//
//        System.out.println("------------------------------------------");
//        System.out.println("Remaining Balance: " + customer.getRemainingBalance());
//        
//        
//        // ==================== [1] إنشاء موظف والمخزون ====================
//        Employee emp = new Employee("E101", "Ahmed", "ahmed123", "1234", "Pharmacist");
//        Inventory inventory = new Inventory();
//
//        // ==================== [2] إضافة منتجات للمخزون ====================
//        Product p11 = new Product(1, 50, 30.5, "Panadol", LocalDate.of(2026, 5, 10), false);
//        Product p12 = new Product(2, 20, 25.0, "Aspirin", LocalDate.of(2025, 12, 15), false);
//        Product p31 = new Product(3, 15, 40.0, "Vitamin C", LocalDate.of(2027, 3, 5), false);
//
//        inventory.setProduct(p11);
//        inventory.setProduct(p12);
//        inventory.setProduct(p31);
//
//        // ==================== [3] تجربة تسجيل الدخول ====================
//        System.out.println("\n--- LOGIN TEST ---");
//        if (!emp.login("ahmed123", "1234")) return;  // إنهاء لو فشل الدخول
//
//        // ==================== [4] عرض قائمة المخزون ====================
//        System.out.println("\n--- INVENTORY DISPLAY ---");
//        inventory.displayAll();
//
//        // ==================== [5] تنفيذ عملية بيع ====================
//        System.out.println("\n--- SALE PROCESS ---");
//        Sale sale1 = new Sale();
//        sale1.setSaleID("S001");
//        sale1.setEmployeeId(emp.getEmployeeID());
//
//        // إضافة عناصر للبيع
//        sale1.addItem(p1, 2); // بيع 2 من Panadol
//        sale1.addItem(p2, 1); // بيع 1 من Aspirin
//
//        // حساب الإجمالي
//        sale1.calculateTotal();
//
//        // تمرير عملية البيع للموظف
//        emp.processSale(sale1);
//
//        // ==================== [6] عرض الفاتورة ====================
//        System.out.println("\n--- PRINT RECEIPT ---");
//        System.out.println(sale1.printReceipt());
//
//        // ==================== [7] تحديث كمية منتج بعد البيع ====================
//        System.out.println("\n--- UPDATED INVENTORY AFTER SALE ---");
//        inventory.displayAll();
//
//        // ==================== [8] حساب البونص ====================
//        System.out.println("\n--- BONUS CALCULATION ---");
//        emp.calculateBonus(10); // 10% Bonus
//
//        // ==================== [9] عرض سجل المبيعات ====================
//        System.out.println("\n--- SALES HISTORY ---");
//        emp.printSalesHistory();
//
//        // ==================== [10] البحث عن منتج ====================
//        System.out.println("\n--- SEARCH PRODUCT ---");
//        inventory.SearchProduct("Panadol");
//        inventory.SearchProduct("UnknownDrug"); // مش موجود
//
//        // ==================== [11] فحص المنتجات منخفضة الكمية ====================
//        System.out.println("\n--- LOW STOCK CHECK ---");
//        inventory.getLowStock(25);

//            System.out.println("===== Pharmacy Management System =====\n");
//
//        // --------------------------------------------------------------------
//        // [1] إنشاء موظف وتجربة تسجيل الدخول
//        // --------------------------------------------------------------------
//        Employee emp = new Employee("E101", "Ahmed", "ahmed123", "1234", "Pharmacist");
//
//        System.out.println("--- LOGIN TEST ---");
//        if (!emp.login("ahmed123", "1234")) {
//            System.out.println("Login failed!");
//            return;
//        }
//
//        // --------------------------------------------------------------------
//        // [2] إنشاء المخزون وإضافة منتجات مختلفة
//        // --------------------------------------------------------------------
//        Inventory inventory = new Inventory();
//
//        // أدوية
//        Drug d1 = new Drug(1, 50, 30.5, "Panadol", LocalDate.of(2026, 5, 10), false, "500mg");
//        Drug d2 = new Drug(2, 20, 25.0, "Aspirin", LocalDate.of(2025, 12, 15), false, "300mg");
//
//        // منتجات أخرى
//        OtherItem i1 = new OtherItem(3, 10, 15.0, "Face Mask", LocalDate.of(2027, 3, 5), false, "Health");
//        OtherItem i2 = new OtherItem(4, 5, 80.0, "Blood Pressure Monitor", LocalDate.of(2030, 1, 1), false, "Medical Device");
//
//        inventory.setProduct(d1);
//        inventory.setProduct(d2);
//        inventory.setProduct(i1);
//        inventory.setProduct(i2);
//
//        System.out.println("\n--- INVENTORY DISPLAY ---");
//        inventory.displayAll();
//
//        // --------------------------------------------------------------------
//        // [3] إنشاء عميل وسجل مالي له
//        // --------------------------------------------------------------------
//        List<String> orders = new ArrayList<>();
//        orders.add("Panadol");
//        orders.add("Face Mask");
//
//        CustomerRecords cust = new CustomerRecords(101, "Omar", "01000000000", "Cairo, Egypt", orders, 150.0, "2025-10-25");
//
//        System.out.println("\n--- CUSTOMER DETAILS ---");
//        System.out.println("Name: " + cust.getName());
//        System.out.println("Phone: " + cust.getPhone());
//        System.out.println("Due Amount: " + cust.getAmountDue());
//        System.out.println("Last Payment: " + cust.getLastPaymentDate());
//
//        // العميل يدفع جزء من المستحق
//        cust.payDueAmount(50);
//
//        // --------------------------------------------------------------------
//        // [4] تنفيذ عملية بيع وربطها بالموظف
//        // --------------------------------------------------------------------
//        Sale sale = new Sale();
//        sale.setSaleID("S001");
//        sale.setEmployeeId(emp.getEmployeeID());
//        sale.addItem(d1, 2); // بيع 2 Panadol
//        sale.addItem(i1, 1); // بيع 1 Face Mask
//        sale.calculateTotal();
//
//        emp.processSale(sale);
//
//        // --------------------------------------------------------------------
//        // [5] طباعة الفاتورة
//        // --------------------------------------------------------------------
//        System.out.println("\n--- PRINT RECEIPT ---");
//        System.out.println(sale.printReceipt());
//
//        // --------------------------------------------------------------------
//        // [6] تحديث المخزون بعد البيع
//        // --------------------------------------------------------------------
//        System.out.println("\n--- UPDATED INVENTORY ---");
//        inventory.updateStock(d1, d1.getStockQuantity() - 2);
//        inventory.updateStock(i1, i1.getStockQuantity() - 1);
//        inventory.displayAll();
//
//        // --------------------------------------------------------------------
//        // [7] البحث عن منتج معين
//        // --------------------------------------------------------------------
//        System.out.println("\n--- SEARCH PRODUCT ---");
//        inventory.SearchProduct("Panadol");
//        inventory.SearchProduct("UnknownDrug");
//
//        // --------------------------------------------------------------------
//        // [8] المنتجات قليلة المخزون
//        // --------------------------------------------------------------------
//        System.out.println("\n--- LOW STOCK CHECK ---");
//        inventory.getLowStock(10);
//
//        // --------------------------------------------------------------------
//        // [9] حساب البونص للموظف
//        // --------------------------------------------------------------------
//        System.out.println("\n--- BONUS CALCULATION ---");
//        emp.calculateBonus(5);
//
//        // --------------------------------------------------------------------
//        // [10] سجل المبيعات للموظف
//        // --------------------------------------------------------------------
//        System.out.println("\n--- EMPLOYEE SALES HISTORY ---");
//        emp.printSalesHistory();
//
//        System.out.println("\n===== END OF DEMO =====");

        // ===== 1. إنشاء المخزون (Inventory) =====
