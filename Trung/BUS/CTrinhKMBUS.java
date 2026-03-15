package BUS;

import DTO.CTrinhKM;
import DTO.KMHD;
import DTO.KMTour;
import DAO.DsCTrinhKM;
import DAO.DsKMHD;
import DAO.DsKMTour;

import java.util.*;


public class CTrinhKMBUS {
    public ArrayList<CTrinhKM> dsCTrinhKM;
    public static DsCTrinhKM dao;   
    public CTrinhKMBUS() {
        if (dsCTrinhKM == null) {
             dao = new DsCTrinhKM();
            //khoit tao dsCTrinhKM tu database
            dsCTrinhKM = dao.getDsCTrinhKM();
        }
    }

    public ArrayList<CTrinhKM> getDsCTrinhKM() {
        return dsCTrinhKM;
    }

    public void setDsCTrinhKM(ArrayList<CTrinhKM> dsCTrinhKM) {
        this.dsCTrinhKM = dsCTrinhKM;
    }

    public boolean timCTrinhKM(CTrinhKM ct) {
        for (CTrinhKM c : dsCTrinhKM) {
            if (c.getMaKM().equals(ct.getMaKM())) {
                return true;
            }
        }
        return false;
    }

    public boolean themCTrinhKM(CTrinhKM ct) {
        if (dao.timCTrinhKM(ct.getMaKM()) != null) return false;
        boolean result = false;
        if (ct instanceof KMTour) {
            DsKMTour daoTour = new DsKMTour();
            result = daoTour.themKMTour((KMTour) ct);
        } else if (ct instanceof KMHD) {
            DsKMHD daoKmhd = new DsKMHD();
            result = daoKmhd.themKMHD((KMHD) ct);  // PHẢI CÓ DÒNG NÀY
        } else {
            result = dao.themCTrinhKM(ct);
        }
        if (result) dsCTrinhKM.add(ct);
        return result;
    }

    public CTrinhKM getFullCTrinhKM(String maKM) {
        CTrinhKM basic = dao.timCTrinhKM(maKM);
        if (basic == null) return null;
        if (basic.getHinhThucKM()) {
            DsKMHD daoHD = new DsKMHD();
            return daoHD.timKMHD(maKM);   // Trả về đối tượng KMHD đầy đủ
        } else {
            DsKMTour daoTour = new DsKMTour();
            return daoTour.timKMTour(maKM);
        }
    }

    public boolean xoaCTrinhKM(String maKM) {
        CTrinhKM ct = timCTrinhKM(maKM);
        if (ct == null) return false;

        boolean result = false;
        if (ct instanceof KMTour) {
            DsKMTour daoTour = new DsKMTour();
            result = daoTour.xoaKMTour(maKM);
        } else if (ct instanceof KMHD) {
            DsKMHD daoKmhd = new DsKMHD();
            result = daoKmhd.xoaKMHD(maKM);
        } else {
            result = dao.xoaCTrinhKM(maKM);
        }

        if (result) {
            dsCTrinhKM.remove(ct);
        }
        return result;
    }

    public boolean suaCTrinhKM(CTrinhKM ct) {
        boolean result = false;
        if (ct instanceof KMTour) {
            DsKMTour daoTour = new DsKMTour();
            result = daoTour.suaKMTour((KMTour) ct);
        } else if (ct instanceof KMHD) {
            DsKMHD daoKmhd = new DsKMHD();
            result = daoKmhd.suaKMHD((KMHD) ct);
        } else {
            result = dao.suaCTrinhKM(ct);
        }

        if (result) {
            // Cập nhật trong danh sách nội bộ
            for (int i = 0; i < dsCTrinhKM.size(); i++) {
                if (dsCTrinhKM.get(i).getMaKM().equals(ct.getMaKM())) {
                    dsCTrinhKM.set(i, ct);
                    break;
                }
            }
        }
        return result;
    }

    public CTrinhKM timCTrinhKM(String maKM) {
        for (CTrinhKM ct : dsCTrinhKM) {
            if (ct.getMaKM().equals(maKM)) {
                return ct;
            }
        }
        return null; // Không tìm thấy
    }

    public ArrayList<CTrinhKM> searchCTrinhKM(String loai, String keyword) {
        ArrayList<CTrinhKM> result = new ArrayList<>();
        for (CTrinhKM ct : dsCTrinhKM) {
            switch (loai) {
                case "Tất cả":
                    if (ct.getMaKM().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ct);
                    }
                    if (ct.getTenKM().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ct);
                    }
                    break;
                case "KMHD":
                    if (ct.getHinhThucKM() && (ct.getMaKM().toLowerCase().contains(keyword.toLowerCase())||ct.getTenKM().toLowerCase().contains(keyword.toLowerCase()))) {
                        result.add(ct);
                    }
                    break;
                case "KMTour":
                    if (!ct.getHinhThucKM() && (ct.getMaKM().toLowerCase().contains(keyword.toLowerCase())||ct.getTenKM().toLowerCase().contains(keyword.toLowerCase()))) {
                        result.add(ct);
                    }
                    break;
            }
        }
        return result;
    }

    public void docDsCTrinhKM() {
        dsCTrinhKM = dao.getDsCTrinhKM();
    }

    public void ghiDsCTrinhKM() {
        // Ghi dsCTrinhKM vào database thông qua DAO
        for (CTrinhKM ct : dsCTrinhKM) {
            if (dao.timCTrinhKM(ct.getMaKM()) != null) {
                dao.suaCTrinhKM(ct); // Cập nhật nếu đã tồn tại
            } else {
                dao.themCTrinhKM(ct); // Thêm mới nếu chưa tồn tại
            }
        }
    }

    public CTrinhKM maptoCTrinhKM(String maKM) {
//        try {
            CTrinhKM ct = dao.timCTrinhKM(maKM);
            if (ct != null) {
                return ct;
            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

}
