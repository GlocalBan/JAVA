package BUS;

import DTO.KMTour;

import DAO.DsKMTour;

import java.util.*;

public class KMTourBUS {
    private ArrayList<KMTour> dsKMTour;
    public static DsKMTour dao;
    public KMTourBUS() {
        if (dsKMTour == null) {
            // dao = new DsKMTour();
            //khoit tao dsKMTour tu database
            dsKMTour = dao.getDsKMTour();
        }
    }

    public ArrayList<KMTour> getDsKMTour() {
        return dsKMTour;
    }

    public void setDsKMTour(ArrayList<KMTour> dsKMTour) {
        this.dsKMTour = dsKMTour;
    }

    public KMTour timKMTour(String maKM) {
        for (KMTour kmTour : dsKMTour) {
            if (kmTour.getMaKM().equals(maKM)) {
                return kmTour;
            }
        }
        return null;
    }

    public boolean timKMTour(KMTour kmTour) {
        for (KMTour km : dsKMTour) {
            if (km.getMaKM().equals(kmTour.getMaKM())) {
                return true;
            }
        }
        if(dao.timKMTour(kmTour.getMaKM()) != null) {
            return true; // Đã tồn tại trong cơ sở dữ liệu
        }
        return false;
    }

    public boolean themKMTour(KMTour kmTour) {
        if (timKMTour(kmTour)) {
            return false; // Đã tồn tại, không thêm
        }
        dsKMTour.add(kmTour);
        return true;
    }

    public boolean xoaKMTour(String maKM) {
        KMTour kmTour = timKMTour(maKM);
        if (kmTour != null) {
            dsKMTour.remove(kmTour);
            return true; // Xóa thành công
        }
        if(dao.timKMTour(maKM) != null) {
            // Xóa từ cơ sở dữ liệu nếu tồn tại
            dao.xoaKMTour(maKM);
            return true; // Giả sử xóa thành công từ database
        }   
        return false; // Không tìm thấy, không xóa
    }

    public boolean suaKMTour(KMTour kmTour) {
        for (int i = 0; i < dsKMTour.size(); i++) {
            if (dsKMTour.get(i).getMaKM().equals(kmTour.getMaKM())) {
                dsKMTour.set(i, kmTour);
                return true; // Sửa thành công
            }
        }
        if(dao.timKMTour(kmTour.getMaKM()) != null) {
            // Cập nhật trong cơ sở dữ liệu nếu tồn tại
            dao.suaKMTour(kmTour);
            return true; // Giả sử cập nhật thành công từ database
        }   
        return false; // Không tìm thấy, không sửa
    }

}   
