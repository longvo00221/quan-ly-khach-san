package presentation.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.BillService;
import domain.model.Bill;
import domain.model.DayBill;
import domain.model.HourBill;

public class AddCommand implements Command {
    private JTextField soPhongTextField;
    private JTextField tenKhachTextField;
    private JTextField soDienThoaiTextField;
    private JTextField thoiGianNhanPhongTextField;
    private JTextField thoiGianTraPhongTextField;
    private JComboBox loaihoadonComboBox;
    private JTextField donGiaTextField;
    private BillService billService;
    private Boolean isValidPhone;
    private Boolean isValidHour;

    public AddCommand() {
    }

    public AddCommand(JTextField soPhongTextField, JTextField tenKhachTextField, JTextField thoiGianNhanPhongTextField,
            JTextField thoiGianTraPhongTextField, JComboBox loaihoadonComboBox,
            JTextField donGiaTextField,
            JTextField soDienThoaiTextField, BillService billService) {

        this.soPhongTextField = soPhongTextField;
        this.tenKhachTextField = tenKhachTextField;
        this.thoiGianNhanPhongTextField = thoiGianNhanPhongTextField;
        this.thoiGianTraPhongTextField = thoiGianTraPhongTextField;
        this.loaihoadonComboBox = loaihoadonComboBox;
        this.donGiaTextField = donGiaTextField;
        this.soDienThoaiTextField = soDienThoaiTextField;
        this.billService = billService;
        this.isValidPhone = true;
        this.isValidHour = true;

    }

    public void addBillPrevious(Bill bill, BillService billService) {
        billService.addBill(bill);
    }

    public boolean isValidPhoneNumber() {
        return isValidPhone;
    }

    public boolean isValidHour() {
        return isValidHour;
    }

    @Override
    public void executed() {

        String regex = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})";

        int soPhong = Integer.parseInt(soPhongTextField.getText());
        int phongId = soPhong;
        String tenKhachHang = tenKhachTextField.getText();
        String thoiGianNhanPhong = thoiGianNhanPhongTextField.getText();
        String thoiGianTraPhong = thoiGianTraPhongTextField.getText();
        int selectedTypeBill = loaihoadonComboBox.getSelectedIndex();
        double donGia = Double.parseDouble(donGiaTextField.getText());
        String soDienThoai = soDienThoaiTextField.getText();

        if (!soDienThoai.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            isValidPhone = false;
            return;
        }

        boolean isNgay = false; // Giả sử ban đầu loaiHoaDon là false

        if (selectedTypeBill == 1) {
            isNgay = true;
        } else {
            isNgay = false;
        }

        System.out.println(selectedTypeBill);
        System.out.println(isNgay);

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

        if (isNgay) {
            DayBill bill = new DayBill();
            bill.setSoPhong(soPhong);
            bill.setTenKhachHang(tenKhachHang);
            bill.setNgayNhanPhong(sqlNgayNhanPhong);
            bill.setNgayTraPhong(sqlNgayTraPhong);
            bill.setLoaiHoaDon(isNgay);
            bill.setDonGia(donGia);
            bill.setPhongId(phongId);
            bill.setSoDienThoai(soDienThoai);

            billService.addBill(bill);

        } else {
            HourBill bill = new HourBill();
            bill.setSoPhong(soPhong);
            bill.setTenKhachHang(tenKhachHang);
            bill.setNgayNhanPhong(sqlNgayNhanPhong);
            bill.setNgayTraPhong(sqlNgayTraPhong);
            bill.setLoaiHoaDon(isNgay);
            bill.setDonGia(donGia);
            bill.setPhongId(phongId);
            bill.setSoDienThoai(soDienThoai);

            int checkHour = bill.calculateDuration();
            System.out.println(checkHour);
            if (checkHour > 30) {
                JOptionPane.showMessageDialog(null, "Thời gian vượt quá 30 giờ, vui lòng dùng hóa đơn ngày!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                isValidHour = false;
                return;
            }

            billService.addBill(bill);

        }

    }

}
