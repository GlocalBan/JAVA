/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.dao;
import org.example.dto.CTietHDDTO;
import org.example.dto.HoaDonDTO;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import org.example.helper.DateHelper;
/**
 *
 * @author Nhat
 */
public class HoaDonDAO {
    public ArrayList<HoaDonDTO> getDsHoaDon(){
        ArrayList<HoaDonDTO> list= new ArrayList<>();
        
        String sql="SELECT * FROM HoaDon";
        
        try(Connection conn=KetNoiCSDL.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery()){
        while(rs.next()){
            HoaDonDTO hd=maptoHd(rs);
            list.add(hd);
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public HoaDonDAO() {
    }
    public HoaDonDTO timHoaDon(String mahd){
        String sql="SELECT * FROM HOADON where mahd=?";
        try(Connection conn=KetNoiCSDL.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, mahd);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return maptoHd(rs);
            }
        }catch(SQLException e){
                    e.printStackTrace();
                    }
        return null;
    }
    
    public HoaDonDTO maptoHd(ResultSet rs) throws SQLException{
        String mahd=rs.getString("MaHD");
        String makhtour = rs.getString("MaKHTour");
        String makhdi = rs.getString("MaKHangDat");
        int tongtien =rs.getInt("TongTien");
        String manv=rs.getString("MaNV");
        int soluong=rs.getInt("soluong");
        LocalDate ngay =LocalDate.parse(rs.getDate("ngay").toString());
        return new HoaDonDTO(mahd, makhtour, makhdi, manv,ngay,soluong, tongtien);
    }
public boolean themHoaDon(HoaDonDTO hd) {
    if(timHoaDon(hd.getMaHD()) != null) {
        return false;
    }
    
    String sqlhd = "Insert into hoadon(mahd,makhtour,makhangdat,tongtien,manv,soluong,ngay) values(?,?,?,?,?,?,?)";
    Connection conn = null;
    
    try {
        conn = KetNoiCSDL.getConnection();
        
        try (PreparedStatement ps = conn.prepareStatement(sqlhd)) {
            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getMaKHTour());
            ps.setString(3, hd.getMaKHDat());
            ps.setFloat(4, hd.getTongTien()); // Nếu DB là int thì đổi thành ps.setInt(4, (int) hd.getTongTien());
            ps.setString(5, hd.getMaNV());
            ps.setFloat(6, hd.getSoluong());  // Nếu DB là int thì đổi thành ps.setInt(6, (int) hd.getSoluong());
            ps.setDate(7, java.sql.Date.valueOf(hd.getNgay()));
            
            int rowAffected = ps.executeUpdate();
            return rowAffected > 0; // Trả về true nếu thêm thành công
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return false;
}
    public boolean xoaHd(HoaDonDTO hd){
            String sqlct = "Delete from cthoadon where mahd=?";
            String sql="Delete from hoadon where mahd=?";
            
            Connection connection=null;
            try{
                connection=KetNoiCSDL.getConnection();
                connection.setAutoCommit(false);
                try(PreparedStatement ps=connection.prepareStatement(sqlct)){
                    ps.setString(1, hd.getMaHD());
                    ps.executeUpdate();
                }
                try(PreparedStatement ps=connection.prepareStatement(sql)){
                    ps.setString(1, hd.getMaHD());
                    int result=ps.executeUpdate();
                    if(result >0){
                    connection.commit();
                    return true;
                }else{
                        connection.rollback();
                        return false;
                        }
                }
            }catch (SQLException ex) {
            ex.printStackTrace();
            if(connection!=null){
                try{
                    connection.rollback();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }finally{
                if(connection!=null){
                    try{
                        connection.close();
                    }catch(SQLException e){
                        
                    }
                }
            }
          return false;
    }
    
    public boolean suaHd(HoaDonDTO hd){
        String sqlhd ="Update hoadon set makhtour=?,makhangdat=?,tongtien=?,manv=?,soluong=?,ngay=? where mahd=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlhd)){
            ps.setString(1, hd.getMaKHTour());
            ps.setString(2, hd.getMaKHDat());
            ps.setFloat(3, hd.getTongTien());
            ps.setString(4, hd.getMaNV());
            ps.setInt(5, hd.getSoluong());
            ps.setDate(6, java.sql.Date.valueOf(hd.getNgay()));
            ps.setString(7, hd.getMaHD());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public float laygia(String makht){
        float gia=0;
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
        
    public ArrayList<HoaDonDTO> timNangcao(String tencot,String key){
        ArrayList<HoaDonDTO> ds =new ArrayList<>();
        String sql ="Select * from Hoadon where "+ tencot +" like ?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, "%"+key+"%");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                HoaDonDTO hd=maptoHd(rs);
                ds.add(hd);
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
        return ds;
    }
    public boolean xoaCt(String mahd, String makh) {
        String sql = "DELETE FROM cthoadon WHERE mahd = ? AND makhang = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mahd);
            ps.setString(2, makh);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public LocalDate layNgay(String mahd){
        String sql ="Select ngay from Hoadon where mahd=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(0, mahd);
            
            ResultSet rs=ps.executeQuery();
            LocalDate ngay=null;
            if(rs.next()){
                ngay=DateHelper.toLocalDate(rs.getDate("Ngay").toString().trim());
            }
            return ngay;
        }catch(SQLException e){
            return null;
        }
    }
    
    public ArrayList<HoaDonDTO> getHdtheoNgay(java.util.Date ngay){
        ArrayList<HoaDonDTO> ds =new ArrayList<>();
        String sql ="Select * from Hoadon where ngay=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                HoaDonDTO hd=maptoHd(rs);
                ds.add(hd);
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
        return ds;
    }
}