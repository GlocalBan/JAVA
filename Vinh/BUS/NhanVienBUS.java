/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DTO.NhanVien;
import java.util.ArrayList;
import DAO.DSNhanVien;
/**
 *
 * @author Admin
 */
public class NhanVienBUS {
    static ArrayList<NhanVien> dsNV;
    static DSNhanVien dataNV = new DSNhanVien();
    public NhanVienBUS() {
        if (dsNV == null) {
            dsNV = dataNV.layDanhSachNV();
        }
    }
    public void docDSNV() {
        if (dsNV == null) {
            dsNV = new ArrayList<NhanVien>();
        }
        dsNV = dataNV.layDanhSachNV();
    }
    public void them(NhanVien nv) {
        try{
            if (dsNV == null) {
                dsNV = new ArrayList<NhanVien>();
            }
            if (nv == null) {
                return;
            }
            if (nv.getMaNV() == null || nv.getMaNV().isEmpty()) {
                return;
            }
            for (NhanVien existingNV : dsNV) {
                if (existingNV.getMaNV().equals(nv.getMaNV())) {
                    return;
                }
            }
            dataNV.themNhanVien(nv);
            if (dsNV != null) {
            dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoaNhanVien(String maNV) {
        try {
            if (dsNV == null) {
                return;
            }
            dataNV.xoaNhanVien(maNV);
            dsNV.removeIf(nv -> nv.getMaNV().equals(maNV));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public NhanVien timKiemNV(String maNV){
        if (dsNV == null) {
            return null;
        }
        for (NhanVien nv : dsNV) {
            if (nv.getMaNV().equals(maNV)) {
                return dataNV.timNhanVienTheoMa(maNV);
            }
        }
        return null;
    }

    public boolean suaNhanVien(NhanVien nv) {
        try {
            if (dsNV == null) {
                return false;
            }
            boolean success = dataNV.suaNhanVien(nv);
            if (success) {
                for (int i = 0; i < dsNV.size(); i++) {
                    if (dsNV.get(i).getMaNV().equals(nv.getMaNV())) {
                        dsNV.set(i, nv);
                        break;
                    }
                }
            }
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}