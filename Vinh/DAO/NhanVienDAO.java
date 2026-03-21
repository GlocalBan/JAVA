package DAO;

import DTO.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public ArrayList<NhanVien> layDanhSachNV() {

        ArrayList<NhanVien> dsNV = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                dsNV.add(mapToNhanVien(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsNV;
    }

    public boolean themNhanVien(NhanVien nv) {

        String sql = "INSERT INTO NhanVien (MaNV, ChucVu, Ho, Ten, DiaChi, SDT, NgaySinh) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv.getMaNV());
            pstmt.setString(2, nv.getChucVu());
            pstmt.setString(3, nv.getHo());
            pstmt.setString(4, nv.getTen());
            pstmt.setString(5, nv.getDiaChi());
            pstmt.setString(6, nv.getSdt());
            pstmt.setDate(7, Date.valueOf(nv.getNgaySinh()));

            boolean themNhanVien = pstmt.executeUpdate() > 0;
            if (!themNhanVien) {
                return false;
            }
            return taiKhoanDAO.taoTaiKhoanNhanVien(nv.getMaNV(), nv.getChucVu());

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public NhanVien timNhanVienTheoMa(String maNV) {

        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maNV);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapToNhanVien(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> timNhanVienTheoNgaySinh(LocalDate date) {
    List<NhanVien> list = new ArrayList<>();

    String sql = "SELECT * FROM NhanVien WHERE DATE(ngaySinh) = ?";

    try (Connection con = KetNoiCSDL.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, java.sql.Date.valueOf(date));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(mapToNhanVien(rs));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    public List<NhanVien> timNhanVien(String column, String keyword) {
    List<NhanVien> list = new ArrayList<>();

    String sql = "SELECT * FROM NhanVien WHERE " + column + " LIKE ?";

    try (Connection con = KetNoiCSDL.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(mapToNhanVien(rs));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    public boolean capNhatNhanVien(NhanVien nv) {

        String sql = "UPDATE NhanVien SET ChucVu = ?, Ho = ?, Ten = ?, DiaChi = ?, SDT = ?, NgaySinh = ? WHERE MaNV = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv.getChucVu());
            pstmt.setString(2, nv.getHo());
            pstmt.setString(3, nv.getTen());
            pstmt.setString(4, nv.getDiaChi());
            pstmt.setString(5, nv.getSdt());
            pstmt.setDate(6, Date.valueOf(nv.getNgaySinh()));
            pstmt.setString(7, nv.getMaNV());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhanVien(String maNV) {

        String sql = "DELETE FROM NhanVien WHERE MaNV = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maNV);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET ChucVu = ?, Ho = ?, Ten = ?, DiaChi = ?, SDT = ?, NgaySinh = ? WHERE MaNV = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv.getChucVu());
            pstmt.setString(2, nv.getHo());
            pstmt.setString(3, nv.getTen());
            pstmt.setString(4, nv.getDiaChi());
            pstmt.setString(5, nv.getSdt());
            pstmt.setDate(6, Date.valueOf(nv.getNgaySinh()));
            pstmt.setString(7, nv.getMaNV());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private NhanVien mapToNhanVien(ResultSet rs) throws SQLException {

        String maNV = rs.getString("MaNV");
        String chucVu = rs.getString("ChucVu");
        String ho = rs.getString("Ho");
        String ten = rs.getString("Ten");
        String diaChi = rs.getString("DiaChi");
        String sdt = rs.getString("SDT");
        LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();

        return new NhanVien(maNV, chucVu, ho, ten, diaChi, sdt, ngaySinh);
    }
}