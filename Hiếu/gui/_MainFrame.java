package org.example.gui;

import org.example.dto.TaiKhoanDTO;
import org.example.gui.panel.*;
import org.example.login.PhanQuyen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.example.login.DangNhap;

public class _MainFrame extends JFrame {

    //layout
    private CardLayout cardLayout;
    private JPanel contentArea;
    private JButton activeButton;
    public _MainFrame(TaiKhoanDTO taiKhoanDangNhap) {
        setTitle("Quản lý Tour du lịch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildSideBar(), BorderLayout.WEST);
        add(buildContentArea(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel buildSideBar(){
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 90, 160));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Logo
//        sidebar.add(buildLogo());

        //menu items: label, cardName
        String[][] menus;
        if (PhanQuyen.laQuanLy()) {
            menus = new String[][]{
                    {"Tour", "Tour"},
                    {"Loại Tour", "LoaiTour"},
                    {"Kế Hoạch Tour", "KeHoachTour"},
                    {"Khách hàng - Kế hoạch Tour", "KHang_KHTour"},
                    {"Hóa đơn", "HoaDon"},
                    {"Địa điểm", "DiaDiem"},
                    {"Nhân viên", "NhanVien"},
                    {"Khách hàng", "KhachHang"},
                    {"Chương trình khuyến mãi", "CTrinhKM"},
                    {"Lịch khuyến mãi", "CalendarKM"},
                    {"Thống kê", "ThongKe"},
            };
        } else {
            menus = new String[][]{
                    {"Tour", "Tour"},
                    {"Loại Tour", "LoaiTour"},
                    {"Kế Hoạch Tour", "KeHoachTour"},
                    {"Khách hàng - Kế hoạch Tour", "KHang_KHTour"},
                    {"Hóa đơn", "HoaDon"},
                    {"Địa điểm", "DiaDiem"},
                    {"Khách hàng", "KhachHang"},
                    {"Chương trình khuyến mãi", "CTrinhKM"},
                    {"Lịch khuyến mãi", "CalendarKM"},
            };
        }

        for(String[] m : menus){
            JButton btn = createMenuButton(m[0], m[1]);
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 2)));
        }
        sidebar.add(Box.createVerticalGlue());

        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.setMaximumSize(new Dimension(240, 48));
        logoutBtn.setPreferredSize(new Dimension(240, 48));
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        logoutBtn.addActionListener(e -> {
            // Clear session (if the method exists) then return to login screen
            try {
                // Use reflection so the code still compiles even if dangXuat() is missing
                PhanQuyen.class.getMethod("dangXuat").invoke(null);
            } catch (Exception ignored) {
                // If there's no dangXuat() in current build, still route back to login.
            }
            SwingUtilities.invokeLater(() -> {
                DangNhap dangNhap = new DangNhap();
                dangNhap.setVisible(true);
                _MainFrame.this.dispose();
            });
        });
        sidebar.add(logoutBtn);

        return sidebar;
    }

    private JPanel buildContentArea(){
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(Color.cyan);

        contentArea.add(new _TourPanel(), "Tour");
        contentArea.add(new _LoaiTourPanel(), "LoaiTour");
        contentArea.add(new _KeHoachTourPanel(), "KeHoachTour");
        contentArea.add(new KHang_KHTourPanel(), "KHang_KHTour");
        contentArea.add(new HoaDonPanel(), "HoaDon");
        contentArea.add(new DiaDiemPanel(), "DiaDiem");
        contentArea.add(new NhanVienPanel(), "NhanVien");
        contentArea.add(new KhachHangPanel(), "KhachHang");
        contentArea.add(new CTrinhKMPanel(), "CTrinhKM");
        contentArea.add(new CalendarKMPanel(), "CalendarKM");
        contentArea.add(new _StatisticsPanel(), "ThongKe");

        return contentArea;
    }

    private JButton createMenuButton(String text, String card){
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                if (this == activeButton) {
                    g.setColor(new Color(255, 255, 255, 30));
                    g.fillRoundRect(8, 4, getWidth() - 16, getHeight() - 8, 10, 10);
                } else if (getModel().isRollover()) {
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(240, 48));
        btn.setPreferredSize(new Dimension(240, 48));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        btn.addActionListener(e -> {

            if(activeButton != null) // fix color painted
                activeButton.repaint();

            activeButton = btn;
            btn.repaint();
            cardLayout.show(contentArea, card);
        });
        return btn;
    }
}
