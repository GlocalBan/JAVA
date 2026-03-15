package DTO;

import DTO.CTrinhKM;

public class KMHD extends CTrinhKM {
    private String maHD;
    private float tongTienApDung;

    public KMHD() {
    }
    
    public KMHD(String maKM, String tenKM, String ngayBD, String ngayKT, boolean hinhThucKM, float chietKhau, String ghiChu, String maHD, float tongTienApDung) {
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

    @Override
    public String toString() {
        return "KMHD{" +
                "maKM='" + getMaKM() + '\'' +
                ", tenKM='" + getTenKM() + '\'' +
                ", ngayBD='" + getNgayBD() + '\'' +
                ", ngayKT='" + getNgayKT() + '\'' +
                ", HinhThucKM='" + ( getHinhThucKM() ? "KMHD" : "KMTour" ) + '\'' +
                ", GhiChu='" + getGhiChu() + '\'' +
                ", maHD='" + maHD + '\'' +
                ", chietKhau=" + getChietKhau() +
                ", tongTienApDung=" + tongTienApDung +
                '}';
    }
}
