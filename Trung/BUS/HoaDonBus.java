/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.DsHoaDon;
import DTO.CTietHD;
import DTO.HoaDon;
import java.util.ArrayList;

/**
 *
 * @author Nhat
 */
public class HoaDonBus {
    public static ArrayList<HoaDon> ds;
    public static DsHoaDon dao = new DsHoaDon();
    
    public HoaDonBus(){
        if(ds==null){
            ds=dao.getDsHoaDon();
        }
    }
    
    public void docDs(){
        ds=dao.getDsHoaDon();
    }
    
    public static ArrayList<HoaDon> getDs(){
        if (ds == null) {
        ds = dao.getDsHoaDon();
    }
    return ds;
    }
    
    public boolean timHd(HoaDon h){
        if(dao.timHoaDon(h.getMaHD())!=null){
            for(HoaDon hd: ds){
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
            ds=new ArrayList<HoaDon>();
            ds=dao.getDsHoaDon();
        }
        return ds;
    }
    public HoaDon timHd(String mahd){
        if(dao.timHoaDon(mahd)!=null){
            for(HoaDon hd: ds){
                if(hd.getMaHD().equals(mahd)){
                    return dao.timHoaDon(mahd);
                }
            }
        }
        return null;
    }
    
    public boolean themHoaDon(HoaDon hd,ArrayList<CTietHD> listct){
        if(timHd(hd)){
            return false;
        }
        
        boolean kq=dao.themHoaDon(hd, listct);
        if(kq){
            ds.add(hd);
            return true;
        }
        return false;
    }
    
    public boolean xoaHoaDon(String mahd){
        HoaDon hd=dao.timHoaDon(mahd);
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
    
    public boolean suaHoaDon(HoaDon hd){
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
    

    
    public ArrayList<DTO.HoaDon> timNangcao(String loai, String key){
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
        ArrayList<DTO.HoaDon> ds =dao.timNangcao(tencot, key);
        return ds;
}
    
}