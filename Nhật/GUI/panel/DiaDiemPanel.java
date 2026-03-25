package org.example.gui.panel;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import org.example.gui.dialog.DiaDiemDialog;
import org.example.dao.*;
import org.example.bus.*;
import org.example.dto.*;

import org.example.helper.*;


public class DiaDiemPanel extends JPanel {
    private DefaultTableModel model;
    private DiaDiemBUS bus;
    
    public DiaDiemPanel() {
        initComponents();
        bus=new DiaDiemBUS();
        txtdate.setVisible(false);
        model = (DefaultTableModel) tbldd.getModel();
        loadData();
    }

    private void loadData(java.util.Date ngay){
        model.setRowCount(0);

        ArrayList<DiaDiemDTO> ds =bus.getDstheongay(ngay);
        if(ds==null) return;
        for(DiaDiemDTO dd: ds){
            model.addRow(new Object[]{
                    dd.getTenDiaDiem(), dd.getdiachi(),dd.getQuocGia()});
        }
    }
    private void loadData(){
        model.setRowCount(0);

        ArrayList<DiaDiemDTO> ds = bus.getDs();
        if(ds==null) return;
        for(DiaDiemDTO dd: ds){
            model.addRow(new Object[]{
                    dd.getTenDiaDiem(),dd.getdiachi(),dd.getQuocGia()});
        }
    }


    private void loadData(String ma){
        model.setRowCount(0);

        ArrayList<DiaDiemDTO> ds = bus.timdd(ma);
        if(ds==null) return;
        for(DiaDiemDTO dd: ds){
            model.addRow(new Object[]{
                    dd.getTenDiaDiem(),dd.getdiachi(),dd.getQuocGia()});
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlheader = new javax.swing.JPanel();
        lbname = new javax.swing.JLabel();
        pnlsearch = new javax.swing.JPanel();
        lbtim = new javax.swing.JLabel();
        cbtim = new javax.swing.JComboBox<>();
        txttendd = new javax.swing.JTextField();
        txtdate = new com.toedter.calendar.JDateChooser();
        pnlfooter = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnxuat = new javax.swing.JButton();
        pnltable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldd = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlheader.setLayout(new java.awt.BorderLayout());

        lbname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbname.setText("Quản lý địa điểm");
        pnlheader.add(lbname, java.awt.BorderLayout.CENTER);

        lbtim.setText("Tìm theo:");
        pnlsearch.add(lbtim);

        cbtim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên địa điểm", "Ngày thực hiện" }));
        cbtim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimItemStateChanged(evt);
            }
        });
        pnlsearch.add(cbtim);

        txttendd.setColumns(20);
        txttendd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenDdKeyReleased(evt);
            }
        });
        pnlsearch.add(txttendd);

        txtdate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDatePropertyChange(evt);
            }
        });
        pnlsearch.add(txtdate);

        pnlheader.add(pnlsearch, java.awt.BorderLayout.PAGE_END);

        add(pnlheader, java.awt.BorderLayout.PAGE_START);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });
        pnlfooter.add(btnthem);

        btnxoa.setText("Xóa");
        btnxoa.setEnabled(false);
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });
        pnlfooter.add(btnxoa);

        btnsua.setText("Sửa");
        btnsua.setEnabled(false);
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlfooter.add(btnsua);

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlfooter.add(btnreset);

        btnxuat.setText("Xuất excel");
        btnxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatActionPerformed(evt);
            }
        });
        pnlfooter.add(btnxuat);

        add(pnlfooter, java.awt.BorderLayout.PAGE_END);

        pnltable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        pnltable.setLayout(new java.awt.BorderLayout());

        tbldd.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Tên địa điểm", "Ngày thực hiện", "Tổng chi"
                }
        ));
        tbldd.setPreferredSize(null);
        tbldd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDdMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldd);

        pnltable.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(pnltable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnxuatActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ExcelHelper.xuatExcel(tbldd, this, "Danh sach hoa don");
    }

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        DiaDiemDialog ddd=new DiaDiemDialog();
        ddd.setModal(true);
        ddd.setVisible(true);
        loadData();
    }

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int row=tbldd.getSelectedRow();

        if(row==-1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn địa điểm cần xóa");
            return;
        }
        String tendd =model.getValueAt(row, 0).toString();
        DiaDiemDTO dd=bus.timDiaDiem(tendd); if(dd==null){
            JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy");
            return;
        }
        if(JOptionPane.showConfirmDialog(this, "Xóa địa điểm?","Xác nhận",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            if(bus.xoaDiaDiem(dd)){
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadData();
            }else{
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        loadData();
    }

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int row=tbldd.getSelectedRow();
        if(row!=-1){
            String ten=tbldd.getValueAt(row, 0).toString().trim();
            DiaDiemDTO dd =bus.timDiaDiem(ten);
            DiaDiemDialog ddd=new DiaDiemDialog(dd);
            ddd.setModal(true);
            ddd.setVisible(true);
        }
    }

    private void txtTenDdKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        String ten = txttendd.getText().trim();
        loadData( ten);
    }

    private void cbTimItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        String chose=cbtim.getSelectedItem().toString().trim();
        if(chose.equals("Ngày thực hiện")){
            txttendd.setVisible(false);
            txtdate.setVisible(true);
        }
        else{
            txttendd.setVisible(true);
            txtdate.setVisible(false);
        }
    }

    private void txtDatePropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            java.util.Date ngay = txtdate.getDate();
            if (ngay != null) {
                loadData(ngay);
            } else {
                loadData();
            }
        }
    }

    private void tblDdMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int row=tbldd.getSelectedRow();
        if(row!=-1){
            btnsua.setEnabled(true);
            btnxoa.setEnabled(true);
            int cf=JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất Excel dòng này không?");
            if(cf==JOptionPane.YES_OPTION){
                ExcelHelper.xuatExcel1Dong(tbldd, row, btnreset, "Địa điểm");
            }
        }
    }

    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxuat;
    private javax.swing.JComboBox<String> cbtim;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbname;
    private javax.swing.JLabel lbtim;
    private javax.swing.JPanel pnlfooter;
    private javax.swing.JPanel pnlheader;
    private javax.swing.JPanel pnlsearch;
    private javax.swing.JPanel pnltable;
    private javax.swing.JTable tbldd;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JTextField txttendd;
}
