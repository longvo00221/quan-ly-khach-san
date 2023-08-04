package presentation.Command;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import domain.BillCaretaker;
import domain.BillOriginator;
import domain.model.Bill;
import domain.model.DayBill;
import domain.model.HourBill;

public class SelectCommand implements Command {
    private JTable table;
    private JTextField sophongTextField;
    private JTextField sodienthoaiTextField;
    private JTextField tenkhachhangTextField;
    private JTextField thoigiannhanphongTextField;
    private JTextField thoigiantraphongTextField;
    private JTextField dongiaTextField;
    private JComboBox loaihoadonComboBox;
    private BillOriginator billOriginator;
    private BillCaretaker billCaretaker;
    private int hoaDonId;
    private Timestamp ngayNhanPhong;
    private Timestamp ngayTraPhong;

    public SelectCommand() {
    }

    public SelectCommand(JTable table, JTextField sophongTextField, JTextField sodienthoaiTextField,
            JTextField tenkhachhangTextField, JTextField thoigiannhanphongTextField,
            JTextField thoigiantraphongTextField, JTextField dongiaTextField,
            JComboBox loaihoadonComboBox, int hoaDonId, BillOriginator billOriginator, BillCaretaker billCaretaker) {
        this.table = table;
        this.sophongTextField = sophongTextField;
        this.sodienthoaiTextField = sodienthoaiTextField;
        this.tenkhachhangTextField = tenkhachhangTextField;
        this.thoigiannhanphongTextField = thoigiannhanphongTextField;
        this.thoigiantraphongTextField = thoigiantraphongTextField;
        this.dongiaTextField = dongiaTextField;
        this.loaihoadonComboBox = loaihoadonComboBox;
        this.hoaDonId = hoaDonId;

        this.billOriginator = billOriginator;
        this.billCaretaker = billCaretaker;

    }

    @Override
    public void executed() {

        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {

            String tenKhachHang = table.getValueAt(selectedRow, 3).toString();
            String soDienThoai = table.getValueAt(selectedRow, 4).toString();
            String soPhong = table.getValueAt(selectedRow, 5).toString();

            String thoiGianNhan = table.getValueAt(selectedRow, 6).toString();
            String thoiGianTra = table.getValueAt(selectedRow, 7).toString();
            String loaiHoaDon = table.getValueAt(selectedRow, 8).toString();
            String donGia = table.getValueAt(selectedRow, 9).toString();

            if (loaiHoaDon == "Giờ") {
                loaihoadonComboBox.setSelectedIndex(0);
            } else {
                loaihoadonComboBox.setSelectedIndex(1);
            }
            sophongTextField.setText(soPhong);
            sodienthoaiTextField.setText(soDienThoai);
            tenkhachhangTextField.setText(tenKhachHang);
            thoigiannhanphongTextField.setText(thoiGianNhan);
            thoigiantraphongTextField.setText(thoiGianTra);

            dongiaTextField.setText(donGia);

            String dinhDang = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(dinhDang);
            try {
                java.util.Date nhan = sdf.parse(thoiGianNhan);
                java.util.Date tra = sdf.parse(thoiGianNhan);
                ngayNhanPhong = new Timestamp(nhan.getTime());
                ngayTraPhong = new Timestamp(tra.getTime());
            } catch (ParseException e) {
                System.out.println("Không thể chuyển đổi chuỗi thành Timestamp.");
            }

            double giaTien = Double.parseDouble(donGia.replace(",", ""));

            if (loaiHoaDon == "Giờ") {

                Bill bill = new HourBill();
                bill.setHoaDonId(hoaDonId);
                bill.setSoPhong(Integer.parseInt(soPhong));
                bill.setTenKhachHang(tenKhachHang);
                bill.setSoDienThoai(soDienThoai);
                bill.setNgayNhanPhong(ngayNhanPhong);
                bill.setNgayTraPhong(ngayTraPhong);
                bill.setLoaiHoaDon(false);
                bill.setDonGia(giaTien);
                bill.setPhongId(Integer.parseInt(soPhong));

                billOriginator.setBill(bill);
                billCaretaker.pushMemento(billOriginator.saveStateMemento());

            } else {

                Bill bill = new DayBill();
                bill.setHoaDonId(hoaDonId);
                bill.setSoPhong(Integer.parseInt(soPhong));
                bill.setTenKhachHang(tenKhachHang);
                bill.setSoDienThoai(soDienThoai);
                bill.setNgayNhanPhong(ngayNhanPhong);
                bill.setNgayTraPhong(ngayTraPhong);
                bill.setLoaiHoaDon(true);
                bill.setDonGia(giaTien);
                bill.setPhongId(Integer.parseInt(soPhong));

                billOriginator.setBill(bill);
                billCaretaker.pushMemento(billOriginator.saveStateMemento());

            }

        }
    }

}
