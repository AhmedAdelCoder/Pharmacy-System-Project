package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private JLabel productsLabel;
    private JLabel employeesLabel;
    private JLabel salesLabel;

    private Inventory inventory;
    private Manager manager;
    private Employee employee;

    public DashboardPanel(Inventory inventory, Manager manager, Employee employee) {
        this.inventory = inventory;
        this.manager = manager;
        this.employee = employee;

        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 250));

        JLabel title = new JLabel("Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 120));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(245, 250, 255));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 220), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        productsLabel = new JLabel();
        productsLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
        productsLabel.setForeground(new Color(60, 60, 100));
        statsPanel.add(productsLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        if (manager != null) {
            employeesLabel = new JLabel();
            employeesLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            employeesLabel.setForeground(new Color(60, 60, 100));
            statsPanel.add(employeesLabel);
            statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            salesLabel = new JLabel();
            salesLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            salesLabel.setForeground(new Color(60, 60, 100));
            statsPanel.add(salesLabel);
        } else if (employee != null) {
            salesLabel = new JLabel();
            salesLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            salesLabel.setForeground(new Color(60, 60, 100));
            statsPanel.add(salesLabel);
        }

        add(statsPanel, BorderLayout.CENTER);

        // حدث القيم لأول مرة
        refreshDashboard();
    }

    // دالة لتحديث القيم في أي وقت
    public void refreshDashboard() {
        if (productsLabel != null) productsLabel.setText("Total Products: " + inventory.getProductList().size());
        if (manager != null) {
            if (employeesLabel != null) employeesLabel.setText("Total Employees: " + manager.getEmployeesManaged().size());
            if (salesLabel != null) salesLabel.setText("Total Sales Amount: " + manager.getTotalSalesAmount());
        } else if (employee != null) {
            if (salesLabel != null) salesLabel.setText("Your Total Sales: " + employee.getTotalSales());
        }
    }
}
