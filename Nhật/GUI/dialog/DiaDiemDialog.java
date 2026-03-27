package org.example.gui.dialog;

import org.example.bus.*;
import org.example.dto.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiaDiemDialog extends JDialog {
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
        lbtongchi = new JLabel();
        txttendd = new JTextField();
        txtquocgia = new JTextField();
        lbten = new JLabel();
        lbngay = new JLabel();
        btnluu = new JButton();
        btnreset = new JButton();
        txtdiachi = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lbtongchi.setText("Quốc gia");

        txtquocgia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtquocgiaActionPerformed(evt);
            }
        });

        lbten.setText("Tên địa điểm");

        lbngay.setText("Địa chỉ");

        btnluu.setText("Lưu");
        btnluu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnluu, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lbten, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbngay, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbtongchi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txttendd)
                                        .addComponent(txtquocgia)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnreset)
                                                .addGap(9, 9, 9))
                                        .addComponent(txtdiachi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(68, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbten)
                                        .addComponent(txttendd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbngay)
                                        .addComponent(txtdiachi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbtongchi)
                                        .addComponent(txtquocgia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnluu)
                                        .addComponent(btnreset))
                                .addGap(89, 89, 89))
        );

        pack();
    }

    private void txtquocgiaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtquocgiaActionPerformed
        // TODO add your handling code here:
    }

    private void btnluuActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
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

    private void btnresetActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        resetField();
    }

    private JButton btnluu;
    private JButton btnreset;
    private JLabel lbngay;
    private JLabel lbten;
    private JLabel lbtongchi;
    private JTextField  txtdiachi;
    private JTextField txttendd;
    private JTextField txtquocgia;
}