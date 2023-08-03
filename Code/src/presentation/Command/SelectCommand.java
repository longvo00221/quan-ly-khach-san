package presentation.Command;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SelectCommand implements Command {
    private JTable table;
    private JTextField sophongTextField;
    private JTextField sodienthoaiTextField;
    private JTextField tenkhachhangTextField;
    private JTextField thoigiannhanphongTextField;
    private JTextField thoigiantraphongTextField;
    private JTextField thangTextField;
    private JTextField dongiaTextField;
    private JComboBox loaihoadonComboBox;

    public SelectCommand(JTable table, JTextField sophongTextField, JTextField sodienthoaiTextField,
            JTextField tenkhachhangTextField, JTextField thoigiannhanphongTextField,
            JTextField thoigiantraphongTextField, JTextField thangTextField, JTextField dongiaTextField,
            JComboBox loaihoadonComboBox) {
        this.table = table;
        this.sophongTextField = sophongTextField;
        this.sodienthoaiTextField = sodienthoaiTextField;
        this.tenkhachhangTextField = tenkhachhangTextField;
        this.thoigiannhanphongTextField = thoigiannhanphongTextField;
        this.thoigiantraphongTextField = thoigiantraphongTextField;
        this.thangTextField = thangTextField;
        this.dongiaTextField = dongiaTextField;
        this.loaihoadonComboBox = loaihoadonComboBox;

    }

    @Override
    public void executed() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {

            String tenKhachHang = table.getValueAt(selectedRow, 3).toString();
            String soDienThoai = table.getValueAt(selectedRow, 4).toString();
            String soPhong = table.getValueAt(selectedRow, 5).toString();

            String thang = table.getValueAt(selectedRow, 6).toString();
            String thoiGianNhan = table.getValueAt(selectedRow, 7).toString();
            String thoiGianTra = table.getValueAt(selectedRow, 8).toString();
            String loaiHoaDon = table.getValueAt(selectedRow, 9).toString();
            String donGia = table.getValueAt(selectedRow, 10).toString();

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

            thangTextField.setText(thang);
            dongiaTextField.setText(donGia);

        }
    }

}
