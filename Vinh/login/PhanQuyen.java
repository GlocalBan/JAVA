/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import DTO.TaiKhoan;

/**
 *
 * @author Admin
 */
public class PhanQuyen {
    private static TaiKhoan currentTaiKhoan;

    private PhanQuyen() {
    }

    public static void dangNhap(TaiKhoan taiKhoan) {
        currentTaiKhoan = taiKhoan;
    }

    public static void dangXuat() {
        currentTaiKhoan = null;
    }

    public static TaiKhoan getCurrentTaiKhoan() {
        return currentTaiKhoan;
    }

    public static boolean laQuanLy() {
        if (currentTaiKhoan == null || currentTaiKhoan.getPosition() == null) {
            return false;
        }
        String chucVu = currentTaiKhoan.getPosition().trim().toLowerCase();
        return chucVu.equals("quản lí") || chucVu.equals("quan li") || chucVu.equals("quản lý") || chucVu.equals("quan ly");
    }
}
