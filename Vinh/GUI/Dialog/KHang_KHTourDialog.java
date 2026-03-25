package GUI.Dialog;

import BUS.KHang_KHTourBUS;
import DAO.KHang_KHTourDAO;
import DAO.KeHoachTourDAO;
import DAO.KhachHangDAO;
import DAO.TourDAO;
import DTO.KHang_KHTour;
import DTO.KeHoachTourDTO;
import DTO.KhachHang;
import GUI.Panel.KHang_KHTourPanel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class KHang_KHTourDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KHang_KHTourDialog.class.getName());
    private KHang_KHTourDAO dskhangkhtour = new KHang_KHTourDAO();
    private KHang_KHTourPanel parentPanel;
    public enum Mode {
        ADD, EDIT
    }
    private Mode mode;
    private KHang_KHTour currentKHangKHTour;
    private KHang_KHTourDAO ds;
    private KhachHangDAO dsKhachHang;
    private KeHoachTourDAO dsKeHoachTour;
    private TourDAO dsTour;
    
    public KHang_KHTourDialog(java.awt.Frame parent, boolean modal, 
        KHang_KHTourDAO ds, Mode mode, KHang_KHTour khangkhtour) {

    super(parent, modal);
    this.ds = ds;
    this.mode = mode;
    this.currentKHangKHTour = khangkhtour;
    this.setLocationRelativeTo(null);

    dsKhachHang = new KhachHangDAO();
    dsKeHoachTour = new KeHoachTourDAO();
    dsTour = new TourDAO();

    initComponents();

    if (mode == Mode.EDIT && khangkhtour != null) {
        setDataToFields();
        setTitle("Sửa thông tin khách hàng - kế hoạch tour");
    }else {
        setTitle("Thêm khách hàng - kế hoạch tour");
    }
}

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        btnLuu = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtMaKHTour = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtGiaVe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtHoKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(this::btnLuuActionPerformed);

        btnDong.setText("Đóng");
        btnDong.addActionListener(this::btnDongActionPerformed);

        jLabel33.setText("Mã khách hàng:");

        txtMaKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaKHFocusLost(evt);
            }
        });
        txtMaKH.addActionListener(this::txtMaKHActionPerformed);

        txtMaKH.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = txtMaKH.getText().trim();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(KHang_KHTourDialog.this, "Mã khách hàng không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                for (KhachHang kh : dsKhachHang.layDanhSachKHang()) {
                    if (kh.getMaKH().equals(text)) {
                        return true;
                    }
                }
                JOptionPane.showMessageDialog(KHang_KHTourDialog.this, "Mã khách hàng không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel34.setText("Mã kế hoạch - Tour:");

        txtMaKHTour.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaKHTourFocusLost(evt);
            }
        });
        txtMaKHTour.addActionListener(this::txtMaKHTourActionPerformed);

        txtMaKHTour.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = txtMaKHTour.getText().trim();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(KHang_KHTourDialog.this, "Mã kế hoạch - Tour không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                for (KeHoachTourDTO kt : dsKeHoachTour.getAllKeHoachTours()) {
                    if (kt.getMaKHTour().equals(text)) {
                        return true;
                    }
                }
                JOptionPane.showMessageDialog(KHang_KHTourDialog.this, "Mã kế hoạch - Tour không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKHTour, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtMaKHTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel35.setText("Giá vé:");

        txtGiaVe.setEnabled(false);
        txtGiaVe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiaVeFocusLost(evt);
            }
        });
        txtGiaVe.addActionListener(this::txtGiaVeActionPerformed);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setText("Họ:");

        txtHoKH.setEnabled(false);
        txtHoKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoKHFocusLost(evt);
            }
        });

        jLabel2.setText("Tên:");

        txtTenKH.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDong)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnDong))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        String maKH = txtMaKH.getText().trim();
        String ho = txtHoKH.getText().trim();
        String ten = txtTenKH.getText().trim();
        String maKHTour = txtMaKHTour.getText().trim();

        if(txtGiaVe.getText().trim().isEmpty()){
            KHang_KHTourDAO dao = new KHang_KHTourDAO();
            long gia = dao.layDonGiaTheoMaKHTour(maKHTour);
            txtGiaVe.setText(String.valueOf(gia));
        }
        long giaVe = Long.parseLong(txtGiaVe.getText().trim());
        KHang_KHTourBUS khangkhtBUS = new KHang_KHTourBUS();
        if(mode==Mode.ADD) {
            KHang_KHTour newKHangKHTour = new KHang_KHTour(maKHTour, maKH, giaVe);
            khangkhtBUS.them(newKHangKHTour);
        } else if(mode==Mode.EDIT&&currentKHangKHTour!=null) {
            currentKHangKHTour.setMaKHang(maKH);
            currentKHangKHTour.setMaKHTour(maKHTour);
            currentKHangKHTour.setGiaVe(giaVe);
            khangkhtBUS.suaKHang_KHTour(currentKHangKHTour);
        }
        dispose();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtMaKHTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHTourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHTourActionPerformed

    private void txtGiaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaVeActionPerformed

    private void txtMaKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaKHFocusLost
        // TODO add your handling code here:
        String ma = txtMaKH.getText().trim();
        for(KhachHang kh : dsKhachHang.layDanhSachKHang()) {
            if(kh.getMaKH().equals(ma)) {
                txtHoKH.setText(kh.getHo());
                txtTenKH.setText(kh.getTen());
                return;
            }
        }
        JOptionPane.showMessageDialog(KHang_KHTourDialog.this, "Mã khách hàng không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_txtMaKHFocusLost

    private void txtMaKHTourFocusLost(java.awt.event.FocusEvent evt) {

        String maKHTour = txtMaKHTour.getText().trim();

        if(!maKHTour.isEmpty()){
            KHang_KHTourDAO dao = new KHang_KHTourDAO();
            long gia = dao.layDonGiaTheoMaKHTour(maKHTour);

            txtGiaVe.setText(String.valueOf(gia));
        }
    }

    private void txtHoKHFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void txtTenKHFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void txtGiaVeFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void setDataToFields() {
        if (currentKHangKHTour != null) {
            txtMaKH.setText(currentKHangKHTour.getMaKHang());
            txtMaKHTour.setText(currentKHangKHTour.getMaKHTour());
            txtGiaVe.setText(String.valueOf(currentKHangKHTour.getGiaVe()));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JTextField txtGiaVe;
    private javax.swing.JTextField txtHoKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKHTour;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
