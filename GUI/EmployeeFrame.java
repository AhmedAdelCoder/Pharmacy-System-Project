package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;

public class EmployeeFrame extends JFrame {
    private Employee employee;

    public EmployeeFrame(Employee employee) {
        this.employee = employee;

        setTitle("Employee Dashboard - " + employee.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 250));

        // Title
        JLabel title = new JLabel("Welcome, " + employee.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 120));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(245, 250, 255));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 220), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel salesLabel = new JLabel("Your Total Sales: " + employee.getTotalSales());
        salesLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
        salesLabel.setForeground(new Color(60, 60, 100));
        infoPanel.add(salesLabel);

        JLabel idLabel = new JLabel("Employee ID: " + employee.getEmployeeID());
        idLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
        idLabel.setForeground(new Color(60, 60, 100));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(idLabel);

        add(infoPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
