package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends JFrame {

    public ManagerFrame(Manager manager) {
        setTitle("Manager Dashboard - " + manager.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Manager: " + manager.getName(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        EmployeesPanel panel = new EmployeesPanel(manager);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
