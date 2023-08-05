package domain.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;

public class DayBill extends Bill {
    private int soNgay;

    @Override
    public double getDonGia() {
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
    public Timestamp getNgayNhanPhong() {
        // TODO Auto-generated method stub
        return super.getNgayNhanPhong();
    }

    @Override
    public Timestamp getNgayTraPhong() {
        // TODO Auto-generated method stub
        return super.getNgayTraPhong();
    }

    @Override
    public int getPhongId() {
        // TODO Auto-generated method stub
        return super.getPhongId();
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
    public void setDonGia(double donGia) {
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
    public void setNgayNhanPhong(Timestamp ngayNhanPhong) {
        // TODO Auto-generated method stub
        super.setNgayNhanPhong(ngayNhanPhong);
    }

    @Override
    public void setNgayTraPhong(Timestamp ngayTraPhong) {
        // TODO Auto-generated method stub
        super.setNgayTraPhong(ngayTraPhong);
    }

    @Override
    public void setPhongId(int phongID) {
        // TODO Auto-generated method stub
        super.setPhongId(phongID);
    }

    @Override
    public void setSoPhong(int soPhong) {
        // TODO Auto-generated method stub
        super.setSoPhong(soPhong);
    }

    @Override
    public void setTenKhachHang(String tenKhachHang) {
        super.setTenKhachHang(tenKhachHang);
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

    @Override
    public String getSoDienThoai() {
        return super.getSoDienThoai();
    }

    @Override
    public void setSoDienThoai(String soDienThoai) {
        super.setSoDienThoai(soDienThoai);
    }

    @Override
    public String unitCost() {
        double cost = 0.0;
        if (getSoNgay() <= 7) {
            cost = getSoNgay() * getDonGia();
        } else {
            cost = 7 * getDonGia();
            double sale = 0.0;

            for (int i = 7; i < getSoNgay(); i++) {
                sale += getDonGia() * 0.8;
            }

            cost += sale;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedCost = decimalFormat.format(cost);

        return formattedCost;
    }

    @Override
    public String getLoaiPhong() {
        // TODO Auto-generated method stub
        return super.getLoaiPhong();
    }

    @Override
    public void setLoaiPhong(String loaiPhong) {
        // TODO Auto-generated method stub
        super.setLoaiPhong(loaiPhong);
    }

}
