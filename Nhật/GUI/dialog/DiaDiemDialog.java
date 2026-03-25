
package org.example.gui.dialog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.example.bus.*;
import org.example.dto.*;
import org.example.helper.*;

public class DiaDiemDialog extends javax.swing.JDialog {
    private DiaDiemBUS bus;
    private boolean sua=false;
    private String tenDiaDiemCu = "";

    public DiaDiemDialog() {
        this.bus=new DiaDiemBUS();
        initComponents();
        this.setTitle("Địa điểm");
        this.setLocationRelativeTo(null);
    }
    public DiaDiemDialog(DiaDiemDTO dd) {
        this.bus=new DiaDiemBUS();
        initComponents();
        this.setTitle("Sửa địa điểm");
        this.setLocationRelativeTo(null);
        this.sua= true;
        this.tenDiaDiemCu=dd.getTenDiaDiem();
        txttendd.setText(dd.getTenDiaDiem());
        txtdiachi.setText(dd.getdiachi());
        txtquocgia.setText(dd.getQuocGia());
    }
    private void resetField(){
        txttendd.setText("");
        txtdiachi.setText("");
        txtquocgia.setText("");
        txttendd.requestFocus();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        lbtongchi = new javax.swing.JLabel();
        txttendd = new javax.swing.JTextField();
        txtquocgia = new javax.swing.JTextField();
        lbten = new javax.swing.JLabel();
        lbngay = new javax.swing.JLabel();
        btnluu = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        txtdiachi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbtongchi.setText("Tổng chi");

        txtquocgia.setEditable(false);
        txtquocgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquocgiaActionPerformed(evt);
            }
        });

        lbten.setText("Tên địa điểm");

        lbngay.setText("Ngày thực hiện");

        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbten, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbngay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbtongchi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txttendd)
                    .addComponent(txtquocgia)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnreset)
                        .addGap(9, 9, 9))
                    .addComponent(txtdiachi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbten)
                    .addComponent(txttendd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbngay)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtongchi)
                    .addComponent(txtquocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnluu)
                    .addComponent(btnreset))
                .addGap(89, 89, 89))
        );

        pack();
    }

    private void txtquocgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquocgiaActionPerformed
        // TODO add your handling code here:
    }

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        // TODO add your handling code here:
        try{
            String ten = txttendd.getText().trim();
            if(ten.isEmpty()){
                JOptionPane.showMessageDialog(this, "Lỗi");
                return;
            }
            String diachi=txtdiachi.getText().trim();
            if(diachi.isEmpty()){
                JOptionPane.showMessageDialog(this, "Lỗi");
                return;
            }
            String quocgia=txtquocgia.getText().trim();
            DiaDiemDTO dd=new DiaDiemDTO(ten, diachi, quocgia);
            
            if(sua){
                if(bus.suaDiaDiem(dd,tenDiaDiemCu)){
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }else{
               if(bus.timDiaDiem(ten)!=null){
                   JOptionPane.showMessageDialog(this, "Lỗi tên địa điểm đã tồn tại");
                   return;
               }
               
               if(bus.themDiaDiem(dd)){
                   resetField();
                   JOptionPane.showMessageDialog(this, "Thêm thành công");
               }else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        resetField();
    }

    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnreset;
    private javax.swing.JLabel lbngay;
    private javax.swing.JLabel lbten;
    private javax.swing.JLabel lbtongchi;
    private javax.swing.JTextField  txtdiachi;
    private javax.swing.JTextField txttendd;
    private javax.swing.JTextField txtquocgia;

}
