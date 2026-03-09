package org.example.gui.dialog;

import org.example.bus.LoaiTourBUS;
import org.example.bus.TourBUS;
import org.example.dto.LoaiTourDTO;
import org.example.dto.TourDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TourDiaLog extends JDialog {
    private JLabel jlbMaTour, jlbTen, jlbSoNgay, jlbDonGia, jlbSoCho, jlbDiaDiemKhoiHanh, jlbMaLoaiTour;
    private JTextField txtMaTour, txtTen, txtSoNgay, txtDonGia, txtSoCho, txtDiaDiemKhoiHanh;
    private TourDTO tourDTO;
    private TourBUS tourBUS;
    private JButton saveBtn, cancelBtn;
    private JPanel formPanel, southPanel;
    private LoaiTourBUS loaiTourBUS;
    private JComboBox<LoaiTourDTO> cbLoaiTours;


    public TourDiaLog(TourBUS tourBUS, TourDTO tourDTO){
        this.tourDTO = tourDTO;
        this.tourBUS = tourBUS;
        loaiTourBUS = new LoaiTourBUS();
        cbLoaiTours = new JComboBox<>();

        setTitle(tourDTO == null ? "Thêm Tour" : "Sửa Tour");
        setSize(300, 360);
        setLocationRelativeTo(null);
        setModal(true);

        init();
        if(tourDTO != null){
            loadData();
        }
    }

    private void init(){
        setLayout(new BorderLayout());

        formPanel = new JPanel(new GridLayout(7, 2));

        // southPanel contain Buttons
        southPanel = new JPanel(new FlowLayout());

        // Save button
        save();
        southPanel.add(saveBtn);

        //Cancel button
        cancel();
        southPanel.add(cancelBtn);

        //row maTour
        jlbMaTour = new JLabel("Mã tour");
        jlbMaTour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbMaTour);
        txtMaTour = new JTextField();
        formPanel.add(txtMaTour);

        // row ten
        jlbTen = new JLabel("Tên");
        jlbTen.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbTen);
        txtTen = new JTextField();
        formPanel.add(txtTen);

        //row soNgay
        jlbSoNgay = new JLabel("Số ngày");
        jlbSoNgay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbSoNgay);
        txtSoNgay = new JTextField();
        formPanel.add(txtSoNgay);

        // row donGia
        jlbDonGia = new JLabel("Đơn giá");
        jlbDonGia.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbDonGia);
        txtDonGia = new JTextField();
        formPanel.add(txtDonGia);

        //row soNguoi
        jlbSoCho = new JLabel("Số chỗ");
        jlbSoCho.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbSoCho);
        txtSoCho = new JTextField();
        formPanel.add(txtSoCho);

        // row ddkhoihanh
        jlbDiaDiemKhoiHanh = new JLabel("Địa điểm khởi hành");
        jlbDiaDiemKhoiHanh.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbDiaDiemKhoiHanh);
        txtDiaDiemKhoiHanh = new JTextField();
        formPanel.add(txtDiaDiemKhoiHanh);

        // row maLoaiTour
        jlbMaLoaiTour = new JLabel("Mã loại tour");
        jlbMaLoaiTour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        formPanel.add(jlbMaLoaiTour);

        ArrayList<LoaiTourDTO> lsCate = loaiTourBUS.getAllLoaiTour();
        DefaultComboBoxModel<LoaiTourDTO> loaiToursModel = new DefaultComboBoxModel<>();
        for(LoaiTourDTO lt : lsCate){
            loaiToursModel.addElement(lt);
        }
        cbLoaiTours.setModel(loaiToursModel);
        formPanel.add(cbLoaiTours);

        add(formPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void loadData(){
        txtMaTour.setText(tourDTO.getMaTour());
        txtMaTour.setEnabled(false);
        txtTen.setText(tourDTO.getTen());
        txtSoNgay.setText(tourDTO.getSoNgay() + "");
        txtDonGia.setText(tourDTO.getDonGia() + "");
        txtSoCho.setText(tourDTO.getSoCho() + "");
        txtDiaDiemKhoiHanh.setText(tourDTO.getDiaDiemKhoiHanh());

        //field maLoaiTour
        for(int i = 0; i < cbLoaiTours.getItemCount(); i++){
            LoaiTourDTO lt = cbLoaiTours.getItemAt(i);
            if(lt.getMaLoaiTour().equalsIgnoreCase(tourDTO.getMaLoaiTour())){
                cbLoaiTours.setSelectedIndex(i);
                break;
            }
        }
        LoaiTourDTO selectedLoaiTour = (LoaiTourDTO) cbLoaiTours.getSelectedItem();
        tourDTO.setMaLoaiTour(selectedLoaiTour.getMaLoaiTour());
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));// Trong jpBtn panel

        return btn;
    }

    private void save(){
        saveBtn = createBtn("Lưu", Color.GREEN);
        saveBtn.addActionListener(e -> {
            if(isEmpty(txtMaTour, txtTen, txtSoNgay, txtDonGia, txtSoCho, txtDiaDiemKhoiHanh)){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
                return;
            }
            
            //validate numbers
            int soNgay;
            long donGia;
            int soCho;
            try {
                soNgay = Integer.parseInt(txtSoNgay.getText().trim());
                donGia = Long.parseLong(txtDonGia.getText().trim());
                soCho = Integer.parseInt(txtSoCho.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số");
                return;
            }

            if(tourDTO == null){
                if(tourBUS.existedTourWithID(txtMaTour.getText())){
                    JOptionPane.showMessageDialog(null, "Mã tour đã tồn tại, vui lòng nhập mã khác!");
                }else{
                    LoaiTourDTO selectedLoaiTour = (LoaiTourDTO) cbLoaiTours.getSelectedItem();
                    String maLoaiTour = selectedLoaiTour.getMaLoaiTour();

                    TourDTO tourMoi = new TourDTO(
                            txtMaTour.getText(), txtTen.getText(),
                            soNgay, donGia, soCho,
                            txtDiaDiemKhoiHanh.getText(), maLoaiTour
                    );
                    boolean result = tourBUS.addTour(tourMoi);
                    if (result){
                        JOptionPane.showMessageDialog(this, "Đã thêm");
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }else{
                tourDTO.setTen(txtTen.getText());
                tourDTO.setSoNgay(soNgay);
                tourDTO.setDonGia(donGia);
                tourDTO.setSoCho(soCho);
                tourDTO.setDiaDiemKhoiHanh(txtDiaDiemKhoiHanh.getText());

                // field maLoaiTour
                LoaiTourDTO selectedLoaiTour = (LoaiTourDTO) cbLoaiTours.getSelectedItem();
                String maLoaiTour = selectedLoaiTour.getMaLoaiTour();
                tourDTO.setMaLoaiTour(maLoaiTour);

                boolean result = tourBUS.editTour(tourDTO);
                if(result){
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thất bại");
                }
            }
        });
    }

    private void cancel(){
        cancelBtn = createBtn("Hủy", Color.RED);
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
}
