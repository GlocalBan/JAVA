/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.KHang_KHTour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class KHang_KHTourDAO {
    public ArrayList<KHang_KHTour> layDanhSachKHang_KHTour() {
        ArrayList<KHang_KHTour> dsKHang_KHTour = new ArrayList<>();
        String sql = "SELECT * FROM KHang_KHTour";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String MaKHTour = rs.getString("MaKHTour");
                String MaKHang = rs.getString("MaKHang");
                long GiaVe = rs.getLong("GiaVe");
                KHang_KHTour kht = new KHang_KHTour(MaKHTour, MaKHang, GiaVe);
                dsKHang_KHTour.add(kht);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKHang_KHTour;
    }

public boolean themKHang_KHTour(KHang_KHTour kht) {

    String sql = "INSERT INTO khang_khtour (MaKHang, MaKHTour, GiaVe) VALUES (?, ?, ?)";

    try (Connection conn = KetNoiCSDL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, kht.getMaKHang().trim());
        pstmt.setString(2, kht.getMaKHTour().trim());
        pstmt.setLong(3, kht.getGiaVe());

        int rows = pstmt.executeUpdate();
        return rows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public KHang_KHTour timKHang_KHTourTheoMaTour(String MaKHTour) {
        String sql = "SELECT * FROM KHang_KHTour WHERE MaKHTour = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, MaKHTour);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String MaKHang = rs.getString("MaKHang");
                    long GiaVe = rs.getLong("GiaVe");
                    return new KHang_KHTour(MaKHTour, MaKHang, GiaVe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KHang_KHTour> timKHang_KHTours(String column, String value) {
        List<KHang_KHTour> results = new ArrayList<>();
        String sql = "SELECT * FROM KHang_KHTour WHERE " + column + " LIKE ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + value + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String MaKHTour = rs.getString("MaKHTour");
                    String MaKHang = rs.getString("MaKHang");
                    long GiaVe = rs.getLong("GiaVe");
                    results.add(new KHang_KHTour(MaKHTour, MaKHang, GiaVe));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<KHang_KHTour> timKHang_KHToursTheoHo(String ho) {
        List<KHang_KHTour> results = new ArrayList<>();
        String sql = """
            SELECT kk.MaKHTour, kk.MaKHang, kk.GiaVe
            FROM KHang_KHTour kk
            JOIN khachhang kh ON kk.MaKHang = kh.MaKHang
            WHERE kh.Ho LIKE ?
            """;
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + ho + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String MaKHTour = rs.getString("MaKHTour");
                    String MaKHang = rs.getString("MaKHang");
                    long GiaVe = rs.getLong("GiaVe");
                    results.add(new KHang_KHTour(MaKHTour, MaKHang, GiaVe));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<KHang_KHTour> timKHang_KHToursTheoTen(String ten) {
        List<KHang_KHTour> results = new ArrayList<>();
        String sql = """
            SELECT kk.MaKHTour, kk.MaKHang, kk.GiaVe
            FROM KHang_KHTour kk
            JOIN khachhang kh ON kk.MaKHang = kh.MaKHang
            WHERE kh.Ten LIKE ?
            """;
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + ten + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String MaKHTour = rs.getString("MaKHTour");
                    String MaKHang = rs.getString("MaKHang");
                    long GiaVe = rs.getLong("GiaVe");
                    results.add(new KHang_KHTour(MaKHTour, MaKHang, GiaVe));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public boolean xoaKHang_KHTour(String MaKHTour, String MaKHang) {
        String sql = "DELETE FROM KHang_KHTour WHERE MaKHTour = ? AND MaKHang = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, MaKHTour);
            pstmt.setString(2, MaKHang);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private KHang_KHTour mapToKHang_KHTour(ResultSet rs) throws SQLException {
        String MaKHTour = rs.getString("MaKHTour");
        String MaKHang = rs.getString("MaKHang");
        long GiaVe = rs.getLong("GiaVe");
        return new KHang_KHTour(MaKHTour, MaKHang, GiaVe);
    }

    public boolean capNhatKHang_KHTour(KHang_KHTour kht) {
        String sql = "UPDATE KHang_KHTour SET MaKHang = ?, GiaVe = ? WHERE MaKHTour = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kht.getMaKHang());
            pstmt.setLong(2, kht.getGiaVe());
            pstmt.setString(3, kht.getMaKHTour());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long layDonGiaTheoMaKHTour(String maKHTour){
    String sql = """
        SELECT DonGia
        FROM tour t
        JOIN kehoachtour k ON t.MaTour = k.MaTour
        WHERE k.MaKHTour = ?
        """;

    try(Connection conn = KetNoiCSDL.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

        ps.setString(1, maKHTour);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return rs.getLong("DonGia");
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return 0;
}
}
