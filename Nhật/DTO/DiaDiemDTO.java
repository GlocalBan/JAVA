/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.dto;

import java.time.LocalDate;

/**
 *
 * @author Nhat
 */
public class DiaDiemDTO {
    public String TenDiaDiem;
    public String DiaChi;
    public String QuocGia;

    public DiaDiemDTO(String TenDiaDiem, String DiaChi, String QuocGia) {
        this.TenDiaDiem = TenDiaDiem;
        this.DiaChi = DiaChi;
        this.QuocGia = QuocGia;
    }
    
    public DiaDiemDTO() {
        this.TenDiaDiem = TenDiaDiem;
        this.DiaChi = DiaChi;
        this.QuocGia = QuocGia;
    }
    
    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public String getdiachi() {
        return DiaChi;
    }

    public String getQuocGia() {
        return QuocGia;
    }

    public void setTenDiaDiem(String TenDiaDiem) {
        this.TenDiaDiem = TenDiaDiem;
    }

    public void setdiachi(String diachi) {
        this.DiaChi = diachi;
    }

    public void setQuocGia(String QuocGia) {
        this.QuocGia = QuocGia;
    }
    
    
}
