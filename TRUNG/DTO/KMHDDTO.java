package DTO;

import java.time.LocalDate;

public class KMHDDTO extends CTrinhKMDTO {
    private String maHD;
    private float tongTienApDung;

    public KMHDDTO() {
    }
    
    public KMHDDTO(String maKM, String tenKM, LocalDate ngayBD, LocalDate ngayKT, boolean hinhThucKM, float chietKhau, String ghiChu, String maHD, float tongTienApDung) {
        super(maKM, tenKM, ngayBD, ngayKT, hinhThucKM, chietKhau, ghiChu);
        this.maHD = maHD;
        this.tongTienApDung = tongTienApDung;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public float getTongTienApDung() {
        return tongTienApDung;
    }

    public void setTongTienApDung(float tongTienApDung) {
        this.tongTienApDung = tongTienApDung;
    }

}
