/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.dialog;

import GUI.panel.CTHoaDon;
import GUI.NhapCTHD;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import org.apache.poi.hpsf.GUID;
/**
 *
 * @author Nhat
 */
public class HoaDonDialog extends javax.swing.JDialog {
    private BUS.HoaDonBus bus;
    private int soluong=0;
    private BUS.CTHoaDonBus busct;
    
    /**
     * Creates new form HoaDonDialog
     */
    public HoaDonDialog() {
        initComponents();
        this.bus=new BUS.HoaDonBus();
        this.setTitle("Hóa đơn");
        this.setLocationRelativeTo(null);
        
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
       
       txtmakht.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmakht.getText().trim();
        
       if(!ma.matches("^KHT\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã kế hoạch tour phải có dạng KHTxx!");
            return false;
        }
       return true;
    }
    });
       
       txtmakhdat.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmakhdat.getText().trim();
        
       if(!ma.matches("^KH\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã khách hàng phải có dạng KHxxx!");
            return false;
        }
       return true;
    }   
    });
    
       txtmanv.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmanv.getText().trim();
        
       if(!ma.matches("^NV\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã nhân viên phải có dạng NVxxx!");
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

    
    public HoaDonDialog(DTO.HoaDon hd) {
        initComponents();
        this.bus=new BUS.HoaDonBus();
        this.setTitle("Hóa đơn");
        this.setLocationRelativeTo(null);
        
        this.soluong=hd.getSoluong();
        txtmahd.setText(hd.getMaHD());
        txtmakhdat.setText(hd.getMaKHDat());
        txtmakht.setText(hd.getMaKHTour());
        txtmanv.setText(hd.getMaNV());
        txtsoluong.setText(String.valueOf(hd.getSoluong()));
        txttongtien.setText(String.format("%.0f", hd.getTongTien()));
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
       
       txtmakht.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmakht.getText().trim();
        
       if(!ma.matches("^KHT\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã kế hoạch tour phải có dạng KHTxx!");
            return false;
        }
       return true;
    }
    });
       
       txtmakhdat.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmakhdat.getText().trim();
        
       if(!ma.matches("^KH\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã khách hàng phải có dạng KHxxx!");
            return false;
        }
       return true;
    }   
    });
    
       txtmanv.setInputVerifier(new InputVerifier(){
       @Override 
       public boolean verify(JComponent input){
        String ma=txtmanv.getText().trim();
        
       if(!ma.matches("^NV\\d{2}$")){
            JOptionPane.showMessageDialog(null, "Mã nhân viên phải có dạng NVxxx!");
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
    
    public void resetField(){
                    txtmahd.setText("");
                    txtmakhdat.setText("");
                    txtmakht.setText("");
                    txtmanv.setText("");
                    txtsoluong.setText("");
                    txttongtien.setText("");
    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        txtmakht = new javax.swing.JTextField();
        txtmanv = new javax.swing.JTextField();
        txtmakhdat = new javax.swing.JTextField();
        txttongtien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnluu = new javax.swing.JButton();
        txtsoluong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Mã kế hoạch tour");

        jLabel3.setText("Mã nhân viên");

        jLabel4.setText("Mã khách hàng đặt");

        jLabel5.setText("Tổng tiền");

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

        txtmakht.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmakhtFocusLost(evt);
            }
        });
        txtmakht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmakhtActionPerformed(evt);
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

        jLabel1.setText("Mã hóa đơn");

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

        jLabel6.setText("Số lượng");

        btnxoa.setText("Làm mới");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmakhdat, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtmahd, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(txtmakht))
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmakht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmakhdat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttongtien)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnluu)
                    .addComponent(btnxoa))
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmahdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmahdActionPerformed

    private void txtmakhtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmakhtFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtmakhtFocusLost

    private void txtmakhtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmakhtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmakhtActionPerformed

    private void txttongtienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttongtienFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_txttongtienFocusLost

    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtienActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        // TODO add your handling code here:
       try {
        String ma = txtmahd.getText().trim();

        int newSl = Integer.parseInt(txtsoluong.getText().trim());
        float tongTien = Float.parseFloat(txttongtien.getText().trim());
        
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa nhập mã hóa đơn");
            return;
        }

        DTO.HoaDon kt = bus.timHd(ma); 

        if (kt != null) { 
        DTO.HoaDon hd = new DTO.HoaDon(ma, txtmakht.getText().trim(), txtmakhdat.getText().trim(), txtmanv.getText().trim(), kt.getSoluong(), kt.getTongTien());

            if (bus.suaHoaDon(hd)) {
            
                if (newSl > this.soluong) { 

                    int canThem = newSl - this.soluong;
                    JOptionPane.showMessageDialog(this, "Số lượng tăng. Vui lòng nhập thêm " + canThem + " vé.");
                    NhapCTHD nhap = new NhapCTHD(null, true, ma, canThem);
                    nhap.setVisible(true);
                    
                } else if (newSl < this.soluong) { 
                    int veDu = this.soluong - newSl;
                    JOptionPane.showMessageDialog(this, "Số lượng giảm. Vui lòng chọn " + veDu + " vé để xóa.");
                    CTHoaDon panelXoa = new CTHoaDon(ma, veDu); 
                    JOptionPane.showConfirmDialog(null, panelXoa, "Xóa chi tiết thừa", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công!");
                }
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
            
        } else { 
            DTO.HoaDon hdmoi = new DTO.HoaDon(ma, txtmakht.getText().trim(), txtmakhdat.getText().trim(), txtmanv.getText().trim(), 0, 0.0f);

            ArrayList<DTO.CTietHD> ds = new ArrayList<>();
            if (bus.themHoaDon(hdmoi, ds)) {
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
        String makht=txtmakht.getText().trim();
        int sl=Integer.parseInt(txtsoluong.getText().trim());
        if(!makht.isEmpty()){
            DAO.DsHoaDon dao =new DAO.DsHoaDon();
            float gia=dao.laygia(makht);
            if(gia>0){
                txttongtien.setText(String.format("%.0f", gia*sl));
            }
            else{
                txttongtien.setText("0");
                return;
            }
        }
    }//GEN-LAST:event_txtsoluongFocusLost

    private void txtmahdInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtmahdInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtmahdInputMethodTextChanged

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        resetField();
    }//GEN-LAST:event_btnxoaActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDonDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDonDialog dialog = new HoaDonDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtmakhdat;
    private javax.swing.JTextField txtmakht;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
