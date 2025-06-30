package app;

import service.MongoUserService;
import javax.swing.*;
import java.awt.*;

public class Login_GUI extends JFrame {
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private MongoUserService userService = new MongoUserService();

    public Login_GUI() {
        setTitle("Login Aplikasi Rental Mobil");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        tfUsername = new JTextField(15);
        JLabel lblPassword = new JLabel("Password:");
        tfPassword = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        panel.add(lblUsername);
        panel.add(tfUsername);
        panel.add(lblPassword);
        panel.add(tfPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);

        btnLogin.addActionListener(e -> {
            String username = tfUsername.getText();
            String password = new String(tfPassword.getPassword());

            if (userService.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                dispose();
                new Main_GUI().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRegister.addActionListener(e -> showRegisterForm());
    }

    private void showRegisterForm() {
        JTextField regUsername = new JTextField();
        JPasswordField regPassword = new JPasswordField();
        JPasswordField regConfirmPassword = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        panel.add(regUsername);
        panel.add(new JLabel("Password:"));
        panel.add(regPassword);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(regConfirmPassword);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrasi Akun Baru", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = regUsername.getText();
            String password = new String(regPassword.getPassword());
            String confirmPassword = new String(regConfirmPassword.getPassword());

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Registrasi Gagal", JOptionPane.WARNING_MESSAGE);
            } else if (userService.isUsernameExist(username)) {
                JOptionPane.showMessageDialog(this, "Username sudah terdaftar. Pilih username lain.", "Registrasi Gagal", JOptionPane.WARNING_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Password dan Konfirmasi Password tidak cocok!", "Registrasi Gagal", JOptionPane.WARNING_MESSAGE);
            } else {
                userService.registerUser(username, password);
                JOptionPane.showMessageDialog(this, "Akun berhasil didaftarkan! Silakan login.", "Registrasi Berhasil", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_GUI().setVisible(true));
    }
}
