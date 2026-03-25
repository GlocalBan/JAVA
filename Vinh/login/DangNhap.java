package org.example.login;

import org.example.dao.TaiKhoanDAO;
import org.example.dto.TaiKhoanDTO;
import org.example.gui._MainFrame;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class DangNhap extends JFrame {
    public DangNhap() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbLogin = new JLabel();
        jlbAccount = new JLabel();
        jlbPassword = new JLabel();
        loginBtn = new JButton();
        logoutBtn = new JButton();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('*');

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jlbLogin.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        jlbLogin.setText("Đăng nhập");

        jlbAccount.setText("Tài khoản:");

        jlbPassword.setText("Mật khẩu:");

        // define login and logout function
        login();
        logout();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(jlbLogin))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(106, 106, 106)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jlbAccount)
                                                        .addComponent(jlbPassword))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtUsername)
                                                        .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(loginBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addComponent(logoutBtn)
                                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlbLogin)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlbAccount)
                                        .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlbPassword)
                                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginBtn)
                                        .addComponent(logoutBtn))
                                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login(){
        loginBtn = createBtn("Đăng nhập", Color.GREEN);
        loginBtn.addActionListener(v -> {
            String username = txtUsername.getText().trim();
            char[] passwordChars = txtPassword.getPassword();
            String password = new String(passwordChars).trim();
            Arrays.fill(passwordChars, '\0');

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu.");
                return;
            }

            TaiKhoanDTO taiKhoan = taiKhoanDAO.dangNhap(username, password);
            if (taiKhoan == null) {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu.");
                return;
            }

            PhanQuyen.dangNhap(taiKhoan);
            _MainFrame mainFrame = new _MainFrame(taiKhoan);
            mainFrame.setVisible(true);
            this.dispose();
        });
    }

    private void logout(){
        logoutBtn = createBtn("Thoát", Color.RED);
        logoutBtn.addActionListener(v -> {
            dispose();
        });
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // in south panel

        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        return btn;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton loginBtn;
    private JButton logoutBtn;
    private JLabel jlbLogin;
    private JLabel jlbAccount;
    private JLabel jlbPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
}
