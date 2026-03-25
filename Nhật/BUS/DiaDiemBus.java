/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.bus;

import org.example.dao.DiaDiemDAO;
import org.example.dto.DiaDiemDTO;
import java.util.ArrayList;
/**
 *
 * @author Nhat
 */
public class DiaDiemBUS {
    public static ArrayList<DiaDiemDTO> ds;
    public static DiaDiemDAO dao=new DiaDiemDAO();
    
    public DiaDiemBUS(){
        if(ds==null){
           ds=dao.getDs();
        }
    }
    
    public void DocDs(){
        dao.getDs();
    }
 
    public static ArrayList<DiaDiemDTO> getDs(){
        return ds;
    }
    
    public boolean timDiaDiem(DiaDiemDTO dd){
        for(DiaDiemDTO d:ds){
            if(d.getTenDiaDiem().equals(dd.getTenDiaDiem())){
                return true;
        }
            
    }
        return false;
    }
    
    public DiaDiemDTO timDiaDiem(String tendd){
        if(tendd.trim().isEmpty()){
            return null;
        }
        return dao.TimDiaDiem(tendd);
    }
    
    public boolean themDiaDiem(DiaDiemDTO dd){
        if(timDiaDiem(dd)){
            return false;
        }
        
        if(dao.TimDiaDiem(dd.getTenDiaDiem())!=null){
            return false;
        }
        
        ds.add(dd);
        dao.themDiaDiem(dd);
        return true;   
    }
    
    public boolean xoaDiaDiem(DiaDiemDTO dd){
        if(timDiaDiem(dd)!=true){
            return false;
        }
        if(dao.TimDiaDiem(dd.getTenDiaDiem())!=null){
            dao.xoaDiaDiem(dd);
        }
        ds.remove(dd);
        return true;
    }
    
    public boolean suaDiaDiem(DiaDiemDTO dd,String tendd){
        if(timDiaDiem(dd)!=true){
            return false;
        }
        if(dao.TimDiaDiem(dd.getTenDiaDiem())!=null){
            dao.suaDiaDiem(dd,tendd);
        }
        for(int i=0;i<ds.size();i++){
            if(ds.get(i).getTenDiaDiem().equals(dd.getTenDiaDiem())){
                ds.set(i, dd);
            }
        }
        return true;
    }
    
    public ArrayList<DiaDiemDTO> timdd(String tendd){
        tendd=tendd.trim();
        
        if(tendd.isEmpty()){
            return dao.getDs();  
        }
        return dao.timdd(tendd);
    }
    
    public ArrayList<DiaDiemDTO> getDstheongay(java.util.Date ngay){
        if(ngay==null){
            return null;
    }
        return dao.getDstheongay(ngay);
    }

    public ArrayList<DiaDiemDTO> getDsTheoDiachi(String DiaChi){
        if(DiaChi.isEmpty()){
            return null;
        }
        return dao.getDstheoDiaChi(DiaChi);
    }

    public ArrayList<DiaDiemDTO> getDsTheoQuocGia(String quocgia){
        if(quocgia.isEmpty()){
            return null;
        }
        return dao.getDstheoQuocGia(quocgia);
    }
}
