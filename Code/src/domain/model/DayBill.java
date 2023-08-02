package domain.model;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

public class DayBill extends Bill {
    private int soNgay;

    @Override
    public int getDonGia() {
        // TODO Auto-generated method stub
        return super.getDonGia();
    }

    @Override
    public int getHoaDonId() {
        // TODO Auto-generated method stub
        return super.getHoaDonId();
    }

    @Override
    public Boolean getLoaiHoaDon() {
        // TODO Auto-generated method stub
        return super.getLoaiHoaDon();
    }

    @Override
    public Date getNgayNhanPhong() {
        // TODO Auto-generated method stub
        return super.getNgayNhanPhong();
    }

    @Override
    public Date getNgayTraPhong() {
        // TODO Auto-generated method stub
        return super.getNgayTraPhong();
    }

    @Override
    public int getPhongID() {
        // TODO Auto-generated method stub
        return super.getPhongID();
    }

    @Override
    public int getSoPhong() {
        // TODO Auto-generated method stub
        return super.getSoPhong();
    }

    @Override
    public String getTenKhachHang() {
        // TODO Auto-generated method stub
        return super.getTenKhachHang();
    }

    @Override
    public int getThang() {
        // TODO Auto-generated method stub
        return super.getThang();
    }

    @Override
    public void setDonGia(int donGia) {
        // TODO Auto-generated method stub
        super.setDonGia(donGia);
    }

    @Override
    public void setHoaDonId(int hoaDonId) {
        // TODO Auto-generated method stub
        super.setHoaDonId(hoaDonId);
    }

    @Override
    public void setLoaiHoaDon(Boolean loaiHoaDon) {
        // TODO Auto-generated method stub
        super.setLoaiHoaDon(loaiHoaDon);
    }

    @Override
    public void setNgayNhanPhong(Date ngayNhanPhong) {
        // TODO Auto-generated method stub
        super.setNgayNhanPhong(ngayNhanPhong);
    }

    @Override
    public void setNgayTraPhong(Date ngayTraPhong) {
        // TODO Auto-generated method stub
        super.setNgayTraPhong(ngayTraPhong);
    }

    @Override
    public void setPhongID(int phongID) {
        // TODO Auto-generated method stub
        super.setPhongID(phongID);
    }

    @Override
    public void setSoPhong(int soPhong) {
        // TODO Auto-generated method stub
        super.setSoPhong(soPhong);
    }

    @Override
    public void setTenKhachHang(String tenKhachHang) {
        // TODO Auto-generated method stub
        super.setTenKhachHang(tenKhachHang);
    }

    @Override
    public void setThang(int thang) {
        // TODO Auto-generated method stub
        super.setThang(thang);
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    @Override
    public int calculateDuration() {
        long ngayNhanPhong = getNgayNhanPhong().getTime();
        long ngayTraPhong = getNgayTraPhong().getTime();
        long result = ngayTraPhong - ngayNhanPhong;
        int days = (int) result / (24 * 60 * 60 * 1000);

        int roundedDays = Math.round(days);
        return roundedDays;
    }

}
