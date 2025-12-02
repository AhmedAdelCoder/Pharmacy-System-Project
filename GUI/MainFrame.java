package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private Inventory inventory;
    private Manager manager;
    private Employee employee;

    // ---------------- Constructor ----------------
    public MainFrame(Inventory inventory, Manager manager, Employee employee) {
        this.inventory = inventory;
        this.manager = manager;
        this.employee = employee;

        setTitle("Pharmacy Dashboard - " + (employee != null ? employee.getName() : manager.getName()));
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setBackground(new Color(220, 220, 250));

        initTabs();
        add(tabbedPane);

        setVisible(true);
    }

    // ---------------- Init Tabs ----------------
private void initTabs() {
    DashboardPanel dashboardPanel = new DashboardPanel(inventory, manager, employee);
    tabbedPane.addTab("Dashboard", dashboardPanel);

    tabbedPane.addTab("Products", new InventoryPanel(inventory));

    // SalesPanel يحتاج تمرير المستخدم + DashboardPanel
    if (employee != null) {
        tabbedPane.addTab("Sales", new SalesPanel(inventory, employee, dashboardPanel));
    } else if (manager != null) {
        tabbedPane.addTab("Sales", new SalesPanel(inventory, manager, dashboardPanel));
    }

    // Employees فقط للمدير
    if (manager != null) {
        tabbedPane.addTab("Employees", new EmployeesPanel(manager));
    }
}

}
