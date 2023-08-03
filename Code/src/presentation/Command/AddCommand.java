package presentation.Command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

import domain.BillService;
import domain.BillServiceImpl;
import domain.model.DayBill;
import domain.model.HourBill;

public class AddCommand implements Command {
    private JTextField soPhongTextField;
    private JTextField tenKhachTextField;
    private JTextField soDienThoaiTextField;
    private JTextField thoiGianNhanPhongTextField;
    private JTextField thoiGianTraPhongTextField;
    private JTextField loaiHoaDonTextField;
    private JTextField thangTextField;
    private JTextField donGiaTextField;
    private BillService billService;

    public AddCommand(JTextField soPhongTextField, JTextField tenKhachTextField, JTextField thoiGianNhanPhongTextField,
            JTextField thoiGianTraPhongTextField, JTextField loaiHoaDonTextField, JTextField thangTextField,
            JTextField donGiaTextField,
            JTextField soDienThoaiTextField, BillService billService) {

        this.soPhongTextField = soPhongTextField;
        this.tenKhachTextField = tenKhachTextField;
        this.thoiGianNhanPhongTextField = thoiGianNhanPhongTextField;
        this.thoiGianTraPhongTextField = thoiGianTraPhongTextField;
        this.loaiHoaDonTextField = loaiHoaDonTextField;
        this.thangTextField = thangTextField;
        this.donGiaTextField = donGiaTextField;
        this.soDienThoaiTextField = soDienThoaiTextField;
        this.billService = billService;
    }

    @Override
    public void executed() {
        int soPhong = Integer.parseInt(soPhongTextField.getText());
        int phongId = soPhong;
        String tenKhachHang = tenKhachTextField.getText();
        String thoiGianNhanPhong = thoiGianNhanPhongTextField.getText();
        String thoiGianTraPhong = thoiGianTraPhongTextField.getText();
        boolean loaiHoaDon = Boolean.parseBoolean(loaiHoaDonTextField.getText());
        int thang = Integer.parseInt(thangTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        String soDienThoai = soDienThoaiTextField.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date ngayNhanPhong = null;
        java.util.Date ngayTraPhong = null;

        try {
            ngayNhanPhong = sdf.parse(thoiGianNhanPhong);
            ngayTraPhong = sdf.parse(thoiGianTraPhong);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Timestamp sqlNgayNhanPhong = new java.sql.Timestamp(ngayNhanPhong.getTime());
        java.sql.Timestamp sqlNgayTraPhong = new java.sql.Timestamp(ngayTraPhong.getTime());

        if (loaiHoaDon) {
            DayBill bill = new DayBill();
            bill.setSoPhong(soPhong);
            bill.setTenKhachHang(tenKhachHang);
            bill.setNgayNhanPhong(sqlNgayNhanPhong);
            bill.setNgayTraPhong(sqlNgayTraPhong);
            bill.setLoaiHoaDon(loaiHoaDon);
            bill.setThang(thang);
            bill.setDonGia(donGia);
            bill.setPhongID(phongId);
            bill.setSoDienThoai(soDienThoai);

            billService.addBill(bill);

        } else {
            HourBill bill = new HourBill();
            bill.setSoPhong(soPhong);
            bill.setTenKhachHang(tenKhachHang);
            bill.setNgayNhanPhong(sqlNgayNhanPhong);
            bill.setNgayTraPhong(sqlNgayTraPhong);
            bill.setLoaiHoaDon(loaiHoaDon);
            bill.setThang(thang);
            bill.setDonGia(donGia);
            bill.setPhongID(phongId);
            bill.setSoDienThoai(soDienThoai);

            billService.addBill(bill);
        }

    }

}
