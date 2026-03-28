package org.example.gui.dialog;

import org.example.bus.NhanVienBUS;
import org.example.bus._KeHoachTourBUS;
import org.example.dto.NhanVienDTO;
import org.example.dto._KeHoachTourDTO;
import org.example.dto._TourDTO;
import org.example.gui.panel.UIColors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class _KeHoachTourDialog extends JDialog {
    // define jlabel and txt
    private JLabel jlbMaKHTour, jlbNgayKhoiHanh, jlbNgayKetThuc, jlbTongSoVe, jlbTongChi, jlbTongThu, jlbMaTour, jlbMaNVHD;
    private JTextField txtMaKHTour, txtNgayKhoiHanh, txtNgayKetThuc, txtTongSoVe, txtTongChi, txtTongThu, txtMaTour;

    // define table
    DefaultTableModel tableModel;

    // combo staff
    private JComboBox<NhanVienDTO> cbStaff;
    private DefaultComboBoxModel<NhanVienDTO> staffModel;

    // define btn
    private JButton saveBtn, cancelBtn;

    private NhanVienBUS nhanVienBUS;
    private _KeHoachTourBUS keHoachTourBUS;
    private _KeHoachTourDTO keHoachTourDTO;

    private String maTour;

    // formatter
    private LocalDate today;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public _KeHoachTourDialog(_KeHoachTourBUS keHoachTourBUS, _KeHoachTourDTO keHoachTourDTO, String maTour) {
        this.keHoachTourBUS = keHoachTourBUS;
        this.keHoachTourDTO = keHoachTourDTO;
        this.maTour = maTour;
        this.nhanVienBUS = new NhanVienBUS();
        nhanVienBUS.docDSNV();

        // load staff combobox
        cbStaff = new JComboBox<>();

        today = LocalDate.now();

        setTitle(keHoachTourDTO == null ? "Thêm kế hoạch tour" : "Sửa kế hoạch Tour");
        setSize(300, 440);
        setLocationRelativeTo(null);
        setModal(true);

        init();
        if (keHoachTourDTO != null) {
            loadData();
        }
    }

    private void init(){
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(9, 2)); // at center

        JPanel southPanel = new JPanel(new FlowLayout());

        // Save button
        save();
        southPanel.add(saveBtn);

        //Cancel button
        cancel();
        southPanel.add(cancelBtn);

        //row maKHTour
        jlbMaKHTour = new JLabel("Mã kế hoạch tour");
        jlbMaKHTour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbMaKHTour);
        txtMaKHTour = new JTextField();
        formPanel.add(txtMaKHTour);

        // row ngayKhoiHanh
        jlbNgayKhoiHanh = new JLabel("Ngày khơi hành");
        jlbNgayKhoiHanh.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbNgayKhoiHanh);
        // txt
        txtNgayKhoiHanh = new JTextField();
        txtNgayKhoiHanh.setText(today.format(formatter));
        formPanel.add(txtNgayKhoiHanh);

        //row ngayKetThuc
        jlbNgayKetThuc = new JLabel("Ngày kết thúc");
        jlbNgayKetThuc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbNgayKetThuc);
        //txt
        txtNgayKetThuc = new JTextField();
        LocalDate endDate = today.plusDays(1);
        txtNgayKetThuc.setText(endDate.format(formatter));
        formPanel.add(txtNgayKetThuc);

        //row tongSoVe
        jlbTongSoVe = new JLabel("Tổng số vé");
        jlbTongSoVe.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbTongSoVe);
        txtTongSoVe = new JTextField();
        formPanel.add(txtTongSoVe);

        //row tongChi
        jlbTongChi = new JLabel("Tổng chi");
        jlbTongChi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbTongChi);
        txtTongChi = new JTextField();
        formPanel.add(txtTongChi);

        //row tongThu
        jlbTongThu = new JLabel("Tổng thu");
        jlbTongThu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbTongThu);
        txtTongThu = new JTextField();
        formPanel.add(txtTongThu);

        //row maTour
        jlbMaTour = new JLabel("Mã tour");
        jlbMaTour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbMaTour);
        txtMaTour = new JTextField(maTour);
        txtMaTour.setEnabled(false);
        formPanel.add(txtMaTour);

        //row maNVHD
        jlbMaNVHD = new JLabel("Mã nhân viên hướng dẫn");
        jlbMaNVHD.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbMaNVHD);

        // Load combo staff
        staffModel = new DefaultComboBoxModel<>();
        staffModel = CBStaffPresent();
        cbStaff.setModel(staffModel);
        if (staffModel.getSize() > 0) {
            cbStaff.setSelectedIndex(0);
        }
        formPanel.add(cbStaff); // center panel add combobox tours

        add(formPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void loadData () {
        txtMaKHTour.setText(keHoachTourDTO.getMaKHTour());
        txtNgayKhoiHanh.setText(keHoachTourDTO.getNgayKhoiHanh().toString());
        txtNgayKetThuc.setText(keHoachTourDTO.getNgayKetThuc().toString());
        txtTongSoVe.setText(keHoachTourDTO.getTongSoVe() + "");
        txtTongChi.setText(keHoachTourDTO.getTongChi() + "");
        txtTongThu.setText(keHoachTourDTO.getTongThu() + "");
        txtMaTour.setText(keHoachTourDTO.getMaTour());
        // combobox manvhd
    }

    private DefaultComboBoxModel<NhanVienDTO> CBStaffPresent(){
        DefaultComboBoxModel<NhanVienDTO> model = new DefaultComboBoxModel<>();

        nhanVienBUS.docDSNV();

        ArrayList<NhanVienDTO> lsStaff = nhanVienBUS.dsNV;
        if (lsStaff == null || lsStaff.isEmpty())
            return model;

        for (NhanVienDTO t : lsStaff)
            model.addElement(t);
        return model;
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));// Trong jpBtn panel
        return btn;
    }

    public void save(){
        saveBtn = createBtn("Lưu", UIColors.SAVE);
        saveBtn.addActionListener(e -> {
            if(isEmpty(txtMaKHTour, txtNgayKhoiHanh, txtNgayKetThuc, txtTongSoVe, txtTongChi, txtTongThu)){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            //validate numbers
            int tongSoVe;
            long tongChi, tongThu;
            LocalDate ngayKhoiHanh;
            LocalDate ngayKetThuc;
            try {
                tongSoVe = Integer.parseInt(txtTongSoVe.getText().trim());
                tongChi = Long.parseLong(txtTongChi.getText().trim());
                tongThu = Long.parseLong(txtTongThu.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số");
                return;
            }

            try {
                ngayKhoiHanh = LocalDate.parse(txtNgayKhoiHanh.getText(), formatter);
                ngayKetThuc = LocalDate.parse(txtNgayKetThuc.getText(), formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày phải đúng định dạng dd/mm/yyyy");
                return;
            }

            if(keHoachTourDTO == null){
                if(keHoachTourBUS.existedKeHoachTourWithID(txtMaKHTour.getText()))
                    JOptionPane.showMessageDialog(null, "Mã kế hoạch tour đã tồn tại, vui lòng nhập mã khác!");
                else{
                    _KeHoachTourDTO keHoachTourMoi = null;
                    NhanVienDTO selectedStaff = getStaffSelected();

                    keHoachTourMoi = new _KeHoachTourDTO(
                            txtMaKHTour.getText(), ngayKhoiHanh,
                            ngayKetThuc, tongSoVe,
                            tongChi, tongThu, txtMaTour.getText(), selectedStaff.getMaNV()
                    );

                    // validate before add
                    String error = keHoachTourBUS.validateKeHoachTour(keHoachTourMoi);
                    if(error == null){
                        boolean result = keHoachTourBUS.addKeHoachTour(keHoachTourMoi);
                        if(result) {
                            JOptionPane.showMessageDialog(this, "Đã thêm");
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, error);
                    }
                }
            }else{
                keHoachTourDTO.setMaKHTour(txtMaKHTour.getText());
                keHoachTourDTO.setNgayKhoiHanh(LocalDate.parse(txtNgayKhoiHanh.getText(), formatter));
                keHoachTourDTO.setNgayKetThuc(LocalDate.parse(txtNgayKetThuc.getText(), formatter));
                keHoachTourDTO.setTongSoVe(tongSoVe);
                keHoachTourDTO.setTongChi(tongChi);
                keHoachTourDTO.setTongThu(tongThu);
                keHoachTourDTO.setMaTour(txtMaTour.getText());
                NhanVienDTO selectedStaff = getStaffSelected();
                keHoachTourDTO.setMaNVHD(selectedStaff.getMaNV());

                keHoachTourBUS.editKeHoachTour(keHoachTourDTO); // edit by keHoachTourBus
            }
        });
    }

    public void cancel(){
        cancelBtn = createBtn("Hủy", UIColors.CANCEL);
        cancelBtn.addActionListener(e -> {
            dispose();
        });
    }

    private boolean isEmpty(JTextField... fields){
        for(JTextField field : fields){
            if(field.getText().trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    private NhanVienDTO getStaffSelected(){
        return (NhanVienDTO) cbStaff.getSelectedItem();
    }
}
