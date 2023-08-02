import java.sql.Date;
import java.util.List;

import javax.swing.SwingUtilities;

import domain.model.Bill;
import domain.model.DayBill;
import pesistence.BillJDBCGateWay;
import presentation.HotelBillManageApp;

public class app {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(new Runnable() {
        // public void run() {
        // new HotelBillManageApp().setVisible(true);
        // }
        // });
        // Bill newBill = new Bill();
        // newBill.setSoPhong(2);
        // newBill.setTenKhachHang("Nguyen Van b");
        // newBill.setNgayNhanPhong(Date.valueOf("2023-08-01"));
        // newBill.setNgayTraPhong(Date.valueOf("2023-08-04"));
        // newBill.setLoaiHoaDon(true);
        // newBill.setThang(8);
        // newBill.setDonGia(1500000);
        // newBill.setPhongID(2);

        BillJDBCGateWay billGateWay = new BillJDBCGateWay();
        Bill bill = billGateWay.findBill(1);
        // List<Bill> allBills = billGateWay.getAllBill();

        // for (Bill bill : allBills) {
        System.out.println("HoaDonId: " + bill.getHoaDonId());
        System.out.println("SoPhong: " + bill.getSoPhong());

        System.out.println("TenKhachHang: " + bill.getTenKhachHang());
        System.out.println("NgayNhanPhong: " + bill.getNgayNhanPhong());
        System.out.println("NgayTraPhong: " + bill.getNgayTraPhong());
        System.out.println("LoaiHoaDon: " + bill.getLoaiHoaDon());
        System.out.println("Thang: " + bill.getThang());
        System.out.println("DonGia: " + bill.getDonGia());
        System.out.println("PhongId: " + bill.getPhongID());
        System.out.println("-------------------------");
        // }

    }
}
