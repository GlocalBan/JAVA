
package org.example.gui.panel;

import org.example.dto.*;
import org.example.bus.*;
import org.example.gui.dialog.*;
import org.example.helper.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HoaDonPanel extends javax.swing.JPanel {
    private HoaDonBUS bus;
    private DefaultTableModel model;

    public HoaDonPanel() {
        initComponents();
        bus=new HoaDonBUS();
        model=(DefaultTableModel) tblhoadon.getModel();
        txtday.setVisible(false);
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        loadData();;
    }

    private void loadData(){
     model.setRowCount(0);
     bus.docDs();
     
     ArrayList<HoaDonDTO> ds = HoaDonBUS.getDs();
     if(ds==null) return;
     for(HoaDonDTO hd: ds){
         model.addRow(new Object[]{
             hd.getMaHD(),hd.getMaKHTour(),hd.getMaKHDat(),hd.getMaNV(),DateHelper.toString(hd.getNgay()),hd.getSoluong(),String.format("%.0f",hd.getTongTien())
         });
     }
    }
    private void loadData(java.util.Date ngay){
     model.setRowCount(0);
     bus.docDs();
     ArrayList<HoaDonDTO> ds =bus.getHDtheongay(ngay);
     if(ds==null) return;
     for(HoaDonDTO hd: ds){
         model.addRow(new Object[]{
             hd.getMaHD(),hd.getMaKHTour(),hd.getMaKHDat(),hd.getMaNV(),DateHelper.toString(hd.getNgay()),hd.getSoluong(),String.format("%.0f",hd.getTongTien())
         });
     }
    }
    private void loadData(String loai,String key){
     model.setRowCount(0);
     bus.docDs();
     ArrayList<HoaDonDTO> ds =bus.timNangcao(loai, key);
     if(ds==null) return;
     for(HoaDonDTO hd: ds){
         model.addRow(new Object[]{
             hd.getMaHD(),hd.getMaKHTour(),hd.getMaKHDat(),hd.getMaNV(),DateHelper.toString(hd.getNgay()),hd.getSoluong(),hd.getTongTien()
         });
     }
    }
    
    

    private void initComponents() {

        pnlheader = new javax.swing.JPanel();
        lbname = new javax.swing.JLabel();
        pnlsearch = new javax.swing.JPanel();
        lbtim = new javax.swing.JLabel();
        cbtim = new javax.swing.JComboBox<>();
        txttim = new javax.swing.JTextField();
        txtday = new com.toedter.calendar.JDateChooser();
        pnlfooter = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnchitiet = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnxuat = new javax.swing.JButton();
        pnltable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlheader.setLayout(new java.awt.BorderLayout());

        lbname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbname.setText("Quản lý hóa đơn");
        pnlheader.add(lbname, java.awt.BorderLayout.CENTER);

        lbtim.setText("Tìm theo:");
        pnlsearch.add(lbtim);

        cbtim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hóa đơn", "Mã kế hoạch tour", "Mã khách hàng đặt", "Mã nhân viên", "Ngày" }));
        cbtim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtimItemStateChanged(evt);
            }
        });
        pnlsearch.add(cbtim);

        txttim.setColumns(10);
        txttim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimActionPerformed(evt);
            }
        });
        txttim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimKeyReleased(evt);
            }
        });
        pnlsearch.add(txttim);

        txtday.setPreferredSize(new java.awt.Dimension(90, 22));
        txtday.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtdayPropertyChange(evt);
            }
        });
        txtday.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdayKeyReleased(evt);
            }
        });
        pnlsearch.add(txtday);

        pnlheader.add(pnlsearch, java.awt.BorderLayout.PAGE_END);

        add(pnlheader, java.awt.BorderLayout.NORTH);

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

        btnsua.setText("Chỉnh sửa");
        btnsua.setEnabled(false);
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });
        pnlfooter.add(btnsua);

        btnchitiet.setText("Xem chi tiết");
        btnchitiet.setEnabled(false);
        btnchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitietActionPerformed(evt);
            }
        });
        pnlfooter.add(btnchitiet);

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
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

        pnltable.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnltable.setLayout(new java.awt.BorderLayout());

        tblhoadon.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã kế hoạch tour", "Mã khách hàng đặt", "Mã nhân viên", "Ngày", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhoadon.setRowSelectionAllowed(true);
        tblhoadon.setColumnSelectionAllowed(false);
        tblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhoadon);
        tblhoadon.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblhoadon.getColumnModel().getColumnCount() > 0) {
            tblhoadon.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblhoadon.getColumnModel().getColumn(6).setPreferredWidth(120);
        }

        pnltable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(pnltable, java.awt.BorderLayout.CENTER);
    }

    private void txttimActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txttimKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        String text=txttim.getText().trim();
        String loai =cbtim.getSelectedItem().toString().trim();
        loadData(loai, text);
    }

    private void cbtimItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        String item=cbtim.getSelectedItem().toString();
        if(item.equals("Ngày")){
                    txttim.setVisible(false);
                    txtday.setVisible(true);
        }else{
                    txttim.setVisible(true);
                    txtday.setVisible(false); 
        }
    }

    private void txtdayKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        
    }

    private void txtdayPropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:
        java.util.Date ngay = txtday.getDate();
        loadData(ngay); 
    }

    private void tblhoadonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int row=tblhoadon.getSelectedRow();
        String ma =tblhoadon.getValueAt(row, 0).toString().trim();
        if(row!=-1){
            btnsua.setEnabled(true);
            btnxoa.setEnabled(true);
            btnchitiet.setEnabled(true);
            if(evt.getClickCount()==2){
                int cf=JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất excel dòng này không?");
                if(cf==JOptionPane.YES_OPTION){
                    ExcelHelper.xuatExcel1Dong(tblhoadon, row, btnreset, ma);
                }
            }
        }
    }

    private void btnxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatActionPerformed
        // TODO add your handling code here:
        ExcelHelper.xuatExcel(tblhoadon, this, "Danh sách hóa đơn");
    }

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        loadData();
    }

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:

        int row =tblhoadon.getSelectedRow();

        if(row==-1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        }
        String ma =tblhoadon.getValueAt(row, 0).toString().trim();
        HoaDonDTO hd=bus.timHd(ma);
        if(hd!=null){
            org.example.gui.dialog.HoaDonDialog hdd=new org.example.gui.dialog.HoaDonDialog(hd);
            hdd.setModal(true);
            hdd.setVisible(true);
            loadData();
        }else{
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu hóa đơn");
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        try{
            int row =tblhoadon.getSelectedRow();

            if(row==-1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xóa");
                return;
            }
            int cf =JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?","Xác nhận",JOptionPane.YES_NO_OPTION);
            if(cf==JOptionPane.YES_OPTION){
                String ma = tblhoadon.getValueAt(row, 0).toString();
                HoaDonDTO hd=bus.timHd(ma);

                if(bus.xoaHoaDon(ma)){
                    DefaultTableModel model=(DefaultTableModel) tblhoadon.getModel();

                    model.removeRow(row);
                    loadData();
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        HoaDonDialog hdd=new org.example.gui.dialog.HoaDonDialog();
        hdd.setModal(true);
        hdd.setVisible(true);
        loadData();
    }

    private void btnchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietActionPerformed
        // TODO add your handling code here:
        int row=tblhoadon.getSelectedRow();
        String ma =tblhoadon.getValueAt(row, 0).toString().trim();
        if(row!=-1){
        btnchitiet.setEnabled(true);
        NhapCTHD nhap=new NhapCTHD(ma);
        nhap.setModal(true);
        nhap.setVisible(true);
        }
    }

    private javax.swing.JButton btnchitiet;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxuat;
    private javax.swing.JComboBox<String> cbtim;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbname;
    private javax.swing.JLabel lbtim;
    private javax.swing.JPanel pnlfooter;
    private javax.swing.JPanel pnlheader;
    private javax.swing.JPanel pnlsearch;
    private javax.swing.JPanel pnltable;
    private javax.swing.JTable tblhoadon;
    private com.toedter.calendar.JDateChooser txtday;
    private javax.swing.JTextField txttim;
}
