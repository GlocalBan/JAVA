
package GUI.dialog;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;


import DAO._TourDAO;
import DTO.KMHDDTO;
import DTO.KMTourDTO;
import DTO._TourDTO;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;


import java.awt.CardLayout;
import java.time.LocalDate;

public class CTrinhKMDialog extends javax.swing.JDialog {
    private BUS.CTrinhKMBUS bus;
    private boolean isEdit = false;
    private CardLayout card;

    
    public CTrinhKMDialog() {
        initComponents();
        card = (CardLayout) pnlSwitch.getLayout();
        this.bus = new BUS.CTrinhKMBUS();
        this.setTitle("Chương trình khuyến mãi");
        this.setLocationRelativeTo(null);
       txtMaCTKM.setInputVerifier(new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            String ma = txtMaCTKM.getText().trim();
            if (isEdit) return true; // Không kiểm tra khi sửa
            if (!ma.matches("^KM\\d{2}$")) {
                JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Mã phải có định dạng KMxx (x là số)");
                return false;
            }
            if (bus.timCTrinhKM(ma) != null) {
                JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Mã đã tồn tại");
                return false;
            }
            return true;
        }
    });
        txtTenctkm.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String ten=txtTenctkm.getText().trim();
                if(ten.isEmpty()){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Tên chương trình khuyến mãi không được để trống");
                    return false;
                }
                return true;
            }
        });
        
        txtChietkhau.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String chietkhau=txtChietkhau.getText().trim();
                try{
                    double ck=Double.parseDouble(chietkhau);
                    if(ck<0||ck>100){
                        JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số từ 0 đến 100");
                        return false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số hợp lệ");
                    return false;
                }
                return true;
            }
        });

    }

        public CTrinhKMDialog(BUS.CTrinhKMBUS bus) {
        this.bus = bus;
        initComponents();
        card = (CardLayout) pnlSwitch.getLayout();
        this.setTitle("Chương trình khuyến mãi");
        this.setLocationRelativeTo(null);
       txtMaCTKM.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String ma = txtMaCTKM.getText().trim();
                if (!ma.matches("^KM\\d{2}$")) {
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Mã phải có định dạng KMxx (x là số)");
                    return false;
                }
                if (bus.timCTrinhKM(ma) != null) {
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Mã đã tồn tại");
                    return false;
                }
                return true;
            }
        });
        txtTenctkm.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String ten=txtTenctkm.getText().trim();
                if(ten.isEmpty()){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Tên chương trình khuyến mãi không được để trống");
                    return false;
                }
                return true;
            }
        });
        
        txtChietkhau.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String chietkhau=txtChietkhau.getText().trim();
                try{
                    double ck=Double.parseDouble(chietkhau);
                    if(ck<0||ck>100){
                        JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số từ 0 đến 100");
                        return false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số hợp lệ");
                    return false;
                }
                return true;
            }
        });

    }

    public CTrinhKMDialog(BUS.CTrinhKMBUS bus,DTO.CTrinhKMDTO ct) {
        initComponents();
        card = (CardLayout) pnlSwitch.getLayout();
        this.setTitle("Chương trình khuyến mãi");
        this.setLocationRelativeTo(null);
        this.bus = bus;
        isEdit = true;
        txtMaCTKM.setText(ct.getMaKM());
        txtMaCTKM.setEnabled(false);   // khóa sửa mã
        txtTenctkm.setText(ct.getTenKM());
        txtNgayBD.setDate(ct.getNgayBD() != null ? java.sql.Date.valueOf(ct.getNgayBD()) : null);
        txtNgayKT.setDate(ct.getNgayKT() != null ? java.sql.Date.valueOf(ct.getNgayKT()) : null);
        if (ct.getHinhThucKM()) {          // KMHD
            rdoHd.setSelected(true);
            card.show(pnlSwitch, "card2");
            if (ct instanceof KMHDDTO) {
                txtDieukienapdung.setText(String.valueOf(((KMHDDTO) ct).getTongTienApDung()));
            }
        } else {                           // KMTour
            rdoTour.setSelected(true);
            loadTourTable();
            card.show(pnlSwitch, "card3");
            if(ct instanceof KMTourDTO){

                KMTourDTO km = (KMTourDTO) ct;
                setSelectedTour(km.getDsMaTour());

            }
        }
        txtChietkhau.setText(String.valueOf(ct.getChietKhau()));
        txtGhichu.setText(ct.getGhiChu());
        txtMaCTKM.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String ma=txtMaCTKM.getText().trim();
                if(!ma.matches("^KM\\d{2}$")|| bus.timCTrinhKM(ma)==null){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Mã chương trình khuyến mãi phải có định dạng KMxx (x là số) và không được trùng");
                    return false;
                }
                return true;
            }
        });
        txtTenctkm.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String ten=txtTenctkm.getText().trim();
                if(ten.isEmpty()){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Tên chương trình khuyến mãi không được để trống");
                    return false;
                }
                return true;
            }
        });
        
        txtChietkhau.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input){
                String chietkhau=txtChietkhau.getText().trim();
                try{
                    double ck=Double.parseDouble(chietkhau);
                    if(ck<0||ck>100){
                        JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số từ 0 đến 100");
                        return false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(CTrinhKMDialog.this, "Chiết khấu phải là số hợp lệ");
                    return false;
                }
                return true;
            }
        });


    }

  

    public void resetForm(){
        txtMaCTKM.setText("");
        txtTenctkm.setText("");
        txtNgayBD.setDate(new java.util.Date());
        txtNgayKT.setDate(new java.util.Date());
        rdoHd.setSelected(true);
        txtChietkhau.setText("");
        txtGhichu.setText("");
    }


    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtMaCTKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenctkm = new javax.swing.JTextField();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        rdoHd = new javax.swing.JRadioButton();
        rdoTour = new javax.swing.JRadioButton();
        ButtonGroup group= new ButtonGroup();
        group.add(rdoHd);
        group.add(rdoTour);
        rdoHd.setSelected(true);
        jLabel6 = new javax.swing.JLabel();
        txtChietkhau = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGhichu = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        pnlSwitch = new javax.swing.JPanel();
        HOADON = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDieukienapdung = new javax.swing.JTextField();
        TOUR = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTour = new javax.swing.JTable();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setText("Mã chương trình khuyến mãi");

        txtMaCTKM.addActionListener(this::txtMaCTKMActionPerformed);

        jLabel2.setText("Tên chương trình khuyến mãi");

        txtTenctkm.addActionListener(this::txtTenctkmActionPerformed);

        jLabel3.setText("Ngày bắt đầu");

        jLabel4.setText("Ngày kết thúc");

        jLabel5.setText("Hình thức khuyến mãi");

        rdoHd.setText("Khuyến mãi hóa đơn");
        rdoHd.addActionListener(this::rdoHdActionPerformed);

        rdoTour.setText("Khuyến mãi tour");
        rdoTour.addActionListener(this::rdoTourActionPerformed);

        jLabel6.setText("Chiết khấu");

        txtChietkhau.addActionListener(this::txtChietkhauActionPerformed);

        jLabel7.setText("Ghi chú");

        txtGhichu.addActionListener(this::txtGhichuActionPerformed);

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(this::btnLuuActionPerformed);

        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(this::btnHuyActionPerformed);

        pnlSwitch.setLayout(new java.awt.CardLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tổng tiền áp dụng tối thiểu:");

        txtDieukienapdung.addActionListener(this::txtDieukienapdungActionPerformed);

        javax.swing.GroupLayout HOADONLayout = new javax.swing.GroupLayout(HOADON);
        HOADON.setLayout(HOADONLayout);
        HOADONLayout.setHorizontalGroup(
            HOADONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOADONLayout.createSequentialGroup()
                .addGroup(HOADONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HOADONLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HOADONLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(txtDieukienapdung, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        HOADONLayout.setVerticalGroup(
            HOADONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOADONLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDieukienapdung, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pnlSwitch.add(HOADON, "card2");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Danh sách tour áp dụng");

        tblTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Mã Tour", "Tên Tour", "Số ngày", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTour);

        javax.swing.GroupLayout TOURLayout = new javax.swing.GroupLayout(TOUR);
        TOUR.setLayout(TOURLayout);
        TOURLayout.setHorizontalGroup(
            TOURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
            .addGroup(TOURLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        TOURLayout.setVerticalGroup(
            TOURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TOURLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSwitch.add(TOUR, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenctkm, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(rdoHd, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(rdoTour, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGhichu, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChietkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addGap(126, 126, 126)
                .addComponent(btnHuy)
                .addGap(32, 32, 32))
            .addComponent(pnlSwitch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenctkm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(rdoTour, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChietkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhichu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSwitch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnReset)
                    .addComponent(btnHuy))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public void loadTourTable() {

        DefaultTableModel model = (DefaultTableModel) tblTour.getModel();
        model.setRowCount(0);

        _TourDAO dao = new _TourDAO();
        ArrayList<_TourDTO> list = dao.getAllTours();

        for (_TourDTO t : list) {

            model.addRow(new Object[]{
                    false,                 // checkbox chọn tour
                    t.getMaTour(),
                    t.getTen(),
                    t.getSoNgay(),
                    t.getDonGia()
            });
        }
    }
    public ArrayList<String> getSelectedTour(){

        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < tblTour.getRowCount(); i++){

            Boolean checked = (Boolean) tblTour.getValueAt(i,0);

            if(checked != null && checked){
                list.add(tblTour.getValueAt(i,1).toString());
            }
        }

        return list;
    }
    public void setSelectedTour(ArrayList<String> dsTour){

        for(int i=0;i<tblTour.getRowCount();i++){

            String maTour = tblTour.getValueAt(i,1).toString();

            if(dsTour.contains(maTour)){
                tblTour.setValueAt(true,i,0);
            }
        }
    }

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtMaCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCTKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCTKMActionPerformed

    private void txtTenctkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenctkmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenctkmActionPerformed

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void txtNgayKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKTActionPerformed
    
    private void txtChietkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChietkhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChietkhauActionPerformed

    private void txtGhichuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhichuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhichuActionPerformed

    private void txtMaCTKMInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtMaCTKMInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCTKMInputMethodTextChanged

    private void txtTenctkmInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTenctkmInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenctkmInputMethodTextChanged

    private void txtNgayBDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtNgayBDInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDInputMethodTextChanged

    private void txtNgayKTInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtNgayKTInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKTInputMethodTextChanged

    private void txtChietkhauInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtChietkhauInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChietkhauInputMethodTextChanged

    private void txtGhichuInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGhichuInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhichuInputMethodTextChanged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        
    }                                             

    private void rdoHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHdActionPerformed
        // TODO add your handling code here:
        rdoHd.setSelected(true);
        card.show(pnlSwitch, "card2");
        JOptionPane.showMessageDialog(this, "Bạn đã chọn hình thức khuyến mãi hóa đơn");
    }//GEN-LAST:event_rdoHdActionPerformed

    private void rdoTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTourActionPerformed
        // TODO add your handling code here:
        rdoTour.setSelected(true);
        card.show(pnlSwitch, "card3");
        loadTourTable();
        JOptionPane.showMessageDialog(this, "Bạn đã chọn hình thức khuyến mãi tour");

    }//GEN-LAST:event_rdoTourActionPerformed

    private void btnHinhthucStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnHinhthucStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHinhthucStateChanged


    private void txtDieukienapdungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDieukienapdungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDieukienapdungActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
        String ma = txtMaCTKM.getText().trim();
        String ten = txtTenctkm.getText().trim();
        Date ngaybd = txtNgayBD.getDate();
        Date ngaykt = txtNgayKT.getDate();
        LocalDate ngayBD =Helper.DateHelper.toLocalDateFromUtil(ngaybd);
        LocalDate ngayKT =Helper.DateHelper.toLocalDateFromUtil(ngaykt);
        LocalDate today = LocalDate.now();

        if (ngaybd == null || ngaykt == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc");
            return;
        }

        if (ngayBD.isBefore(today)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng hôm nay");
            return;
        }

        if (!ngayKT.isAfter(ngayBD)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            return;
}
        float chietkhau = Float.parseFloat(txtChietkhau.getText().trim());
        String ghichu = txtGhichu.getText().trim();

        DTO.CTrinhKMDTO ct;

        if (rdoTour.isSelected()) {
                ArrayList<String> dsTour = getSelectedTour();
                ct = new DTO.KMTourDTO(
                    ma, ten, ngayBD, ngayKT, false, chietkhau, ghichu, dsTour
                );
            } else { // KM Hóa đơn
                float dieuKien = Float.parseFloat(txtDieukienapdung.getText().trim());
                ct = new DTO.KMHDDTO(
                    ma, ten, ngayBD, ngayKT, true, chietkhau, ghichu,  dieuKien
                );
            }

            if (isEdit) {
                if (bus.suaCTrinhKM(ct)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                    return;
                }
            } else {
                if (bus.themCTrinhKM(ct)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại (có thể trùng mã)");
                    return;
                }
            }

            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho chiết khấu và điều kiện áp dụng");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLuuActionPerformed

        

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        resetForm();
    }
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HOADON;
    private javax.swing.JPanel TOUR;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlSwitch;
    private javax.swing.JRadioButton rdoHd;
    private javax.swing.JRadioButton rdoTour;
    private javax.swing.JTable tblTour;
    private javax.swing.JTextField txtChietkhau;
    private javax.swing.JTextField txtDieukienapdung;
    private javax.swing.JTextField txtGhichu;
    private javax.swing.JTextField txtMaCTKM;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtTenctkm;
    // End of variables declaration//GEN-END:variables
}
