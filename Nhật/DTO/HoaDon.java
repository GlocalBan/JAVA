/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DTO;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author Nhat
 */
public class HoaDon {
    public static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    private String MaHD;
    private String MaKHTour;
    private String MaKHDat;
    private String MaNV;
    private LocalDate ngay;
    private int soluong;
    private float TongTien;
    
    public HoaDon(String MaHD, String MaKHTour, String MaKHDat, String MaNV,LocalDate ngay,int soluong, float TongTien) {
        this.MaHD = MaHD;
        this.MaKHTour = MaKHTour;
        this.MaKHDat = MaKHDat;
        this.MaNV = MaNV;
        this.ngay=ngay;
        this.soluong=soluong;
        this.TongTien = TongTien;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public static void setSc(Scanner sc) {
        HoaDon.sc = sc;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public static Scanner getSc() {
        return sc;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaKHTour() {
        return MaKHTour;
    }

    public String getMaKHDat() {
        return MaKHDat;
    }

    public String getMaNV() {
        return MaNV;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaKHTour(String MaKHTour) {
        this.MaKHTour = MaKHTour;
    }

    public void setMaKHDat(String MaKHDat) {
        this.MaKHDat = MaKHDat;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }
    
    public void TinhTien(){
        
    }
   
}
