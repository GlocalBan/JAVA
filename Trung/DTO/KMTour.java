package DTO;

import java.util.ArrayList;


public class KMTour extends CTrinhKM {
    private ArrayList<String> dsMaTour;

    public KMTour() {
        this.dsMaTour = new ArrayList<>();
    }

    public KMTour(String maKM, String tenKM, String ngayBD, String ngayKT, boolean hinhThucKM, float chietKhau, String ghiChu, ArrayList<String> dsMaTour) {
        super(maKM, tenKM, ngayBD, ngayKT, hinhThucKM, chietKhau, ghiChu);
        this.dsMaTour = dsMaTour;
    }

    public ArrayList<String> getDsMaTour() {
        return dsMaTour;
    }

    public void setDsMaTour(ArrayList<String> dsMaTour) {
        this.dsMaTour = dsMaTour;
    }

    
    @Override
    public String toString() {  
        return "KMTour{" +
                "maKM='" + getMaKM() + '\'' +
                ", tenKM='" + getTenKM() + '\'' +
                ", ngayBD='" + getNgayBD() + '\'' +
                ", ngayKT='" + getNgayKT() + '\'' +
                ", HinhThucKM='" + ( getHinhThucKM() ? "KMHD" : "KMTour" ) + '\'' +
                ", GhiChu='" + getGhiChu() + '\'' +
                ", dsMaTour=" + dsMaTour +
                ", chietKhau=" + getChietKhau() +
                '}';
    }

    @Override
    public void Nhap() {
        super.Nhap();
        System.out.print("Nhập số lượng tour áp dụng: ");
        int n = Integer.parseInt(sc.nextLine());
        while (n<=0) {
            System.out.print("Số lượng tour phải lớn hơn 0. Vui lòng nhập lại: ");
            n = Integer.parseInt(sc.nextLine());
        }   
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập mã tour thứ " + (i + 1) + "(VD: MT001): ");
            while (true) {
                String maTour = sc.nextLine().trim();

                if (maTour.isEmpty()) {
                    System.out.println(" Mã không được để trống!");
                } else if (!maTour.matches("^MT\\d{3}$")) {
                    System.out.println(" Mã phải có dạng MT + 3 chữ số (VD: MT001)");
                } else {
                    this.dsMaTour.add(maTour);
                    break;
                }
            }
        }
    }
}
