package org.example.dao;

import org.example.dto.KeHoachTourDTO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class KeHoachTourDAO {
    Connection c = MyConnection.getConnection();
    Statement st = null;

    public KeHoachTourDAO(){
        ArrayList<KeHoachTourDTO> lsKeHoachTour;
    }

    //get all ke hoach tours
    public ArrayList<KeHoachTourDTO> getAllKeHoachTours(){
        ArrayList<KeHoachTourDTO> lsKeHoachTour = new ArrayList<>();
        try {
            String sql = "select * from kehoachtour";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                KeHoachTourDTO t = new KeHoachTourDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getLong(6),
                        rs.getLong(7),
                        rs.getString(8),
                        rs.getString(9)
                );
                lsKeHoachTour.add(t);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lsKeHoachTour;
    }

    //add
    public boolean addKeHoachTour(KeHoachTourDTO t){
        try{
            String sql = "Insert into kehoachtour values(";
            sql += "'" +  t.getMaKHTour() + "'";
            sql += ","  + "'" +  t.getNgayKhoiHanh() + "'";
            sql += ","  + "'" +  t.getNgayKetThuc() + "'";
            sql += ","  + "'" +  t.getTongSoNguoi() + "'";
            sql += ","  + "'" +  t.getTongSoVe() + "'";
            sql += ","  + "'" +  t.getTongChi() + "'";
            sql += ","  + "'" +  t.getTongThu() + "'";
            sql += ","  + "'" +  t.getMaTour() + "'";
            sql += ","  + "'" +  t.getMaNVHD() + "'";
            sql += ")";
            st = c.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeKeHoachTour(String maKeHoachTour){
        try{
            String sql = "delete from kehoachtour where makhtour = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maKeHoachTour);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //edit
    public void editKeHoachTour(KeHoachTourDTO t){
        String id = t.getMaKHTour();

        try{
            String qry = "update kehoachtour set ";
            qry += "ngaykhoihanh = " + "'" + t.getNgayKhoiHanh() + "'";
            qry += ",ngayketthuc = " + "'" + t.getNgayKetThuc() + "'";
            qry += ",tongsonguoi = " + "'" + t.getTongSoNguoi() + "'";
            qry += ",tongsove = " + "'" + t.getTongSoVe() + "'";
            qry += ",tongchi = " + "'" + t.getTongChi() + "'";
            qry += ",tongthu = " + "'" + t.getTongThu() + "'";
            qry += ",matour = " + "'" + t.getMaTour() + "'";
            qry += ",manvhd = " + "'" + t.getMaNVHD() + "'";
            qry += "where makhtour = '" + id + "';";
            st = c.createStatement();
            st.executeUpdate(qry);
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật kế hoạch tour!");
        }
    }
}
