/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.dao;
import org.example.dto.DiaDiemDTO;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import org.example.bus.HoaDonBUS;
/**
 *
 * @author Nhat
 */
public class DiaDiemDAO {

    public ArrayList<DiaDiemDTO> getDs() {
        ArrayList<DiaDiemDTO> ds =new ArrayList<>();
        
        String sql ="Select * from Diadiem";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiemDTO dd=maptoDiaDiem(rs);
                ds.add(dd);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ds;
    }
    
     public DiaDiemDTO TimDiaDiem(String tendd){
        String sql = "Select * from DiaDiem where TenDiaDiem=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setNString(1, tendd.trim());
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    return maptoDiaDiem(rs);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public DiaDiemDTO maptoDiaDiem(ResultSet rs) throws SQLException{
        String tendd = rs.getString("TenDiaDiem");
        Date sqldate=rs.getDate("NgayThucHien");
        LocalDate ngaythuchien=null;
        if(sqldate!=null){
            ngaythuchien=sqldate.toLocalDate();
        }
        Float tongchi =rs.getFloat("TongChi");
        
        return new DiaDiemDTO(tendd,ngaythuchien,tongchi);
    }
    
    public boolean themDiaDiem(DiaDiemDTO dd){
        String sql = "Insert into DiaDiem(TenDiaDiem,NgayThucHien,TongChi) Values (?,?,?) ";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, dd.getTenDiaDiem());
   
            if(dd.getNgayThucHien()!=null){
                ps.setDate(2, Date.valueOf(dd.getNgayThucHien()));
            }
            ps.setFloat(3,dd.getTongChi());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
                    return false;
        }
    }
    
    public boolean xoaDiaDiem(DiaDiemDTO dd){
        String sql = "Delete from DiaDiem where TenDiaDiem=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, dd.getTenDiaDiem());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean suaDiaDiem(DiaDiemDTO dd,String tendd){
        String sql ="Update DiaDiem set TenDiaDiem=?,NgayThucHien=?,TongChi=? where Tendiadiem=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setNString(1, dd.getTenDiaDiem());
            if(dd.getNgayThucHien()!=null){
                ps.setDate(2, Date.valueOf(dd.getNgayThucHien()));
            }else{
                ps.setNull(2, java.sql.Types.DATE);
            }
            ps.setFloat(3, dd.getTongChi());
            ps.setNString(4, tendd);
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<DiaDiemDTO> timdd(String tendd){
        ArrayList<DiaDiemDTO> ds=new ArrayList<>();
        String sql ="Select * from Diadiem where tendiadiem like ?";
        try(Connection conn= KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, "%" + tendd + "%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiemDTO dd=maptoDiaDiem(rs);
                ds.add(dd);
            }   
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return ds;
    }
    
    public ArrayList<DiaDiemDTO> getDstheongay(java.util.Date ngay){
        ArrayList<DiaDiemDTO> dd=new ArrayList<>();
        String sql ="Select * from DiaDiem where ngaythuchien=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiemDTO dddto=maptoDiaDiem(rs);
                dd.add(dddto);
            }
        }catch(SQLException e){
            return null;
        }
        return dd;
    }
}
