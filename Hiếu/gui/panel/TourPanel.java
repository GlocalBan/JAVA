
package org.example.gui.panel;

import org.example.bus.TourBUS;
import org.example.dto.TourDTO;
import org.example.gui.dialog.TourDiaLog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TourPanel extends JPanel {
    private JTextField txtSearch;
    private JPanel northPanel, southPanel;
    private JButton addBtn, deleteBtn, editBtn, searchBtn, refreshBtn;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private TourBUS tourBUS;

    public TourPanel(){
        tourBUS = new TourBUS();
        init();
        loadTable();
        hasSelectedRow();
    }

    private void init(){
        setLayout(new BorderLayout());

        // northPanel
        northPanel = new JPanel(new BorderLayout());
        JLabel jlbTitle = new JLabel("QUẢN LÝ TOUR DU LỊCH", JLabel.CENTER);
        jlbTitle.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(jlbTitle, BorderLayout.NORTH); // northPanel add components

        // Search panel
        JPanel searchPanel = new JPanel();

        txtSearch = new JTextField(20);
        searchPanel.add(new JLabel("Tìm kiếm tour:"));
        searchPanel.add(txtSearch);

        search(); // search button
        searchPanel.add(searchBtn);

        northPanel.add(searchPanel, BorderLayout.CENTER);

        initTable(); // init table

        // southPanel contain buttons
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        add();
        southPanel.add(addBtn);
        delete();
        southPanel.add(deleteBtn);
        edit();
        southPanel.add(editBtn);
        refresh();
        southPanel.add(refreshBtn);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void initTable(){
        String[] columns = {"Mã tour", "Tên", "Số ngày", "Đơn giá", "Số chỗ", "Địa điểm khởi hành", "Mã loại tour"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        scrollPane = new JScrollPane(table);
    }

    private JButton createBtn(String text, Color color){
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        return btn;
    }

    private void search(){
        searchBtn = createBtn("Tìm", Color.CYAN);
        searchBtn.addActionListener(e -> {
            // Tìm kiếm theo thể tên tour
            String keyWord = txtSearch.getText().trim().toLowerCase();
            ArrayList<TourDTO> lsTour = tourBUS.search(keyWord);

            tableModel.setRowCount(0);
            loadTableByName(lsTour);
        });
    }

    private void add(){
        addBtn = createBtn("Thêm Tour", Color.GREEN);
        addBtn.addActionListener(e -> openDiaLog(null));
    }

    private void openDiaLog(TourDTO tourDTO){
        TourDiaLog tourDiaLog = new TourDiaLog(tourBUS, tourDTO);
        tourDiaLog.setVisible(true);
        loadTable();
    }

    private void delete(){
        deleteBtn = createBtn("Xóa tour", Color.RED);
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(e ->{
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tour cần xóa");
                return;
            }

            String maTour = tableModel.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?");
            if(confirm == JOptionPane.YES_OPTION){
                boolean result = tourBUS.removeTour(maTour);
                if(result)
                    JOptionPane.showMessageDialog(null, "Đã xóa tour có mã: " + maTour);
                else
                    JOptionPane.showMessageDialog(null, "Mã tour không tồn tại: " + maTour);
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
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tour cần sửa");
                return;
            }
            String maTour = tableModel.getValueAt(row, 0).toString();
            TourDTO t = tourBUS.getByID(maTour);
            openDiaLog(t);
        });
    }

    private void refresh(){
        refreshBtn = createBtn("Làm mới", Color.BLUE);
        refreshBtn.addActionListener(e -> {
            String keyWord = txtSearch.getText().trim().toLowerCase();
            ArrayList<TourDTO> lsTour = tourBUS.search(keyWord);
            loadTableByName(lsTour);
        });
    }

    public void loadTable(){
        tableModel.setRowCount(0);

        for(TourDTO t : tourBUS.getAllTours()){
            tableModel.addRow(new Object[]{
                    t.getMaTour(),
                    t.getTen(),
                    t.getSoNgay(),
                    t.getDonGia(),
                    t.getSoCho(),
                    t.getDiaDiemKhoiHanh(),
                    t.getMaLoaiTour()
            });
        }
    }

    private void loadTableByName(ArrayList<TourDTO> list){
        tableModel.setRowCount(0);

        for(TourDTO t : list) {
            tableModel.addRow(new Object[]{
                    t.getMaTour(),
                    t.getTen(),
                    t.getSoNgay(),
                    t.getDonGia(),
                    t.getSoCho(),
                    t.getDiaDiemKhoiHanh(),
                    t.getMaLoaiTour()
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
