/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.bus;
import org.example.dao.HoaDonDAO;
import org.example.dto.CTietHDDTO;
import org.example.dto.HoaDonDTO;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author Nhat
 */
public class HoaDonBUS {
    public static ArrayList<HoaDonDTO> ds;
    public static HoaDonDAO dao = new HoaDonDAO();
    
    public HoaDonBUS(){
        if(ds==null){
            ds=dao.getDsHoaDon();
        }
    }
    
    public void docDs(){
        ds=dao.getDsHoaDon();
    }
    
    public static ArrayList<HoaDonDTO> getDs(){
        if (ds == null) {
        ds = dao.getDsHoaDon();
    }
    return ds;
    }
    
    public boolean timHd(HoaDonDTO h){
        if(dao.timHoaDon(h.getMaHD())!=null){
            for(HoaDonDTO hd: ds){
                if(hd.getMaHD().equals(h.getMaHD())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList docDS(){
        if(ds==null)
        {
            ds=new ArrayList<HoaDonDTO>();
            ds=dao.getDsHoaDon();
        }
        return ds;
    }
    public HoaDonDTO timHd(String mahd){
        if(dao.timHoaDon(mahd)!=null){
            for(HoaDonDTO hd: ds){
                if(hd.getMaHD().equals(mahd)){
                    return dao.timHoaDon(mahd);
                }
            }
        }
        return null;
    }
    
    public boolean themHoaDon(HoaDonDTO hd){
        if(timHd(hd)){
            return false;
        }
        
        boolean kq=dao.themHoaDon(hd);
        if(kq){
            ds.add(hd);
            return true;
        }
        return false;
    }
    
    public boolean xoaHoaDon(String mahd){
        HoaDonDTO hd=dao.timHoaDon(mahd);
        boolean b=timHd(hd);
        if(hd==null){
            return false;
        }
        
        if(b!=true){
            return false;
        }
        dao.xoaHd(hd);
        ds.remove(hd);
        return true;
    }
    
    public boolean suaHoaDon(HoaDonDTO hd){
        if(!timHd(hd)){
            return false;
        }
        if(dao.timHoaDon(hd.getMaHD())!=null){
            boolean kt=dao.suaHd(hd);

            if(kt==true){
                for(int i=0;i<ds.size();i++){
                if(hd.getMaHD().equals(ds.get(i).getMaHD())){
                    ds.set(i, hd);
                }
            }
            }else return false;
            return true;
        }
        return false;
    }
    

    
    public ArrayList<HoaDonDTO> timNangcao(String loai, String key){
        if(key.trim().isEmpty()){
            return getDs();
    }
        String tencot="";
        if(loai.equals("Mã hóa đơn")){
            tencot="mahd";
        }
        else if(loai.equals("Mã kế hoạch tour")){
            tencot="makhtour";
    }
        else if(loai.equals("Mã khách hàng đặt")){
            tencot="makhangdat";
        }else if(loai.equals("Mã nhân viên")){
            tencot="manv";
        }
        ArrayList<org.example.dto.HoaDonDTO> ds =dao.timNangcao(tencot, key);
        return ds;
}
    public LocalDate layngay(String mahd){
        if(mahd.isEmpty()){
            return null;
    }   
        if(ds==null){
            dao.getDsHoaDon();
        }
        return dao.layNgay(mahd);
    }
    
    public ArrayList<HoaDonDTO> getHDtheongay(java.util.Date ngay){

        if(ngay==null){
            return ds;
        }
        else{
            return dao.getHdtheoNgay(ngay);
        }
    }
}