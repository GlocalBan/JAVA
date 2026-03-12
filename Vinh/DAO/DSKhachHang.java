/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class DSKhachHang {
    public ArrayList<KhachHang> layDanhSachKHang() {
        ArrayList<KhachHang> dsKH = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                KhachHang khang = mapToKhachHang(rs);
                dsKH.add(khang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKH;
    }

    public boolean themKhachHang(KhachHang khang) {
        String sql = "INSERT INTO KhachHang (MaKH, Ho, Ten, DiaChi, SDT, NgaySinh) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, khang.getMaKH());
            pstmt.setString(2, khang.getHo());
            pstmt.setString(3, khang.getTen());
            pstmt.setString(4, khang.getDiaChi());
            pstmt.setString(5, khang.getSdt());
            pstmt.setDate(6, khang.getNgaySinh());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public KhachHang timKhachHangTheoMa(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang timKhachHangTheoHo(String ho) {
        String sql = "SELECT * FROM KhachHang WHERE Ho = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ho);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang timKhachHangTheoTen(String ten) {
        String sql = "SELECT * FROM KhachHang WHERE Ten = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ten);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean xoaKhachHang(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaKhachHang(KhachHang khang) {
        String sql = "UPDATE KhachHang SET Ho = ?, Ten = ?, DiaChi = ?, SDT = ?, NgaySinh = ? WHERE MaKH = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, khang.getHo());
            pstmt.setString(2, khang.getTen());
            pstmt.setString(3, khang.getDiaChi());
            pstmt.setString(4, khang.getSdt());
            pstmt.setDate(5, khang.getNgaySinh());
            pstmt.setString(6, khang.getMaKH());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private KhachHang mapToKhachHang(ResultSet rs) throws SQLException {
        String maKH = rs.getString("MaKH");
        String ho = rs.getString("Ho");
        String ten = rs.getString("Ten");
        String diaChi = rs.getString("DiaChi");
        String sdt = rs.getString("SDT");
        Date ngaySinh = rs.getDate("NgaySinh");
        return new KhachHang(maKH, ho, ten, diaChi, sdt, ngaySinh);
    }

    public boolean capNhatKhachHang(KhachHang khang) {
        String sql = "UPDATE KhachHang SET Ho = ?, Ten = ?, DiaChi = ?, SDT = ?, NgaySinh = ? WHERE MaKH = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, khang.getHo());
            pstmt.setString(2, khang.getTen());
            pstmt.setString(3, khang.getDiaChi());
            pstmt.setString(4, khang.getSdt());
            pstmt.setDate(5, khang.getNgaySinh());
            pstmt.setString(6, khang.getMaKH());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
