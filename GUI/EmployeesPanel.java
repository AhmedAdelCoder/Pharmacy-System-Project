package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeesPanel extends JPanel {
    private final Manager manager;
    private final JTable table;
    private final DefaultTableModel tableModel;

    public EmployeesPanel(Manager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 250));

        // Table setup
        String[] columns = {"Employee ID", "Name", "Role"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // منع التعديل مباشرة
            }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Consolas", Font.PLAIN, 14));
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel ctrl = new JPanel();
        ctrl.setBackground(new Color(245, 245, 255));
        JButton add = new JButton("Add Employee");
        JButton remove = new JButton("Remove Employee");
        JButton refresh = new JButton("Refresh");
        ctrl.add(add);
        ctrl.add(remove);
        ctrl.add(refresh);
        add(ctrl, BorderLayout.NORTH);

        add.addActionListener(e -> addEmployee());
        remove.addActionListener(e -> removeEmployee());
        refresh.addActionListener(e -> refresh());

        refresh();
    }

    private void refresh() {
        tableModel.setRowCount(0); // تفريغ الجدول
        for (Employee e : manager.getEmployeesManaged()) {
            tableModel.addRow(new Object[]{e.getEmployeeID(), e.getName(), e.getRole()});
        }
    }

    private void addEmployee() {
        String id = JOptionPane.showInputDialog(this, "Employee ID:");
        if (id == null || id.isEmpty()) return;
        String name = JOptionPane.showInputDialog(this, "Name:");
        String username = JOptionPane.showInputDialog(this, "Username:");
        String pwd = JOptionPane.showInputDialog(this, "Password:");
        String role = JOptionPane.showInputDialog(this, "Role:");
        Employee e = new Employee(id, name, username, pwd, role);
        manager.addEmployee(e);
        refresh();
    }

    private void removeEmployee() {
        String id = JOptionPane.showInputDialog(this, "Employee ID to remove:");
        if (id == null || id.isEmpty()) return;
        manager.removeEmployee(id);
        refresh();
    }
}
