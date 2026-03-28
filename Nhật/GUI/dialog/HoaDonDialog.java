
package org.example.gui.dialog;


import com.toedter.calendar.JDateChooser;
import org.example.bus.*;
import org.example.dto.*;
import org.example.gui.helper.DateHelper;
import org.example.gui.panel.CTHoaDonPanel;
import org.example.dao.*;
import org.example.gui.panel.UIColors;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.*;
import java.util.List;

public class HoaDonDialog extends JDialog {
    private HoaDonBUS bus;
    private int soluong=0;
    private CTHoaDonBUS busct;
    private _KeHoachTourBUS khtbus;
    private KhachHangBUS khbus;
    private NhanVienBUS nvbus;

    public HoaDonDialog() {
        initComponents();
        this.bus=new HoaDonBUS();
        this.khtbus=new _KeHoachTourBUS();
        this.khbus =new KhachHangBUS();
        this.nvbus =new NhanVienBUS();
        this.setTitle("Hóa đơn");
        this.setLocationRelativeTo(null);
        loadCbox();
        txtmahd.setInputVerifier(new InputVerifier(){
            @Override
            public boolean verify(JComponent input){
                String ma=txtmahd.getText().trim();

                if(!ma.matches("^HD\\d{3}$") && bus.timHd(ma)==null){
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn phải có dạng HDxx!");
                    return false;
                }
                return true;
            }

        });

        txtsoluong.setInputVerifier(new InputVerifier(){
            @Override
            public boolean verify(JComponent input){
                int sl=Integer.parseInt(txtsoluong.getText().trim());

                if(sl<=0){
                    JOptionPane.showMessageDialog(null, "Số lượng không được bé hơn 0");
                    return false;
                }
                return true;
            }
        });

    }

    public void setupAutoComplete(JComboBox<String> cbx, List<String> data) {
        cbx.setEditable(true);
        cbx.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
        JTextField txt = (JTextField) cbx.getEditor().getEditorComponent();

        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == 38 || k == 40 || k == 10) return;

