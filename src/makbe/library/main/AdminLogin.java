package makbe.library.main;

import makbe.library.admin.*;
import makbe.library.connections.Connections;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminLogin extends JDialog {

    private final JTextField nameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    AdminLogin(Login owner) {
        super(owner, "Administrator Login", true);
        setResizable(false);
        setSize(owner.width, owner.height);
        setLocationRelativeTo(owner);
        setLayout(null);

        JPanel buttonsPanel = new JPanel();
        Font font = new Font("Iosevka Term", Font.PLAIN, 20);

        JLabel label = new JLabel("NAME:");
        label.setBounds(100, 50, 100, 40);
        label.setFont(font);
        add(label);

        nameField.setBounds(250, 50, 300, 40);
        nameField.setFont(font);
        add(nameField);

        label = new JLabel("PASSWORD:");
        label.setBounds(100, 120, 100, 40);
        label.setFont(font);
        add(label);

        passwordField.setBounds(250, 120, 300, 40);
        passwordField.setFont(font);
        add(passwordField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.addActionListener(e -> {
            setVisible(false);
            owner.setVisible(true);
        });
        buttonsPanel.add(cancelButton);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.addActionListener(e -> {
            if (loginValidate()) {
                erasePassword();
                setVisible(false);
                new AdminHome(this, nameField.getText());
            } else {
                erasePassword();
            }
        });
        buttonsPanel.add(loginButton);

        buttonsPanel.setBounds(0, 200, owner.width, 50);
        add(buttonsPanel);

        setVisible(true);
    }

    private boolean loginValidate() {
        if (Objects.equals(nameField.getText(), "") || Objects.equals(passwordField.getText(), "")) {
            JOptionPane.showMessageDialog(this, "Please Fill Out All Fields!", null, JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            Connections conn = new Connections();
            String query =
                    "SELECT * FROM login " +
                    "WHERE username = '" + nameField.getText() + "' AND password = '" + passwordField.getText() + "' ";
            try {
                ResultSet resultSet = conn.statement.executeQuery(query);
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void erasePassword() {
        passwordField.setText("");
    }

}
