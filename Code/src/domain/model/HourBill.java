package domain.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class HourBill extends Bill {
    private int soGio;

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
        double cost = 0;
        int totalHours = calculateDuration();

        if (totalHours <= 24) {
            cost = totalHours * getDonGia();
        } else if (totalHours > 24 && totalHours <= 30) {
            cost = 24 * getDonGia();
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
