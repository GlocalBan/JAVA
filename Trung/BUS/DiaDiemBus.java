/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DsDiaDiem;
import DTO.DiaDiem;
import java.util.ArrayList;
/**
 *
 * @author Nhat
 */
public class DiaDiemBus {
    public static ArrayList<DiaDiem> ds;
    public static DsDiaDiem dao=new DsDiaDiem();
    
    public DiaDiemBus(){
        if(ds==null){
           ds=dao.getDs();
        }
    }
    
    public void DocDs(){
        dao.getDs();
    }
 
    public static ArrayList<DiaDiem> getDs(){
        return ds;
    }
    
    public boolean timDiaDiem(DiaDiem dd){
        for(DiaDiem d:ds){
            if(d.getTenDiaDiem().equals(dd.getTenDiaDiem())){
                return true;
        }
            
    }
        return false;
    }
    
    public DiaDiem timDiaDiem(String tendd){
        if(tendd==null || tendd.trim().isEmpty()){
            return null;
        }
        return dao.TimDiaDiem(tendd);
    }
    
    public boolean themDiaDiem(DiaDiem dd){
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
    
    public boolean xoaDiaDiem(DiaDiem dd){
        if(timDiaDiem(dd)!=true){
            return false;
        }
        if(dao.TimDiaDiem(dd.getTenDiaDiem())!=null){
            dao.xoaDiaDiem(dd);
        }
        ds.remove(dd);
        return true;
    }
    
    public boolean suaDiaDiem(DiaDiem dd){
        if(timDiaDiem(dd)!=true){
            return false;
        }
        if(dao.TimDiaDiem(dd.getTenDiaDiem())!=null){
            dao.suaDiaDiem(dd);
        }
        for(int i=0;i<ds.size();i++){
            if(ds.get(i).getTenDiaDiem().equals(dd.getTenDiaDiem())){
                ds.set(i, dd);
            }
        }
        return true;
    }
    
    public ArrayList timdd(String tendd){
        tendd=tendd.trim();
        
        if(tendd.isEmpty()){
            return dao.getDs();  
        }
        return dao.timdd(tendd);
    }
}
