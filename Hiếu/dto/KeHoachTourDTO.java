package org.example.dto;

public class KeHoachTourDTO {
    private String maKHTour;
    private String ngayKhoiHanh;
    private String ngayKetThuc;
    private int tongSoNguoi;
    private int tongSoVe;
    private long tongChi;
    private long tongThu;
    private String maTour;
    private String maNVHD;


    public KeHoachTourDTO() {
        this.maKHTour = "";
        this.ngayKhoiHanh = "";
        this.ngayKetThuc = "";
        this.tongSoNguoi = 0;
        this.tongSoVe = 0;
        this.tongChi = 0;
        this.tongThu = 0;
        this.maTour = "";
        this.maNVHD = "";
    }

    public KeHoachTourDTO(String maKHTour, String ngayKhoiHanh, String ngayKetThuc, int tongSoNguoi, int tongSoVe, long tongChi, long tongThu, String maTour, String maNVHD) {
        this.maKHTour = maKHTour;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.tongSoNguoi = tongSoNguoi;
        this.tongSoVe = tongSoVe;
        this.tongChi = tongChi;
        this.tongThu = tongThu;
        this.maTour = maTour;
        this.maNVHD = maNVHD;
    }

    public void setMaKHTour(String maKHTour) {
        this.maKHTour = maKHTour;
    }

    public void setNgayKhoiHanh(String ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setTongSoNguoi(int tongSoNguoi) {
        this.tongSoNguoi = tongSoNguoi;
    }

    public void setTongSoVe(int tongSoVe) {
        this.tongSoVe = tongSoVe;
    }

    public void setTongChi(long tongChi) {
        this.tongChi = tongChi;
    }

    public void setTongThu(long tongThu) {
        this.tongThu = tongThu;
    }

    public void setMaTour(String maTour){
        this.maTour = maTour;
    }

    public void setMaNVHD(String maNVHD){
        this.maNVHD = maNVHD;
    }


    public String getMaKHTour() {
        return maKHTour;
    }

    public String getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public int getTongSoNguoi() {
        return tongSoNguoi;
    }

    public int getTongSoVe() {
        return tongSoVe;
    }

    public long getTongChi() {
        return tongChi;
    }

    public long getTongThu() {
        return tongThu;
    }

    public String getMaTour() {
        return maTour;
    }

    public String getMaNVHD() {
        return maNVHD;
    }
}
