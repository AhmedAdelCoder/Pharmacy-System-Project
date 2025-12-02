package internal.pharmacy.system.project.GUI;

import internal.pharmacy.system.project.Employee;
import internal.pharmacy.system.project.Manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private LoginSuccessListener loginSuccessListener;

    public interface LoginSuccessListener {
        void onLoginSuccess(Object loggedUser); // Employee or Manager
    }

    public void setLoginSuccessListener(LoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }

    public LoginFrame() {
        setTitle("Life Pharmacy - Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(false);

        // ---------------- Gradient Background ----------------
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(58, 123, 213),
                        0, getHeight(), new Color(58, 96, 115));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ---------------- Header ----------------
        JLabel pharmacyName = new JLabel("Life Pharmacy", SwingConstants.CENTER);
        pharmacyName.setFont(new Font("Tahoma", Font.BOLD, 30));
        pharmacyName.setForeground(Color.WHITE);
        pharmacyName.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(pharmacyName, BorderLayout.NORTH);

        // ---------------- Center Panel ----------------
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(userLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ---------------- Login Button ----------------
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        loginBtn.setBackground(new Color(255, 140, 0));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setPreferredSize(new Dimension(200, 45));

        // Hover effect
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(new Color(255, 165, 0));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(new Color(255, 140, 0));
            }
        });

        loginBtn.addActionListener(e -> login());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(loginBtn);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void login() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        Object obj = UserManager.login(user, pass);

        if (obj == null) {
            JOptionPane.showMessageDialog(this, "Wrong username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String welcomeName;
        if (obj instanceof Employee emp) {
            welcomeName = emp.getName();
            System.out.println("Employee logged in: " + welcomeName);
        } else if (obj instanceof Manager mgr) {
            welcomeName = mgr.getName();
            System.out.println("Manager logged in: " + welcomeName);
        } else {
            JOptionPane.showMessageDialog(this, "Unknown user type!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Welcome " + welcomeName);

        if (loginSuccessListener != null) {
            loginSuccessListener.onLoginSuccess(obj);
        }

        dispose();
    }
}
