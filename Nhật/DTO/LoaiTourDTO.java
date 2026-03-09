package DTO;

public class LoaiTourDTO {
    private String maLoaiTour;
    private String theLoai;

    public LoaiTourDTO(){
        this.maLoaiTour = "";
        this.theLoai = "";
    }

    public LoaiTourDTO(String maLoaiTour, String theLoai) {
        this.maLoaiTour = maLoaiTour;
        this.theLoai = theLoai;
    }

    public void setMaLoaiTour(String maLoaiTour) {
        this.maLoaiTour = maLoaiTour;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getMaLoaiTour() {
        return maLoaiTour;
    }

    public String getTheLoai() {
        return theLoai;
    }

    @Override
    public String toString(){
        return maLoaiTour + " - " + theLoai;
    }
}
