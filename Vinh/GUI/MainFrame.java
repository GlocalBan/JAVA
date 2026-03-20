package GUI;

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

import GUI.Panel.KHang_KHTourPanel;
import GUI.Panel.KhachHangPanel;
import GUI.Panel.NhanVienPanel;

public class MainFrame extends JFrame {

    //layout
    private CardLayout cardLayout;
    private JPanel contentArea;
    private JButton activeButton;

    public MainFrame() {
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
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Logo
//        sidebar.add(buildLogo());

        //menu items: label, cardName
        String[][] menus = {
                {"Nhân viên", "NhanVien"},
                {"Khách hàng", "KhachHang"},
                {"Khách hàng-Kế hoạch Tour", "KHang_KHTour"}
        };

        for(String[] m : menus){
            JButton btn = createMenuButton(m[0], m[1]);
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 2)));
        }
        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JPanel buildContentArea(){
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(Color.cyan);

        contentArea.add(new NhanVienPanel(), "NhanVien");
        contentArea.add(new KhachHangPanel(), "KhachHang");
        contentArea.add(new KHang_KHTourPanel(), "KHang_KHTour");

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
        btn.setMaximumSize(new Dimension(190, 48));
        btn.setPreferredSize(new Dimension(190, 48));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        btn.addActionListener(e -> {
            activeButton = btn;
            btn.repaint();
            cardLayout.show(contentArea, card);
        });
        return btn;
    }
    public static void main (String []args){
        MainFrame main = new MainFrame();
    }

}