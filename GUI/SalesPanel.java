package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class SalesPanel extends JPanel {
    private final Inventory inventory;
    private final Object user; // Employee أو Manager
    private final DashboardPanel dashboardPanel; // ← المرجع الجديد
    private final DefaultTableModel invoiceModel;
    private final JTable invoiceTable;
    private final JTextField prodIdField;
    private final JTextField qtyField;
    private final JLabel totalLabel;

    // Constructor للـ Manager
    public SalesPanel(Inventory inventory, Manager manager, DashboardPanel dashboardPanel) {
        this.inventory = inventory;
        this.user = manager;
        this.dashboardPanel = dashboardPanel; // حفظ المرجع
        invoiceModel = new DefaultTableModel(new String[]{"Product", "Unit Price", "Qty", "Line Total"}, 0);
        invoiceTable = new JTable(invoiceModel);
        prodIdField = new JTextField(6);
        qtyField = new JTextField(4);
        totalLabel = new JLabel("Total: 0.0");
        buildUI();
    }

    // Constructor للـ Employee
    public SalesPanel(Inventory inventory, Employee employee, DashboardPanel dashboardPanel) {
        this.inventory = inventory;
        this.user = employee;
        this.dashboardPanel = dashboardPanel; // حفظ المرجع
        invoiceModel = new DefaultTableModel(new String[]{"Product", "Unit Price", "Qty", "Line Total"}, 0);
        invoiceTable = new JTable(invoiceModel);
        prodIdField = new JTextField(6);
        qtyField = new JTextField(4);
        totalLabel = new JLabel("Total: 0.0");
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Top panel لإدخال المنتج والكمية
        JPanel top = new JPanel();
        top.setBackground(new Color(240, 240, 255));
        top.add(new JLabel("Product ID:"));
        top.add(prodIdField);
        top.add(new JLabel("Qty:"));
        top.add(qtyField);
        JButton addBtn = new JButton("Add to Invoice");
        addBtn.setBackground(new Color(60, 120, 180));
        addBtn.setForeground(Color.WHITE);
        top.add(addBtn);
        add(top, BorderLayout.NORTH);

        addBtn.addActionListener(e -> addSelectedToInvoice());

        // جدول الفاتورة
        invoiceTable.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(new JScrollPane(invoiceTable), BorderLayout.CENTER);

        // Bottom panel مع الأزرار والمجموع
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(new Color(240, 240, 255));
        JPanel actions = new JPanel();
        actions.setBackground(new Color(240, 240, 255));
        JButton finalize = new JButton("Finalize & Save Invoice");
        JButton clear = new JButton("Clear");
        finalize.setBackground(new Color(50, 150, 50));
        finalize.setForeground(Color.WHITE);
        clear.setBackground(new Color(180, 60, 60));
        clear.setForeground(Color.WHITE);
        actions.add(finalize);
        actions.add(clear);
        bottom.add(actions, BorderLayout.WEST);

        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottom.add(totalLabel, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        finalize.addActionListener(e -> finalizeSale());
        clear.addActionListener(e -> { invoiceModel.setRowCount(0); updateTotal(); });
    }

    private void addSelectedToInvoice() {
        if (prodIdField.getText().isBlank()) return;

        int prodId;
        try { prodId = Integer.parseInt(prodIdField.getText()); }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Enter a valid numeric ID"); return; }

        Product p = inventory.searchById(prodId);
        if (p == null) { JOptionPane.showMessageDialog(this, "Product not found"); return; }

        int qty;
        try { qty = Integer.parseInt(qtyField.getText()); } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid qty"); return; }
        if (qty <= 0) { JOptionPane.showMessageDialog(this, "Qty must be > 0"); return; }
        if (p.getStockQuantity() < qty) { JOptionPane.showMessageDialog(this, "Insufficient stock"); return; }

        double line = p.getPrice() * qty;
        invoiceModel.addRow(new Object[]{p.getName(), p.getPrice(), qty, line});
        updateTotal();
    }

    private void updateTotal() {
        double sum = 0.0;
        for (int i = 0; i < invoiceModel.getRowCount(); i++)
            sum += ((Number) invoiceModel.getValueAt(i, 3)).doubleValue();
        totalLabel.setText("Total: " + sum);
    }

    private void finalizeSale() {
        if (invoiceModel.getRowCount() == 0) { JOptionPane.showMessageDialog(this, "Invoice empty"); return; }

        HashMap<Product, Integer> soldItems = new HashMap<>();
        for (int i = 0; i < invoiceModel.getRowCount(); i++) {
            String prodName = invoiceModel.getValueAt(i, 0).toString();
            int qty = ((Number) invoiceModel.getValueAt(i, 2)).intValue();
            Product p = null;
            for (Product prod : inventory.getProductList()) if (prod.getName().equals(prodName)) { p = prod; break; }
            if (p != null) soldItems.put(p, qty);
        }

        String saleId = "S" + System.currentTimeMillis();
        String employeeId = (user instanceof Employee) ? ((Employee) user).getEmployeeID() :
                (user instanceof Manager && ((Manager) user).getEmployeesManaged().size() > 0 ?
                        ((Manager) user).getEmployeesManaged().get(0).getEmployeeID() : "E000");

        Sale sale = new Sale(saleId, employeeId, LocalDate.now(), soldItems, 0.0, "Cash");
        sale.calculateTotal();
        soldItems.forEach((p, q) -> p.setStockQuantity(p.getStockQuantity() - q));

        if (user instanceof Employee emp) emp.processSale(sale);
        else if (user instanceof Manager mgr) {
            String chosen = JOptionPane.showInputDialog(this, "Enter employee ID who processed this sale:");
            if (chosen != null && !chosen.isBlank()) {
                Employee e = mgr.getEmployeesManaged().stream().filter(x -> x.getEmployeeID().equals(chosen)).findFirst().orElse(null);
                if (e != null) e.processSale(sale);
            }
        }

        JTextArea ta = new JTextArea(sale.printReceipt());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Receipt", JOptionPane.INFORMATION_MESSAGE);

        invoiceModel.setRowCount(0);
        updateTotal();

        // ← تحديث Dashboard بعد البيع
        if (dashboardPanel != null) dashboardPanel.refreshDashboard();
    }
}
