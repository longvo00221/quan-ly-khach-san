package domain.model;

import java.sql.Date;
import java.sql.Timestamp;

public abstract class Bill {
    private int hoaDonId;
    private int soPhong;
    private String tenKhachHang;
    private String soDienThoai;
    private Timestamp ngayNhanPhong;
    private Timestamp ngayTraPhong;
    private Boolean loaiHoaDon;
    private int thang;
    private double donGia;
    private int phongID;
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

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getPhongID() {
        return phongID;
    }

    public void setPhongID(int phongID) {
        this.phongID = phongID;
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
