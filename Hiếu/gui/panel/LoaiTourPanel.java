package org.example.gui.panel;

import org.example.bus.LoaiTourBUS;
import org.example.dto.LoaiTourDTO;
import org.example.gui.dialog.LoaiTourDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LoaiTourPanel extends JPanel {
    private JTable table;
    private LoaiTourBUS loaiTourBUS;
    private JTextField txtSearch;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton addBtn, deleteBtn, editBtn, searchBtn, refreshBtn;
    private ArrayList<LoaiTourDTO> lsLoaiTour;

    public LoaiTourPanel(){
        loaiTourBUS = new LoaiTourBUS();
        init();
        loadTable();
        hasSelectedRow();
    }

    public void init(){
        setLayout(new BorderLayout());

        //North Panel
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("QUẢN LÝ LOẠI TOUR", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(lblTitle, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel();

        txtSearch = new JTextField(20);
        searchPanel.add(new JLabel("Tìm kiếm theo thể loại:"));
        searchPanel.add(txtSearch);

        search(); // search button
        searchPanel.add(searchBtn);

        northPanel.add(searchPanel, BorderLayout.CENTER);

        // init table
        initTable();

        //South Panel
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        add(); // add button
        southPanel.add(addBtn);
        delete(); // delete button
        southPanel.add(deleteBtn);
        edit(); // edit button
        southPanel.add(editBtn);
        refresh();
        southPanel.add(refreshBtn);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void initTable(){
        String[] columns = {"Mã loại tour", "Thể loại"};

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(table);
    }

    private void loadTable(){
        tableModel.setRowCount(0);
        lsLoaiTour = loaiTourBUS.getAllLoaiTour();

        for (LoaiTourDTO lt : lsLoaiTour){
            tableModel.addRow(new Object[]{
                    lt.getMaLoaiTour(),
                    lt.getTheLoai()
            });
        }
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // in south panel

        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        return btn;
    }

    private void search(){
        searchBtn = createBtn("Tìm", Color.CYAN);
        searchBtn.addActionListener(e -> {
            // Tìm kiếm theo thể loại
            String keyWord = txtSearch.getText().trim().toLowerCase();
            ArrayList<LoaiTourDTO> list = loaiTourBUS.search(keyWord);

            tableModel.setRowCount(0);

            for (LoaiTourDTO lt : list){
                tableModel.addRow(new Object[]{
                        lt.getMaLoaiTour(),
                        lt.getTheLoai()
                });
            }
        });
    }

    private void add(){
        addBtn = createBtn("Thêm loại Tour", Color.GREEN);
        addBtn.addActionListener(e -> openDiaLog(null));
    }

    private void openDiaLog(LoaiTourDTO loaiTourDTO){
        LoaiTourDialog loaiTourDialog = new LoaiTourDialog(loaiTourBUS, loaiTourDTO);
        loaiTourDialog.setVisible(true);
        loadTable();
    }

    private void delete(){
        deleteBtn = createBtn("Xóa loại Tour", Color.RED);
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(e ->{
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa");
                return;
            }

            String maLoaiTour = tableModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?");
            if(confirm == JOptionPane.YES_OPTION){
                boolean result = loaiTourBUS.removeLoaiTour(maLoaiTour);
                if(result)
                    JOptionPane.showMessageDialog(null, "Đã xóa loại tour có mã: " + maLoaiTour);
                else
                    JOptionPane.showMessageDialog(null, "Lỗi! Xóa không thành công loại tour: " + maLoaiTour);
                loadTable();
            }
        });
    }

    private void edit(){
        editBtn = createBtn("Chỉnh sửa", Color.ORANGE);
        editBtn.setEnabled(false);
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tour cần sửa");
                return;
            }
            String maLoaiTour = tableModel.getValueAt(row, 0).toString();
            LoaiTourDTO lt = loaiTourBUS.getById(maLoaiTour);
            openDiaLog(lt);
        });
    }

    private void refresh(){
        refreshBtn = createBtn("Làm mới", Color.BLUE);
        refreshBtn.addActionListener(e -> {
            String keyWord = txtSearch.getText().trim().toLowerCase();
            ArrayList<LoaiTourDTO> lsCate = loaiTourBUS.search(keyWord);
            loadTableByName(lsCate);
        });
    }

    private void loadTableByName(ArrayList<LoaiTourDTO> list){
        tableModel.setRowCount(0);

        for(LoaiTourDTO lt : list) {
            tableModel.addRow(new Object[]{
                    lt.getMaLoaiTour(),
                    lt.getTheLoai()
            });
        }
    }

    private void hasSelectedRow(){
        table.getSelectionModel().addListSelectionListener(e ->{
            boolean hadSelection = table.getSelectedRow() != -1;
            deleteBtn.setEnabled(hadSelection);
            editBtn.setEnabled(hadSelection);
        });
    }
}
