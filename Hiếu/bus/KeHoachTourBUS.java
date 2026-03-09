package org.example.bus;

import org.example.dao.KeHoachTourDAO;
import org.example.dto.KeHoachTourDTO;

import javax.swing.*;
import java.util.ArrayList;

public class KeHoachTourBUS {
    private ArrayList<KeHoachTourDTO> lsKeHoachTour;
    KeHoachTourDAO keHoachTourDAO;

    public KeHoachTourBUS(){
        lsKeHoachTour = new ArrayList<>();
        keHoachTourDAO = new KeHoachTourDAO();
    }

    public ArrayList<KeHoachTourDTO> getAllKeHoachTours(){
        lsKeHoachTour = keHoachTourDAO.getAllKeHoachTours();
        return lsKeHoachTour;
    }

    public ArrayList<KeHoachTourDTO> getAllKeHoachToursByID(String maTour){
        lsKeHoachTour = keHoachTourDAO.getAllKeHoachTours();
        ArrayList<KeHoachTourDTO> lsKeHoachToursID = new ArrayList<>();

        for(KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaTour().trim().equalsIgnoreCase(maTour)){
                lsKeHoachToursID.add(kt);
            }
        }
        return lsKeHoachToursID;
    }

    public boolean addKeHoachTour(KeHoachTourDTO t){
        if(t == null) return false;
        if(t.getNgayKhoiHanh() == null || t.getNgayKetThuc() == null) {
            JOptionPane.showMessageDialog(null, "Ngày không được để trống");
            return false;
        }
        if(t.getNgayKetThuc().isBefore(t.getNgayKhoiHanh())) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày khởi hành");
            return false;
        }

        boolean success = keHoachTourDAO.addKeHoachTour(t);

        if(success)
            lsKeHoachTour.add(t);
        return success;
    }

    public String validateKeHoachTour(KeHoachTourDTO t){
        if(t == null) return "Dữ liệu không hợp lệ";

        if(t.getNgayKhoiHanh() == null || t.getNgayKetThuc() == null)
            return "Ngày không được để trống";

        if(t.getNgayKetThuc().isBefore(t.getNgayKhoiHanh()))
            return "Ngày kết thúc phải sau ngày khởi hành";

        if(t.getTongChi() < 0)
            return "Tổng chi không hợp lệ";

        if(t.getTongThu() < 0)
            return "Tổng thu không hợp lệ";

        if(t.getTongSoVe() < 0)
            return "Tổng số vé không hợp lệ";

        return null;
    }

    public boolean editKeHoachTour(KeHoachTourDTO t){
        return keHoachTourDAO.editKeHoachTour(t);
    }

    public boolean removeKeHoachTour(String matour){
        boolean success = keHoachTourDAO.removeKeHoachTour(matour);
        return success;
    }

    public KeHoachTourDTO getById(String maKHTour){
        KeHoachTourDTO result = new KeHoachTourDTO();
        for (KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaKHTour().trim().equalsIgnoreCase(maKHTour)) {
                result = kt;
                break;
            }
        }
        return result;
    }

    public boolean existedKeHoachTourWithID(String maKHTour){
        for (KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaTour().trim().equalsIgnoreCase(maKHTour))
                return true;
        }
        return false;
    }
}
