
package org.example.gui.dialog;

import java.util.ArrayList;
import org.example.bus.CTHoaDonBUS;
import org.example.bus.HoaDonBUS;
import org.example.bus.KhachHangBUS;
import org.example.dto.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.example.dto.CTietHDDTO;

public class NhapCTHD extends javax.swing.JDialog {
    private String mahd;
    private int soluong;
    private CTHoaDonBUS bus;
    public HoaDonBUS hdbus;

    public NhapCTHD(java.awt.Frame parent, boolean modal,String mahd,int soluong) {
        super(parent, modal);
        this.soluong=soluong;
        this.mahd=mahd;
        bus=new CTHoaDonBUS();
        hdbus=new HoaDonBUS();
        this.setTitle("Nhập chi tiết hóa đơn");
        this.setLocationRelativeTo(null);
        initComponents();
        loaddata();
        setupComboBoxKhachHang();
    }
    
    public NhapCTHD(String ma) {
        this.soluong=soluong;
        this.mahd=mahd;
        bus=new org.example.bus.CTHoaDonBUS();
        this.setTitle("Chi tiết hóa đơn của hóa đơn: "+ma);
        this.setLocationRelativeTo(null);
        initComponents();
        btnluu.setVisible(false);
        tblnhapct.setDefaultEditor(Object.class, null);
        loaddata(ma);
    }

    private void setupComboBoxKhachHang() {
    JComboBox<String> cbKhachHang = new JComboBox<>();
    
    KhachHangBUS khBus = new KhachHangBUS();
    if (KhachHangBUS.dsKH == null) {
        khBus.docDSKH();
    }
    
    for (KhachHangDTO kh : KhachHangBUS.dsKH) {
        cbKhachHang.addItem(kh.getMaKH());
    }
    
    TableColumn khColumn = tblnhapct.getColumnModel().getColumn(1);
    khColumn.setCellEditor(new DefaultCellEditor(cbKhachHang));
    }

    private void loaddata(){
        DefaultTableModel model=(DefaultTableModel) tblnhapct.getModel();
        
        model.setRowCount(0);
        
        for(int i=0;i<soluong;i++){
            model.addRow(new Object[]{mahd,"",bus.laygia(mahd)});
        }
    }
    private void loaddata(String mahd){
        DefaultTableModel model=(DefaultTableModel) tblnhapct.getModel();
        
        model.setRowCount(0);
        
        ArrayList<CTietHDDTO> ds=bus.getDstheoma(mahd);
        for(CTietHDDTO cthddto: ds){
            model.addRow(new Object[]{
                cthddto.getMaHD(),cthddto.getMaKHDi(),String.valueOf(cthddto.getGiaVe())
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhapct = new javax.swing.JTable();
        btnluu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblnhapct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã khách hàng", "Giá vé"
            }
        ));
        tblnhapct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblnhapctKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhapct);

        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnluu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        
        DefaultTableModel model=(DefaultTableModel) tblnhapct.getModel();
        
        if(tblnhapct.isEditing()){
            tblnhapct.getCellEditor().stopCellEditing();
        }
        
        boolean loi=false;
        String mahd =model.getValueAt(0, 0).toString().trim();

        bus.capNhatSoluong(soluong,hdbus.timHd(mahd).getMaKHTour());
        for(int i=0;i<soluong;i++){
            mahd =model.getValueAt(i, 0).toString().trim();
            String makh =model.getValueAt(i, 1).toString().trim();
            float giave= Float.parseFloat(model.getValueAt(i, 2).toString().trim());
            CTietHDDTO cthd=new CTietHDDTO(mahd,makh,giave);
            if(!bus.themCTietHd(cthd)){
                loi=true;
                break;
        }
    }
        if(!loi){
        JOptionPane.showMessageDialog(this, "Lưu thành công");
        this.dispose();
    }else{
        JOptionPane.showMessageDialog(this, "Lỗi");
    }
    }

    private void tblnhapctKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }

    private javax.swing.JButton btnluu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblnhapct;
}
