package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;

public class CompanyPanel extends JPanel {
    private Manager manager;
    private JTextArea displayArea;

    public CompanyPanel(Manager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 250));

        // Title
        JLabel title = new JLabel("Managed Companies", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 120));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(250, 250, 255));
        displayArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 220)));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Buttons panel
        JPanel controls = new JPanel();
        controls.setBackground(new Color(240, 245, 250));
        controls.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton refreshBtn = new JButton("Refresh Companies");
        refreshBtn.setBackground(new Color(60, 120, 180));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 14));
        refreshBtn.addActionListener(e -> displayCompanies());
        controls.add(refreshBtn);

        JButton addBtn = new JButton("Add Company");
        addBtn.setBackground(new Color(40, 180, 80));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addBtn.addActionListener(e -> addCompany());
        controls.add(addBtn);

        JButton removeBtn = new JButton("Remove Company");
        removeBtn.setBackground(new Color(200, 50, 50));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        removeBtn.addActionListener(e -> removeCompany());
        controls.add(removeBtn);

        add(controls, BorderLayout.SOUTH);

        displayCompanies();
    }

    private void displayCompanies() {
        displayArea.setText("");
        for (DistributingCompany c : manager.getCompaniesManaged()) {
            displayArea.append(c.toString() + "\n");
        }
    }

    private void addCompany() {
        String id = JOptionPane.showInputDialog(this, "Company ID:");
        if (id == null || id.isBlank()) return;

        String name = JOptionPane.showInputDialog(this, "Company Name:");
        if (name == null || name.isBlank()) return;

        String email = JOptionPane.showInputDialog(this, "Email Address:");
        if (email == null || email.isBlank()) return;

        String loc = JOptionPane.showInputDialog(this, "Location:");
        if (loc == null || loc.isBlank()) return;

        DistributingCompany c = new DistributingCompany(id, name, email, loc);
        manager.addCompany(c);
        displayCompanies();
    }

    private void removeCompany() {
        String id = JOptionPane.showInputDialog(this, "Enter Company ID to remove:");
        if (id == null || id.isBlank()) return;

        manager.removeCompany(id);
        displayCompanies();
    }
}
