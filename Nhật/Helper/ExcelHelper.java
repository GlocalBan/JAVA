package Helper;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

    // CHỈ CẦN 1 HÀM DUY NHẤT CHO TOÀN BỘ DỰ ÁN
    public static void xuatExcel(JTable table, Component parent, String tenSheet) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet(tenSheet);

                // ================== ĐỊNH DẠNG CƠ BẢN ==================
                // Style cho Header Bảng (In đậm, có viền)
                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);

                // Style cho Dữ liệu (Có viền)
                CellStyle dataStyle = workbook.createCellStyle();
                dataStyle.setBorderTop(BorderStyle.THIN);
                dataStyle.setBorderBottom(BorderStyle.THIN);
                dataStyle.setBorderLeft(BorderStyle.THIN);
                dataStyle.setBorderRight(BorderStyle.THIN);

                int rowIndex = 0;

                // ================== 1. DÒNG TIÊU ĐỀ LỚN ==================
                CellStyle titleStyle = workbook.createCellStyle();
                Font titleFont = workbook.createFont();
                titleFont.setBold(true);
                titleFont.setFontHeightInPoints((short) 14); // Cỡ chữ to hơn một chút
                titleStyle.setFont(titleFont);
                titleStyle.setAlignment(HorizontalAlignment.CENTER); // Căn giữa bảng

                Row titleRow = sheet.createRow(rowIndex++);
                Cell titleCell = titleRow.createCell(0);
                // Dùng chính biến tenSheet làm Tên Báo Cáo
                titleCell.setCellValue(tenSheet); 
                titleCell.setCellStyle(titleStyle);
                // Gộp cột từ cột 0 đến cột cuối cùng để dòng chữ nằm ở giữa
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, Math.max(1, table.getColumnCount() - 1)));

                // ================== 2. DÒNG NGÀY XUẤT ==================
                Row dateRow = sheet.createRow(rowIndex++);
                // Tính toán để đặt chữ "Ngày xuất" ở 2 cột cuối cùng bên phải
                int colDateLabel = Math.max(0, table.getColumnCount() - 2); 
                int colDateValue = Math.max(1, table.getColumnCount() - 1); 

                Cell dateLabelCell = dateRow.createCell(colDateLabel);
                dateLabelCell.setCellValue("Ngày xuất");
                
                Cell dateValueCell = dateRow.createCell(colDateValue);
                // Lấy ngày hiện tại tự động
                String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                dateValueCell.setCellValue(currentDate);

                // ================== 3. DÒNG TRỐNG CHO THOÁNG ==================
                rowIndex++;

                // ================== 4. TIÊU ĐỀ CÁC CỘT (HEADER) ==================
                Row headerRow = sheet.createRow(rowIndex++);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(table.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }

                // ================== 5. DỮ LIỆU CỦA BẢNG ==================
                for (int i = 0; i < table.getRowCount(); i++) {
                    Row row = sheet.createRow(rowIndex++);
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        Object value = table.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                        cell.setCellStyle(dataStyle);
                    }
                }

                // Tự động co giãn độ rộng của từng cột cho vừa với chữ
                for (int i = 0; i < table.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Lưu file ra ổ cứng
                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                }
                
                JOptionPane.showMessageDialog(parent, "Xuất file Excel thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(parent, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}