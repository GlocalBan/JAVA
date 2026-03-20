/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.KHang_KHTourDAO;
import DTO.KHang_KHTour;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class KHang_KHTourBUS {
    static ArrayList<KHang_KHTour> dsKHKHTour;
    static KHang_KHTourDAO dataKHKHTour = new KHang_KHTourDAO();
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

    public KHang_KHTour timKiemKHang_KHTourTheoMaKHTour(String maKHTour) {
        try {
            if (dsKHKHTour == null || maKHTour == null) {
                return null;
            }
            for (KHang_KHTour kht : dsKHKHTour) {
                if (kht.getMaKHTour().equals(maKHTour)) {
                    return kht;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KHang_KHTour> timKHang_KHTours(String column, String value) {
        try {
            if (dsKHKHTour == null || column == null || value == null) {
                return new ArrayList<>();
            }
            List<KHang_KHTour> result = new ArrayList<>();
            for (KHang_KHTour kht : dsKHKHTour) {
                switch (column) {
                    case "MaKHTour":
                        if (kht.getMaKHTour().equalsIgnoreCase(value)) {
                            result.add(kht);
                        }
                        break;
                    case "MaKHang":
                        if (kht.getMaKHang().equalsIgnoreCase(value)) {
                            result.add(kht);
                        }
                        break;
                    case "GiaVe":
                        try {
                            long giaVeValue = Long.parseLong(value);
                            if (kht.getGiaVe() == giaVeValue) {
                                result.add(kht);
                            }
                        } catch (NumberFormatException e) {
                            // Ignore invalid number format
                        }
                        break;
                    default:
                        // Invalid column name
                        return new ArrayList<>();
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<KHang_KHTour> timKHang_KHToursTheoHo(String ho) {
        try {
            if (dsKHKHTour == null || ho == null) {
                return new ArrayList<>();
            }
            List<KHang_KHTour> result = new ArrayList<>();
            for (KHang_KHTour kht : dsKHKHTour) {
                String maKHang = kht.getMaKHang();
                if (maKHang != null && maKHang.startsWith(ho)) {
                    result.add(kht);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public KHang_KHTour timKHang_KHTourTheoTen(String ten) {
        try {
            if (dsKHKHTour == null || ten == null) {
                return null;
            }
            for (KHang_KHTour kht : dsKHKHTour) {
                String maKHang = kht.getMaKHang();
                if (maKHang != null && maKHang.equalsIgnoreCase(ten)) {
                    return kht;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}       
    
