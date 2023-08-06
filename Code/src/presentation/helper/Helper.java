package presentation.helper;

import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

public class Helper {
    public Helper() {
    }

    public boolean checkDayBillHelper(Date ngayTraPhong, Date ngayNhanPhong) {
        long khoangThoiGian = ngayTraPhong.getTime() - ngayNhanPhong.getTime();
        System.out.println(khoangThoiGian);

        if (khoangThoiGian < (1 * 1000 * 60 * 60 * 24)) {
            JOptionPane.showMessageDialog(null, "Hóa đơn phải lớn hơn 1 ngày", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }

    }

    public boolean checkHourBillHelper(Timestamp ngayTraPhong, Timestamp ngayNhanPhong) {

        long gioNhanPhong = ngayNhanPhong.getTime();
        long gioTraPhong = ngayTraPhong.getTime();
        long result = gioTraPhong - gioNhanPhong;
        int hours = (int) result / (60 * 60 * 1000);
        int roundedHours = Math.round(hours);
        if (roundedHours > 0) {

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Hóa đơn phải lớn hơn 1 giờ", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}
