
package org.example.gui.dialog;


import org.example.bus.*;
import org.example.dto.*;
import org.example.gui.panel.CTHoaDonPanel;
import org.example.helper.*;
import org.example.dao.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.*;
import java.util.List;

public class HoaDonDialog extends javax.swing.JDialog {
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
        this.soluong=hd.getSoluong();
        loadCbox();
        txtmahd.setText(hd.getMaHD());
        cbmakh.setSelectedItem(hd.getMaKHDat());
        cbmakht.setSelectedItem(hd.getMaKHTour());
        cbmanv.setSelectedItem(hd.getMaNV());
        txtsoluong.setText(String.valueOf(hd.getSoluong()));
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

        lbmakht = new javax.swing.JLabel();
        lbmanv = new javax.swing.JLabel();
        lbmakh = new javax.swing.JLabel();
        lbtongtien = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        txttongtien = new javax.swing.JTextField();
        lbmahd = new javax.swing.JLabel();
        btnluu = new javax.swing.JButton();
        txtsoluong = new javax.swing.JTextField();
        lbsoluong = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();
        lbngay = new javax.swing.JLabel();
        txtngay = new com.toedter.calendar.JDateChooser();
        cbmakht = new javax.swing.JComboBox<>();
        cbmakh = new javax.swing.JComboBox<>();
        cbmanv = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbmakht.setText("Mã kế hoạch tour");

        lbmanv.setText("Mã nhân viên");

        lbmakh.setText("Mã khách hàng đặt");

        lbtongtien.setText("Tổng tiền");

        txtmahd.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtmahdInputMethodTextChanged(evt);
            }
        });
        txtmahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmahdActionPerformed(evt);
            }
        });

        txttongtien.setEditable(false);
        txttongtien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttongtienFocusLost(evt);
            }
        });
        txttongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });

        lbmahd.setText("Mã hóa đơn");

        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        txtsoluong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsoluongFocusLost(evt);
            }
        });

        lbsoluong.setText("Số lượng");

        btnxoa.setText("Làm mới");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        lbngay.setText("Ngày");

        txtngay.setDateFormatString("dd/MM/yyyy");

        cbmakht.setEditable(true);
        cbmakht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnxoa)))
        );

        pack();
    }

    private void txtmahdActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txttongtienFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:

    }

    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
        HoaDonDTO hd = new HoaDonDTO(ma, cbmakht.getSelectedItem().toString().trim(), cbmakh.getSelectedItem().toString().trim(), cbmanv.getSelectedItem().toString().trim(), kt.getNgay(),kt.getSoluong(), kt.getTongTien());

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
    }//GEN-LAST:event_btnluuActionPerformed

    private void txtsoluongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsoluongFocusLost
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

    private void txtmahdInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtmahdInputMethodTextChanged
        // TODO add your handling code here:
        
    }

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        resetField();
    }

    private void cbmakhtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmakhtActionPerformed
        // TODO add your handling code here:
    }


    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbmakh;
    private javax.swing.JComboBox<String> cbmakht;
    private javax.swing.JComboBox<String> cbmanv;
    private javax.swing.JLabel lbmahd;
    private javax.swing.JLabel lbmakh;
    private javax.swing.JLabel lbmakht;
    private javax.swing.JLabel lbmanv;
    private javax.swing.JLabel lbngay;
    private javax.swing.JLabel lbsoluong;
    private javax.swing.JLabel lbtongtien;
    private javax.swing.JTextField txtmahd;
    private com.toedter.calendar.JDateChooser txtngay;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttongtien;
}
