package org.example.dto;

public class TourDTO {
    private String maTour;
    private String ten;
    private int soNgay;
    private long donGia;
    private int soNguoi;
    private String diaDiemKhoiHanh;
    private String maLoaiTour;

    public TourDTO(){
        this.maTour = "";
        this.ten = "";
        this.soNgay = 0;
        this.donGia = 0;
        this.soNguoi = 0;
        this.diaDiemKhoiHanh = "";
        this.maLoaiTour = "";
    }

    public TourDTO(String maTour, String ten, int soNgay, long donGia, int soNguoi, String diaDiemKhoiHanh, String maLoaiTour) {
        this.maTour = maTour;
        this.ten = ten;
        this.soNgay = soNgay;
        this.donGia = donGia;
        this.soNguoi = soNguoi;
        this.diaDiemKhoiHanh = diaDiemKhoiHanh;
        this.maLoaiTour = maLoaiTour;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public String getDiaDiemKhoiHanh() {
        return diaDiemKhoiHanh;
    }

    public void setDiaDiemKhoiHanh(String diaDiemKhoiHanh) {
        this.diaDiemKhoiHanh = diaDiemKhoiHanh;
    }

    public String getMaLoaiTour() {
        return maLoaiTour;
    }
    public void setMaLoaiTour(String maLoaiTour) {
        this.maLoaiTour = maLoaiTour;
    }

    @Override
    public String toString(){
        return maTour + " - " + ten;
    }
}
