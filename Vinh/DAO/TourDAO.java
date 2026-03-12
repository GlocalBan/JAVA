package DAO;

import DTO.TourDTO;
import java.sql.*;
import java.util.ArrayList;

public class TourDAO {
    Connection c = KetNoiCSDL.getConnection();
    Statement st = null;

    public TourDAO(){
        ArrayList<TourDTO> lsTour = new ArrayList<>();
    }

    //get all tours
    public ArrayList<TourDTO> getAllTours(){
        ArrayList<TourDTO> lsTour = new ArrayList<>();
        try {
            String sql = "select * from tour";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                TourDTO t = new TourDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                lsTour.add(t);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lsTour;
    }

    //add
    public boolean addTour(TourDTO t){
        try{
            String sql = "Insert into tour values(";
            sql += "'" +  t.getMaTour() + "'";
            sql += ","  + "'" +  t.getTen() + "'";
            sql += ","  + "'" +  t.getSoNgay() + "'";
            sql += ","  + "'" +  t.getDonGia() + "'";
            sql += ","  + "'" +  t.getSoNguoi() + "'";
            sql += ","  + "'" +  t.getDiaDiemKhoiHanh() + "'";
            sql += ","  + "'" +  t.getMaLoaiTour() + "'";
            sql += ")";
            st = c.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeTour(String matour){
        try{
            String sql = "delete from tour where matour = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, matour);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    //edit
    public boolean editTour(TourDTO t){
        String id = t.getMaTour();

        try{
            String qry = "update tour set ";
            qry += "ten = " + "'" + t.getTen() + "'";
            qry += ",songay = " + "'" + t.getSoNgay() + "'";
            qry += ",dongia = " + "'" + t.getDonGia() + "'";
            qry += ",socho = " + "'" + t.getSoNguoi() + "'";
            qry += ",ddkhoihanh = " + "'" + t.getDiaDiemKhoiHanh() + "'";
            qry += ",ddkhoihanh = " + "'" + t.getMaLoaiTour() + "'";
            qry += "where matour = '" + id + "';";
            st = c.createStatement();
            st.executeUpdate(qry);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
