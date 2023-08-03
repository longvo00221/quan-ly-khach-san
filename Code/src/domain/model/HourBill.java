package domain.model;

import java.sql.Timestamp;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class HourBill extends Bill {
    private int soGio;

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
        // TODO Auto-generated method stub
        return super.getSoDienThoai();
    }

    @Override
    public void setSoDienThoai(String soDienThoai) {
        // TODO Auto-generated method stub
        super.setSoDienThoai(soDienThoai);
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

    @Override
    public String unitCost() {
        double cost = 0;
        int totalHours = calculateDuration();

        if (totalHours <= 24) {
            cost = totalHours * getDonGia();
        } else if (totalHours > 24 && totalHours < 30 * 24) {
            cost = 24 * getDonGia();
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedCost = decimalFormat.format(cost);
        return formattedCost;
    }

}
