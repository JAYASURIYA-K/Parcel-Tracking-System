import javax.swing.*;
import java.awt.*;

public class ParcelTrackingSystem extends JFrame {

    // Arrays to store user credentials and parcel details
    private final String[] usernames = {"user1", "user2"};
    private final String[] passwords = {"password1", "password2"};
    private final String[] parcelIds = {"12345", "67890"};
    private final String[] deliveryDetails = {
            "BANGALORE TO TRICHY.",
            "COIMBATORE TO TRICHY."
    };
    private final String[] dischargeDetails = {
            "DISCHARGED AT TRYC HD OFFICE.",
            "YET TO BE DISCHARGED."
    };

    private int currentUserIndex; // Track the current logged-in user

    public ParcelTrackingSystem() {
        setTitle("Parcel Tracking System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showLoginScreen();
    }

    // Login Screen
    private void showLoginScreen() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel loginHeading = new JLabel("Login Interface", JLabel.CENTER);
        loginHeading.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate user credentials
            boolean validLogin = false;
            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(username) && passwords[i].equals(password)) {
                    validLogin = true;
                    currentUserIndex = i; // Set current user index
                    showDashboardScreen();
                    break;
                }
            }

            if (!validLogin) {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(loginHeading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 3;
        loginPanel.add(loginButton, gbc);

        setContentPane(loginPanel);
        revalidate();
    }

    // Dashboard Screen
    private void showDashboardScreen() {
        JPanel dashboardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel dashboardHeading = new JLabel("Dashboard", JLabel.CENTER);
        dashboardHeading.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField parcelIdField = new JTextField(15);
        parcelIdField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton trackButton = new JButton("Track");
        trackButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));

        trackButton.addActionListener(e -> {
            String parcelId = parcelIdField.getText();

            // Validate if the parcel ID matches the one assigned to the current user
            if (parcelIds[currentUserIndex].equals(parcelId)) {
                showTrackingResultScreen(parcelId, deliveryDetails[currentUserIndex], dischargeDetails[currentUserIndex]);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Parcel ID.");
            }
        });

        logoutButton.addActionListener(e -> showLoginScreen());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dashboardPanel.add(dashboardHeading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        dashboardPanel.add(new JLabel("Enter Parcel ID:"), gbc);

        gbc.gridx = 1;
        dashboardPanel.add(parcelIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        dashboardPanel.add(trackButton, gbc);

        gbc.gridy = 3;
        dashboardPanel.add(logoutButton, gbc);

        setContentPane(dashboardPanel);
        revalidate();
    }

    // Tracking Result Screen
    private void showTrackingResultScreen(String parcelId, String deliveryDetail, String dischargeDetail) {
        JPanel resultPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel resultHeading = new JLabel("Tracking Results", JLabel.CENTER);
        resultHeading.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel resultLabel = new JLabel("<html>PARCEL ID: " + parcelId
                + "<br>PARCEL STATUS: " + deliveryDetail
                + "<br>DISCHARGE DETAILS: " + dischargeDetail + "</html>");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));

        logoutButton.addActionListener(e -> showLoginScreen());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        resultPanel.add(resultHeading, gbc);

        gbc.gridy = 1;
        resultPanel.add(resultLabel, gbc);

        gbc.gridy = 2;
        resultPanel.add(logoutButton, gbc);

        setContentPane(resultPanel);
        revalidate();
    }

    // Main method for application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParcelTrackingSystem system = new ParcelTrackingSystem();
            system.setVisible(true);
        });
    }
}