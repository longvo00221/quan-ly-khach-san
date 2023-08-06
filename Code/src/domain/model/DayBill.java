package domain.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class DayBill extends Bill {
    private int soNgay;

    @Override
    public double getDonGia() {
        return super.getDonGia();
    }

    @Override
    public int getHoaDonId() {
        return super.getHoaDonId();
    }

    @Override
    public Boolean getLoaiHoaDon() {
        return super.getLoaiHoaDon();
    }

    @Override
    public Timestamp getNgayNhanPhong() {
        return super.getNgayNhanPhong();
    }

    @Override
    public Timestamp getNgayTraPhong() {
        return super.getNgayTraPhong();
    }

    @Override
    public int getPhongId() {
        return super.getPhongId();
    }

    @Override
    public int getSoPhong() {
        return super.getSoPhong();
    }

    @Override
    public String getTenKhachHang() {
        return super.getTenKhachHang();
    }

    @Override
    public void setDonGia(double donGia) {
        super.setDonGia(donGia);
    }

    @Override
    public void setHoaDonId(int hoaDonId) {
        super.setHoaDonId(hoaDonId);
    }

    @Override
    public void setLoaiHoaDon(Boolean loaiHoaDon) {
        super.setLoaiHoaDon(loaiHoaDon);
    }

    @Override
    public void setNgayNhanPhong(Timestamp ngayNhanPhong) {
        super.setNgayNhanPhong(ngayNhanPhong);
    }

    @Override
    public void setNgayTraPhong(Timestamp ngayTraPhong) {
        super.setNgayTraPhong(ngayTraPhong);
    }

    @Override
    public void setPhongId(int phongID) {
        super.setPhongId(phongID);
    }

    @Override
    public void setSoPhong(int soPhong) {
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
        return super.getLoaiPhong();
    }

    @Override
    public void setLoaiPhong(String loaiPhong) {
        super.setLoaiPhong(loaiPhong);
    }

}
