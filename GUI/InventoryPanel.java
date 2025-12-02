package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.Inventory;
import internal.pharmacy.system.project.PharmacyDBHandler;
import internal.pharmacy.system.project.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {
    private Inventory inventory;
    private JTable table;
    private DefaultTableModel tableModel;
    
public void loadProductsFromDB() {
    try {
        inventory.clearProducts(); // لازم تعمل دالة clearProducts() في Inventory
        ArrayList<Product> productsFromDB = PharmacyDBHandler.getProductList();
        for (Product p : productsFromDB) {
            inventory.addProduct(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
    }
}

    public InventoryPanel(Inventory inventory) {
        this.inventory = inventory;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        JLabel title = new JLabel("Inventory", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        title.setForeground(new Color(50, 50, 150));
        add(title, BorderLayout.NORTH);

        // إعداد الجدول
        String[] columns = {"ID", "Name", "Category", "Price", "Stock", "Expiry", "Prescription"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        
        table = new JTable(tableModel);
        table.setFont(new Font("Consolas", Font.PLAIN, 14));
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // أزرار التحكم
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(240, 240, 255));
        JButton addBtn = new JButton("Add Product");
        JButton refreshBtn = new JButton("Refresh");
        btnPanel.add(addBtn);
        btnPanel.add(refreshBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // في قسم أزرار التحكم
        JButton deleteBtn = new JButton("Delete Product");
        btnPanel.add(deleteBtn);

// حدث الزر
        deleteBtn.addActionListener(e -> deleteProduct());

        addBtn.addActionListener(e -> addProduct());
        refreshBtn.addActionListener(e -> refresh());
        loadProductsFromDB();
        refresh();
    }

    private void refresh() {
        tableModel.setRowCount(0);
        for (Product p : inventory.getProductList()) {
            tableModel.addRow(new Object[]{
                p.getProductID(),
                p.getName(),
                p.getCategory(),
                p.getPrice(),
                p.getStockQuantity(),
                p.getExpiryDate(),
                p.isIsPrescriptionRequired()
            });
        }
    }

        private void addProduct() {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Product ID:"));
                String name = JOptionPane.showInputDialog(this, "Name:");
                String category = JOptionPane.showInputDialog(this, "Category:");
                double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Price:"));
                int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Stock Quantity:"));
                String expiryStr = JOptionPane.showInputDialog(this, "Expiry Date (YYYY-MM-DD):");
                LocalDate expiry = LocalDate.parse(expiryStr);
                boolean prescription = JOptionPane.showConfirmDialog(this, "Prescription Required?") == JOptionPane.YES_OPTION;

                Product p = new Product(id, stock, price, name, expiry, prescription, category);

                // إضافة للـ inventory
                inventory.addProduct(p);

                // إضافة للقاعدة
                PharmacyDBHandler.insertProduct(p);

                // تحديث الجدول
                refresh();
                JOptionPane.showMessageDialog(this, "Product added successfully!");

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID, stock, and price.");
            } catch (DateTimeParseException dtpe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid date in format YYYY-MM-DD.");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
        
    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // الحصول على الـ ID من الجدول
        int productID = (int) table.getValueAt(selectedRow, 0);

        // حذف من الـ inventory
        inventory.removeProduct(productID);

        // حذف من قاعدة البيانات
        PharmacyDBHandler.deleteProduct(productID); // تحتاج تعمل دالة deleteProduct في DBHandler

        // تحديث الجدول
        refresh();
        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
    }

    }
