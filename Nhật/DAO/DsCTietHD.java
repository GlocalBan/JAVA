/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import DTO.CTietHD;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Nhat
 */
public class DsCTietHD {
    public DsCTietHD() {
    }

    public ArrayList<CTietHD> getDs() {
        ArrayList<CTietHD> ds=new ArrayList<>();
        String sql ="Select * from CThoadon";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                CTietHD ct=maptoCthd(rs);
                ds.add(ct);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<CTietHD> getDstheoma(String mahd){
        ArrayList<CTietHD> ds=new ArrayList<>();
        String sql ="Select * from CThoadon where mahd=?";
        try(Connection conn =KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setString(1, mahd);
                    ResultSet rs=ps.executeQuery();
                    while(rs.next()){
                        CTietHD cthd=maptoCthd(rs);
                        ds.add(cthd);
                    }
                }
        catch(SQLException ex){
            ex.printStackTrace();
        
    }
        return ds;
    }
    

    public CTietHD maptoCthd(ResultSet rs) throws SQLException{
        String MaHD =rs.getString("MaHD");
        String MaKHDi =rs.getString("MaKHang");
        Float GiaVe =rs.getFloat("GiaVe");
        return new CTietHD(MaHD,MaKHDi,GiaVe);
    }
    
    public CTietHD TimHD(String mahd){
        String sql = "Select * from CThoadon where mahd=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setString(1, mahd);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    return maptoCthd(rs);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean themCtietHD(CTietHD ct){
        String sqlcheck = "Select * from cthoadon where mahd=? and makhang=?";
        String sqlinsert = "Insert into cthoadon(mahd,makhang,giave) Values(?,?,?)";
        String sqlUpdateHD = "UPDATE hoadon SET soluong = soluong + 1, tongtien = tongtien + ? WHERE mahd = ?";

        Connection conn = null;
        try {
            conn = KetNoiCSDL.getConnection();
            conn.setAutoCommit(false); 

            try (PreparedStatement ps = conn.prepareStatement(sqlcheck)) {
                ps.setString(1, ct.getMaHD());
                ps.setString(2, ct.getMaKHDi());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    conn.rollback(); 
                    return false;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlinsert)) {
                ps.setString(1, ct.getMaHD());
                ps.setString(2, ct.getMaKHDi());
                ps.setFloat(3, ct.getGiaVe());
                if (ps.executeUpdate() <= 0) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateHD)) {
                ps.setFloat(1, ct.getGiaVe());
                ps.setString(2, ct.getMaHD());
                
                if (ps.executeUpdate() > 0) {
                    conn.commit(); 
                    return true;
                } else {
                    conn.rollback(); 
                }
            }

        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean xoaCtietHd(String mahd,String makh){
        String sqlXoa = "DELETE FROM cthoadon WHERE mahd=? AND makhang=?";

        String sqlUpdateHD = "UPDATE hoadon SET soluong = soluong - 1, "
                           + "tongtien = tongtien - (SELECT t.dongia FROM kehoachtour k JOIN tour t ON k.matour = t.matour WHERE k.makhtour = (SELECT makhtour FROM hoadon WHERE mahd=?)) "
                           + "WHERE mahd=?";
        
        Connection conn = null;
        try {
            conn = KetNoiCSDL.getConnection();
            conn.setAutoCommit(false);

            // 1. Thực hiện xóa chi tiết
            try (PreparedStatement psXoa = conn.prepareStatement(sqlXoa)) {
                psXoa.setString(1, mahd);
                psXoa.setString(2, makh);
                psXoa.executeUpdate();
            }

            try (PreparedStatement psUp = conn.prepareStatement(sqlUpdateHD)) {
                psUp.setString(1, mahd);
                psUp.setString(2, mahd);
                psUp.executeUpdate();
            }

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
        }
        return false;
    }
     public float laygia(String mahd){
        float gia=0;
        String makht="";
        String sql1="Select makhtour from hoadon where mahd=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql1)){
            ps.setString(1, mahd);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                makht=rs.getString("makhtour");
            }
        }catch (SQLException ex) {
             ex.printStackTrace();
         }
        String sql ="Select t.dongia from kehoachtour k join tour t on k.matour = t.matour where k.makhtour=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, makht);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                gia=rs.getFloat("Dongia");
            }}
        catch(SQLException e){
                    e.printStackTrace();
                    }
        return gia;
        }
     
     public ArrayList<DTO.CTietHD> timNangcao(String tencot,String key){
         ArrayList<DTO.CTietHD> ds =new ArrayList<>();
         
         String sql="Select * from cthoadon where "+tencot+" like ?";
        
         try(Connection conn =KetNoiCSDL.getConnection();
                 PreparedStatement ps=conn.prepareStatement(sql)){
             ps.setString(1, "%" + key + "%");
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 DTO.CTietHD ct =maptoCthd(rs);
                 ds.add(ct);
             }
             
         }catch(SQLException e){
             e.printStackTrace();
         }
         return ds;
     }
     
         public boolean suaCthd(CTietHD ct){
        String sql = "Update cthoadon set giave=? where mahd=? and makhang=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setFloat(1,ct.getGiaVe() );
            ps.setString(2, ct.getMaHD());
            ps.setString(3, ct.getMaKHDi());
            
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
