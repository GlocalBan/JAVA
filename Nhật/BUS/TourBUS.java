package BUS;

import DAO.*;
import DTO.*;

import javax.swing.*;
import java.util.ArrayList;

public class TourBUS {
    private ArrayList<TourDTO> lsTour;
    private TourDAO tourDAO;

    //constructor
    public TourBUS(){
        tourDAO = new TourDAO();
        lsTour = new ArrayList<>();
    }

    public ArrayList<TourDTO> getAllTours(){
        lsTour = tourDAO.getAllTours();
        return lsTour;
    }

    public boolean addTour(TourDTO t){
        if(t == null) return false;

        if(t.getSoNgay() <= 0 || t.getSoNguoi() < 0){
            return false;
        }

        boolean success = tourDAO.addTour(t);
        if(success) lsTour.add(t);

        return success;
    }

    public boolean editTour(TourDTO t){
        if(t.getSoNgay() <= 0 || t.getSoNguoi() < 0)
            return false;

        return tourDAO.editTour(t);
    }

    public boolean removeTour(String maTour){
        return tourDAO.removeTour(maTour);
    }

    public long totalCost(String maTour){
        ArrayList<TourDTO> lsTour = getAllTours();
        for (TourDTO t : lsTour) {
            if (t.getMaTour().equalsIgnoreCase(maTour))
                return t.getSoNgay() * t.getDonGia();
        }
        return 0;
    }

    public ArrayList<TourDTO> search(String keyWord){
        ArrayList<TourDTO> list = new ArrayList<>();
        for (TourDTO lt : lsTour){
            if(lt.getTen().trim().toLowerCase().contains(keyWord)){
                list.add(lt);
            }
        }
        return list;
    }

    public TourDTO getByID(String maTour){
        TourDTO tour = new TourDTO();
        for (TourDTO t : lsTour){
            if(t.getMaTour().trim().equalsIgnoreCase(maTour)){
                tour = t;
                break;
            }
        }
        return tour;
    }

    public boolean existedTourWithID(String maTour){
        for (TourDTO t : lsTour){
            if(t.getMaTour().trim().equalsIgnoreCase(maTour))
                return true;
        }
        return false;
    }
}
