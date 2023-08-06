package domain.model;

import java.sql.Timestamp;

public abstract class Bill {
    private int hoaDonId;
    private int soPhong;
    private String tenKhachHang;
    private String soDienThoai;
    private Timestamp ngayNhanPhong;
    private Timestamp ngayTraPhong;
    private Boolean loaiHoaDon;

    private double donGia;
    private int phongId;
    private String loaiPhong;

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Timestamp getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Timestamp ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Timestamp getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Timestamp ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public Boolean getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(Boolean loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getPhongId() {
        return phongId;
    }

    public void setPhongId(int phongID) {
        this.phongId = phongID;
    }

    public int getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(int hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public abstract String unitCost();

    public abstract int calculateDuration();

}
