package BUS;

import DTO.KMHD;

import DAO.DsKMHD;

import java.util.*;

public class KMHDBUS {
    public ArrayList<KMHD> dsKMHD;
    public static DsKMHD dao;   

    public KMHDBUS() {
        if (dsKMHD == null) {
            // dao = new DsKMHD();
            //khoit tao dsKMHD tu database
            dsKMHD = dao.getDsKMHD();
        }
    }

    public ArrayList<KMHD> getDsKMHD() {
        return dsKMHD;
    }

    public void setDsKMHD(ArrayList<KMHD> dsKMHD) {
        this.dsKMHD = dsKMHD;
    }

    public boolean timKMHD(KMHD kmhd) {
        for (KMHD km : dsKMHD) {
            if (km.getMaKM().equals(kmhd.getMaKM())) {
                return true;
            }
        }
        return false;
    }

    public boolean themKMHD(KMHD kmhd) {
        if (timKMHD(kmhd)) {
            return false; // Đã tồn tại, không thêm
        }
        if (dao.timKMHD(kmhd.getMaKM()) != null) {
            return false; // Đã tồn tại trong cơ sở dữ liệu, không thêm
        }
        dsKMHD.add(kmhd);
        return true;
    }

    public boolean xoaKMHD(String maKM) {
        KMHD kmhd = null;
        for (KMHD km : dsKMHD) {
            if (km.getMaKM().equals(maKM)) {
                kmhd = km;
                break;
            }
        }
        if (kmhd != null) {
            dsKMHD.remove(kmhd);
            return true;
        }
        if(dao.timKMHD(maKM) != null) {
            dao.xoaKMHD(maKM);
            return true; // Xóa thành công từ cơ sở dữ liệu
        }
        return false; // Không tìm thấy, không xóa
    }

    public boolean suaKMHD(KMHD kmhd) {
        for (int i = 0; i < dsKMHD.size(); i++) {
            if (dsKMHD.get(i).getMaKM().equals(kmhd.getMaKM())) {
                dsKMHD.set(i, kmhd);
                return true; // Sửa thành công
            }
        }
        if(dao.timKMHD(kmhd.getMaKM()) != null) {
            dao.suaKMHD(kmhd);
            return true; // Sửa thành công từ cơ sở dữ liệu
        }
        return false; // Không tìm thấy, không sửa
    }

}