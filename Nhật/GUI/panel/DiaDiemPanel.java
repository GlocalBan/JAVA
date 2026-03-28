package org.example.gui.panel;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import com.toedter.calendar.JDateChooser;
import org.example.gui.dialog.DiaDiemDialog;
import org.example.dao.*;
import org.example.bus.*;
import org.example.dto.*;
import org.example.gui.helper.ExcelHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
                    dd.getMaDiaDiem(), dd.getTenDiaDiem(), dd.getdiachi(),dd.getQuocGia()});
        }
    }
    private void loadData(){
        model.setRowCount(0);

        ArrayList<DiaDiemDTO> ds = bus.getDs();
        if(ds==null) return;
        for(DiaDiemDTO dd: ds){
            model.addRow(new Object[]{
                    dd.getMaDiaDiem() ,dd.getTenDiaDiem(),dd.getdiachi(),dd.getQuocGia()});
        }
    }

    private void loadData(String ma){
        model.setRowCount(0);

        ArrayList<DiaDiemDTO> ds = bus.timdd(ma);
        if(ds==null) return;
        for(DiaDiemDTO dd: ds){
            model.addRow(new Object[]{
                    dd.getMaDiaDiem(), dd.getTenDiaDiem(),dd.getdiachi(),dd.getQuocGia()});
        }
    }

    private void initComponents() {
        pnlheader = new JPanel();
        lbname = new JLabel();
        pnlsearch = new JPanel();
        lbtim = new JLabel();
        cbtim = new JComboBox<>();
        txttendd = new JTextField();
        txtdate = new com.toedter.calendar.JDateChooser();
        pnlfooter = new JPanel();
        btnthem = new JButton();
        btnxoa = new JButton();
        btnsua = new JButton();
        btnreset = new JButton();
        btnxuat = new JButton();
        pnltable = new JPanel();
        jScrollPane2 = new JScrollPane();
        tbldd = new JTable();

        setLayout(new BorderLayout());

        pnlheader.setLayout(new BorderLayout());

        lbname.setFont(new Font("Arial", 0, 18)); // NOI18N
        lbname.setHorizontalAlignment(SwingConstants.CENTER);
        lbname.setText("Quản lý địa điểm");
        pnlheader.add(lbname, BorderLayout.CENTER);

        lbtim.setText("Tìm theo:");
        pnlsearch.add(lbtim);

        cbtim.setModel(new DefaultComboBoxModel<>(new String[] { "Tên địa điểm", "Địa chỉ", "Quốc gia" }));
        cbtim.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                cbTimItemStateChanged(evt);
            }
        });
        pnlsearch.add(cbtim);

        txttendd.setColumns(20);
        txttendd.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtTenDdKeyReleased(evt);
            }
        });
        pnlsearch.add(txttendd);

        txtdate.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                txtDatePropertyChange(evt);
            }
        });
        pnlsearch.add(txtdate);

        pnlheader.add(pnlsearch, BorderLayout.PAGE_END);

        add(pnlheader, BorderLayout.PAGE_START);

        them();
        pnlfooter.add(btnthem);

        xoa();
        pnlfooter.add(btnxoa);

        sua();
        pnlfooter.add(btnsua);

        lamMoi();
        pnlfooter.add(btnreset);

        xuatExcel();
        pnlfooter.add(btnxuat);

        add(pnlfooter, BorderLayout.PAGE_END);

        pnltable.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
        pnltable.setLayout(new BorderLayout());

        tbldd.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Mã địa điểm" ,"Tên địa điểm", "Địa chỉ", "Quốc gia"
                }
        ));
        tbldd.setPreferredSize(null);
        tbldd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblDdMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldd);

        pnltable.add(jScrollPane2, BorderLayout.CENTER);

        add(pnltable, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));// Trong jpBtn panel

        return btn;
    }

    private void them(){
        btnthem = createBtn("Thêm", UIColors.ADD);
        btnthem.addActionListener(v -> {
            DiaDiemDialog ddd=new DiaDiemDialog();
            ddd.setModal(true);
            ddd.setVisible(true);
            loadData();
        });
    }

    private void sua(){
        btnsua = createBtn("Sửa", UIColors.EDIT);
        btnxoa.setEnabled(false);
        btnsua.addActionListener(v -> {
            int row=tbldd.getSelectedRow();
            if(row!=-1){
                String ten=tbldd.getValueAt(row, 0).toString().trim();
                DiaDiemDTO dd =bus.timDiaDiem(ten);
                DiaDiemDialog ddd=new DiaDiemDialog(dd);
                ddd.setModal(true);
                ddd.setVisible(true);
            }
        });
    }

    private void xoa(){
        btnxoa = createBtn("Xóa", UIColors.DELETE);
        btnxoa.setEnabled(false);
        btnxoa.addActionListener(v -> {
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
        });
    }

    private void lamMoi(){
        btnreset = createBtn("Làm mới", UIColors.REFRESH);
        btnreset.addActionListener(v -> {
            loadData();
        });
    }

    private void xuatExcel(){
        btnxuat = createBtn("Xuất excel", UIColors.EXPORT_EXCEL);
        btnxuat.addActionListener(v -> {
            ExcelHelper.xuatExcel(tbldd, this, "Danh sach hoa don");
        });
    }

    private void txtTenDdKeyReleased(KeyEvent evt) {
        // TODO add your handling code here:
        String ten = txttendd.getText().trim();
        loadData( ten);
    }

    private void cbTimItemStateChanged(ItemEvent evt) {
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

    private void txtDatePropertyChange(PropertyChangeEvent evt) {
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

    private void tblDdMouseClicked(MouseEvent evt) {
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

    private JButton btnreset, btnsua, btnthem, btnxoa, btnxuat;

    private JComboBox<String> cbtim;

    private JScrollPane jScrollPane2;

    private JLabel lbname, lbtim;

    private JPanel pnlfooter, pnlheader, pnltable, pnlsearch;

    private JTable tbldd;

    private JDateChooser txtdate;

    private JTextField txttendd;
}