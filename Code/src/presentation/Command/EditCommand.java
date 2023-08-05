package presentation.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.BillCaretaker;
import domain.BillOriginator;
import domain.BillService;
import domain.model.Bill;
import domain.model.DayBill;
import domain.model.HourBill;
import presentation.helper.Helper;

public class EditCommand implements Command {
    private int hoaDonId;
    private int phongIdPrevious;
    private JTextField soPhongTextField;
    private JTextField tenKhachTextField;
    private JTextField soDienThoaiTextField;
    private JTextField thoiGianNhanPhongTextField;
    private JTextField thoiGianTraPhongTextField;
    private JComboBox loaihoadonComboBox;
    private BillOriginator billOriginator;
    private BillCaretaker billCaretaker;
    private JTextField donGiaTextField;
    private BillService billService;
    private boolean isValid;
    private Boolean isValidHour;
    private boolean isValidDay;
    private Helper helper;

    public EditCommand() {
    }

    public EditCommand(int hoaDonId, int phongIdPrevious, JTextField soPhongTextField, JTextField tenKhachTextField,
            JTextField thoiGianNhanPhongTextField,
            JTextField thoiGianTraPhongTextField, JComboBox loaihoadonComboBox,
            JTextField donGiaTextField,
            JTextField soDienThoaiTextField, BillService billService, BillOriginator billOriginator,
            BillCaretaker billCaretaker) {
        this.hoaDonId = hoaDonId;
        this.soPhongTextField = soPhongTextField;
        this.tenKhachTextField = tenKhachTextField;
        this.thoiGianNhanPhongTextField = thoiGianNhanPhongTextField;
        this.thoiGianTraPhongTextField = thoiGianTraPhongTextField;
        this.loaihoadonComboBox = loaihoadonComboBox;
        this.donGiaTextField = donGiaTextField;
        this.soDienThoaiTextField = soDienThoaiTextField;
        this.billService = billService;
        this.phongIdPrevious = phongIdPrevious;
        this.isValid = true;
        this.billOriginator = billOriginator;
        this.billCaretaker = billCaretaker;
        this.isValidHour = true;
        this.isValidDay = true;

        helper = new Helper();

    };

    public void setCurentBill(Bill bill, BillService billService) {
        billService.updateBill(bill);
    }

    @Override
    public void executed() {
        if (hoaDonId < 0) {
            return;
        }
        String regex = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})";

        int soPhong = Integer.parseInt(soPhongTextField.getText());
        int phongId = soPhong;
        String tenKhachHang = tenKhachTextField.getText();
        String thoiGianNhanPhong = thoiGianNhanPhongTextField.getText();
        String thoiGianTraPhong = thoiGianTraPhongTextField.getText();
        int selectedTypeBill = loaihoadonComboBox.getSelectedIndex();
        double donGia = Double.parseDouble(donGiaTextField.getText().replace(",", ""));
        String soDienThoai = soDienThoaiTextField.getText();

        if (!soDienThoai.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            isValid = false;
            return;
        }

        boolean isNgay = false;

        if (phongId != phongIdPrevious) {
            boolean trangThaiPhong = billService.getRoomStatus(phongId);
            if (trangThaiPhong) {
                JOptionPane.showMessageDialog(null, "Phòng đã được thuê!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                billService.updateRoomStatus(phongIdPrevious, false);
            }
        }

        if (selectedTypeBill == 1) {
            isNgay = true;
        } else {
            isNgay = false;
        }

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
            bill.setHoaDonId(hoaDonId);

            boolean isValidDayBill = helper.checkDayBillHelper(ngayTraPhong, ngayTraPhong);

            if (!isValidDayBill) {
                isValidDay = false;
                return;
            }

            billOriginator.setBill(bill);
            billCaretaker.pushRedoStack(billOriginator.saveStateMemento());
            billService.updateBill(bill);

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
            bill.setHoaDonId(hoaDonId);

            int checkHour = bill.calculateDuration();
            if (checkHour > 30) {
                JOptionPane.showMessageDialog(null, "Thời gian vượt quá 30 giờ, vui lòng dùng hóa đơn ngày!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                isValidHour = false;
                return;
            }

            boolean isValidDayBill = helper.checkHourBillHelper(sqlNgayTraPhong, sqlNgayNhanPhong);

            if (!isValidDayBill) {
                isValidHour = false;
                return;
            }

            billOriginator.setBill(bill);
            billCaretaker.pushRedoStack(billOriginator.saveStateMemento());
            billService.updateBill(bill);
        }

    }

    public boolean isValidPhoneNumber() {
        return isValid;
    }

    public boolean isValidHour() {
        return isValidHour;
    }

    public boolean isValidDay() {
        return isValidDay;
    }
}
