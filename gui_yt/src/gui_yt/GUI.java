package gui_yt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userTextField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static JButton clearButton;
    private static JButton exitButton;
    private static JButton signUpButton;
    private static JCheckBox showPasswordCheckbox;
    private static JLabel statusLabel;

    private JFrame frame;
    private JPanel panel;

    private Database db;

    public GUI() {
        db = new Database();

        // Initialize frame and panel
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
    }

    public void showInitialScreen() {
        panel.removeAll();
        frame.repaint();
        frame.setTitle("Welcome");

        JButton signUpInitButton = new JButton("Sign Up");
        signUpInitButton.setBounds(80, 60, 100, 25);
        signUpInitButton.addActionListener(e -> showSignUp());
        panel.add(signUpInitButton);

        JButton loginInitButton = new JButton("Log In");
        loginInitButton.setBounds(200, 60, 100, 25);
        loginInitButton.addActionListener(e -> showLogin());
        panel.add(loginInitButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(318, 120, 60, 25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        frame.setVisible(true);
    }

    public void showSignUp() {
        panel.removeAll();
        frame.repaint();
        frame.setTitle("Sign Up");

        userLabel = new JLabel("User");
        userLabel.setBounds(50, 20, 80, 25);
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 20, 165, 25);
        panel.add(userTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 50, 165, 25);
        panel.add(passwordField);

        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setBounds(150, 80, 165, 25);
        showPasswordCheckbox.addActionListener(this);
        panel.add(showPasswordCheckbox);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(50, 120, 100, 25);
        signUpButton.addActionListener(this);
        panel.add(signUpButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(220, 120, 70, 25);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 120, 70, 25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 160, 300, 25);
        panel.add(statusLabel);

        frame.setVisible(true);
    }

    public void showLogin() {
        panel.removeAll();
        frame.repaint();
        frame.setTitle("Login");

        userLabel = new JLabel("User");
        userLabel.setBounds(50, 20, 80, 25);
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 20, 165, 25);
        panel.add(userTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 50, 165, 25);
        panel.add(passwordField);

        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setBounds(150, 80, 165, 25);
        showPasswordCheckbox.addActionListener(this);
        panel.add(showPasswordCheckbox);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 120, 100, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(220, 120, 70, 25);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 120, 70, 25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 160, 300, 25);
        panel.add(statusLabel);

        frame.setVisible(true);
    }

    private void showSuccessMessage() {
        panel.removeAll();
        frame.repaint();
        frame.setTitle("Login Successful");

        JLabel successLabel = new JLabel("Login successful!");
        successLabel.setBounds(150, 50, 200, 25);
        panel.add(successLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(150, 100, 100, 25);
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == showPasswordCheckbox) {
            togglePasswordVisibility();
        } else if (e.getSource() == signUpButton) {
            handleSignUp();
        }
    }

    private void handleSignUp() {
        String user = userTextField.getText();
        char[] password = passwordField.getPassword();

        if (db.addUser(user, String.valueOf(password))) {
            statusLabel.setText("Sign up successful!");
            showLogin();
        } else {
            statusLabel.setText("Sign up failed :(");
        }

        // Clear password array
        java.util.Arrays.fill(password, '0');
    }

    private void handleLogin() {
        String user = userTextField.getText();
        char[] password = passwordField.getPassword();

        if (db.authenticate(user, String.valueOf(password))) {
            statusLabel.setText("Login successful!");
            showSuccessMessage();
        } else {
            statusLabel.setText("Login failed :(");
        }

        // Clear password array
        java.util.Arrays.fill(password, '0');
    }

    private void clearFields() {
        userTextField.setText("");
        passwordField.setText("");
        statusLabel.setText("");
    }

    private void togglePasswordVisibility() {
        if (showPasswordCheckbox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }
}
