/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class KHang_KHTour {
    private String MaKHTour;
    private String MaKHang;
    private long GiaVe;

    public KHang_KHTour() {
    }

    public KHang_KHTour(String MaKHTour, String MaKHang, long GiaVe) {
        this.MaKHTour = MaKHTour;
        this.MaKHang = MaKHang;
        this.GiaVe = GiaVe;
    }

    public String getMaKHTour() {
        return MaKHTour;
    }

    public void setMaKHTour(String MaKHTour) {
        this.MaKHTour = MaKHTour;
    }

    public String getMaKHang() {
        return MaKHang;
    }

    public void setMaKHang(String MaKHang) {
        this.MaKHang = MaKHang;
    }

    public long getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(long GiaVe) {
        this.GiaVe = GiaVe;
    }
}
