/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.DSKHang_KHTour;
import DTO.KHang_KHTour;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class KHang_KHTourBUS {
    static ArrayList<KHang_KHTour> dsKHKHTour;
    static DSKHang_KHTour dataKHKHTour = new DSKHang_KHTour();
    public KHang_KHTourBUS() {}
    public void docDSKHKHTour() {
        if (dsKHKHTour == null) {
            dsKHKHTour = new ArrayList<KHang_KHTour>();
        }
        dsKHKHTour = dataKHKHTour.layDanhSachKHang_KHTour();
    }
    public void them(KHang_KHTour kht) {
        try{
            if (dsKHKHTour == null) {
                dsKHKHTour = new ArrayList<KHang_KHTour>();
            }
            if (kht == null) {
                return;
            }
            if (kht.getMaKHTour() == null || kht.getMaKHTour().isEmpty()) {
                return;
            }
            for (KHang_KHTour existingKHT : dsKHKHTour) {
                if (existingKHT.getMaKHTour().equals(kht.getMaKHTour())) {
                    return;
                }
            }
            dataKHKHTour.themKHang_KHTour(kht);
            if (dsKHKHTour != null) {
            dsKHKHTour.add(kht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaKHang_KHTour(String maKHTour, String maKHang) {
        try {
            if (dsKHKHTour == null) {
                return;
            }
            dataKHKHTour.xoaKHang_KHTour(maKHTour, maKHang);
            dsKHKHTour.removeIf(kht -> kht.getMaKHTour().equals(maKHTour) && kht.getMaKHang().equals(maKHang));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean suaKHang_KHTour(KHang_KHTour kht) {
        try {
            if (dsKHKHTour == null || kht == null || kht.getMaKHTour() == null || kht.getMaKHTour().isEmpty()) {
                return false;
            }
            for (int i = 0; i < dsKHKHTour.size(); i++) {
                KHang_KHTour existingKHT = dsKHKHTour.get(i);
                if (existingKHT.getMaKHTour().equals(kht.getMaKHTour()) && existingKHT.getMaKHang().equals(kht.getMaKHang())) {
                    dataKHKHTour.capNhatKHang_KHTour(kht);
                    dsKHKHTour.set(i, kht);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public KHang_KHTour timKiemKHang_KHTour(String maKHTour){
        if (dsKHKHTour == null) {
            return null;
        }
        for (KHang_KHTour kht : dsKHKHTour) {
            if (kht.getMaKHTour().equals(maKHTour)) {
                return kht;
            }
        }
        return null;
    }

    public KHang_KHTour timKiemKHang_KHTourTheoMaKHang(String maKHang){
        if (dsKHKHTour == null) {
            return null;
        }
        for (KHang_KHTour kht : dsKHKHTour) {
            if (kht.getMaKHang().equals(maKHang)) {
                return kht;
            }
        }
        return null;
    }
}
