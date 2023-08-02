package domain.model;

import java.sql.Date;

public class HourBill extends Bill {
    private int soGio;

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

    public int getSoGio() {
        return soGio;
    }

    public void setSoGio(int soGio) {
        this.soGio = soGio;
    }

    @Override
    public int calculateDuration() {
        long gioNhanPhong = getNgayNhanPhong().getTime();
        long gioTraPhong = getNgayTraPhong().getTime();
        long result = gioTraPhong - gioNhanPhong;
        int hours = (int) result / (60 * 60 * 1000);
        int roundedHours = Math.round(hours);

        return roundedHours;
    }

}
