package DTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CTrinhKM {
    public static Scanner sc = new Scanner(System.in);
    private String maKM;
    private String tenKM;
    private String ngayBD;
    private String ngayKT;
    private boolean HinhThucKM;
    private float ChietKhau;
    private String GhiChu;
    public CTrinhKM() {
    }

    public CTrinhKM(String maKM, String tenKM, String ngayBD, String ngayKT, boolean hinhThucKM, float chietKhau, String ghiChu) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.HinhThucKM = hinhThucKM;
        this.ChietKhau = chietKhau;
        this.GhiChu = ghiChu;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public boolean getHinhThucKM() {
        return HinhThucKM;
    }

    public void setHinhThucKM(boolean hinhThucKM) {
        HinhThucKM = hinhThucKM;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public float getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(float chietKhau) {
        ChietKhau = chietKhau;
    }

    public void Nhap() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate startDate = null;
    LocalDate endDate = null;

    while (true) {
        System.out.print("Nhập mã khuyến mãi (VD: KM001): ");
        maKM = sc.nextLine().trim();

        if (maKM.isEmpty()) {
            System.out.println(" Mã không được để trống!");
        } else if (!maKM.matches("^KM\\d{3}$")) {
            System.out.println(" Mã phải có dạng KM + 3 chữ số (VD: KM001)");
        } else {
            break;
        }
    }

    while (true) {
        System.out.print("Nhập tên khuyến mãi: ");
        tenKM = sc.nextLine().trim();

        if (tenKM.isEmpty()) {
            System.out.println(" Tên không được để trống!");
        } else {
            break;
        }
    }

    while (true) {
        try {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            ngayBD = sc.nextLine().trim();
            startDate = LocalDate.parse(ngayBD, formatter);
            break;
        } catch (Exception e) {
            System.out.println(" Ngày không đúng định dạng dd/MM/yyyy!");
        }
    }

    while (true) {
        try {
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            ngayKT = sc.nextLine().trim();
            endDate = LocalDate.parse(ngayKT, formatter);

            if (endDate.isBefore(startDate)) {
                System.out.println(" Ngày kết thúc phải sau hoặc bằng ngày bắt đầu!");
            } else {
                break;
            }

        } catch (Exception e) {
            System.out.println(" Ngày không đúng định dạng dd/MM/yyyy!");
        }
    }

    System.out.print("Nhập hình thức khuyến mãi: ");
    HinhThucKM = sc.nextBoolean();

    System.out.print("Nhập chiết khấu (%): ");
    while (true) {
        try {
            ChietKhau = sc.nextFloat();
            if (ChietKhau < 0 || ChietKhau > 100) {
                System.out.println(" Chiết khấu phải từ 0 đến 100!");
            } else {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println(" Vui lòng nhập một số hợp lệ cho chiết khấu!");
            sc.next(); // Clear invalid input
        }
    }

    System.out.print("Nhập ghi chú (có thể bỏ trống): ");
    GhiChu = sc.nextLine().trim();

    System.out.println(" Nhập thông tin khuyến mãi thành công!");
}

    public void Xuat() {
        System.out.println("Mã khuyến mãi: " + maKM);
        System.out.println("Tên khuyến mãi: " + tenKM);
        System.out.println("Ngày bắt đầu: " + ngayBD);
        System.out.println("Ngày kết thúc: " + ngayKT);
        System.out.println("Hình thức khuyến mãi: " +( HinhThucKM ? "KMHD" : "KMTour" ));
        System.out.println("Ghi chú: " + GhiChu);
    }

    public String toString() {
        return "CTrinhKM{" +
                "maKM='" + maKM + '\'' +
                ", tenKM='" + tenKM + '\'' +
                ", ngayBD='" + ngayBD + '\'' +
                ", ngayKT='" + ngayKT + '\'' +
                ", HinhThucKM='" + ( HinhThucKM ? "KMHD" : "KMTour" ) + '\'' +
                ", ChietKhau=" + ChietKhau +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }

}
