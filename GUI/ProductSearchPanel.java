package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchPanel extends JPanel {
    private final Inventory inventory;
    private final JTextField searchField;
    private final JTextArea resultsArea;

    public ProductSearchPanel(Inventory inventory) {
        this.inventory = inventory;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Title
        JLabel title = new JLabel("Product Search", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        title.setForeground(new Color(50, 50, 150));
        add(title, BorderLayout.NORTH);

        // Top panel for search
        JPanel top = new JPanel();
        top.setBackground(new Color(240, 240, 255));
        searchField = new JTextField(20);
        searchField.setFont(new Font("Consolas", Font.PLAIN, 14));
        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));
        searchBtn.setBackground(new Color(60, 120, 180));
        searchBtn.setForeground(Color.WHITE);

        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchBtn);
        add(top, BorderLayout.NORTH);

        // Results area
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultsArea.setBackground(new Color(245, 245, 250));
        resultsArea.setForeground(new Color(50, 50, 50));
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        // Action listener
        searchBtn.addActionListener(e -> doSearch());
    }

    private void doSearch() {
        String q = searchField.getText().trim();
        resultsArea.setText("");

        if (q.isEmpty()) {
            resultsArea.setText("Type product name or ID.");
            return;
        }

        List<Product> matches = new ArrayList<>();
        try {
            int id = Integer.parseInt(q);
            Product p = inventory.searchById(id);
            if (p != null) matches.add(p);
        } catch (NumberFormatException e) {
            for (Product p : inventory.getProductList()) {
                if (p.getName().equalsIgnoreCase(q)) matches.add(p);
            }
        }

        if (matches.isEmpty()) {
            resultsArea.setText("No products found for \"" + q + "\".");
        } else {
            for (Product p : matches) {
                resultsArea.append(
                        p.getProductID() + " | " +
                        p.getName() + " | Stock: " +
                        p.getStockQuantity() + " | Price: " +
                        p.getPrice() + "\n"
                );
            }
        }
    }
}
