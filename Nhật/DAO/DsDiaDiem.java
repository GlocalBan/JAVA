/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.KetNoiCSDL;
import DTO.DiaDiem;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
/**
 *
 * @author Nhat
 */
public class DsDiaDiem {

    public ArrayList<DiaDiem> getDs() {
        ArrayList<DiaDiem> ds =new ArrayList<>();
        
        String sql ="Select * from Diadiem";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiem dd=maptoDiaDiem(rs);
                ds.add(dd);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ds;
    }
    
     public DiaDiem TimDiaDiem(String tendd){
        String sql = "Select * from DiaDiem where TenDiaDiem=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setString(1, tendd);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    return maptoDiaDiem(rs);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public DiaDiem maptoDiaDiem(ResultSet rs) throws SQLException{
        String tendd = rs.getString("TenDiaDiem");
        Date sqldate=rs.getDate("NgayThucHien");
        LocalDate ngaythuchien=null;
        if(sqldate!=null){
            ngaythuchien=sqldate.toLocalDate();
        }
        Float tongchi =rs.getFloat("TongChi");
        
        return new DiaDiem(tendd,ngaythuchien,tongchi);
    }
    
    public boolean themDiaDiem(DiaDiem dd){
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
    
    public boolean xoaDiaDiem(DiaDiem dd){
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
    
    public boolean suaDiaDiem(DiaDiem dd){
        String sql ="Update DiaDiem set TenDiaDiem=?,NgayThucHien=?,TongChi=? where Tendiadiem=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, dd.getTenDiaDiem());
            if(dd.getNgayThucHien()!=null){
                ps.setDate(2, Date.valueOf(dd.getNgayThucHien()));
            }
            ps.setFloat(3, dd.getTongChi());
            ps.setString(4, dd.getTenDiaDiem());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList timdd(String tendd){
        ArrayList<DTO.DiaDiem> ds=new ArrayList<>();
        String sql ="Select * from Diadiem where tendiadiem like ?";
        try(Connection conn= KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, "%" + tendd + "%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DTO.DiaDiem dd=dd=maptoDiaDiem(rs);
                ds.add(dd);
            }   
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return ds;
    }
}
