package presentation.helper;

import java.util.Date;

import javax.swing.JOptionPane;

public class Helper {
    public Helper() {
    }

    public boolean checkTimeBillHelper(String type, Date ngayTraPhong, Date ngayNhanPhong) {
        long khoangThoiGian = ngayTraPhong.getTime() - ngayNhanPhong.getTime();
        System.out.println(khoangThoiGian);
        switch (type) {
            case "ngay":
                if (khoangThoiGian < (1 * 1000 * 60 * 60 * 24)) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn phải lớn hơn 1 ngày", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    return true;
                }

            case "gio":

                if (khoangThoiGian < (1 * 1000 * 60 * 60)) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn phải lớn hơn 1 giờ", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    return true;
                }

            default:
                break;
        }
        return false;
    }
}