                SwingUtilities.invokeLater(() -> {
                    String input = txt.getText();

                    String[] filtered = data.stream()
                            .filter(s -> s.toLowerCase().contains(input.toLowerCase()))
                            .toArray(String[]::new);

                    cbx.setModel(new DefaultComboBoxModel<>(filtered));
                    txt.setText(input);
                    cbx.setPopupVisible(filtered.length > 0);
                });
            }
        });
    }

    public HoaDonDialog(HoaDonDTO hd) {
        initComponents();
        this.setTitle("Sửa hóa đơn");
        this.setLocationRelativeTo(null);
        this.soluong=hd.getSoLuong();
        loadCbox();
        txtmahd.setText(hd.getMaHD());
        cbmakh.setSelectedItem(hd.getMaKHDat());
        cbmakht.setSelectedItem(hd.getMaKHTour());
        cbmanv.setSelectedItem(hd.getMaNV());
        txtsoluong.setText(String.valueOf(hd.getSoLuong()));
        txttongtien.setText(String.format("%.0f", hd.getTongTien()));
        txtngay.setDate(DateHelper.toUtilDate(hd.getNgay()));
        txtmahd.setInputVerifier(new InputVerifier(){
            @Override
            public boolean verify(JComponent input){
                String ma=txtmahd.getText().trim();

                if(!ma.matches("^HD\\d{3}$") && bus.timHd(ma)==null){
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn phải có dạng HDxx!");
                    return false;
                }
                return true;
            }

        });


        txtsoluong.setInputVerifier(new InputVerifier(){
            @Override
            public boolean verify(JComponent input){
                int sl=Integer.parseInt(txtsoluong.getText().trim());

                if(sl<=0){
                    JOptionPane.showMessageDialog(null, "Số lượng không được bé hơn 0");
                    return false;
                }
                return true;
            }
        });

    }

    public void loadCbox(){
        this.bus=new HoaDonBUS();
        this.khtbus=new _KeHoachTourBUS();
        this.khbus =new KhachHangBUS();
        ArrayList<_KeHoachTourDTO> dskht =khtbus.getAllKeHoachTours();
        ArrayList<KhachHangDTO> dskh =KhachHangBUS.dsKH;
        ArrayList<NhanVienDTO> dsnv =NhanVienBUS.dsNV;
        List<String> dsMa = new ArrayList<>();

        for(_KeHoachTourDTO kht: dskht){
            dsMa.add(kht.getMaKHTour());
        }
        setupAutoComplete(cbmakht, dsMa);
        dsMa=new ArrayList<>();
        for(KhachHangDTO kh:dskh){
            dsMa.add(kh.getMaKH());
        }
        setupAutoComplete(cbmakh, dsMa);
        dsMa=new ArrayList<>();
        for(NhanVienDTO nv:dsnv){
            dsMa.add(nv.getMaNV());
        }
        setupAutoComplete(cbmanv, dsMa);
    }
    public void resetField(){
        txtmahd.setText("");
        cbmakh.setSelectedItem("");
        cbmakht.setSelectedItem("");
        cbmanv.setSelectedItem("");
        txtsoluong.setText("");
        txttongtien.setText("");
        txtngay.setDate(new java.util.Date());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lbmakht = new JLabel();
        lbmanv = new JLabel();
        lbmakh = new JLabel();
        lbtongtien = new JLabel();
        txtmahd = new JTextField();
        txttongtien = new JTextField();
        lbmahd = new JLabel();
        btnluu = new JButton();
        txtsoluong = new JTextField();
        lbsoluong = new JLabel();
        btnLamMoi = new JButton();
        lbngay = new JLabel();
        txtngay = new JDateChooser();
        cbmakht = new JComboBox<>();
        cbmakh = new JComboBox<>();
        cbmanv = new JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbmakht.setText("Mã kế hoạch tour");

        lbmanv.setText("Mã nhân viên");

        lbmakh.setText("Mã khách hàng đặt");

        lbtongtien.setText("Tổng tiền");

        txtmahd.addInputMethodListener(new InputMethodListener() {
            public void caretPositionChanged(InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(InputMethodEvent evt) {
                txtmahdInputMethodTextChanged(evt);
            }
        });
        txtmahd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtmahdActionPerformed(evt);
            }
        });

        txttongtien.setEditable(false);
        txttongtien.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                txttongtienFocusLost(evt);
            }
        });
        txttongtien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });

        lbmahd.setText("Mã hóa đơn");

        luu();

        txtsoluong.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                txtsoluongFocusLost(evt);
            }
        });

        lbsoluong.setText("Số lượng");

        lamMoi();

        lbngay.setText("Ngày");

        txtngay.setDateFormatString("dd/MM/yyyy");

        cbmakht.setEditable(true);
        cbmakht.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cbmakhtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lbmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lbmakht, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(cbmakht, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lbngay, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(140, 140, 140)
                                                .addComponent(txtngay, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lbsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(119, 119, 119)
                                                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lbtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(53, 53, 53)
                                                .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(112, 112, 112)
                                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbmakh, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                                                .addGap(38, 38, 38))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbmanv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(53, 53, 53)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cbmakh, 0, 177, Short.MAX_VALUE)
                                                        .addComponent(cbmanv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(lbmahd))
                                        .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(lbmakht))
                                        .addComponent(cbmakht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(lbmakh))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(cbmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(lbmanv))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lbngay))
                                        .addComponent(txtngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(lbsoluong))
                                        .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnluu)
                                        .addComponent(btnLamMoi)))
        );
        pack();
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));// Trong jpBtn panel

        return btn;
    }

    private void luu(){
        btnluu = createBtn("Lưu", UIColors.SAVE);
        btnluu.addActionListener(v -> {
            try {

                String ma = txtmahd.getText().trim();
                String makh =cbmakh.getSelectedItem().toString().trim();
                int newSl = Integer.parseInt(txtsoluong.getText().trim());
                float tongTien = Float.parseFloat(txttongtien.getText().trim());

                if (ma.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lỗi chưa nhập mã hóa đơn");
                    return;
                }

                HoaDonDTO kt = bus.timHd(ma);

                if (kt != null) {
                    HoaDonDTO hd = new HoaDonDTO(ma, cbmakht.getSelectedItem().toString().trim(), cbmakh.getSelectedItem().toString().trim(), cbmanv.getSelectedItem().toString().trim(), kt.getNgay(),kt.getSoLuong(), kt.getTongTien());

                    if (bus.suaHoaDon(hd)) {
                        if (newSl > this.soluong) {

                            int canThem = newSl - this.soluong;
                            JOptionPane.showMessageDialog(this, "Số lượng tăng. Vui lòng nhập thêm " + canThem + " vé.");
                            NhapCTHD nhap = new NhapCTHD(null, true, ma, canThem);
                            nhap.setVisible(true);

                        } else if (newSl < this.soluong) {
                            int veDu = this.soluong - newSl;
                            JOptionPane.showMessageDialog(this, "Số lượng giảm. Vui lòng chọn " + veDu + " vé để xóa.");
                            CTHoaDonPanel panelXoa = new CTHoaDonPanel(ma, veDu);
                            JOptionPane.showConfirmDialog(null, panelXoa, "Xóa chi tiết thừa", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công!");
                        }
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                    }

                } else {
                    java.util.Date ngaydl = txtngay.getDate();

                    if (ngaydl == null) {
                        JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng chọn ngày lập hóa đơn!");
                        return;
                    }

                    LocalDate ngay = DateHelper.toLocalDateFromUtil(ngaydl);

                    HoaDonDTO hdmoi = new HoaDonDTO(ma, cbmakht.getSelectedItem().toString().trim(), cbmakh.getSelectedItem().toString().trim(), cbmanv.getSelectedItem().toString().trim(), ngay, 0, 0.0f);

                    if (bus.themHoaDon(hdmoi)) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công! Hãy nhập thông tin chi tiết.");
                        NhapCTHD nhapCTHD = new NhapCTHD(null, true, ma, newSl);
                        nhapCTHD.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi định dạng dữ liệu hoặc tính toán!");
            }
        });
    }

    private void lamMoi(){
        btnLamMoi = createBtn("Làm mới", UIColors.REFRESH);
        btnLamMoi.addActionListener(v -> {
            resetField();
        });
    }

    private void txtmahdActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txttongtienFocusLost(FocusEvent evt) {
        // TODO add your handling code here:

    }

    private void txttongtienActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtsoluongFocusLost(FocusEvent evt) {//GEN-FIRST:event_txtsoluongFocusLost
        // TODO add your handling code here:
        String makht = cbmakht.getSelectedItem() != null ? cbmakht.getSelectedItem().toString().trim() : "";        int sl=Integer.parseInt(txtsoluong.getText().trim());
        if(!makht.isEmpty()){
            HoaDonDAO dao =new HoaDonDAO();
            float gia=dao.laygia(makht);
            if(gia>0){
                txttongtien.setText(String.format("%.0f", gia*sl));
            }
            else{
                txttongtien.setText("0");
                return;
            }
        }
    }

    private void txtmahdInputMethodTextChanged(InputMethodEvent evt) {//GEN-FIRST:event_txtmahdInputMethodTextChanged
        // TODO add your handling code here:

    }

    private void cbmakhtActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cbmakhtActionPerformed
        // TODO add your handling code here:
    }

    private JButton btnluu, btnLamMoi;

    private JComboBox<String> cbmakh, cbmakht, cbmanv;

    private JLabel lbmahd, lbmakh, lbmakht, lbmanv, lbngay, lbsoluong, lbtongtien;

    private JTextField txtmahd, txtsoluong, txttongtien;

    private JDateChooser txtngay;
}