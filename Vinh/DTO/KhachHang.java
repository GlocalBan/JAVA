/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
/**
 *
 * @author Admin
 */
public class KhachHang extends Person {
    private String maKH;

    public KhachHang() {
    }

    public KhachHang(String maKH, String ho, String ten, String diaChi, String sdt, java.sql.Date ngaySinh) {
        super(ho, ten, diaChi, sdt, ngaySinh);
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

}
