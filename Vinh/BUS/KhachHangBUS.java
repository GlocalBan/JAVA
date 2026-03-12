/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DTO.KhachHang;
import DAO.DSKhachHang;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class KhachHangBUS {
    static ArrayList<KhachHang> dsKH;
    static DSKhachHang dataKH = new DSKhachHang();
    public KhachHangBUS() {}
    public void docDSKH() {
        if (dsKH == null) {
            dsKH = new ArrayList<KhachHang>();
        }
        dsKH = dataKH.layDanhSachKHang();
    }
    public void them(KhachHang khang) {
        try{
            if (dsKH == null) {
                dsKH = new ArrayList<KhachHang>();
            }
            if (khang == null) {
                return;
            }
            if (khang.getMaKH() == null || khang.getMaKH().isEmpty()) {
                return;
            }
            for (KhachHang existingKH : dsKH) {
                if (existingKH.getMaKH().equals(khang.getMaKH())) {
                    return;
                }
            }
            dataKH.themKhachHang(khang);
            if (dsKH != null) {
            dsKH.add(khang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoaKhachHang(String maKH) {
        try {
            if (dsKH == null) {
                return;
            }
            dataKH.xoaKhachHang(maKH);
            dsKH.removeIf(kh -> kh.getMaKH().equals(maKH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public KhachHang timKiemKH(String maKH){
        if (dsKH == null) {
            return null;
        }
        for (KhachHang kh : dsKH) {
            if (kh.getMaKH().equals(maKH)) {
                return dataKH.timKhachHangTheoMa(maKH);
            }
        }
        return null;
    }

    public KhachHang timKiemKHTheoHo(String tenKH) {
        if (dsKH == null) {
            return null;
        }
        for (KhachHang kh : dsKH) {
            if (kh.getTen().equalsIgnoreCase(tenKH)) {
                return dataKH.timKhachHangTheoTen(tenKH);
            }
        }
        return null;
    }

    public KhachHang timKiemKHTheoTen(String tenKH) {
        if (dsKH == null) {
            return null;
        }
        for (KhachHang kh : dsKH) {
            if (kh.getTen().equalsIgnoreCase(tenKH)) {
                return dataKH.timKhachHangTheoTen(tenKH);
            }
        }
        return null;
    }

    public boolean suaKhachHang(KhachHang khang) {
        try {
            if (dsKH == null) {
                return false;
            }
            boolean success = dataKH.suaKhachHang(khang);
            if (success) {
                for (int i = 0; i < dsKH.size(); i++) {
                    if (dsKH.get(i).getMaKH().equals(khang.getMaKH())) {
                        dsKH.set(i, khang);
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
