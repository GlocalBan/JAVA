package GUI.dialog;

import BUS.*;
import DTO.*;

import javax.swing.*;
import java.awt.*;

public class LoaiTourDialog extends JDialog{
    private JLabel jlbMaLoaiTour, jlbTheLoai;
    private JTextField txtMaLoaiTour, txtTheLoai;
    private JButton saveBtn, cancelBtn;
    private LoaiTourDTO loaiTourDTO;
    private LoaiTourBUS loaiTourBUS;

    public LoaiTourDialog(LoaiTourBUS loaiTourBUS, LoaiTourDTO loaiTourDTO){
        this.loaiTourBUS = loaiTourBUS;
        this.loaiTourDTO = loaiTourDTO;

        setTitle(loaiTourDTO == null ? "Thêm loại Tour" : "Sửa loại Tour");
        setSize(300, 140);
        setLocationRelativeTo(null);
        setModal(true);

        init();
        if(loaiTourDTO != null){
            loadData();
        }
    }

    private void loadData(){
        txtMaLoaiTour.setText(loaiTourDTO.getMaLoaiTour());
        txtMaLoaiTour.setEnabled(false);
        txtTheLoai.setText(loaiTourDTO.getTheLoai());
    }

    public void init(){
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));

        // Panel Buttons
        JPanel jpBtn = new JPanel(new FlowLayout());

        // Save button
        save();
        jpBtn.add(saveBtn);

        //Cancel button
        cancel();
        jpBtn.add(cancelBtn);

        //row MaLoai
        jlbMaLoaiTour = new JLabel("Mã loại");
        jlbMaLoaiTour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        panelForm.add(jlbMaLoaiTour);
        txtMaLoaiTour = new JTextField();
        panelForm.add(txtMaLoaiTour);

        // row the loai
        jlbTheLoai = new JLabel("Thể loại");
        jlbTheLoai.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        panelForm.add(jlbTheLoai);
        txtTheLoai = new JTextField();
        panelForm.add(txtTheLoai);

        add(panelForm, BorderLayout.CENTER);
        add(jpBtn, BorderLayout.SOUTH);
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
        saveBtn = createBtn("Lưu", Color.GREEN);
        saveBtn.addActionListener(e -> {
            if(txtMaLoaiTour.getText().trim().isEmpty() || txtTheLoai.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if(loaiTourDTO == null){
                if(loaiTourBUS.existedLoaiTourWithID(txtMaLoaiTour.getText()))
                    JOptionPane.showMessageDialog(null, "Mã loại tour đã tồn tại, vui lòng nhập mã khác!");
                else {
                    LoaiTourDTO loaiMoi = new LoaiTourDTO(txtMaLoaiTour.getText(), txtTheLoai.getText());
                    loaiTourBUS.addLoaiTour(loaiMoi);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Đã thêm");
                }
            }else{
                loaiTourDTO.setTheLoai(txtTheLoai.getText());
                loaiTourBUS.editLoaiTour(loaiTourDTO);
                dispose();
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công");
            }
        });
    }

    public void cancel(){
        cancelBtn = createBtn("Hủy", Color.RED);
        cancelBtn.addActionListener(e -> {
            dispose();
        });
    }
}
