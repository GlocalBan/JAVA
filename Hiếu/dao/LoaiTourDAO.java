package org.example.dao;

import org.example.dto._LoaiTourDTO;
import org.example.dto._TourDTO;

import java.sql.*;
import java.util.ArrayList;

public class _LoaiTourDAO {
    Connection c = _MyConnection.getConnection();
    Statement st = null;

    public _LoaiTourDAO(){
        ArrayList<_TourDTO> lsTour = new ArrayList<>();
    }

    //get all tours
    public ArrayList<_LoaiTourDTO> getAllLoaiTour(){
        ArrayList<_LoaiTourDTO> lsCate = new ArrayList<>();
        try {
            String sql = "select * from loaitour";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                _LoaiTourDTO t = new _LoaiTourDTO(
                        rs.getString(1),
                        rs.getString(2)
                );
                lsCate.add(t);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lsCate;
    }

    //add
    public boolean addLoaiTour(_LoaiTourDTO t){
        try{
            String sql = "Insert into loaitour values(";
            sql += "'" +  t.getMaLoaiTour() + "'";
            sql += ","  + "'" +  t.getTheLoai() + "'";
            sql += ")";
            st = c.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeLoaiTour(String maLoaiTour){
        try{
            String sql = "delete from loaitour where maloaitour = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maLoaiTour);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //edit
    public boolean editLoaiTour(_LoaiTourDTO t){
        String id = t.getMaLoaiTour();

        try{
            String qry = "update loaitour set ";
            qry += "theloai = '" + t.getTheLoai() + "'";
            qry += "where maloaitour = '" + id + "';";
            st = c.createStatement();
            st.executeUpdate(qry);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
