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

/**
 *
 * @author Nhat
 */
public class DiaDiemDAO {

    public ArrayList<DiaDiemDTO> getDs() {
        ArrayList<DiaDiemDTO> ds =new ArrayList<>();
        
        String sql ="Select * from Diadiem";
        try(Connection conn= MyConnection.getConnection();
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
        try(Connection conn= MyConnection.getConnection();
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
        String diachi=rs.getString("DiaChi");
        String quocgia =rs.getString("QuocGia");
        
        return new DiaDiemDTO(tendd,diachi,quocgia);
    }
    
    public boolean themDiaDiem(DiaDiemDTO dd){
        String sql = "Insert into DiaDiem(TenDiaDiem,DiaChi,QuocGia) Values (?,?,?) ";
        
        try(Connection conn= MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setNString(1, dd.getTenDiaDiem());
            ps.setNString(2,dd.getdiachi());
            ps.setNString(3,dd.getQuocGia());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
                    return false;
        }
    }
    
    public boolean xoaDiaDiem(DiaDiemDTO dd){
        String sql = "Delete from DiaDiem where TenDiaDiem=?";
        
        try(Connection conn= MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, dd.getTenDiaDiem());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean suaDiaDiem(DiaDiemDTO dd,String tendd){
        String sql ="Update DiaDiem set TenDiaDiem=?,DiaChi=?,QuocGia=? where Tendiadiem=?";
        
        try(Connection conn= MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setNString(1, dd.getTenDiaDiem());
            ps.setNString(2,dd.getdiachi());
            ps.setNString(3, dd.getQuocGia());
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
        try(Connection conn= MyConnection.getConnection();
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
        try(Connection conn= MyConnection.getConnection();
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
    public ArrayList<DiaDiemDTO> getDstheoDiaChi(String diachi){
        ArrayList<DiaDiemDTO> ds=new ArrayList<>();
        String sql ="Select * from DiaDiem where diachi like";
        try(Connection conn =MyConnection.getConnection();
        PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setString(1,"%"+diachi+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiemDTO dd=maptoDiaDiem(rs);
                ds.add(dd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ds;
    }
    public ArrayList<DiaDiemDTO> getDstheoQuocGia(String diachi){
        ArrayList<DiaDiemDTO> ds=new ArrayList<>();

        String sql="Select * from DiaDiem where quocgia like ?";

        try(Connection conn=MyConnection.getConnection();
        PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setString(1,"%"+diachi+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DiaDiemDTO dd=maptoDiaDiem(rs);
                ds.add(dd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
